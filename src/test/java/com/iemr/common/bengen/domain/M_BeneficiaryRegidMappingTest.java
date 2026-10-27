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

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("M_BeneficiaryRegidMapping Test Suite")
class M_BeneficiaryRegidMappingTest {

    private static final Long BEN_REG_ID = 753812721192L;
    private static final Long BENEFICIARY_ID = 796702837334L;
    private static final Timestamp CREATED_DATE = Timestamp.valueOf("2025-06-25 10:00:00");
    private static final Timestamp OTHER_DATE = Timestamp.valueOf("2030-01-01 00:00:00");

    private M_BeneficiaryRegidMapping row() {
        return new M_BeneficiaryRegidMapping(BEN_REG_ID, BENEFICIARY_ID, CREATED_DATE, "admin");
    }

    private M_BeneficiaryRegidMapping fullyPopulated() {
        M_BeneficiaryRegidMapping row = row();
        row.setProvisioned(false);
        row.setReserved(false);
        row.setVanID(12);
        row.setBenIDRequired(500L);
        return row;
    }

    @Nested
    @DisplayName("Construction and field access")
    class FieldTests {

        @Test
        @DisplayName("the constructor should populate the identity and audit fields")
        void constructor_shouldPopulateIdentityAndAuditFields() {
            M_BeneficiaryRegidMapping row = row();

            assertEquals(BEN_REG_ID, row.getBenRegId());
            assertEquals(BENEFICIARY_ID, row.getBeneficiaryId());
            assertEquals(CREATED_DATE, row.getCreatedDate());
            assertEquals("admin", row.getCreatedBy());
        }

        @Test
        @DisplayName("the constructor should leave the provisioning and reservation flags unset")
        void constructor_shouldLeaveFlagsUnset() {
            M_BeneficiaryRegidMapping row = row();

            assertNull(row.getProvisioned());
            assertNull(row.getReserved());
            assertNull(row.getVanID());
            assertNull(row.getBenIDRequired());
        }

        @Test
        @DisplayName("the provisioning, reservation and van fields should round-trip through their accessors")
        void mutableFields_shouldRoundTrip() {
            M_BeneficiaryRegidMapping row = fullyPopulated();

            assertEquals(false, row.getProvisioned());
            assertEquals(false, row.getReserved());
            assertEquals(12, row.getVanID());
            assertEquals(500L, row.getBenIDRequired());
        }
    }

    @Nested
    @DisplayName("Value semantics and serialisation")
    class ValueSemanticsTests {

        @Test
        @DisplayName("two rows holding the same values should be equal and share a hash code")
        void rowsWithSameValues_shouldBeEqual() {
            assertEquals(fullyPopulated(), fullyPopulated());
            assertEquals(fullyPopulated().hashCode(), fullyPopulated().hashCode());
        }

        @Test
        @DisplayName("rows differing in provisioning state should not be equal")
        void rowsDifferingInProvisioningState_shouldNotBeEqual() {
            M_BeneficiaryRegidMapping other = fullyPopulated();
            other.setProvisioned(true);

            assertNotEquals(fullyPopulated(), other);
        }

        @Test
        @DisplayName("a row should not equal an unrelated object")
        void row_shouldNotEqualUnrelatedObject() {
            assertNotEquals(fullyPopulated(), "not a mapping row");
        }

        @Test
        @DisplayName("toString should render the exposed fields as JSON with ids serialised as strings")
        void toString_shouldRenderExposedFieldsAsJson() {
            String json = fullyPopulated().toString();

            assertTrue(json.contains("\"benRegId\":\"753812721192\""), json);
            assertTrue(json.contains("\"beneficiaryId\":\"796702837334\""), json);
            assertTrue(json.contains("\"createdBy\":\"admin\""), json);
        }

        @Test
        @DisplayName("toString should omit the fields that are not exposed for serialisation")
        void toString_shouldOmitUnexposedFields() {
            String json = fullyPopulated().toString();

            assertTrue(!json.contains("provisioned"), json);
            assertTrue(!json.contains("reserved"), json);
            assertTrue(!json.contains("vanID"), json);
            assertTrue(!json.contains("benIDRequired"), json);
        }

        @Test
        @DisplayName("toString should serialise a null audit field rather than dropping it")
        void toString_shouldSerialiseNullAuditField() {
            M_BeneficiaryRegidMapping row =
                    new M_BeneficiaryRegidMapping(BEN_REG_ID, BENEFICIARY_ID, null, null);

            String json = row.toString();

            assertTrue(json.contains("\"createdDate\":null"), json);
            assertTrue(json.contains("\"createdBy\":null"), json);
        }
    }

    @Nested
    @DisplayName("Generated equality across every field")
    class PerFieldEqualityTests {

        /** One mutator per field, so every generated equality branch is exercised. */
        private List<Map.Entry<String, Consumer<M_BeneficiaryRegidMapping>>> differingValues() {
            return List.of(
                    Map.entry("benRegId", (Consumer<M_BeneficiaryRegidMapping>) r -> r.setBenRegId(1L)),
                    Map.entry("beneficiaryId", (Consumer<M_BeneficiaryRegidMapping>) r -> r.setBeneficiaryId(2L)),
                    Map.entry("provisioned", (Consumer<M_BeneficiaryRegidMapping>) r -> r.setProvisioned(true)),
                    Map.entry("reserved", (Consumer<M_BeneficiaryRegidMapping>) r -> r.setReserved(true)),
                    Map.entry("vanID", (Consumer<M_BeneficiaryRegidMapping>) r -> r.setVanID(99)),
                    Map.entry("createdDate", (Consumer<M_BeneficiaryRegidMapping>) r -> r.setCreatedDate(OTHER_DATE)),
                    Map.entry("createdBy", (Consumer<M_BeneficiaryRegidMapping>) r -> r.setCreatedBy("someone")),
                    Map.entry("benIDRequired", (Consumer<M_BeneficiaryRegidMapping>) r -> r.setBenIDRequired(1L)));
        }

        private List<Map.Entry<String, Consumer<M_BeneficiaryRegidMapping>>> nullValues() {
            return List.of(
                    Map.entry("benRegId", (Consumer<M_BeneficiaryRegidMapping>) r -> r.setBenRegId(null)),
                    Map.entry("beneficiaryId", (Consumer<M_BeneficiaryRegidMapping>) r -> r.setBeneficiaryId(null)),
                    Map.entry("provisioned", (Consumer<M_BeneficiaryRegidMapping>) r -> r.setProvisioned(null)),
                    Map.entry("reserved", (Consumer<M_BeneficiaryRegidMapping>) r -> r.setReserved(null)),
                    Map.entry("vanID", (Consumer<M_BeneficiaryRegidMapping>) r -> r.setVanID(null)),
                    Map.entry("createdDate", (Consumer<M_BeneficiaryRegidMapping>) r -> r.setCreatedDate(null)),
                    Map.entry("createdBy", (Consumer<M_BeneficiaryRegidMapping>) r -> r.setCreatedBy(null)),
                    Map.entry("benIDRequired", (Consumer<M_BeneficiaryRegidMapping>) r -> r.setBenIDRequired(null)));
        }

        @Test
        @DisplayName("a difference in any single field should break equality")
        void equals_shouldDetectDifferenceInEveryField() {
            assertAll(differingValues().stream().map(field -> () -> {
                M_BeneficiaryRegidMapping variant = fullyPopulated();
                field.getValue().accept(variant);
                assertNotEquals(fullyPopulated(), variant,
                        "changing " + field.getKey() + " must break equality");
            }));
        }

        @Test
        @DisplayName("a null in any single field should break equality in both directions")
        void equals_shouldDetectNullInEveryField() {
            assertAll(nullValues().stream().map(field -> () -> {
                M_BeneficiaryRegidMapping variant = fullyPopulated();
                field.getValue().accept(variant);
                assertNotEquals(fullyPopulated(), variant,
                        "nulling " + field.getKey() + " must break equality");
                assertNotEquals(variant, fullyPopulated(),
                        "equality must stay symmetric when " + field.getKey() + " is null");
            }));
        }

        @Test
        @DisplayName("hashCode should tolerate a null in any single field")
        void hashCode_shouldTolerateNullInEveryField() {
            assertAll(nullValues().stream().map(field -> () -> {
                M_BeneficiaryRegidMapping variant = fullyPopulated();
                field.getValue().accept(variant);
                assertDoesNotThrow(variant::hashCode,
                        "hashCode must not fail when " + field.getKey() + " is null");
            }));
        }

        @Test
        @DisplayName("two rows sharing the same null field should still be equal")
        void equals_shouldTreatMatchingNullFieldsAsEqual() {
            assertAll(nullValues().stream().map(field -> () -> {
                M_BeneficiaryRegidMapping first = fullyPopulated();
                M_BeneficiaryRegidMapping second = fullyPopulated();
                field.getValue().accept(first);
                field.getValue().accept(second);
                assertEquals(first, second,
                        "rows sharing a null " + field.getKey() + " must remain equal");
                assertEquals(first.hashCode(), second.hashCode());
            }));
        }

        @Test
        @DisplayName("a row should equal itself and never equal null")
        void row_shouldEqualItselfAndNotNull() {
            M_BeneficiaryRegidMapping row = fullyPopulated();

            assertEquals(row, row);
            assertNotEquals(row, null);
        }
    }
}
