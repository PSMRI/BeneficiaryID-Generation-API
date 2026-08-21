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
package com.iemr.common.bengen.service.health;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.RejectedExecutionException;

import javax.sql.DataSource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayName("HealthService Test Suite")
class HealthServiceTest {

    private static final int THRESHOLD = 5000;

    @Mock
    private DataSource dataSource;

    @Mock
    private RedisTemplate<String, Object> redisTemplate;

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    private HealthService healthService;

    @BeforeEach
    @DisplayName("Set up the service with mocked infrastructure before each test")
    void setUp() {
        healthService = new HealthService(dataSource, redisTemplate, THRESHOLD);
    }

    /**
     * Wires the JDBC mock chain so every query the service issues succeeds.
     * getLong(1) backs the beneficiary pool COUNT, getInt(1) backs the advanced
     * lock-wait and slow-query diagnostics.
     */
    private void stubJdbc(long availableIds, int diagnosticCount) throws SQLException {
        lenient().when(dataSource.getConnection()).thenReturn(connection);
        lenient().when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        lenient().doNothing().when(preparedStatement).setQueryTimeout(anyInt());
        lenient().when(preparedStatement.executeQuery()).thenReturn(resultSet);
        lenient().when(resultSet.next()).thenReturn(true);
        lenient().when(resultSet.getLong(1)).thenReturn(availableIds);
        lenient().when(resultSet.getInt(1)).thenReturn(diagnosticCount);
    }

    private void stubRedisPong(String pong) {
        lenient().when(redisTemplate.execute(any(RedisCallback.class))).thenReturn(pong);
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> component(Map<String, Object> response, String key) {
        return (Map<String, Object>) response.get(key);
    }

    @Nested
    @DisplayName("Overall health aggregation")
    class OverallStatusTests {

        @Test
        @DisplayName("checkHealth should report UP when MySQL, Redis and the ID pool are all healthy")
        void checkHealth_shouldReportUpWhenAllComponentsHealthy() throws SQLException {
            stubJdbc(THRESHOLD * 2L, 0);
            stubRedisPong("PONG");

            Map<String, Object> response = healthService.checkHealth();

            assertEquals("UP", response.get("status"));
            assertNotNull(response.get("checkedAt"));
            assertEquals("UP", component(response, "mysql").get("status"));
            assertEquals("OK", component(response, "mysql").get("severity"));
            assertEquals("UP", component(response, "redis").get("status"));
            assertEquals("OK", component(response, "redis").get("severity"));
            assertEquals("UP", component(response, "beneficiaryIdPool").get("status"));
        }

        @Test
        @DisplayName("checkHealth should expose only status and severity for MySQL and Redis")
        void checkHealth_shouldExposeOnlyStatusAndSeverityForMysqlAndRedis() throws SQLException {
            stubJdbc(THRESHOLD * 2L, 0);
            stubRedisPong("PONG");

            Map<String, Object> response = healthService.checkHealth();

            assertEquals(2, component(response, "mysql").size());
            assertEquals(2, component(response, "redis").size());
            assertFalse(component(response, "mysql").containsKey("responseTimeMs"));
            assertFalse(component(response, "redis").containsKey("responseTimeMs"));
        }

        @Test
        @DisplayName("checkHealth should report DOWN when MySQL cannot be reached")
        void checkHealth_shouldReportDownWhenMysqlUnreachable() throws SQLException {
            lenient().when(dataSource.getConnection()).thenThrow(new SQLException("connection refused"));
            stubRedisPong("PONG");

            Map<String, Object> response = healthService.checkHealth();

            assertEquals("DOWN", response.get("status"));
            assertEquals("DOWN", component(response, "mysql").get("status"));
            assertEquals("CRITICAL", component(response, "mysql").get("severity"));
        }

        @Test
        @DisplayName("checkHealth should report DEGRADED when MySQL reports lock waits and slow queries")
        void checkHealth_shouldReportDegradedWhenAdvancedChecksFlagIssues() throws SQLException {
            stubJdbc(THRESHOLD * 2L, 25);
            stubRedisPong("PONG");

            Map<String, Object> response = healthService.checkHealth();

            assertEquals("DEGRADED", response.get("status"));
            assertEquals("DEGRADED", component(response, "mysql").get("status"));
            assertEquals("WARNING", component(response, "mysql").get("severity"));
        }
    }

    @Nested
    @DisplayName("Redis health check")
    class RedisHealthTests {

        @Test
        @DisplayName("checkRedisHealth should report DOWN when PING does not answer PONG")
        void checkHealth_shouldReportDownWhenRedisPingFails() throws SQLException {
            stubJdbc(THRESHOLD * 2L, 0);
            stubRedisPong("nope");

            Map<String, Object> response = healthService.checkHealth();

            assertEquals("DOWN", response.get("status"));
            assertEquals("DOWN", component(response, "redis").get("status"));
            assertEquals("CRITICAL", component(response, "redis").get("severity"));
        }

        @Test
        @DisplayName("checkRedisHealth should report DOWN when the Redis call throws")
        void checkHealth_shouldReportDownWhenRedisThrows() throws SQLException {
            stubJdbc(THRESHOLD * 2L, 0);
            lenient().when(redisTemplate.execute(any(RedisCallback.class)))
                    .thenThrow(new IllegalStateException("redis unavailable"));

            Map<String, Object> response = healthService.checkHealth();

            assertEquals("DOWN", response.get("status"));
            assertEquals("DOWN", component(response, "redis").get("status"));
        }

        @Test
        @DisplayName("checkRedisHealth should report UP and skip the check when Redis is not configured")
        void checkHealth_shouldSkipRedisWhenNotConfigured() throws SQLException {
            stubJdbc(THRESHOLD * 2L, 0);
            HealthService serviceWithoutRedis = new HealthService(dataSource, null, THRESHOLD);

            Map<String, Object> response = serviceWithoutRedis.checkHealth();

            assertEquals("UP", response.get("status"));
            assertEquals("UP", component(response, "redis").get("status"));
            verify(redisTemplate, never()).execute(any(RedisCallback.class));
            serviceWithoutRedis.shutdown();
        }
    }

    @Nested
    @DisplayName("Beneficiary ID pool check")
    class BeneficiaryPoolTests {

        @Test
        @DisplayName("checkBeneficiaryIdPool should report the available count and threshold when healthy")
        void checkHealth_shouldReportPoolCountAndThreshold() throws SQLException {
            stubJdbc(12345L, 0);
            stubRedisPong("PONG");

            Map<String, Object> pool = component(healthService.checkHealth(), "beneficiaryIdPool");

            assertEquals("UP", pool.get("status"));
            assertEquals("OK", pool.get("severity"));
            assertEquals(12345L, pool.get("availableIds"));
            assertEquals(THRESHOLD, pool.get("threshold"));
        }

        @Test
        @DisplayName("checkBeneficiaryIdPool should flag DEGRADED with a warning when the pool is below threshold")
        void checkHealth_shouldFlagDegradedWhenPoolBelowThreshold() throws SQLException {
            stubJdbc(10L, 0);
            stubRedisPong("PONG");

            Map<String, Object> response = healthService.checkHealth();
            Map<String, Object> pool = component(response, "beneficiaryIdPool");

            assertEquals("DEGRADED", response.get("status"));
            assertEquals("DEGRADED", pool.get("status"));
            assertEquals("WARNING", pool.get("severity"));
            assertEquals(10L, pool.get("availableIds"));
            assertEquals("Available beneficiary ID pool is below the configured threshold",
                    pool.get("message"));
        }

        @Test
        @DisplayName("checkBeneficiaryIdPool should stay DEGRADED rather than DOWN when the count query yields no row")
        void checkHealth_shouldStayDegradedWhenCountQueryReturnsNoRow() throws SQLException {
            lenient().when(dataSource.getConnection()).thenReturn(connection);
            lenient().when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
            lenient().when(preparedStatement.executeQuery()).thenReturn(resultSet);
            lenient().when(resultSet.next()).thenReturn(false);
            stubRedisPong("PONG");

            Map<String, Object> response = healthService.checkHealth();
            Map<String, Object> pool = component(response, "beneficiaryIdPool");

            assertEquals("DEGRADED", pool.get("status"));
            assertEquals("WARNING", pool.get("severity"));
            assertEquals("Beneficiary ID pool count could not be determined", pool.get("error"));
        }

        @Test
        @DisplayName("countAvailableBeneficiaryIds should be throttled so repeated polls reuse the cached count")
        void checkHealth_shouldThrottleRepeatedPoolCounts() throws SQLException {
            stubJdbc(THRESHOLD * 2L, 0);
            stubRedisPong("PONG");

            healthService.checkHealth();
            healthService.checkHealth();

            verify(connection, times(1)).prepareStatement(
                    "SELECT COUNT(*) FROM m_beneficiaryregidmapping WHERE Provisioned = 0 AND Reserved = 0");
        }
    }

    @Nested
    @DisplayName("Lifecycle")
    class ShutdownTests {

        @Test
        @DisplayName("shutdown should stop the executor so no further checks are accepted")
        void shutdown_shouldStopTheExecutor() throws SQLException {
            stubJdbc(THRESHOLD * 2L, 0);
            stubRedisPong("PONG");
            healthService.checkHealth();

            healthService.shutdown();

            assertThrows(RejectedExecutionException.class, () -> healthService.checkHealth());
        }

        @Test
        @DisplayName("shutdown should be safe to call twice")
        void shutdown_shouldBeIdempotent() {
            healthService.shutdown();

            assertDoesNotThrow(() -> healthService.shutdown());
        }
    }
}
