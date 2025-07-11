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
package com.iemr.common.bengen.service;

import com.iemr.common.bengen.service.BengenService;

import io.jsonwebtoken.security.InvalidKeyException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import javax.crypto.*;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Assertions;

@DisplayName("BengenService Test Suite")
public class BengenServiceTest {

    private BengenService bengenService;

    @BeforeEach
    @DisplayName("Set up test environment before each test")
    void setUp() {
        bengenService = new BengenService();
    }

    @Test
    @DisplayName("Should return correct number of query strings for positive input")
    void testGetQueryStringPositiveNum() {
        List<String> result = bengenService.getQueryString(5);
        Assertions.assertEquals(5, result.size());
        for (String s : result) {
            Assertions.assertTrue(s.contains("'bengenApp'"));
        }
    }

    @Test
    @DisplayName("Should return empty list when input is zero")
    void testGetQueryStringZeroNum() {
        List<String> result = bengenService.getQueryString(0);
        Assertions.assertEquals(0, result.size());
    }

    @Test
    @DisplayName("Should return empty list when input is negative")
    void testGetQueryStringNegativeNum() {
        List<String> result = bengenService.getQueryString(-5);
        Assertions.assertEquals(0, result.size());
    }


    @Test
    @DisplayName("Should successfully perform encryption and decryption without exceptions")
    void testEncryption() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        // bengenService.encryption();
        // Assertion is implicit.  The method logs the result; we assume successful encryption/decryption if no exception is thrown.
        Assertions.assertDoesNotThrow(()->bengenService.encryption());

    }

    @Test
    @DisplayName("Should generate valid filename with thread name and custom name")
    void testGetFileName() {
        String result = bengenService.getFileName("testThread", "testName");
        Assertions.assertTrue(result.endsWith(".csv"));
        Assertions.assertTrue(result.contains("testThread"));
        Assertions.assertTrue(result.contains("testName"));
    }

    @Test
    @DisplayName("Should successfully perform hashing without exceptions")
    void testHashing() throws NoSuchAlgorithmException {
        Assertions.assertDoesNotThrow(() -> bengenService.hashing());
    }
}