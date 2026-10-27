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
package com.iemr.common.bengen;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
@DisplayName("BeneficiaryGenApplication Test Suite")
class BeneficiaryGenApplicationTest {

    @Mock
    private RedisConnectionFactory connectionFactory;

    private BeneficiaryGenApplication application;

    @BeforeEach
    @DisplayName("Create the application configuration before each test")
    void setUp() {
        application = new BeneficiaryGenApplication();
    }

    @Test
    @DisplayName("restTemplate should supply a RestTemplate bean for outbound calls")
    void restTemplate_shouldSupplyRestTemplateBean() {
        RestTemplate restTemplate = application.restTemplate();

        assertNotNull(restTemplate);
    }

    @Test
    @DisplayName("redisTemplate should bind the supplied connection factory")
    void redisTemplate_shouldBindSuppliedConnectionFactory() {
        RedisTemplate<String, Object> template = application.redisTemplate(connectionFactory);

        assertNotNull(template);
        assertSame(connectionFactory, template.getConnectionFactory());
    }

    @Test
    @DisplayName("redisTemplate should serialise keys as plain strings and values as User JSON")
    void redisTemplate_shouldSerialiseKeysAsStringsAndValuesAsJson() {
        RedisTemplate<String, Object> template = application.redisTemplate(connectionFactory);

        assertTrue(template.getKeySerializer() instanceof StringRedisSerializer);
        assertTrue(template.getValueSerializer() instanceof Jackson2JsonRedisSerializer);
    }

    @Test
    @DisplayName("configure should register the application class as the WAR deployment source")
    void configure_shouldRegisterApplicationClassAsSource() {
        SpringApplicationBuilder builder = new SpringApplicationBuilder();

        SpringApplicationBuilder configured = application.configure(builder);

        assertSame(builder, configured, "configure should keep building on the supplied builder");
        // SpringApplicationBuilder stages sources until build(), so read them back directly.
        Set<Class<?>> sources = (Set<Class<?>>) ReflectionTestUtils.getField(configured, "sources");
        assertTrue(sources.contains(BeneficiaryGenApplication.class));
    }
}
