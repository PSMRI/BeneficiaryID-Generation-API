package com.iemr.common.bengen.service;

import com.iemr.common.bengen.service.BengenService;

import io.jsonwebtoken.security.InvalidKeyException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.crypto.*;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Assertions;

public class BengenServiceTest {

    private BengenService bengenService;

    @BeforeEach
    void setUp() {
        bengenService = new BengenService();
    }

    @Test
    void testGetQueryStringPositiveNum() {
        List<String> result = bengenService.getQueryString(5);
        Assertions.assertEquals(5, result.size());
        for (String s : result) {
            Assertions.assertTrue(s.contains("'bengenApp'"));
        }
    }

    @Test
    void testGetQueryStringZeroNum() {
        List<String> result = bengenService.getQueryString(0);
        Assertions.assertEquals(0, result.size());
    }

    @Test
    void testGetQueryStringNegativeNum() {
        List<String> result = bengenService.getQueryString(-5);
        Assertions.assertEquals(0, result.size());
    }


    @Test
    void testEncryption() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        // bengenService.encryption();
        // Assertion is implicit.  The method logs the result; we assume successful encryption/decryption if no exception is thrown.
        Assertions.assertDoesNotThrow(()->bengenService.encryption());

    }

    @Test
    void testGetFileName() {
        String result = bengenService.getFileName("testThread", "testName");
        Assertions.assertTrue(result.endsWith(".csv"));
        Assertions.assertTrue(result.contains("testThread"));
        Assertions.assertTrue(result.contains("testName"));
    }

    @Test
    void testHashing() throws NoSuchAlgorithmException {
        Assertions.assertDoesNotThrow(() -> bengenService.hashing());
    }
}