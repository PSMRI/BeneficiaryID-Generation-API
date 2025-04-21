/*
* AMRIT - Accessible Medical Records via Integrated Technology
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

import java.math.BigInteger;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="m_beneficiaryregidmapping")
public class BeneficiaryId {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private BigInteger benRegId; 
	private BigInteger beneficiaryId;
	private Boolean provisioned;
	private Integer provisionedById;
	private String provisionedBy;
	private Timestamp provisionedOn;
	private Boolean reserved;
	
	private Integer reservedForId;
	@Column(length=45)
	private String reservedForName;
	
	private Integer reservedForCountryId;
	@Column(length=45)
	private String reservedForCountryName;
	
	private Integer reservedForStateId;
	@Column(length=45)
	private String reservedForStateName;
	
	private Integer reservedForDistrictId;
	@Column(length=45)
	private String reservedForDistrictName;
	
	private Integer reservedForPSMapId;
	@Column(length=45)
	private String reservedForPSMapName;
	
	private Integer reservedById;
	@Column(length=45)
	private String reservedByName;
	
	private Timestamp reservedOn;
	private Timestamp reservedUntil;
	private String createdBy;
	private Timestamp createdDate;
	
	// 753812721192, 796702837334, b'0', b'0',,,,,,,,admin,0
}
