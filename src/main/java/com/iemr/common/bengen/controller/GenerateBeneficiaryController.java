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
package com.iemr.common.bengen.controller;

import java.lang.reflect.Type;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.reflect.TypeToken;
import com.iemr.common.bengen.domain.M_BeneficiaryRegidMapping;
import com.iemr.common.bengen.service.GenerateBeneficiaryService;
import com.iemr.common.bengen.utils.OutputResponse;
import com.iemr.common.bengen.utils.mapper.InputMapper;
import com.iemr.common.bengen.utils.mapper.OutputMapper;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;

@RequestMapping("/generateBeneficiaryController")
@RestController
public class GenerateBeneficiaryController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	
	@Autowired
	GenerateBeneficiaryService generateBeneficiaryService;
	
	@Operation(summary = "Generate beneficiary Ids")
	@CrossOrigin()
	@PostMapping(value = "/generateBeneficiaryIDs", headers = "Authorization", produces = { "application/json" })
	public String getBeneficiaryIDs(@Param("{\"benIDRequired\":\"Integer\",\"vanID\":\"Integer\"}") @RequestBody String request, HttpServletRequest httpRequest)
	{
		
			M_BeneficiaryRegidMapping benMapping= InputMapper.gson().fromJson(request, M_BeneficiaryRegidMapping.class);
			
			List<M_BeneficiaryRegidMapping> list = generateBeneficiaryService.getBeneficiaryIDs(benMapping.getBenIDRequired(), benMapping.getVanID());
			
			String response = getSuccessResponseString(list, 200, "success", "generateBeneficiaryIDs");
			
			logger.info("generateBeneficiaryIDs response "+response.toString());
		/**
		 * sending the response...
		 */
		return response;
	}
	
	private String getSuccessResponseString(List<M_BeneficiaryRegidMapping> list, Integer statusCode, String statusMsg,
			String methodName)
	{
		Type typeOfSrc = new TypeToken<List<M_BeneficiaryRegidMapping>>()
			{
				private static final long serialVersionUID = 1L;
			}.getType();
		String data = OutputMapper.getInstance().gson().toJson(list, typeOfSrc);
		logger.info("data: " + data);
		logger.info("data.toStr: " + data.toString());
		OutputResponse response = new OutputResponse.Builder().setDataJsonType("JsonObject.class")
				.setStatusCode(statusCode).setStatusMessage(statusMsg)
				.setDataObjectType(this.getClass().getSimpleName()).setMethodName(methodName).setData(data).build();
		return response.toString();
	}
}
