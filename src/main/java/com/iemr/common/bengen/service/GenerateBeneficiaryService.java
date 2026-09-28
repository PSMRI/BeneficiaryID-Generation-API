/*
* AMRIT - Accessible Medical Records via Integrated Technologies
* Integrated EHR (Electronic Health Records) Solution
*
* Copyright (C) "Piramal Swasthya Management and Research Institute"
*
* This file is part of AMRIT.
*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program.  If not, see https://www.gnu.org/licenses/.
*/
package com.iemr.common.bengen.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.iemr.common.bengen.domain.M_BeneficiaryRegidMapping;
import com.iemr.common.bengen.repo.BeneficiaryIdRepo;
import com.iemr.common.bengen.utils.Generator;
import com.iemr.common.bengen.utils.config.ConfigProperties;

@Service
public class GenerateBeneficiaryService {
	private static final Logger logger = LoggerFactory.getLogger(GenerateBeneficiaryService.class);

	static final String POOL_INSERT_SQL = "INSERT IGNORE INTO `m_beneficiaryregidmapping` "
			+ "(`BeneficiaryID`,`Provisioned`,`Deleted`,`Reserved`,`CreatedDate`,`CreatedBy`) "
			+ "VALUES (?, b'0', b'0', b'0', ?, 'admin-batch')";

	static final String RESERVED_INSERT_SQL = "INSERT IGNORE INTO `m_beneficiaryregidmapping` "
			+ "(`BeneficiaryID`,`Provisioned`,`Deleted`,`Reserved`,`CreatedDate`,`CreatedBy`,`VanID`) "
			+ "VALUES (?, b'0', b'0', b'1', ?, 'admin-batch', ?)";

	static final int DEFAULT_BATCH_SIZE = 500;
	static final int DEFAULT_TOTAL_TO_GENERATE = 25000;
	static final int DEFAULT_LOWER_LIMIT = 10000;

	static final int MAX_SHORTFALL_PASSES = 5;

	static final int READ_BACK_CHUNK_SIZE = 1000;

	static final int READ_BACK_CLOCK_SLACK_SECONDS = 2;

	private final AtomicBoolean generationInProgress = new AtomicBoolean(false);

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	BeneficiaryIdRepo beneficiaryIdRepo;

	public int topUpPoolIfBelowLimit() {
		if (!generationInProgress.compareAndSet(false, true)) {
			logger.info("Beneficiary ID generation already running, skipping this check");
			return 0;
		}
		try {
			Long available = beneficiaryIdRepo.countBenID();
			long currentPool = available == null ? 0L : available;
			int lowerLimit = configValue("lower-limit-of-beneficiary", DEFAULT_LOWER_LIMIT);
			logger.info("Beneficiary ID pool check: available={}, lowerLimit={}", currentPool, lowerLimit);

			if (currentPool >= lowerLimit) {
				return 0;
			}
			int configured = configValue("no-of-benID-to-be-generate", DEFAULT_TOTAL_TO_GENERATE);
			long deficit = lowerLimit - currentPool;
			int toGenerate = (int) Math.max(configured, deficit);
			logger.warn("Beneficiary ID pool {} is below lower limit {} - generating {}", currentPool, lowerLimit,
					toGenerate);
			return generateBeneficiaryIDs(toGenerate);
		} finally {
			generationInProgress.set(false);
		}
	}

	public int generateBeneficiaryIDs() {
		return generateBeneficiaryIDs(configValue("no-of-benID-to-be-generate", DEFAULT_TOTAL_TO_GENERATE));
	}

	public int generateBeneficiaryIDs(int total) {
		long start = System.currentTimeMillis();
		logger.info("Beneficiary ID generation start: total={}", total);

		int inserted = insertInBatches(POOL_INSERT_SQL, total, null);

		logger.info("Beneficiary ID generation finished: requested={}, inserted={}, time={} ms", total, inserted,
				System.currentTimeMillis() - start);
		if (inserted < total) {
			logger.warn("Generated {} of {} requested beneficiary IDs", inserted, total);
		}
		return inserted;
	}

	int insertInBatches(String sql, int total, Integer vanID) {
		return insertInBatches(sql, total, vanID, null);
	}

	int insertInBatches(String sql, int total, Integer vanID, List<BigInteger> generatedIds) {
		if (total <= 0) {
			logger.warn("Nothing to generate, requested count was {}", total);
			return 0;
		}
		int batchSize = configValue("benID-batch-size", DEFAULT_BATCH_SIZE);
		int totalBatches = (total + batchSize - 1) / batchSize;
		Generator generator = new Generator();
		int inserted = 0;
		int batchNo = 0;

		for (int pass = 0; pass < MAX_SHORTFALL_PASSES && inserted < total; pass++) {
			if (pass > 0) {
				logger.warn("Duplicate IDs skipped, retry pass {} for remaining {} rows", pass, total - inserted);
			}
			int remaining = total - inserted;
			while (remaining > 0) {
				int size = Math.min(batchSize, remaining);
				int rows = insertOneBatch(sql, size, generator, vanID, generatedIds);
				inserted += rows;
				remaining -= size;
				logger.info("Batch {}/{}: generated {}, inserted {} (total {}/{})", ++batchNo, totalBatches, size,
						rows, inserted, total);
			}
		}
		return inserted;
	}

	private int insertOneBatch(String sql, int size, Generator generator, Integer vanID,
			List<BigInteger> generatedIds) {
		final Timestamp createdDate = Timestamp.from(Instant.now());
		final List<BigInteger> ids = new ArrayList<>(size);
		for (int i = 0; i < size; i++) {
			ids.add(generator.generateBeneficiaryId());
		}

		int[] updateCounts = jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setBigDecimal(1, new BigDecimal(ids.get(i)));
				ps.setTimestamp(2, createdDate);
				if (vanID != null) {
					ps.setInt(3, vanID);
				}
			}

			@Override
			public int getBatchSize() {
				return size;
			}
		});

		if (generatedIds != null) {
			generatedIds.addAll(ids);
		}
		return countInserted(updateCounts);
	}

	static int countInserted(int[] updateCounts) {
		int rows = 0;
		for (int count : updateCounts) {
			if (count == Statement.SUCCESS_NO_INFO || count > 0) {
				rows++;
			}
		}
		return rows;
	}

	private static int configValue(String key, int fallback) {
		Integer configured = ConfigProperties.getInteger(key);
		if (configured == null || configured <= 0) {
			logger.warn("Property {} missing or not positive, falling back to {}", key, fallback);
			return fallback;
		}
		return configured;
	}

	public List<M_BeneficiaryRegidMapping> getBeneficiaryIDs(Long num, Integer vanID) {
		logger.info("getBeneficiaryIDs start: num={}, vanID={}", num, vanID);
		long start = System.currentTimeMillis();

		int requested = num == null ? 0 : num.intValue();
		Timestamp createdFrom = Timestamp
				.from(Instant.now().minusSeconds(READ_BACK_CLOCK_SLACK_SECONDS));

		List<BigInteger> generatedIds = new ArrayList<>(Math.max(requested, 0));
		int inserted = insertInBatches(RESERVED_INSERT_SQL, requested, vanID, generatedIds);
		if (inserted < requested) {
			logger.warn("Reserved only {} of {} requested IDs for vanID {}", inserted, requested, vanID);
		}

		List<M_BeneficiaryRegidMapping> list = readBackOwnRows(vanID, createdFrom, generatedIds);
		if (list.size() < inserted) {
			logger.warn("Read back {} of {} reserved IDs for vanID {}", list.size(), inserted, vanID);
		}

		logger.info("getBeneficiaryIDs finish. time = {} ms.", System.currentTimeMillis() - start);
		return list;
	}

	private List<M_BeneficiaryRegidMapping> readBackOwnRows(Integer vanID, Timestamp createdFrom,
			List<BigInteger> generatedIds) {
		List<M_BeneficiaryRegidMapping> list = new ArrayList<>(generatedIds.size());
		for (int from = 0; from < generatedIds.size(); from += READ_BACK_CHUNK_SIZE) {
			int to = Math.min(from + READ_BACK_CHUNK_SIZE, generatedIds.size());
			List<Long> chunk = new ArrayList<>(to - from);
			for (BigInteger id : generatedIds.subList(from, to)) {
				chunk.add(id.longValueExact());
			}
			mapRows(beneficiaryIdRepo.getBenIDGeneratedForRequest(vanID, createdFrom, chunk), list);
		}
		return list;
	}

	private static void mapRows(List<Object[]> rows, List<M_BeneficiaryRegidMapping> target) {
		if (rows == null) {
			return;
		}
		for (Object[] objects : rows) {
			if (objects != null && objects.length > 0) {
				target.add(new M_BeneficiaryRegidMapping(((Number) objects[0]).longValue(),
						((Number) objects[1]).longValue(), (Timestamp) objects[2], "admin-batch"));
			}
		}
	}
}
