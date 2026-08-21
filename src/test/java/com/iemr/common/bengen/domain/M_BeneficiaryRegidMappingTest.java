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

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("M_BeneficiaryRegidMapping Test Suite")
class M_BeneficiaryRegidMappingTest {

    private static final Long BEN_REG_ID = 753812721192L;
    private static final Long BENEFICIARY_ID = 796702837334L;
    private static final Timestamp CREATED_DATE = Timestamp.valueOf("2025-06-25 10:00:00");

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
}
