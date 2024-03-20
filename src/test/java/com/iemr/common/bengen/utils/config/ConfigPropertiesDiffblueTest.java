package com.iemr.common.bengen.utils.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.web.reactive.context.StandardReactiveWebEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.test.context.aot.DisabledInAotMode;

@DisabledInAotMode
class ConfigPropertiesDiffblueTest {
    @Mock
    private Environment environment;

    /**
     * Method under test: {@link ConfigProperties#setEnvironment(Environment)}
     */
    @Test
    void testSetEnvironment() {
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

        // Arrange
        ConfigProperties configProperties = new ConfigProperties();

        // Act
        configProperties.setEnvironment(new StandardReactiveWebEnvironment());
    }

    /**
     * Method under test: {@link ConfigProperties#getRedisUrl()}
     */
    @Test
    void testGetRedisUrl() {
        // Arrange, Act and Assert
        assertNull(ConfigProperties.getRedisUrl());
    }

    /**
     * Method under test: {@link ConfigProperties#getRedisPort()}
     */
    @Test
    void testGetRedisPort() {
        // Arrange, Act and Assert
        assertEquals(0, ConfigProperties.getRedisPort());
    }

    /**
     * Method under test: {@link ConfigProperties#getExtendExpiryTime()}
     */
    @Test
    void testGetExtendExpiryTime() {
        // Arrange, Act and Assert
        assertFalse(ConfigProperties.getExtendExpiryTime());
    }

    /**
     * Method under test: {@link ConfigProperties#getSessionExpiryTime()}
     */
    @Test
    void testGetSessionExpiryTime() {
        // Arrange, Act and Assert
        assertEquals(7200, ConfigProperties.getSessionExpiryTime());
    }

    /**
     * Method under test: {@link ConfigProperties#getPropertyByName(String)}
     */
    @Test
    void testGetPropertyByName() {
        // Arrange, Act and Assert
        assertNull(ConfigProperties.getPropertyByName("Property Name"));
        assertNull(ConfigProperties.getPropertyByName(null));
    }

    /**
     * Method under test: {@link ConfigProperties#getBoolean(String)}
     */
    @Test
    void testGetBoolean() {
        // Arrange, Act and Assert
        assertFalse(ConfigProperties.getBoolean("Property Name"));
        assertFalse(ConfigProperties.getBoolean(null));
    }

    /**
     * Method under test: {@link ConfigProperties#getInteger(String)}
     */
    @Test
    void testGetInteger() {
        // Arrange, Act and Assert
        assertEquals(0, ConfigProperties.getInteger("Property Name").intValue());
        assertEquals(0, ConfigProperties.getInteger(null).intValue());
        assertEquals(7200, ConfigProperties.getInteger("iemr.session.expiry.time").intValue());
    }

    /**
     * Method under test: {@link ConfigProperties#getLong(String)}
     */
    @Test
    void testGetLong() {
        // Arrange, Act and Assert
        assertEquals(0L, ConfigProperties.getLong("Property Name").longValue());
        assertEquals(0L, ConfigProperties.getLong(null).longValue());
        assertEquals(7200L, ConfigProperties.getLong("iemr.session.expiry.time").longValue());
    }

    /**
     * Method under test: {@link ConfigProperties#getFloat(String)}
     */
    @Test
    void testGetFloat() {
        // Arrange, Act and Assert
        assertEquals(7200.0f, ConfigProperties.getFloat("iemr.session.expiry.time").floatValue());
    }

    /**
     * Method under test: {@link ConfigProperties#getPassword(String)}
     */
    @Test
    void testGetPassword() {
        // Arrange, Act and Assert
        assertEquals("7200", ConfigProperties.getPassword("iemr.session.expiry.time"));
    }
}
