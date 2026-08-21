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

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Verhoeff Test Suite")
class VerhoeffTest {

    @Test
    @DisplayName("generateVerhoeff should produce the documented check digit for a known number")
    void generateVerhoeff_shouldProduceCheckDigitForKnownNumber() {
        assertEquals("3", Verhoeff.generateVerhoeff("236"));
    }

    @Test
    @DisplayName("generateVerhoeff should produce a single digit for a long number")
    void generateVerhoeff_shouldProduceSingleDigitForLongNumber() {
        String digit = Verhoeff.generateVerhoeff("75381272119");

        assertEquals(1, digit.length());
        assertTrue(digit.charAt(0) >= '0' && digit.charAt(0) <= '9');
    }

    @Test
    @DisplayName("validateVerhoeff should accept a number carrying its own generated check digit")
    void validateVerhoeff_shouldAcceptNumberWithGeneratedCheckDigit() {
        String base = "75381272119";

        assertTrue(Verhoeff.validateVerhoeff(base + Verhoeff.generateVerhoeff(base)));
    }

    @Test
    @DisplayName("validateVerhoeff should reject a number whose check digit is wrong")
    void validateVerhoeff_shouldRejectWrongCheckDigit() {
        String base = "75381272119";
        int correct = Integer.parseInt(Verhoeff.generateVerhoeff(base));
        int wrong = (correct + 1) % 10;

        assertFalse(Verhoeff.validateVerhoeff(base + wrong));
    }

    @Test
    @DisplayName("validateVerhoeff should reject a number with a transposed pair of digits")
    void validateVerhoeff_shouldRejectTransposedDigits() {
        String valid = "236" + Verhoeff.generateVerhoeff("236");
        String transposed = "" + valid.charAt(1) + valid.charAt(0) + valid.substring(2);

        assertFalse(Verhoeff.validateVerhoeff(transposed),
                "Verhoeff must catch adjacent transposition, the error it exists to detect");
    }

    @Test
    @DisplayName("generateVerhoeff should be stable across repeated calls")
    void generateVerhoeff_shouldBeStableAcrossCalls() {
        assertEquals(Verhoeff.generateVerhoeff("123456789"), Verhoeff.generateVerhoeff("123456789"));
    }
}
