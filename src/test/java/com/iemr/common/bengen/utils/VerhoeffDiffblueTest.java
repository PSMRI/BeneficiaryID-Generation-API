package com.iemr.common.bengen.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class VerhoeffDiffblueTest {
    /**
     * Method under test: {@link Verhoeff#generateVerhoeff(String)}
     */
    @Test
    void testGenerateVerhoeff() {
        // Arrange, Act and Assert
        assertEquals("0", Verhoeff.generateVerhoeff("42"));
    }

    /**
     * Method under test: {@link Verhoeff#validateVerhoeff(String)}
     */
    @Test
    void testValidateVerhoeff() {
        // Arrange, Act and Assert
        assertFalse(Verhoeff.validateVerhoeff("42"));
        assertTrue(Verhoeff.validateVerhoeff(""));
    }
}
