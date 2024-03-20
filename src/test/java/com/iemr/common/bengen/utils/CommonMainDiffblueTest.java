package com.iemr.common.bengen.utils;

import org.junit.jupiter.api.Test;

class CommonMainDiffblueTest {
    /**
     * Method under test: {@link CommonMain#configProperties()}
     */
    @Test
    void testConfigProperties() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     ConfigProperties.configProperties
        //     ConfigProperties.environment
        //     ConfigProperties.extendExpiryTime
        //     ConfigProperties.logger
        //     ConfigProperties.properties
        //     ConfigProperties.redisport
        //     ConfigProperties.redisurl
        //     ConfigProperties.sessionExpiryTime

        // Arrange and Act
        (new CommonMain()).configProperties();
    }

    /**
     * Method under test: {@link CommonMain#redisSession()}
     */
    @Test
    void testRedisSession() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     AbstractRedisHttpSessionConfiguration.classLoader
        //     AbstractRedisHttpSessionConfiguration.defaultRedisSerializer
        //     AbstractRedisHttpSessionConfiguration.flushMode
        //     AbstractRedisHttpSessionConfiguration.maxInactiveInterval
        //     AbstractRedisHttpSessionConfiguration.redisConnectionFactory
        //     AbstractRedisHttpSessionConfiguration.redisNamespace
        //     AbstractRedisHttpSessionConfiguration.saveMode
        //     AbstractRedisHttpSessionConfiguration.sessionRepositoryCustomizers
        //     RedisHttpSessionConfiguration.embeddedValueResolver
        //     RedisHttpSessionConfiguration.sessionIdGenerator

        // Arrange and Act
        (new CommonMain()).redisSession();
    }

    /**
     * Method under test: {@link CommonMain#redisStorage()}
     */
    @Test
    void testRedisStorage() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     RedisStorage.connection
        //     RedisStorage.logger

        // Arrange and Act
        (new CommonMain()).redisStorage();
    }
}
