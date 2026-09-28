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
package com.iemr.common.bengen.config.quartz;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iemr.common.bengen.service.GenerateBeneficiaryService;

@Service
@DisallowConcurrentExecution
public class ScheduleJobServiceForBenGen implements Job {
	private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	GenerateBeneficiaryService generateBeneficiaryService;

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		logger.info("Started scheduled beneficiary ID top-up");
		try {
			int inserted = generateBeneficiaryService.topUpPoolIfBelowLimit();
			logger.info("Completed scheduled beneficiary ID top-up, inserted {} IDs", inserted);
		} catch (Exception e) {
			logger.error("Scheduled beneficiary ID top-up failed", e);
			throw new JobExecutionException(e, false);
		}
	}
}
