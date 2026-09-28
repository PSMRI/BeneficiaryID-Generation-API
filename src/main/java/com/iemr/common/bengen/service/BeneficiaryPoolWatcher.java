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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
@ConditionalOnProperty(name = "bengen.pool-watcher-enabled", havingValue = "true", matchIfMissing = true)
public class BeneficiaryPoolWatcher {
	private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	GenerateBeneficiaryService generateBeneficiaryService;

	@Scheduled(initialDelayString = "${bengen.pool-watcher-initial-delay-ms:60000}",
			fixedDelayString = "${bengen.pool-watcher-interval-ms:300000}")
	public void watchPool() {
		try {
			int inserted = generateBeneficiaryService.topUpPoolIfBelowLimit();
			if (inserted > 0) {
				logger.info("Pool watcher topped up {} beneficiary IDs", inserted);
			}
		} catch (Exception e) {
			logger.error("Pool watcher top-up failed", e);
		}
	}
}
