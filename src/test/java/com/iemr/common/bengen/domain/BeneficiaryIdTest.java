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
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
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
    private static final Timestamp OTHER_TIMESTAMP = Timestamp.valueOf("2030-01-01 00:00:00");

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

    @Nested
    @DisplayName("Generated equality across every field")
    class PerFieldEqualityTests {

        /** One mutator per field, so every generated equality branch is exercised. */
        private List<Map.Entry<String, Consumer<BeneficiaryId>>> differingValues() {
            return List.of(
                    Map.entry("benRegId", (Consumer<BeneficiaryId>) r -> r.setBenRegId(BigInteger.ONE)),
                    Map.entry("beneficiaryId", (Consumer<BeneficiaryId>) r -> r.setBeneficiaryId(BigInteger.TWO)),
                    Map.entry("provisioned", (Consumer<BeneficiaryId>) r -> r.setProvisioned(false)),
                    Map.entry("provisionedById", (Consumer<BeneficiaryId>) r -> r.setProvisionedById(99)),
                    Map.entry("provisionedBy", (Consumer<BeneficiaryId>) r -> r.setProvisionedBy("someone")),
                    Map.entry("provisionedOn", (Consumer<BeneficiaryId>) r -> r.setProvisionedOn(OTHER_TIMESTAMP)),
                    Map.entry("reserved", (Consumer<BeneficiaryId>) r -> r.setReserved(false)),
                    Map.entry("reservedForId", (Consumer<BeneficiaryId>) r -> r.setReservedForId(99)),
                    Map.entry("reservedForName", (Consumer<BeneficiaryId>) r -> r.setReservedForName("Other")),
                    Map.entry("reservedForCountryId", (Consumer<BeneficiaryId>) r -> r.setReservedForCountryId(99)),
                    Map.entry("reservedForCountryName", (Consumer<BeneficiaryId>) r -> r.setReservedForCountryName("Nepal")),
                    Map.entry("reservedForStateId", (Consumer<BeneficiaryId>) r -> r.setReservedForStateId(99)),
                    Map.entry("reservedForStateName", (Consumer<BeneficiaryId>) r -> r.setReservedForStateName("Kerala")),
                    Map.entry("reservedForDistrictId", (Consumer<BeneficiaryId>) r -> r.setReservedForDistrictId(99)),
                    Map.entry("reservedForDistrictName", (Consumer<BeneficiaryId>) r -> r.setReservedForDistrictName("Mysuru")),
                    Map.entry("reservedForPSMapId", (Consumer<BeneficiaryId>) r -> r.setReservedForPSMapId(99)),
                    Map.entry("reservedForPSMapName", (Consumer<BeneficiaryId>) r -> r.setReservedForPSMapName("PS-Map-99")),
                    Map.entry("reservedById", (Consumer<BeneficiaryId>) r -> r.setReservedById(99)),
                    Map.entry("reservedByName", (Consumer<BeneficiaryId>) r -> r.setReservedByName("operator")),
                    Map.entry("reservedOn", (Consumer<BeneficiaryId>) r -> r.setReservedOn(OTHER_TIMESTAMP)),
                    Map.entry("reservedUntil", (Consumer<BeneficiaryId>) r -> r.setReservedUntil(OTHER_TIMESTAMP)),
                    Map.entry("createdBy", (Consumer<BeneficiaryId>) r -> r.setCreatedBy("someone")),
                    Map.entry("createdDate", (Consumer<BeneficiaryId>) r -> r.setCreatedDate(OTHER_TIMESTAMP)));
        }

        private List<Map.Entry<String, Consumer<BeneficiaryId>>> nullValues() {
            return List.of(
                    Map.entry("benRegId", (Consumer<BeneficiaryId>) r -> r.setBenRegId(null)),
                    Map.entry("beneficiaryId", (Consumer<BeneficiaryId>) r -> r.setBeneficiaryId(null)),
                    Map.entry("provisioned", (Consumer<BeneficiaryId>) r -> r.setProvisioned(null)),
                    Map.entry("provisionedById", (Consumer<BeneficiaryId>) r -> r.setProvisionedById(null)),
                    Map.entry("provisionedBy", (Consumer<BeneficiaryId>) r -> r.setProvisionedBy(null)),
                    Map.entry("provisionedOn", (Consumer<BeneficiaryId>) r -> r.setProvisionedOn(null)),
                    Map.entry("reserved", (Consumer<BeneficiaryId>) r -> r.setReserved(null)),
                    Map.entry("reservedForId", (Consumer<BeneficiaryId>) r -> r.setReservedForId(null)),
                    Map.entry("reservedForName", (Consumer<BeneficiaryId>) r -> r.setReservedForName(null)),
                    Map.entry("reservedForCountryId", (Consumer<BeneficiaryId>) r -> r.setReservedForCountryId(null)),
                    Map.entry("reservedForCountryName", (Consumer<BeneficiaryId>) r -> r.setReservedForCountryName(null)),
                    Map.entry("reservedForStateId", (Consumer<BeneficiaryId>) r -> r.setReservedForStateId(null)),
                    Map.entry("reservedForStateName", (Consumer<BeneficiaryId>) r -> r.setReservedForStateName(null)),
                    Map.entry("reservedForDistrictId", (Consumer<BeneficiaryId>) r -> r.setReservedForDistrictId(null)),
                    Map.entry("reservedForDistrictName", (Consumer<BeneficiaryId>) r -> r.setReservedForDistrictName(null)),
                    Map.entry("reservedForPSMapId", (Consumer<BeneficiaryId>) r -> r.setReservedForPSMapId(null)),
                    Map.entry("reservedForPSMapName", (Consumer<BeneficiaryId>) r -> r.setReservedForPSMapName(null)),
                    Map.entry("reservedById", (Consumer<BeneficiaryId>) r -> r.setReservedById(null)),
                    Map.entry("reservedByName", (Consumer<BeneficiaryId>) r -> r.setReservedByName(null)),
                    Map.entry("reservedOn", (Consumer<BeneficiaryId>) r -> r.setReservedOn(null)),
                    Map.entry("reservedUntil", (Consumer<BeneficiaryId>) r -> r.setReservedUntil(null)),
                    Map.entry("createdBy", (Consumer<BeneficiaryId>) r -> r.setCreatedBy(null)),
                    Map.entry("createdDate", (Consumer<BeneficiaryId>) r -> r.setCreatedDate(null)));
        }

        @Test
        @DisplayName("a difference in any single field should break equality")
        void equals_shouldDetectDifferenceInEveryField() {
            assertAll(differingValues().stream().map(field -> () -> {
                BeneficiaryId variant = populated();
                field.getValue().accept(variant);
                assertNotEquals(populated(), variant,
                        "changing " + field.getKey() + " must break equality");
            }));
        }

        @Test
        @DisplayName("a null in any single field should break equality in both directions")
        void equals_shouldDetectNullInEveryField() {
            assertAll(nullValues().stream().map(field -> () -> {
                BeneficiaryId variant = populated();
                field.getValue().accept(variant);
                assertNotEquals(populated(), variant,
                        "nulling " + field.getKey() + " must break equality");
                assertNotEquals(variant, populated(),
                        "equality must stay symmetric when " + field.getKey() + " is null");
            }));
        }

        @Test
        @DisplayName("hashCode should tolerate a null in any single field")
        void hashCode_shouldTolerateNullInEveryField() {
            assertAll(nullValues().stream().map(field -> () -> {
                BeneficiaryId variant = populated();
                field.getValue().accept(variant);
                assertDoesNotThrow(variant::hashCode,
                        "hashCode must not fail when " + field.getKey() + " is null");
            }));
        }

        @Test
        @DisplayName("a row should equal itself and never equal null")
        void row_shouldEqualItselfAndNotNull() {
            BeneficiaryId row = populated();

            assertEquals(row, row);
            assertNotEquals(row, null);
        }
    }
}
