package com.iemr.common.bengen.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {BengenService.class})
@ExtendWith(SpringExtension.class)
class BengenServiceDiffblueTest {
    @Autowired
    private BengenService bengenService;

    /**
     * Method under test: {@link BengenService#getFileName(String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetFileName() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Method may be time-sensitive.
        //   Diffblue Cover was only able to write tests that are time-sensitive.
        //   The assertions don't pass when run at an alternate date, time, and
        //   timezone. Try refactoring the method to take a java.time.Clock instance so
        //   that the time can be parameterized during testing.
        //   See Working with code R031 (https://diff.blue/R031) for details.

        // Arrange and Act
        bengenService.getFileName("Thread Name", "Name");
    }

    /**
     * Method under test: {@link BengenService#getQueryString(int)}
     */
    @Test
    void testGetQueryString() {
        // Arrange, Act and Assert
        assertEquals(10, bengenService.getQueryString(10).size());
        assertTrue(bengenService.getQueryString(0).isEmpty());
    }

    /**
     * Method under test: {@link BengenService#encryption()}
     */
    @Test
    void testEncryption() throws InvalidKeyException, NoSuchAlgorithmException, BadPaddingException,
            IllegalBlockSizeException, NoSuchPaddingException {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     BengenService.logger

        // Arrange and Act
        bengenService.encryption();
    }

    /**
     * Method under test: {@link BengenService#hashing()}
     */
    @Test
    void testHashing() throws NoSuchAlgorithmException {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     BengenService.logger

        // Arrange and Act
        bengenService.hashing();
    }
}
