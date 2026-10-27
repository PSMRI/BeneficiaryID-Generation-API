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

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Generator Test Suite")
class GeneratorTest {

    private static Level originalLevel;

    private Generator generator;

    @BeforeAll
    @DisplayName("Enable debug logging so the generator's diagnostic branches are exercised")
    static void enableDebugLogging() {
        Logger logger = (Logger) LoggerFactory.getLogger(Generator.class);
        originalLevel = logger.getLevel();
        logger.setLevel(Level.DEBUG);
    }

    @AfterAll
    @DisplayName("Restore the original log level")
    static void restoreLogging() {
        ((Logger) LoggerFactory.getLogger(Generator.class)).setLevel(originalLevel);
    }

    @BeforeEach
    @DisplayName("Create a generator before each test")
    void setUp() {
        generator = new Generator();
    }

    @Nested
    @DisplayName("Beneficiary id generation")
    class GenerateBeneficiaryIdTests {

        @RepeatedTest(value = 5, name = "run {currentRepetition} of {totalRepetitions}")
        @DisplayName("generateBeneficiaryId should produce a 12-digit id")
        void generateBeneficiaryId_shouldProduceTwelveDigitId() {
            BigInteger id = generator.generateBeneficiaryId();

            assertEquals(12, id.toString().length(), "beneficiary ids are 12 digits: " + id);
        }

        @RepeatedTest(value = 5, name = "run {currentRepetition} of {totalRepetitions}")
        @DisplayName("generateBeneficiaryId should end with the Verhoeff digit of its zero-terminated base")
        void generateBeneficiaryId_shouldEndWithVerhoeffDigitOfZeroTerminatedBase() {
            String id = generator.generateBeneficiaryId().toString();

            // The generator derives the check digit from the base value while its last
            // position is still a zero, then adds the digit into that position. Note this
            // is NOT the standard convention, under which the digit would be derived from
            // the 11-digit prefix alone, so Verhoeff.validateVerhoeff(id) usually fails.
            String zeroTerminatedBase = id.substring(0, id.length() - 1) + "0";
            assertEquals(id.substring(id.length() - 1), Verhoeff.generateVerhoeff(zeroTerminatedBase),
                    "the trailing digit must be the Verhoeff digit of " + zeroTerminatedBase);
        }

        @RepeatedTest(value = 5, name = "run {currentRepetition} of {totalRepetitions}")
        @DisplayName("generateFirst should start with a digit between 2 and 9")
        void generateFirst_shouldStartWithDigitBetweenTwoAndNine() {
            char leading = generator.generateFirst().toString().charAt(0);

            assertTrue(leading >= '2' && leading <= '9', "leading digit was " + leading);
        }
    }

    @Nested
    @DisplayName("Digit counting")
    class DigitCountTests {

        @Test
        @DisplayName("getDigitCount should count the digits of a single-digit number")
        void getDigitCount_shouldCountSingleDigit() {
            assertEquals(1, generator.getDigitCount(BigInteger.valueOf(7)));
        }

        @Test
        @DisplayName("getDigitCount should count the digits at a power-of-ten boundary")
        void getDigitCount_shouldCountAtPowerOfTenBoundary() {
            assertEquals(3, generator.getDigitCount(BigInteger.valueOf(100)));
            assertEquals(3, generator.getDigitCount(BigInteger.valueOf(999)));
            assertEquals(4, generator.getDigitCount(BigInteger.valueOf(1000)));
        }

        @Test
        @DisplayName("getDigitCount should count the digits of a twelve-digit beneficiary id")
        void getDigitCount_shouldCountTwelveDigitId() {
            assertEquals(12, generator.getDigitCount(new BigInteger("753812721192")));
        }
    }

    @Nested
    @DisplayName("Random helpers")
    class RandomHelperTests {

        @RepeatedTest(value = 10, name = "run {currentRepetition} of {totalRepetitions}")
        @DisplayName("getRandomNum should return a single decimal digit")
        void getRandomNum_shouldReturnSingleDecimalDigit() {
            int num = generator.getRandomNum();

            assertTrue(num >= 0 && num <= 9, "expected a single digit but got " + num);
        }

        @RepeatedTest(value = 10, name = "run {currentRepetition} of {totalRepetitions}")
        @DisplayName("getRandomNumRad should return a value inside the requested radix")
        void getRandomNumRad_shouldReturnValueInsideRadix() {
            int num = generator.getRandomNumRad(5);

            assertTrue(num >= 0 && num < 5, "expected a value below the radix but got " + num);
        }

        @RepeatedTest(value = 10, name = "run {currentRepetition} of {totalRepetitions}")
        @DisplayName("getRandomNumRadRange should return a value inside the requested range")
        void getRandomNumRadRange_shouldReturnValueInsideRange() {
            int num = generator.getRandomNumRadRange(2, 9);

            assertTrue(num >= 2 && num <= 9, "expected a value within 2..9 but got " + num);
        }
    }

    @Nested
    @DisplayName("Diagnostics")
    class DiagnosticsTests {

        @Test
        @DisplayName("displayArrays should log both arrays without failing")
        void displayArrays_shouldLogBothArrays() {
            assertDoesNotThrow(() -> generator.displayArrays(new int[] { 1, 2, 3 }, new int[] { 4, 5, 6 }));
        }

        @Test
        @DisplayName("displayArrays should tolerate empty arrays")
        void displayArrays_shouldTolerateEmptyArrays() {
            assertDoesNotThrow(() -> generator.displayArrays(new int[0], new int[0]));
        }
    }
}
