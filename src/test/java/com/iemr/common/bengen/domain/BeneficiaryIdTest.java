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
package com.iemr.common.bengen.domain;

import java.math.BigInteger;
import java.sql.Timestamp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("BeneficiaryId Test Suite")
class BeneficiaryIdTest {

    private static final BigInteger BEN_REG_ID = new BigInteger("753812721192");
    private static final BigInteger BENEFICIARY_ID = new BigInteger("796702837334");
    private static final Timestamp RESERVED_ON = Timestamp.valueOf("2025-06-25 10:00:00");
    private static final Timestamp RESERVED_UNTIL = Timestamp.valueOf("2025-06-26 10:00:00");
    private static final Timestamp PROVISIONED_ON = Timestamp.valueOf("2025-06-25 11:00:00");
    private static final Timestamp CREATED_DATE = Timestamp.valueOf("2025-06-01 09:00:00");

    private BeneficiaryId beneficiaryId;

    @BeforeEach
    @DisplayName("Create an unpopulated mapping row before each test")
    void setUp() {
        beneficiaryId = new BeneficiaryId();
    }

    private BeneficiaryId populated() {
        BeneficiaryId row = new BeneficiaryId();
        row.setBenRegId(BEN_REG_ID);
        row.setBeneficiaryId(BENEFICIARY_ID);
        row.setProvisioned(true);
        row.setProvisionedById(7);
        row.setProvisionedBy("admin");
        row.setProvisionedOn(PROVISIONED_ON);
        row.setReserved(true);
        row.setReservedForId(11);
        row.setReservedForName("MMU");
        row.setReservedForCountryId(1);
        row.setReservedForCountryName("India");
        row.setReservedForStateId(2);
        row.setReservedForStateName("Karnataka");
        row.setReservedForDistrictId(3);
        row.setReservedForDistrictName("Bengaluru Urban");
        row.setReservedForPSMapId(4);
        row.setReservedForPSMapName("PS-Map-4");
        row.setReservedById(5);
        row.setReservedByName("scheduler");
        row.setReservedOn(RESERVED_ON);
        row.setReservedUntil(RESERVED_UNTIL);
        row.setCreatedBy("admin");
        row.setCreatedDate(CREATED_DATE);
        return row;
    }

    @Nested
    @DisplayName("Field round-tripping")
    class FieldTests {

        @Test
        @DisplayName("a new mapping row should leave every field unset")
        void newRow_shouldLeaveEveryFieldUnset() {
            assertNull(beneficiaryId.getBenRegId());
            assertNull(beneficiaryId.getBeneficiaryId());
            assertNull(beneficiaryId.getProvisioned());
            assertNull(beneficiaryId.getReserved());
            assertNull(beneficiaryId.getCreatedBy());
        }

        @Test
        @DisplayName("the identity and provisioning fields should round-trip through their accessors")
        void identityAndProvisioningFields_shouldRoundTrip() {
            BeneficiaryId row = populated();

            assertEquals(BEN_REG_ID, row.getBenRegId());
            assertEquals(BENEFICIARY_ID, row.getBeneficiaryId());
            assertTrue(row.getProvisioned());
            assertEquals(7, row.getProvisionedById());
            assertEquals("admin", row.getProvisionedBy());
            assertEquals(PROVISIONED_ON, row.getProvisionedOn());
        }

        @Test
        @DisplayName("the reservation fields should round-trip through their accessors")
        void reservationFields_shouldRoundTrip() {
            BeneficiaryId row = populated();

            assertTrue(row.getReserved());
            assertEquals(11, row.getReservedForId());
            assertEquals("MMU", row.getReservedForName());
            assertEquals(1, row.getReservedForCountryId());
            assertEquals("India", row.getReservedForCountryName());
            assertEquals(2, row.getReservedForStateId());
            assertEquals("Karnataka", row.getReservedForStateName());
            assertEquals(3, row.getReservedForDistrictId());
            assertEquals("Bengaluru Urban", row.getReservedForDistrictName());
            assertEquals(4, row.getReservedForPSMapId());
            assertEquals("PS-Map-4", row.getReservedForPSMapName());
            assertEquals(5, row.getReservedById());
            assertEquals("scheduler", row.getReservedByName());
            assertEquals(RESERVED_ON, row.getReservedOn());
            assertEquals(RESERVED_UNTIL, row.getReservedUntil());
        }

        @Test
        @DisplayName("the audit fields should round-trip through their accessors")
        void auditFields_shouldRoundTrip() {
            BeneficiaryId row = populated();

            assertEquals("admin", row.getCreatedBy());
            assertEquals(CREATED_DATE, row.getCreatedDate());
        }
    }

    @Nested
    @DisplayName("Value semantics")
    class ValueSemanticsTests {

        @Test
        @DisplayName("two rows holding the same values should be equal and share a hash code")
        void rowsWithSameValues_shouldBeEqual() {
            assertEquals(populated(), populated());
            assertEquals(populated().hashCode(), populated().hashCode());
        }

        @Test
        @DisplayName("two unpopulated rows should be equal")
        void unpopulatedRows_shouldBeEqual() {
            assertEquals(new BeneficiaryId(), new BeneficiaryId());
        }

        @Test
        @DisplayName("rows differing in the beneficiary id should not be equal")
        void rowsDifferingInBeneficiaryId_shouldNotBeEqual() {
            BeneficiaryId other = populated();
            other.setBeneficiaryId(new BigInteger("111111111111"));

            assertNotEquals(populated(), other);
        }

        @Test
        @DisplayName("rows differing in reservation state should not be equal")
        void rowsDifferingInReservationState_shouldNotBeEqual() {
            BeneficiaryId other = populated();
            other.setReserved(false);

            assertNotEquals(populated(), other);
        }

        @Test
        @DisplayName("a row should not equal an unrelated object")
        void row_shouldNotEqualUnrelatedObject() {
            assertNotEquals(populated(), "not a BeneficiaryId");
        }

        @Test
        @DisplayName("toString should name the type and expose the identity fields")
        void toString_shouldNameTypeAndExposeIdentityFields() {
            String text = populated().toString();

            assertTrue(text.startsWith("BeneficiaryId("));
            assertTrue(text.contains(BEN_REG_ID.toString()));
            assertTrue(text.contains(BENEFICIARY_ID.toString()));
            assertTrue(text.contains("scheduler"));
        }
    }
}
