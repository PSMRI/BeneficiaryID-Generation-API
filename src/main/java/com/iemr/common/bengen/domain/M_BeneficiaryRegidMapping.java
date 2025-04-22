/*
* AMRIT � Accessible Medical Records via Integrated Technology
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
package com.iemr.common.bengen.domain;

import java.sql.Timestamp;

import com.google.gson.GsonBuilder;
import com.google.gson.LongSerializationPolicy;
import com.google.gson.annotations.Expose;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

@Data
@Entity
@Table(name="m_beneficiaryregidmapping")
public class M_BeneficiaryRegidMapping {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Expose
	private Long benRegId;
	
	@Expose
	private Long beneficiaryId;
	
	private Boolean provisioned;
	
	private Boolean reserved;
	
	private Integer vanID;
	
	@Expose
	private Timestamp createdDate;
	
	@Expose
	private String createdBy;
	
	@Transient
	private Long benIDRequired;
	
	@Override
	public String toString() {
		return new GsonBuilder()
				.excludeFieldsWithoutExposeAnnotation()
				.setLongSerializationPolicy(LongSerializationPolicy.STRING)
				.serializeNulls()
				.setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create().toJson(this);
	}

	public M_BeneficiaryRegidMapping(Long benRegId, Long beneficiaryId, Timestamp createdDate, String createdBy) {
		this.benRegId=benRegId;
		this.beneficiaryId=beneficiaryId;
		this.createdDate=createdDate;
		this.createdBy=createdBy;
	}
}
