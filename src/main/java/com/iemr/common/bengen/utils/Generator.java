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
package com.iemr.common.bengen.utils;

import java.math.BigInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* 
*
* @author Sunil.K.Sundaram
*/
public class Generator {
	
	private static final Logger log = LoggerFactory.getLogger(Generator.class);

	public BigInteger generateBeneficiaryId(){
		BigInteger bid1 = generateFirst();
		BigInteger bid2 = generateNumN(10);
		if (log.isDebugEnabled()){
			log.debug("bid1: "+bid1+" length: "+getDigitCount(bid1));
			log.debug("bid2: "+bid2+" length: "+getDigitCount(bid2));
		}
		
		BigInteger bid = bid1.add(bid2).multiply(new BigInteger("10"));
		String chsum = Verhoeff.generateVerhoeff(bid.toString());
		if (log.isDebugEnabled()){
			log.debug("bid:  "+bid+" length: "+getDigitCount(bid)+" chsum: " + chsum);
		}
		
		bid = bid.add(new BigInteger(chsum));
		if (log.isDebugEnabled()){
			log.debug("BENEFICIARY ID: " + bid /*+ ": Length: " + getDigitCount(bid)*/);
		}
		return bid;
	}
	
	public BigInteger generateFirst(){
		int one = getRandomNumRadRange(2, 9);
		
		BigInteger bn = new BigInteger(""+one).multiply(new BigInteger("10").pow(10));
		return bn;
	}

	protected BigInteger generateNumN(int n){
		int myArr1[] = new int[n];
		int myArr2[] = new int[n];

		for(int i=0;i<=n-1;i++){
			myArr1[i] = getRandomNum();
		}

		int count = n-1;
		for(int i=0; i <= myArr1.length-1 ; i++){
			int num;
			if(count == 0){
				num = getRandomNum();
			} else {
				num = getRandomNumRad(count);
			}
			
			int tmp = num;
			myArr2[count] = myArr1[i];
			myArr1[i] = tmp;
			count--;
		}

		StringBuilder str = new StringBuilder();
		for(int i=0;i<myArr2.length;i++){
			str.append(myArr2[i]);
		}

		BigInteger bn = new BigInteger(str.toString());

		return bn;
	}

	public int getDigitCount(BigInteger number) {
		double factor = Math.log(2) / Math.log(10);
		final int digitCount = (int) (factor * number.bitLength() + 1);
		if (BigInteger.TEN.pow(digitCount - 1).compareTo(number) > 0) {
			return digitCount - 1;
		}
		return digitCount;
	}
	
	public int getRandomNum(){
		int num = (int) (Math.random() * 100 % 10);
		if (log.isDebugEnabled()){
			log.debug("Rand generated: " + num);
		}
		return num;
	}

	public int getRandomNumRad(int rad){
		int num = getRandomNum();
		num = num % rad;
		if (log.isDebugEnabled()){
			log.debug("Rand generated ("+ rad + "): " + num);
		}
		return num;
	}

	public int getRandomNumRadRange(int rad1, int rad2){
		int num = getRandomNum();
		if(num >= rad1 && num <= rad2){
			return num;
		} else {
			num = getRandomNumRadRange(rad1, rad2);
		}

		if (log.isDebugEnabled()){
			log.debug("Rand range generated: " + num);
		}
		return num;
	}

	public void displayArrays(int[] myArr, int[] myArr2){
		StringBuilder str = new StringBuilder();
		for(int i=0;i<myArr.length;i++){
			str.append(myArr[i]);
		}
		if (log.isDebugEnabled()){
			log.debug("myarr : " + str);
		}

		str = new StringBuilder();
		for(int i=0;i<myArr2.length;i++){
			str.append(myArr2[i]);
		}
		if (log.isDebugEnabled()){
			log.debug("myarr2: " + str);
		}
	}
}
