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
package com.iemr.common.bengen.utils.sessionobject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iemr.common.bengen.utils.config.ConfigProperties;
import com.iemr.common.bengen.utils.redis.RedisSessionException;
import com.iemr.common.bengen.utils.redis.RedisStorage;

@Component
public class SessionObject
{


	private RedisStorage objectStore;

	@Autowired
	public void setObjectStore(RedisStorage objectStore)
	{
		this.objectStore = objectStore;
	}

	public SessionObject()
	{
		extendExpirationTime = ConfigProperties.getExtendExpiryTime();
		sessionExpiryTime = ConfigProperties.getSessionExpiryTime();
	}

	private boolean extendExpirationTime;
											
	private int sessionExpiryTime;

	public String getSessionObject(String key) throws RedisSessionException
	{
		Boolean extendExpirationTime = ConfigProperties.getExtendExpiryTime();
		Integer sessionExpiryTime = ConfigProperties.getSessionExpiryTime();
		
		return objectStore.getObject(key, Boolean.valueOf(extendExpirationTime), sessionExpiryTime);
	}

	public String setSessionObject(String key, String value) throws RedisSessionException
	{
		Integer sessionExpiryTime = ConfigProperties.getSessionExpiryTime();
		return objectStore.setObject(key, value, sessionExpiryTime);
	}

	public String updateSessionObject(String key, String value) throws RedisSessionException
	{
		Boolean extendExpirationTime = ConfigProperties.getExtendExpiryTime();
		Integer sessionExpiryTime = ConfigProperties.getSessionExpiryTime();
		
		return objectStore.updateObject(key, value, Boolean.valueOf(extendExpirationTime), sessionExpiryTime);
	}

	public void deleteSessionObject(String key) throws RedisSessionException
	{
		System.out.println(objectStore.deleteObject(key));
	}

}
