package com.iemr.common.bengen.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import java.math.BigInteger;
import java.sql.Timestamp;

import org.junit.jupiter.api.Test;

class BeneficiaryIdDiffblueTest {
    /**
     * Method under test: {@link BeneficiaryId#canEqual(Object)}
     */
    @Test
    void testCanEqual() {
        // Arrange, Act and Assert
        assertFalse((new BeneficiaryId()).canEqual("Other"));
    }

    /**
     * Method under test: {@link BeneficiaryId#canEqual(Object)}
     */
    @Test
    void testCanEqual2() {
        // Arrange
        BeneficiaryId beneficiaryId = new BeneficiaryId();
        beneficiaryId.setBenRegId(BigInteger.valueOf(1L));
        beneficiaryId.setBeneficiaryId(BigInteger.valueOf(1L));
        beneficiaryId.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        beneficiaryId.setCreatedDate(mock(Timestamp.class));
        beneficiaryId.setProvisioned(true);
        beneficiaryId.setProvisionedBy("Provisioned By");
        beneficiaryId.setProvisionedById(1);
        beneficiaryId.setProvisionedOn(mock(Timestamp.class));
        beneficiaryId.setReserved(true);
        beneficiaryId.setReservedById(1);
        beneficiaryId.setReservedByName("Reserved By Name");
        beneficiaryId.setReservedForCountryId(1);
        beneficiaryId.setReservedForCountryName("GB");
        beneficiaryId.setReservedForDistrictId(1);
        beneficiaryId.setReservedForDistrictName("Reserved For District Name");
        beneficiaryId.setReservedForId(1);
        beneficiaryId.setReservedForName("Reserved For Name");
        beneficiaryId.setReservedForPSMapId(1);
        beneficiaryId.setReservedForPSMapName("Reserved For PSMap Name");
        beneficiaryId.setReservedForStateId(1);
        beneficiaryId.setReservedForStateName("Reserved For State Name");
        beneficiaryId.setReservedOn(mock(Timestamp.class));
        beneficiaryId.setReservedUntil(mock(Timestamp.class));

        BeneficiaryId beneficiaryId2 = new BeneficiaryId();
        beneficiaryId2.setBenRegId(BigInteger.valueOf(1L));
        beneficiaryId2.setBeneficiaryId(BigInteger.valueOf(1L));
        beneficiaryId2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        beneficiaryId2.setCreatedDate(mock(Timestamp.class));
        beneficiaryId2.setProvisioned(true);
        beneficiaryId2.setProvisionedBy("Provisioned By");
        beneficiaryId2.setProvisionedById(1);
        beneficiaryId2.setProvisionedOn(mock(Timestamp.class));
        beneficiaryId2.setReserved(true);
        beneficiaryId2.setReservedById(1);
        beneficiaryId2.setReservedByName("Reserved By Name");
        beneficiaryId2.setReservedForCountryId(1);
        beneficiaryId2.setReservedForCountryName("GB");
        beneficiaryId2.setReservedForDistrictId(1);
        beneficiaryId2.setReservedForDistrictName("Reserved For District Name");
        beneficiaryId2.setReservedForId(1);
        beneficiaryId2.setReservedForName("Reserved For Name");
        beneficiaryId2.setReservedForPSMapId(1);
        beneficiaryId2.setReservedForPSMapName("Reserved For PSMap Name");
        beneficiaryId2.setReservedForStateId(1);
        beneficiaryId2.setReservedForStateName("Reserved For State Name");
        beneficiaryId2.setReservedOn(mock(Timestamp.class));
        beneficiaryId2.setReservedUntil(mock(Timestamp.class));

        // Act and Assert
        assertTrue(beneficiaryId.canEqual(beneficiaryId2));
    }

    /**
     * Method under test: {@link BeneficiaryId#equals(Object)}
     */
    @Test
    void testEquals() {
        // Arrange
        BeneficiaryId beneficiaryId = new BeneficiaryId();
        beneficiaryId.setBenRegId(BigInteger.valueOf(1L));
        beneficiaryId.setBeneficiaryId(BigInteger.valueOf(1L));
        beneficiaryId.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        beneficiaryId.setCreatedDate(mock(Timestamp.class));
        beneficiaryId.setProvisioned(true);
        beneficiaryId.setProvisionedBy("Provisioned By");
        beneficiaryId.setProvisionedById(1);
        beneficiaryId.setProvisionedOn(mock(Timestamp.class));
        beneficiaryId.setReserved(true);
        beneficiaryId.setReservedById(1);
        beneficiaryId.setReservedByName("Reserved By Name");
        beneficiaryId.setReservedForCountryId(1);
        beneficiaryId.setReservedForCountryName("GB");
        beneficiaryId.setReservedForDistrictId(1);
        beneficiaryId.setReservedForDistrictName("Reserved For District Name");
        beneficiaryId.setReservedForId(1);
        beneficiaryId.setReservedForName("Reserved For Name");
        beneficiaryId.setReservedForPSMapId(1);
        beneficiaryId.setReservedForPSMapName("Reserved For PSMap Name");
        beneficiaryId.setReservedForStateId(1);
        beneficiaryId.setReservedForStateName("Reserved For State Name");
        beneficiaryId.setReservedOn(mock(Timestamp.class));
        beneficiaryId.setReservedUntil(mock(Timestamp.class));

        // Act and Assert
        assertNotEquals(beneficiaryId, null);
    }

    /**
     * Method under test: {@link BeneficiaryId#equals(Object)}
     */
    @Test
    void testEquals2() {
        // Arrange
        BeneficiaryId beneficiaryId = new BeneficiaryId();
        beneficiaryId.setBenRegId(BigInteger.valueOf(1L));
        beneficiaryId.setBeneficiaryId(BigInteger.valueOf(1L));
        beneficiaryId.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        beneficiaryId.setCreatedDate(mock(Timestamp.class));
        beneficiaryId.setProvisioned(true);
        beneficiaryId.setProvisionedBy("Provisioned By");
        beneficiaryId.setProvisionedById(1);
        beneficiaryId.setProvisionedOn(mock(Timestamp.class));
        beneficiaryId.setReserved(true);
        beneficiaryId.setReservedById(1);
        beneficiaryId.setReservedByName("Reserved By Name");
        beneficiaryId.setReservedForCountryId(1);
        beneficiaryId.setReservedForCountryName("GB");
        beneficiaryId.setReservedForDistrictId(1);
        beneficiaryId.setReservedForDistrictName("Reserved For District Name");
        beneficiaryId.setReservedForId(1);
        beneficiaryId.setReservedForName("Reserved For Name");
        beneficiaryId.setReservedForPSMapId(1);
        beneficiaryId.setReservedForPSMapName("Reserved For PSMap Name");
        beneficiaryId.setReservedForStateId(1);
        beneficiaryId.setReservedForStateName("Reserved For State Name");
        beneficiaryId.setReservedOn(mock(Timestamp.class));
        beneficiaryId.setReservedUntil(mock(Timestamp.class));

        // Act and Assert
        assertNotEquals(beneficiaryId, "Different type to BeneficiaryId");
    }

    /**
     * Method under test: {@link BeneficiaryId#equals(Object)}
     */
    @Test
    void testEquals3() {
        // Arrange
        BeneficiaryId beneficiaryId = new BeneficiaryId();
        beneficiaryId.setBenRegId(BigInteger.valueOf(1L));
        beneficiaryId.setBeneficiaryId(BigInteger.valueOf(1L));
        beneficiaryId.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        beneficiaryId.setCreatedDate(mock(Timestamp.class));
        beneficiaryId.setProvisioned(true);
        beneficiaryId.setProvisionedBy("Provisioned By");
        beneficiaryId.setProvisionedById(1);
        beneficiaryId.setProvisionedOn(mock(Timestamp.class));
        beneficiaryId.setReserved(true);
        beneficiaryId.setReservedById(1);
        beneficiaryId.setReservedByName("Reserved By Name");
        beneficiaryId.setReservedForCountryId(1);
        beneficiaryId.setReservedForCountryName("GB");
        beneficiaryId.setReservedForDistrictId(1);
        beneficiaryId.setReservedForDistrictName("Reserved For District Name");
        beneficiaryId.setReservedForId(1);
        beneficiaryId.setReservedForName("Reserved For Name");
        beneficiaryId.setReservedForPSMapId(1);
        beneficiaryId.setReservedForPSMapName("Reserved For PSMap Name");
        beneficiaryId.setReservedForStateId(1);
        beneficiaryId.setReservedForStateName("Reserved For State Name");
        beneficiaryId.setReservedOn(mock(Timestamp.class));
        beneficiaryId.setReservedUntil(mock(Timestamp.class));

        BeneficiaryId beneficiaryId2 = new BeneficiaryId();
        beneficiaryId2.setBenRegId(BigInteger.valueOf(1L));
        beneficiaryId2.setBeneficiaryId(BigInteger.valueOf(1L));
        beneficiaryId2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        beneficiaryId2.setCreatedDate(mock(Timestamp.class));
        beneficiaryId2.setProvisioned(true);
        beneficiaryId2.setProvisionedBy("Provisioned By");
        beneficiaryId2.setProvisionedById(1);
        beneficiaryId2.setProvisionedOn(mock(Timestamp.class));
        beneficiaryId2.setReserved(true);
        beneficiaryId2.setReservedById(1);
        beneficiaryId2.setReservedByName("Reserved By Name");
        beneficiaryId2.setReservedForCountryId(1);
        beneficiaryId2.setReservedForCountryName("GB");
        beneficiaryId2.setReservedForDistrictId(1);
        beneficiaryId2.setReservedForDistrictName("Reserved For District Name");
        beneficiaryId2.setReservedForId(1);
        beneficiaryId2.setReservedForName("Reserved For Name");
        beneficiaryId2.setReservedForPSMapId(1);
        beneficiaryId2.setReservedForPSMapName("Reserved For PSMap Name");
        beneficiaryId2.setReservedForStateId(1);
        beneficiaryId2.setReservedForStateName("Reserved For State Name");
        beneficiaryId2.setReservedOn(mock(Timestamp.class));
        beneficiaryId2.setReservedUntil(mock(Timestamp.class));

        // Act and Assert
        assertNotEquals(beneficiaryId, beneficiaryId2);
    }

    /**
     * Method under test: {@link BeneficiaryId#equals(Object)}
     */
    @Test
    void testEquals4() {
        // Arrange
        BeneficiaryId beneficiaryId = new BeneficiaryId();
        beneficiaryId.setBenRegId(BigInteger.valueOf(3L));
        beneficiaryId.setBeneficiaryId(BigInteger.valueOf(1L));
        beneficiaryId.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        beneficiaryId.setCreatedDate(mock(Timestamp.class));
        beneficiaryId.setProvisioned(true);
        beneficiaryId.setProvisionedBy("Provisioned By");
        beneficiaryId.setProvisionedById(1);
        beneficiaryId.setProvisionedOn(mock(Timestamp.class));
        beneficiaryId.setReserved(true);
        beneficiaryId.setReservedById(1);
        beneficiaryId.setReservedByName("Reserved By Name");
        beneficiaryId.setReservedForCountryId(1);
        beneficiaryId.setReservedForCountryName("GB");
        beneficiaryId.setReservedForDistrictId(1);
        beneficiaryId.setReservedForDistrictName("Reserved For District Name");
        beneficiaryId.setReservedForId(1);
        beneficiaryId.setReservedForName("Reserved For Name");
        beneficiaryId.setReservedForPSMapId(1);
        beneficiaryId.setReservedForPSMapName("Reserved For PSMap Name");
        beneficiaryId.setReservedForStateId(1);
        beneficiaryId.setReservedForStateName("Reserved For State Name");
        beneficiaryId.setReservedOn(mock(Timestamp.class));
        beneficiaryId.setReservedUntil(mock(Timestamp.class));

        BeneficiaryId beneficiaryId2 = new BeneficiaryId();
        beneficiaryId2.setBenRegId(BigInteger.valueOf(1L));
        beneficiaryId2.setBeneficiaryId(BigInteger.valueOf(1L));
        beneficiaryId2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        beneficiaryId2.setCreatedDate(mock(Timestamp.class));
        beneficiaryId2.setProvisioned(true);
        beneficiaryId2.setProvisionedBy("Provisioned By");
        beneficiaryId2.setProvisionedById(1);
        beneficiaryId2.setProvisionedOn(mock(Timestamp.class));
        beneficiaryId2.setReserved(true);
        beneficiaryId2.setReservedById(1);
        beneficiaryId2.setReservedByName("Reserved By Name");
        beneficiaryId2.setReservedForCountryId(1);
        beneficiaryId2.setReservedForCountryName("GB");
        beneficiaryId2.setReservedForDistrictId(1);
        beneficiaryId2.setReservedForDistrictName("Reserved For District Name");
        beneficiaryId2.setReservedForId(1);
        beneficiaryId2.setReservedForName("Reserved For Name");
        beneficiaryId2.setReservedForPSMapId(1);
        beneficiaryId2.setReservedForPSMapName("Reserved For PSMap Name");
        beneficiaryId2.setReservedForStateId(1);
        beneficiaryId2.setReservedForStateName("Reserved For State Name");
        beneficiaryId2.setReservedOn(mock(Timestamp.class));
        beneficiaryId2.setReservedUntil(mock(Timestamp.class));

        // Act and Assert
        assertNotEquals(beneficiaryId, beneficiaryId2);
    }

    /**
     * Method under test: {@link BeneficiaryId#equals(Object)}
     */
    @Test
    void testEquals5() {
        // Arrange
        BeneficiaryId beneficiaryId = new BeneficiaryId();
        beneficiaryId.setBenRegId(null);
        beneficiaryId.setBeneficiaryId(BigInteger.valueOf(1L));
        beneficiaryId.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        beneficiaryId.setCreatedDate(mock(Timestamp.class));
        beneficiaryId.setProvisioned(true);
        beneficiaryId.setProvisionedBy("Provisioned By");
        beneficiaryId.setProvisionedById(1);
        beneficiaryId.setProvisionedOn(mock(Timestamp.class));
        beneficiaryId.setReserved(true);
        beneficiaryId.setReservedById(1);
        beneficiaryId.setReservedByName("Reserved By Name");
        beneficiaryId.setReservedForCountryId(1);
        beneficiaryId.setReservedForCountryName("GB");
        beneficiaryId.setReservedForDistrictId(1);
        beneficiaryId.setReservedForDistrictName("Reserved For District Name");
        beneficiaryId.setReservedForId(1);
        beneficiaryId.setReservedForName("Reserved For Name");
        beneficiaryId.setReservedForPSMapId(1);
        beneficiaryId.setReservedForPSMapName("Reserved For PSMap Name");
        beneficiaryId.setReservedForStateId(1);
        beneficiaryId.setReservedForStateName("Reserved For State Name");
        beneficiaryId.setReservedOn(mock(Timestamp.class));
        beneficiaryId.setReservedUntil(mock(Timestamp.class));

        BeneficiaryId beneficiaryId2 = new BeneficiaryId();
        beneficiaryId2.setBenRegId(BigInteger.valueOf(1L));
        beneficiaryId2.setBeneficiaryId(BigInteger.valueOf(1L));
        beneficiaryId2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        beneficiaryId2.setCreatedDate(mock(Timestamp.class));
        beneficiaryId2.setProvisioned(true);
        beneficiaryId2.setProvisionedBy("Provisioned By");
        beneficiaryId2.setProvisionedById(1);
        beneficiaryId2.setProvisionedOn(mock(Timestamp.class));
        beneficiaryId2.setReserved(true);
        beneficiaryId2.setReservedById(1);
        beneficiaryId2.setReservedByName("Reserved By Name");
        beneficiaryId2.setReservedForCountryId(1);
        beneficiaryId2.setReservedForCountryName("GB");
        beneficiaryId2.setReservedForDistrictId(1);
        beneficiaryId2.setReservedForDistrictName("Reserved For District Name");
        beneficiaryId2.setReservedForId(1);
        beneficiaryId2.setReservedForName("Reserved For Name");
        beneficiaryId2.setReservedForPSMapId(1);
        beneficiaryId2.setReservedForPSMapName("Reserved For PSMap Name");
        beneficiaryId2.setReservedForStateId(1);
        beneficiaryId2.setReservedForStateName("Reserved For State Name");
        beneficiaryId2.setReservedOn(mock(Timestamp.class));
        beneficiaryId2.setReservedUntil(mock(Timestamp.class));

        // Act and Assert
        assertNotEquals(beneficiaryId, beneficiaryId2);
    }

    /**
     * Method under test: {@link BeneficiaryId#equals(Object)}
     */
    @Test
    void testEquals6() {
        // Arrange
        BeneficiaryId beneficiaryId = new BeneficiaryId();
        beneficiaryId.setBenRegId(BigInteger.valueOf(1L));
        beneficiaryId.setBeneficiaryId(BigInteger.valueOf(3L));
        beneficiaryId.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        beneficiaryId.setCreatedDate(mock(Timestamp.class));
        beneficiaryId.setProvisioned(true);
        beneficiaryId.setProvisionedBy("Provisioned By");
        beneficiaryId.setProvisionedById(1);
        beneficiaryId.setProvisionedOn(mock(Timestamp.class));
        beneficiaryId.setReserved(true);
        beneficiaryId.setReservedById(1);
        beneficiaryId.setReservedByName("Reserved By Name");
        beneficiaryId.setReservedForCountryId(1);
        beneficiaryId.setReservedForCountryName("GB");
        beneficiaryId.setReservedForDistrictId(1);
        beneficiaryId.setReservedForDistrictName("Reserved For District Name");
        beneficiaryId.setReservedForId(1);
        beneficiaryId.setReservedForName("Reserved For Name");
        beneficiaryId.setReservedForPSMapId(1);
        beneficiaryId.setReservedForPSMapName("Reserved For PSMap Name");
        beneficiaryId.setReservedForStateId(1);
        beneficiaryId.setReservedForStateName("Reserved For State Name");
        beneficiaryId.setReservedOn(mock(Timestamp.class));
        beneficiaryId.setReservedUntil(mock(Timestamp.class));

        BeneficiaryId beneficiaryId2 = new BeneficiaryId();
        beneficiaryId2.setBenRegId(BigInteger.valueOf(1L));
        beneficiaryId2.setBeneficiaryId(BigInteger.valueOf(1L));
        beneficiaryId2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        beneficiaryId2.setCreatedDate(mock(Timestamp.class));
        beneficiaryId2.setProvisioned(true);
        beneficiaryId2.setProvisionedBy("Provisioned By");
        beneficiaryId2.setProvisionedById(1);
        beneficiaryId2.setProvisionedOn(mock(Timestamp.class));
        beneficiaryId2.setReserved(true);
        beneficiaryId2.setReservedById(1);
        beneficiaryId2.setReservedByName("Reserved By Name");
        beneficiaryId2.setReservedForCountryId(1);
        beneficiaryId2.setReservedForCountryName("GB");
        beneficiaryId2.setReservedForDistrictId(1);
        beneficiaryId2.setReservedForDistrictName("Reserved For District Name");
        beneficiaryId2.setReservedForId(1);
        beneficiaryId2.setReservedForName("Reserved For Name");
        beneficiaryId2.setReservedForPSMapId(1);
        beneficiaryId2.setReservedForPSMapName("Reserved For PSMap Name");
        beneficiaryId2.setReservedForStateId(1);
        beneficiaryId2.setReservedForStateName("Reserved For State Name");
        beneficiaryId2.setReservedOn(mock(Timestamp.class));
        beneficiaryId2.setReservedUntil(mock(Timestamp.class));

        // Act and Assert
        assertNotEquals(beneficiaryId, beneficiaryId2);
    }

    /**
     * Method under test: {@link BeneficiaryId#equals(Object)}
     */
    @Test
    void testEquals7() {
        // Arrange
        BeneficiaryId beneficiaryId = new BeneficiaryId();
        beneficiaryId.setBenRegId(BigInteger.valueOf(1L));
        beneficiaryId.setBeneficiaryId(null);
        beneficiaryId.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        beneficiaryId.setCreatedDate(mock(Timestamp.class));
        beneficiaryId.setProvisioned(true);
        beneficiaryId.setProvisionedBy("Provisioned By");
        beneficiaryId.setProvisionedById(1);
        beneficiaryId.setProvisionedOn(mock(Timestamp.class));
        beneficiaryId.setReserved(true);
        beneficiaryId.setReservedById(1);
        beneficiaryId.setReservedByName("Reserved By Name");
        beneficiaryId.setReservedForCountryId(1);
        beneficiaryId.setReservedForCountryName("GB");
        beneficiaryId.setReservedForDistrictId(1);
        beneficiaryId.setReservedForDistrictName("Reserved For District Name");
        beneficiaryId.setReservedForId(1);
        beneficiaryId.setReservedForName("Reserved For Name");
        beneficiaryId.setReservedForPSMapId(1);
        beneficiaryId.setReservedForPSMapName("Reserved For PSMap Name");
        beneficiaryId.setReservedForStateId(1);
        beneficiaryId.setReservedForStateName("Reserved For State Name");
        beneficiaryId.setReservedOn(mock(Timestamp.class));
        beneficiaryId.setReservedUntil(mock(Timestamp.class));

        BeneficiaryId beneficiaryId2 = new BeneficiaryId();
        beneficiaryId2.setBenRegId(BigInteger.valueOf(1L));
        beneficiaryId2.setBeneficiaryId(BigInteger.valueOf(1L));
        beneficiaryId2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        beneficiaryId2.setCreatedDate(mock(Timestamp.class));
        beneficiaryId2.setProvisioned(true);
        beneficiaryId2.setProvisionedBy("Provisioned By");
        beneficiaryId2.setProvisionedById(1);
        beneficiaryId2.setProvisionedOn(mock(Timestamp.class));
        beneficiaryId2.setReserved(true);
        beneficiaryId2.setReservedById(1);
        beneficiaryId2.setReservedByName("Reserved By Name");
        beneficiaryId2.setReservedForCountryId(1);
        beneficiaryId2.setReservedForCountryName("GB");
        beneficiaryId2.setReservedForDistrictId(1);
        beneficiaryId2.setReservedForDistrictName("Reserved For District Name");
        beneficiaryId2.setReservedForId(1);
        beneficiaryId2.setReservedForName("Reserved For Name");
        beneficiaryId2.setReservedForPSMapId(1);
        beneficiaryId2.setReservedForPSMapName("Reserved For PSMap Name");
        beneficiaryId2.setReservedForStateId(1);
        beneficiaryId2.setReservedForStateName("Reserved For State Name");
        beneficiaryId2.setReservedOn(mock(Timestamp.class));
        beneficiaryId2.setReservedUntil(mock(Timestamp.class));

        // Act and Assert
        assertNotEquals(beneficiaryId, beneficiaryId2);
    }

    /**
     * Method under test: {@link BeneficiaryId#equals(Object)}
     */
    @Test
    void testEquals8() {
        // Arrange
        BeneficiaryId beneficiaryId = new BeneficiaryId();
        beneficiaryId.setBenRegId(BigInteger.valueOf(1L));
        beneficiaryId.setBeneficiaryId(BigInteger.valueOf(1L));
        beneficiaryId.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        beneficiaryId.setCreatedDate(mock(Timestamp.class));
        beneficiaryId.setProvisioned(false);
        beneficiaryId.setProvisionedBy("Provisioned By");
        beneficiaryId.setProvisionedById(1);
        beneficiaryId.setProvisionedOn(mock(Timestamp.class));
        beneficiaryId.setReserved(true);
        beneficiaryId.setReservedById(1);
        beneficiaryId.setReservedByName("Reserved By Name");
        beneficiaryId.setReservedForCountryId(1);
        beneficiaryId.setReservedForCountryName("GB");
        beneficiaryId.setReservedForDistrictId(1);
        beneficiaryId.setReservedForDistrictName("Reserved For District Name");
        beneficiaryId.setReservedForId(1);
        beneficiaryId.setReservedForName("Reserved For Name");
        beneficiaryId.setReservedForPSMapId(1);
        beneficiaryId.setReservedForPSMapName("Reserved For PSMap Name");
        beneficiaryId.setReservedForStateId(1);
        beneficiaryId.setReservedForStateName("Reserved For State Name");
        beneficiaryId.setReservedOn(mock(Timestamp.class));
        beneficiaryId.setReservedUntil(mock(Timestamp.class));

        BeneficiaryId beneficiaryId2 = new BeneficiaryId();
        beneficiaryId2.setBenRegId(BigInteger.valueOf(1L));
        beneficiaryId2.setBeneficiaryId(BigInteger.valueOf(1L));
        beneficiaryId2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        beneficiaryId2.setCreatedDate(mock(Timestamp.class));
        beneficiaryId2.setProvisioned(true);
        beneficiaryId2.setProvisionedBy("Provisioned By");
        beneficiaryId2.setProvisionedById(1);
        beneficiaryId2.setProvisionedOn(mock(Timestamp.class));
        beneficiaryId2.setReserved(true);
        beneficiaryId2.setReservedById(1);
        beneficiaryId2.setReservedByName("Reserved By Name");
        beneficiaryId2.setReservedForCountryId(1);
        beneficiaryId2.setReservedForCountryName("GB");
        beneficiaryId2.setReservedForDistrictId(1);
        beneficiaryId2.setReservedForDistrictName("Reserved For District Name");
        beneficiaryId2.setReservedForId(1);
        beneficiaryId2.setReservedForName("Reserved For Name");
        beneficiaryId2.setReservedForPSMapId(1);
        beneficiaryId2.setReservedForPSMapName("Reserved For PSMap Name");
        beneficiaryId2.setReservedForStateId(1);
        beneficiaryId2.setReservedForStateName("Reserved For State Name");
        beneficiaryId2.setReservedOn(mock(Timestamp.class));
        beneficiaryId2.setReservedUntil(mock(Timestamp.class));

        // Act and Assert
        assertNotEquals(beneficiaryId, beneficiaryId2);
    }

    /**
     * Method under test: {@link BeneficiaryId#equals(Object)}
     */
    @Test
    void testEquals9() {
        // Arrange
        BeneficiaryId beneficiaryId = new BeneficiaryId();
        beneficiaryId.setBenRegId(BigInteger.valueOf(1L));
        beneficiaryId.setBeneficiaryId(BigInteger.valueOf(1L));
        beneficiaryId.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        beneficiaryId.setCreatedDate(mock(Timestamp.class));
        beneficiaryId.setProvisioned(null);
        beneficiaryId.setProvisionedBy("Provisioned By");
        beneficiaryId.setProvisionedById(1);
        beneficiaryId.setProvisionedOn(mock(Timestamp.class));
        beneficiaryId.setReserved(true);
        beneficiaryId.setReservedById(1);
        beneficiaryId.setReservedByName("Reserved By Name");
        beneficiaryId.setReservedForCountryId(1);
        beneficiaryId.setReservedForCountryName("GB");
        beneficiaryId.setReservedForDistrictId(1);
        beneficiaryId.setReservedForDistrictName("Reserved For District Name");
        beneficiaryId.setReservedForId(1);
        beneficiaryId.setReservedForName("Reserved For Name");
        beneficiaryId.setReservedForPSMapId(1);
        beneficiaryId.setReservedForPSMapName("Reserved For PSMap Name");
        beneficiaryId.setReservedForStateId(1);
        beneficiaryId.setReservedForStateName("Reserved For State Name");
        beneficiaryId.setReservedOn(mock(Timestamp.class));
        beneficiaryId.setReservedUntil(mock(Timestamp.class));

        BeneficiaryId beneficiaryId2 = new BeneficiaryId();
        beneficiaryId2.setBenRegId(BigInteger.valueOf(1L));
        beneficiaryId2.setBeneficiaryId(BigInteger.valueOf(1L));
        beneficiaryId2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        beneficiaryId2.setCreatedDate(mock(Timestamp.class));
        beneficiaryId2.setProvisioned(true);
        beneficiaryId2.setProvisionedBy("Provisioned By");
        beneficiaryId2.setProvisionedById(1);
        beneficiaryId2.setProvisionedOn(mock(Timestamp.class));
        beneficiaryId2.setReserved(true);
        beneficiaryId2.setReservedById(1);
        beneficiaryId2.setReservedByName("Reserved By Name");
        beneficiaryId2.setReservedForCountryId(1);
        beneficiaryId2.setReservedForCountryName("GB");
        beneficiaryId2.setReservedForDistrictId(1);
        beneficiaryId2.setReservedForDistrictName("Reserved For District Name");
        beneficiaryId2.setReservedForId(1);
        beneficiaryId2.setReservedForName("Reserved For Name");
        beneficiaryId2.setReservedForPSMapId(1);
        beneficiaryId2.setReservedForPSMapName("Reserved For PSMap Name");
        beneficiaryId2.setReservedForStateId(1);
        beneficiaryId2.setReservedForStateName("Reserved For State Name");
        beneficiaryId2.setReservedOn(mock(Timestamp.class));
        beneficiaryId2.setReservedUntil(mock(Timestamp.class));

        // Act and Assert
        assertNotEquals(beneficiaryId, beneficiaryId2);
    }

    /**
     * Method under test: {@link BeneficiaryId#equals(Object)}
     */
    @Test
    void testEquals10() {
        // Arrange
        BeneficiaryId beneficiaryId = new BeneficiaryId();
        beneficiaryId.setBenRegId(BigInteger.valueOf(1L));
        beneficiaryId.setBeneficiaryId(BigInteger.valueOf(1L));
        beneficiaryId.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        beneficiaryId.setCreatedDate(mock(Timestamp.class));
        beneficiaryId.setProvisioned(true);
        beneficiaryId.setProvisionedBy(null);
        beneficiaryId.setProvisionedById(1);
        beneficiaryId.setProvisionedOn(mock(Timestamp.class));
        beneficiaryId.setReserved(true);
        beneficiaryId.setReservedById(1);
        beneficiaryId.setReservedByName("Reserved By Name");
        beneficiaryId.setReservedForCountryId(1);
        beneficiaryId.setReservedForCountryName("GB");
        beneficiaryId.setReservedForDistrictId(1);
        beneficiaryId.setReservedForDistrictName("Reserved For District Name");
        beneficiaryId.setReservedForId(1);
        beneficiaryId.setReservedForName("Reserved For Name");
        beneficiaryId.setReservedForPSMapId(1);
        beneficiaryId.setReservedForPSMapName("Reserved For PSMap Name");
        beneficiaryId.setReservedForStateId(1);
        beneficiaryId.setReservedForStateName("Reserved For State Name");
        beneficiaryId.setReservedOn(mock(Timestamp.class));
        beneficiaryId.setReservedUntil(mock(Timestamp.class));

        BeneficiaryId beneficiaryId2 = new BeneficiaryId();
        beneficiaryId2.setBenRegId(BigInteger.valueOf(1L));
        beneficiaryId2.setBeneficiaryId(BigInteger.valueOf(1L));
        beneficiaryId2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        beneficiaryId2.setCreatedDate(mock(Timestamp.class));
        beneficiaryId2.setProvisioned(true);
        beneficiaryId2.setProvisionedBy("Provisioned By");
        beneficiaryId2.setProvisionedById(1);
        beneficiaryId2.setProvisionedOn(mock(Timestamp.class));
        beneficiaryId2.setReserved(true);
        beneficiaryId2.setReservedById(1);
        beneficiaryId2.setReservedByName("Reserved By Name");
        beneficiaryId2.setReservedForCountryId(1);
        beneficiaryId2.setReservedForCountryName("GB");
        beneficiaryId2.setReservedForDistrictId(1);
        beneficiaryId2.setReservedForDistrictName("Reserved For District Name");
        beneficiaryId2.setReservedForId(1);
        beneficiaryId2.setReservedForName("Reserved For Name");
        beneficiaryId2.setReservedForPSMapId(1);
        beneficiaryId2.setReservedForPSMapName("Reserved For PSMap Name");
        beneficiaryId2.setReservedForStateId(1);
        beneficiaryId2.setReservedForStateName("Reserved For State Name");
        beneficiaryId2.setReservedOn(mock(Timestamp.class));
        beneficiaryId2.setReservedUntil(mock(Timestamp.class));

        // Act and Assert
        assertNotEquals(beneficiaryId, beneficiaryId2);
    }

    /**
     * Method under test: {@link BeneficiaryId#equals(Object)}
     */
    @Test
    void testEquals11() {
        // Arrange
        BeneficiaryId beneficiaryId = new BeneficiaryId();
        beneficiaryId.setBenRegId(BigInteger.valueOf(1L));
        beneficiaryId.setBeneficiaryId(BigInteger.valueOf(1L));
        beneficiaryId.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        beneficiaryId.setCreatedDate(mock(Timestamp.class));
        beneficiaryId.setProvisioned(true);
        beneficiaryId.setProvisionedBy("com.iemr.common.bengen.domain.BeneficiaryId");
        beneficiaryId.setProvisionedById(1);
        beneficiaryId.setProvisionedOn(mock(Timestamp.class));
        beneficiaryId.setReserved(true);
        beneficiaryId.setReservedById(1);
        beneficiaryId.setReservedByName("Reserved By Name");
        beneficiaryId.setReservedForCountryId(1);
        beneficiaryId.setReservedForCountryName("GB");
        beneficiaryId.setReservedForDistrictId(1);
        beneficiaryId.setReservedForDistrictName("Reserved For District Name");
        beneficiaryId.setReservedForId(1);
        beneficiaryId.setReservedForName("Reserved For Name");
        beneficiaryId.setReservedForPSMapId(1);
        beneficiaryId.setReservedForPSMapName("Reserved For PSMap Name");
        beneficiaryId.setReservedForStateId(1);
        beneficiaryId.setReservedForStateName("Reserved For State Name");
        beneficiaryId.setReservedOn(mock(Timestamp.class));
        beneficiaryId.setReservedUntil(mock(Timestamp.class));

        BeneficiaryId beneficiaryId2 = new BeneficiaryId();
        beneficiaryId2.setBenRegId(BigInteger.valueOf(1L));
        beneficiaryId2.setBeneficiaryId(BigInteger.valueOf(1L));
        beneficiaryId2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        beneficiaryId2.setCreatedDate(mock(Timestamp.class));
        beneficiaryId2.setProvisioned(true);
        beneficiaryId2.setProvisionedBy("Provisioned By");
        beneficiaryId2.setProvisionedById(1);
        beneficiaryId2.setProvisionedOn(mock(Timestamp.class));
        beneficiaryId2.setReserved(true);
        beneficiaryId2.setReservedById(1);
        beneficiaryId2.setReservedByName("Reserved By Name");
        beneficiaryId2.setReservedForCountryId(1);
        beneficiaryId2.setReservedForCountryName("GB");
        beneficiaryId2.setReservedForDistrictId(1);
        beneficiaryId2.setReservedForDistrictName("Reserved For District Name");
        beneficiaryId2.setReservedForId(1);
        beneficiaryId2.setReservedForName("Reserved For Name");
        beneficiaryId2.setReservedForPSMapId(1);
        beneficiaryId2.setReservedForPSMapName("Reserved For PSMap Name");
        beneficiaryId2.setReservedForStateId(1);
        beneficiaryId2.setReservedForStateName("Reserved For State Name");
        beneficiaryId2.setReservedOn(mock(Timestamp.class));
        beneficiaryId2.setReservedUntil(mock(Timestamp.class));

        // Act and Assert
        assertNotEquals(beneficiaryId, beneficiaryId2);
    }

    /**
     * Method under test: {@link BeneficiaryId#equals(Object)}
     */
    @Test
    void testEquals12() {
        // Arrange
        BeneficiaryId beneficiaryId = new BeneficiaryId();
        beneficiaryId.setBenRegId(BigInteger.valueOf(1L));
        beneficiaryId.setBeneficiaryId(BigInteger.valueOf(1L));
        beneficiaryId.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        beneficiaryId.setCreatedDate(mock(Timestamp.class));
        beneficiaryId.setProvisioned(true);
        beneficiaryId.setProvisionedBy("Provisioned By");
        beneficiaryId.setProvisionedById(2);
        beneficiaryId.setProvisionedOn(mock(Timestamp.class));
        beneficiaryId.setReserved(true);
        beneficiaryId.setReservedById(1);
        beneficiaryId.setReservedByName("Reserved By Name");
        beneficiaryId.setReservedForCountryId(1);
        beneficiaryId.setReservedForCountryName("GB");
        beneficiaryId.setReservedForDistrictId(1);
        beneficiaryId.setReservedForDistrictName("Reserved For District Name");
        beneficiaryId.setReservedForId(1);
        beneficiaryId.setReservedForName("Reserved For Name");
        beneficiaryId.setReservedForPSMapId(1);
        beneficiaryId.setReservedForPSMapName("Reserved For PSMap Name");
        beneficiaryId.setReservedForStateId(1);
        beneficiaryId.setReservedForStateName("Reserved For State Name");
        beneficiaryId.setReservedOn(mock(Timestamp.class));
        beneficiaryId.setReservedUntil(mock(Timestamp.class));

        BeneficiaryId beneficiaryId2 = new BeneficiaryId();
        beneficiaryId2.setBenRegId(BigInteger.valueOf(1L));
        beneficiaryId2.setBeneficiaryId(BigInteger.valueOf(1L));
        beneficiaryId2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        beneficiaryId2.setCreatedDate(mock(Timestamp.class));
        beneficiaryId2.setProvisioned(true);
        beneficiaryId2.setProvisionedBy("Provisioned By");
        beneficiaryId2.setProvisionedById(1);
        beneficiaryId2.setProvisionedOn(mock(Timestamp.class));
        beneficiaryId2.setReserved(true);
        beneficiaryId2.setReservedById(1);
        beneficiaryId2.setReservedByName("Reserved By Name");
        beneficiaryId2.setReservedForCountryId(1);
        beneficiaryId2.setReservedForCountryName("GB");
        beneficiaryId2.setReservedForDistrictId(1);
        beneficiaryId2.setReservedForDistrictName("Reserved For District Name");
        beneficiaryId2.setReservedForId(1);
        beneficiaryId2.setReservedForName("Reserved For Name");
        beneficiaryId2.setReservedForPSMapId(1);
        beneficiaryId2.setReservedForPSMapName("Reserved For PSMap Name");
        beneficiaryId2.setReservedForStateId(1);
        beneficiaryId2.setReservedForStateName("Reserved For State Name");
        beneficiaryId2.setReservedOn(mock(Timestamp.class));
        beneficiaryId2.setReservedUntil(mock(Timestamp.class));

        // Act and Assert
        assertNotEquals(beneficiaryId, beneficiaryId2);
    }

    /**
     * Method under test: {@link BeneficiaryId#equals(Object)}
     */
    @Test
    void testEquals13() {
        // Arrange
        BeneficiaryId beneficiaryId = new BeneficiaryId();
        beneficiaryId.setBenRegId(BigInteger.valueOf(1L));
        beneficiaryId.setBeneficiaryId(BigInteger.valueOf(1L));
        beneficiaryId.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        beneficiaryId.setCreatedDate(mock(Timestamp.class));
        beneficiaryId.setProvisioned(true);
        beneficiaryId.setProvisionedBy("Provisioned By");
        beneficiaryId.setProvisionedById(null);
        beneficiaryId.setProvisionedOn(mock(Timestamp.class));
        beneficiaryId.setReserved(true);
        beneficiaryId.setReservedById(1);
        beneficiaryId.setReservedByName("Reserved By Name");
        beneficiaryId.setReservedForCountryId(1);
        beneficiaryId.setReservedForCountryName("GB");
        beneficiaryId.setReservedForDistrictId(1);
        beneficiaryId.setReservedForDistrictName("Reserved For District Name");
        beneficiaryId.setReservedForId(1);
        beneficiaryId.setReservedForName("Reserved For Name");
        beneficiaryId.setReservedForPSMapId(1);
        beneficiaryId.setReservedForPSMapName("Reserved For PSMap Name");
        beneficiaryId.setReservedForStateId(1);
        beneficiaryId.setReservedForStateName("Reserved For State Name");
        beneficiaryId.setReservedOn(mock(Timestamp.class));
        beneficiaryId.setReservedUntil(mock(Timestamp.class));

        BeneficiaryId beneficiaryId2 = new BeneficiaryId();
        beneficiaryId2.setBenRegId(BigInteger.valueOf(1L));
        beneficiaryId2.setBeneficiaryId(BigInteger.valueOf(1L));
        beneficiaryId2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        beneficiaryId2.setCreatedDate(mock(Timestamp.class));
        beneficiaryId2.setProvisioned(true);
        beneficiaryId2.setProvisionedBy("Provisioned By");
        beneficiaryId2.setProvisionedById(1);
        beneficiaryId2.setProvisionedOn(mock(Timestamp.class));
        beneficiaryId2.setReserved(true);
        beneficiaryId2.setReservedById(1);
        beneficiaryId2.setReservedByName("Reserved By Name");
        beneficiaryId2.setReservedForCountryId(1);
        beneficiaryId2.setReservedForCountryName("GB");
        beneficiaryId2.setReservedForDistrictId(1);
        beneficiaryId2.setReservedForDistrictName("Reserved For District Name");
        beneficiaryId2.setReservedForId(1);
        beneficiaryId2.setReservedForName("Reserved For Name");
        beneficiaryId2.setReservedForPSMapId(1);
        beneficiaryId2.setReservedForPSMapName("Reserved For PSMap Name");
        beneficiaryId2.setReservedForStateId(1);
        beneficiaryId2.setReservedForStateName("Reserved For State Name");
        beneficiaryId2.setReservedOn(mock(Timestamp.class));
        beneficiaryId2.setReservedUntil(mock(Timestamp.class));

        // Act and Assert
        assertNotEquals(beneficiaryId, beneficiaryId2);
    }

    /**
     * Method under test: {@link BeneficiaryId#equals(Object)}
     */
    @Test
    void testEquals14() {
        // Arrange
        BeneficiaryId beneficiaryId = new BeneficiaryId();
        beneficiaryId.setBenRegId(BigInteger.valueOf(1L));
        beneficiaryId.setBeneficiaryId(BigInteger.valueOf(1L));
        beneficiaryId.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        beneficiaryId.setCreatedDate(mock(Timestamp.class));
        beneficiaryId.setProvisioned(true);
        beneficiaryId.setProvisionedBy("Provisioned By");
        beneficiaryId.setProvisionedById(1);
        beneficiaryId.setProvisionedOn(null);
        beneficiaryId.setReserved(true);
        beneficiaryId.setReservedById(1);
        beneficiaryId.setReservedByName("Reserved By Name");
        beneficiaryId.setReservedForCountryId(1);
        beneficiaryId.setReservedForCountryName("GB");
        beneficiaryId.setReservedForDistrictId(1);
        beneficiaryId.setReservedForDistrictName("Reserved For District Name");
        beneficiaryId.setReservedForId(1);
        beneficiaryId.setReservedForName("Reserved For Name");
        beneficiaryId.setReservedForPSMapId(1);
        beneficiaryId.setReservedForPSMapName("Reserved For PSMap Name");
        beneficiaryId.setReservedForStateId(1);
        beneficiaryId.setReservedForStateName("Reserved For State Name");
        beneficiaryId.setReservedOn(mock(Timestamp.class));
        beneficiaryId.setReservedUntil(mock(Timestamp.class));

        BeneficiaryId beneficiaryId2 = new BeneficiaryId();
        beneficiaryId2.setBenRegId(BigInteger.valueOf(1L));
        beneficiaryId2.setBeneficiaryId(BigInteger.valueOf(1L));
        beneficiaryId2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        beneficiaryId2.setCreatedDate(mock(Timestamp.class));
        beneficiaryId2.setProvisioned(true);
        beneficiaryId2.setProvisionedBy("Provisioned By");
        beneficiaryId2.setProvisionedById(1);
        beneficiaryId2.setProvisionedOn(mock(Timestamp.class));
        beneficiaryId2.setReserved(true);
        beneficiaryId2.setReservedById(1);
        beneficiaryId2.setReservedByName("Reserved By Name");
        beneficiaryId2.setReservedForCountryId(1);
        beneficiaryId2.setReservedForCountryName("GB");
        beneficiaryId2.setReservedForDistrictId(1);
        beneficiaryId2.setReservedForDistrictName("Reserved For District Name");
        beneficiaryId2.setReservedForId(1);
        beneficiaryId2.setReservedForName("Reserved For Name");
        beneficiaryId2.setReservedForPSMapId(1);
        beneficiaryId2.setReservedForPSMapName("Reserved For PSMap Name");
        beneficiaryId2.setReservedForStateId(1);
        beneficiaryId2.setReservedForStateName("Reserved For State Name");
        beneficiaryId2.setReservedOn(mock(Timestamp.class));
        beneficiaryId2.setReservedUntil(mock(Timestamp.class));

        // Act and Assert
        assertNotEquals(beneficiaryId, beneficiaryId2);
    }

    /**
     * Method under test: {@link BeneficiaryId#equals(Object)}
     */
    @Test
    void testEquals15() {
        // Arrange
        BeneficiaryId beneficiaryId = new BeneficiaryId();
        beneficiaryId.setBenRegId(BigInteger.valueOf(1L));
        beneficiaryId.setBeneficiaryId(BigInteger.valueOf(1L));
        beneficiaryId.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        beneficiaryId.setCreatedDate(mock(Timestamp.class));
        beneficiaryId.setProvisioned(true);
        beneficiaryId.setProvisionedBy("Provisioned By");
        beneficiaryId.setProvisionedById(1);
        beneficiaryId.setProvisionedOn(mock(Timestamp.class));
        beneficiaryId.setReserved(false);
        beneficiaryId.setReservedById(1);
        beneficiaryId.setReservedByName("Reserved By Name");
        beneficiaryId.setReservedForCountryId(1);
        beneficiaryId.setReservedForCountryName("GB");
        beneficiaryId.setReservedForDistrictId(1);
        beneficiaryId.setReservedForDistrictName("Reserved For District Name");
        beneficiaryId.setReservedForId(1);
        beneficiaryId.setReservedForName("Reserved For Name");
        beneficiaryId.setReservedForPSMapId(1);
        beneficiaryId.setReservedForPSMapName("Reserved For PSMap Name");
        beneficiaryId.setReservedForStateId(1);
        beneficiaryId.setReservedForStateName("Reserved For State Name");
        beneficiaryId.setReservedOn(mock(Timestamp.class));
        beneficiaryId.setReservedUntil(mock(Timestamp.class));

        BeneficiaryId beneficiaryId2 = new BeneficiaryId();
        beneficiaryId2.setBenRegId(BigInteger.valueOf(1L));
        beneficiaryId2.setBeneficiaryId(BigInteger.valueOf(1L));
        beneficiaryId2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        beneficiaryId2.setCreatedDate(mock(Timestamp.class));
        beneficiaryId2.setProvisioned(true);
        beneficiaryId2.setProvisionedBy("Provisioned By");
        beneficiaryId2.setProvisionedById(1);
        beneficiaryId2.setProvisionedOn(mock(Timestamp.class));
        beneficiaryId2.setReserved(true);
        beneficiaryId2.setReservedById(1);
        beneficiaryId2.setReservedByName("Reserved By Name");
        beneficiaryId2.setReservedForCountryId(1);
        beneficiaryId2.setReservedForCountryName("GB");
        beneficiaryId2.setReservedForDistrictId(1);
        beneficiaryId2.setReservedForDistrictName("Reserved For District Name");
        beneficiaryId2.setReservedForId(1);
        beneficiaryId2.setReservedForName("Reserved For Name");
        beneficiaryId2.setReservedForPSMapId(1);
        beneficiaryId2.setReservedForPSMapName("Reserved For PSMap Name");
        beneficiaryId2.setReservedForStateId(1);
        beneficiaryId2.setReservedForStateName("Reserved For State Name");
        beneficiaryId2.setReservedOn(mock(Timestamp.class));
        beneficiaryId2.setReservedUntil(mock(Timestamp.class));

        // Act and Assert
        assertNotEquals(beneficiaryId, beneficiaryId2);
    }

    /**
     * Method under test: {@link BeneficiaryId#equals(Object)}
     */
    @Test
    void testEquals16() {
        // Arrange
        BeneficiaryId beneficiaryId = new BeneficiaryId();
        beneficiaryId.setBenRegId(BigInteger.valueOf(1L));
        beneficiaryId.setBeneficiaryId(BigInteger.valueOf(1L));
        beneficiaryId.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        beneficiaryId.setCreatedDate(mock(Timestamp.class));
        beneficiaryId.setProvisioned(true);
        beneficiaryId.setProvisionedBy("Provisioned By");
        beneficiaryId.setProvisionedById(1);
        beneficiaryId.setProvisionedOn(mock(Timestamp.class));
        beneficiaryId.setReserved(null);
        beneficiaryId.setReservedById(1);
        beneficiaryId.setReservedByName("Reserved By Name");
        beneficiaryId.setReservedForCountryId(1);
        beneficiaryId.setReservedForCountryName("GB");
        beneficiaryId.setReservedForDistrictId(1);
        beneficiaryId.setReservedForDistrictName("Reserved For District Name");
        beneficiaryId.setReservedForId(1);
        beneficiaryId.setReservedForName("Reserved For Name");
        beneficiaryId.setReservedForPSMapId(1);
        beneficiaryId.setReservedForPSMapName("Reserved For PSMap Name");
        beneficiaryId.setReservedForStateId(1);
        beneficiaryId.setReservedForStateName("Reserved For State Name");
        beneficiaryId.setReservedOn(mock(Timestamp.class));
        beneficiaryId.setReservedUntil(mock(Timestamp.class));

        BeneficiaryId beneficiaryId2 = new BeneficiaryId();
        beneficiaryId2.setBenRegId(BigInteger.valueOf(1L));
        beneficiaryId2.setBeneficiaryId(BigInteger.valueOf(1L));
        beneficiaryId2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        beneficiaryId2.setCreatedDate(mock(Timestamp.class));
        beneficiaryId2.setProvisioned(true);
        beneficiaryId2.setProvisionedBy("Provisioned By");
        beneficiaryId2.setProvisionedById(1);
        beneficiaryId2.setProvisionedOn(mock(Timestamp.class));
        beneficiaryId2.setReserved(true);
        beneficiaryId2.setReservedById(1);
        beneficiaryId2.setReservedByName("Reserved By Name");
        beneficiaryId2.setReservedForCountryId(1);
        beneficiaryId2.setReservedForCountryName("GB");
        beneficiaryId2.setReservedForDistrictId(1);
        beneficiaryId2.setReservedForDistrictName("Reserved For District Name");
        beneficiaryId2.setReservedForId(1);
        beneficiaryId2.setReservedForName("Reserved For Name");
        beneficiaryId2.setReservedForPSMapId(1);
        beneficiaryId2.setReservedForPSMapName("Reserved For PSMap Name");
        beneficiaryId2.setReservedForStateId(1);
        beneficiaryId2.setReservedForStateName("Reserved For State Name");
        beneficiaryId2.setReservedOn(mock(Timestamp.class));
        beneficiaryId2.setReservedUntil(mock(Timestamp.class));

        // Act and Assert
        assertNotEquals(beneficiaryId, beneficiaryId2);
    }

    /**
     * Method under test: {@link BeneficiaryId#equals(Object)}
     */
    @Test
    void testEquals17() {
        // Arrange
        BeneficiaryId beneficiaryId = new BeneficiaryId();
        beneficiaryId.setBenRegId(BigInteger.valueOf(1L));
        beneficiaryId.setBeneficiaryId(BigInteger.valueOf(1L));
        beneficiaryId.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        beneficiaryId.setCreatedDate(mock(Timestamp.class));
        beneficiaryId.setProvisioned(true);
        beneficiaryId.setProvisionedBy("Provisioned By");
        beneficiaryId.setProvisionedById(1);
        beneficiaryId.setProvisionedOn(mock(Timestamp.class));
        beneficiaryId.setReserved(true);
        beneficiaryId.setReservedById(2);
        beneficiaryId.setReservedByName("Reserved By Name");
        beneficiaryId.setReservedForCountryId(1);
        beneficiaryId.setReservedForCountryName("GB");
        beneficiaryId.setReservedForDistrictId(1);
        beneficiaryId.setReservedForDistrictName("Reserved For District Name");
        beneficiaryId.setReservedForId(1);
        beneficiaryId.setReservedForName("Reserved For Name");
        beneficiaryId.setReservedForPSMapId(1);
        beneficiaryId.setReservedForPSMapName("Reserved For PSMap Name");
        beneficiaryId.setReservedForStateId(1);
        beneficiaryId.setReservedForStateName("Reserved For State Name");
        beneficiaryId.setReservedOn(mock(Timestamp.class));
        beneficiaryId.setReservedUntil(mock(Timestamp.class));

        BeneficiaryId beneficiaryId2 = new BeneficiaryId();
        beneficiaryId2.setBenRegId(BigInteger.valueOf(1L));
        beneficiaryId2.setBeneficiaryId(BigInteger.valueOf(1L));
        beneficiaryId2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        beneficiaryId2.setCreatedDate(mock(Timestamp.class));
        beneficiaryId2.setProvisioned(true);
        beneficiaryId2.setProvisionedBy("Provisioned By");
        beneficiaryId2.setProvisionedById(1);
        beneficiaryId2.setProvisionedOn(mock(Timestamp.class));
        beneficiaryId2.setReserved(true);
        beneficiaryId2.setReservedById(1);
        beneficiaryId2.setReservedByName("Reserved By Name");
        beneficiaryId2.setReservedForCountryId(1);
        beneficiaryId2.setReservedForCountryName("GB");
        beneficiaryId2.setReservedForDistrictId(1);
        beneficiaryId2.setReservedForDistrictName("Reserved For District Name");
        beneficiaryId2.setReservedForId(1);
        beneficiaryId2.setReservedForName("Reserved For Name");
        beneficiaryId2.setReservedForPSMapId(1);
        beneficiaryId2.setReservedForPSMapName("Reserved For PSMap Name");
        beneficiaryId2.setReservedForStateId(1);
        beneficiaryId2.setReservedForStateName("Reserved For State Name");
        beneficiaryId2.setReservedOn(mock(Timestamp.class));
        beneficiaryId2.setReservedUntil(mock(Timestamp.class));

        // Act and Assert
        assertNotEquals(beneficiaryId, beneficiaryId2);
    }

    /**
     * Method under test: {@link BeneficiaryId#equals(Object)}
     */
    @Test
    void testEquals18() {
        // Arrange
        BeneficiaryId beneficiaryId = new BeneficiaryId();
        beneficiaryId.setBenRegId(BigInteger.valueOf(1L));
        beneficiaryId.setBeneficiaryId(BigInteger.valueOf(1L));
        beneficiaryId.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        beneficiaryId.setCreatedDate(mock(Timestamp.class));
        beneficiaryId.setProvisioned(true);
        beneficiaryId.setProvisionedBy("Provisioned By");
        beneficiaryId.setProvisionedById(1);
        beneficiaryId.setProvisionedOn(mock(Timestamp.class));
        beneficiaryId.setReserved(true);
        beneficiaryId.setReservedById(null);
        beneficiaryId.setReservedByName("Reserved By Name");
        beneficiaryId.setReservedForCountryId(1);
        beneficiaryId.setReservedForCountryName("GB");
        beneficiaryId.setReservedForDistrictId(1);
        beneficiaryId.setReservedForDistrictName("Reserved For District Name");
        beneficiaryId.setReservedForId(1);
        beneficiaryId.setReservedForName("Reserved For Name");
        beneficiaryId.setReservedForPSMapId(1);
        beneficiaryId.setReservedForPSMapName("Reserved For PSMap Name");
        beneficiaryId.setReservedForStateId(1);
        beneficiaryId.setReservedForStateName("Reserved For State Name");
        beneficiaryId.setReservedOn(mock(Timestamp.class));
        beneficiaryId.setReservedUntil(mock(Timestamp.class));

        BeneficiaryId beneficiaryId2 = new BeneficiaryId();
        beneficiaryId2.setBenRegId(BigInteger.valueOf(1L));
        beneficiaryId2.setBeneficiaryId(BigInteger.valueOf(1L));
        beneficiaryId2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        beneficiaryId2.setCreatedDate(mock(Timestamp.class));
        beneficiaryId2.setProvisioned(true);
        beneficiaryId2.setProvisionedBy("Provisioned By");
        beneficiaryId2.setProvisionedById(1);
        beneficiaryId2.setProvisionedOn(mock(Timestamp.class));
        beneficiaryId2.setReserved(true);
        beneficiaryId2.setReservedById(1);
        beneficiaryId2.setReservedByName("Reserved By Name");
        beneficiaryId2.setReservedForCountryId(1);
        beneficiaryId2.setReservedForCountryName("GB");
        beneficiaryId2.setReservedForDistrictId(1);
        beneficiaryId2.setReservedForDistrictName("Reserved For District Name");
        beneficiaryId2.setReservedForId(1);
        beneficiaryId2.setReservedForName("Reserved For Name");
        beneficiaryId2.setReservedForPSMapId(1);
        beneficiaryId2.setReservedForPSMapName("Reserved For PSMap Name");
        beneficiaryId2.setReservedForStateId(1);
        beneficiaryId2.setReservedForStateName("Reserved For State Name");
        beneficiaryId2.setReservedOn(mock(Timestamp.class));
        beneficiaryId2.setReservedUntil(mock(Timestamp.class));

        // Act and Assert
        assertNotEquals(beneficiaryId, beneficiaryId2);
    }

    /**
     * Method under test: {@link BeneficiaryId#equals(Object)}
     */
    @Test
    void testEquals19() {
        // Arrange
        BeneficiaryId beneficiaryId = new BeneficiaryId();
        beneficiaryId.setBenRegId(BigInteger.valueOf(1L));
        beneficiaryId.setBeneficiaryId(BigInteger.valueOf(1L));
        beneficiaryId.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        beneficiaryId.setCreatedDate(mock(Timestamp.class));
        beneficiaryId.setProvisioned(true);
        beneficiaryId.setProvisionedBy("Provisioned By");
        beneficiaryId.setProvisionedById(1);
        beneficiaryId.setProvisionedOn(mock(Timestamp.class));
        beneficiaryId.setReserved(true);
        beneficiaryId.setReservedById(1);
        beneficiaryId.setReservedByName("Reserved By Name");
        beneficiaryId.setReservedForCountryId(2);
        beneficiaryId.setReservedForCountryName("GB");
        beneficiaryId.setReservedForDistrictId(1);
        beneficiaryId.setReservedForDistrictName("Reserved For District Name");
        beneficiaryId.setReservedForId(1);
        beneficiaryId.setReservedForName("Reserved For Name");
        beneficiaryId.setReservedForPSMapId(1);
        beneficiaryId.setReservedForPSMapName("Reserved For PSMap Name");
        beneficiaryId.setReservedForStateId(1);
        beneficiaryId.setReservedForStateName("Reserved For State Name");
        beneficiaryId.setReservedOn(mock(Timestamp.class));
        beneficiaryId.setReservedUntil(mock(Timestamp.class));

        BeneficiaryId beneficiaryId2 = new BeneficiaryId();
        beneficiaryId2.setBenRegId(BigInteger.valueOf(1L));
        beneficiaryId2.setBeneficiaryId(BigInteger.valueOf(1L));
        beneficiaryId2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        beneficiaryId2.setCreatedDate(mock(Timestamp.class));
        beneficiaryId2.setProvisioned(true);
        beneficiaryId2.setProvisionedBy("Provisioned By");
        beneficiaryId2.setProvisionedById(1);
        beneficiaryId2.setProvisionedOn(mock(Timestamp.class));
        beneficiaryId2.setReserved(true);
        beneficiaryId2.setReservedById(1);
        beneficiaryId2.setReservedByName("Reserved By Name");
        beneficiaryId2.setReservedForCountryId(1);
        beneficiaryId2.setReservedForCountryName("GB");
        beneficiaryId2.setReservedForDistrictId(1);
        beneficiaryId2.setReservedForDistrictName("Reserved For District Name");
        beneficiaryId2.setReservedForId(1);
        beneficiaryId2.setReservedForName("Reserved For Name");
        beneficiaryId2.setReservedForPSMapId(1);
        beneficiaryId2.setReservedForPSMapName("Reserved For PSMap Name");
        beneficiaryId2.setReservedForStateId(1);
        beneficiaryId2.setReservedForStateName("Reserved For State Name");
        beneficiaryId2.setReservedOn(mock(Timestamp.class));
        beneficiaryId2.setReservedUntil(mock(Timestamp.class));

        // Act and Assert
        assertNotEquals(beneficiaryId, beneficiaryId2);
    }

    /**
     * Method under test: {@link BeneficiaryId#equals(Object)}
     */
    @Test
    void testEquals20() {
        // Arrange
        BeneficiaryId beneficiaryId = new BeneficiaryId();
        beneficiaryId.setBenRegId(BigInteger.valueOf(1L));
        beneficiaryId.setBeneficiaryId(BigInteger.valueOf(1L));
        beneficiaryId.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        beneficiaryId.setCreatedDate(mock(Timestamp.class));
        beneficiaryId.setProvisioned(true);
        beneficiaryId.setProvisionedBy("Provisioned By");
        beneficiaryId.setProvisionedById(1);
        beneficiaryId.setProvisionedOn(mock(Timestamp.class));
        beneficiaryId.setReserved(true);
        beneficiaryId.setReservedById(1);
        beneficiaryId.setReservedByName("Reserved By Name");
        beneficiaryId.setReservedForCountryId(null);
        beneficiaryId.setReservedForCountryName("GB");
        beneficiaryId.setReservedForDistrictId(1);
        beneficiaryId.setReservedForDistrictName("Reserved For District Name");
        beneficiaryId.setReservedForId(1);
        beneficiaryId.setReservedForName("Reserved For Name");
        beneficiaryId.setReservedForPSMapId(1);
        beneficiaryId.setReservedForPSMapName("Reserved For PSMap Name");
        beneficiaryId.setReservedForStateId(1);
        beneficiaryId.setReservedForStateName("Reserved For State Name");
        beneficiaryId.setReservedOn(mock(Timestamp.class));
        beneficiaryId.setReservedUntil(mock(Timestamp.class));

        BeneficiaryId beneficiaryId2 = new BeneficiaryId();
        beneficiaryId2.setBenRegId(BigInteger.valueOf(1L));
        beneficiaryId2.setBeneficiaryId(BigInteger.valueOf(1L));
        beneficiaryId2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        beneficiaryId2.setCreatedDate(mock(Timestamp.class));
        beneficiaryId2.setProvisioned(true);
        beneficiaryId2.setProvisionedBy("Provisioned By");
        beneficiaryId2.setProvisionedById(1);
        beneficiaryId2.setProvisionedOn(mock(Timestamp.class));
        beneficiaryId2.setReserved(true);
        beneficiaryId2.setReservedById(1);
        beneficiaryId2.setReservedByName("Reserved By Name");
        beneficiaryId2.setReservedForCountryId(1);
        beneficiaryId2.setReservedForCountryName("GB");
        beneficiaryId2.setReservedForDistrictId(1);
        beneficiaryId2.setReservedForDistrictName("Reserved For District Name");
        beneficiaryId2.setReservedForId(1);
        beneficiaryId2.setReservedForName("Reserved For Name");
        beneficiaryId2.setReservedForPSMapId(1);
        beneficiaryId2.setReservedForPSMapName("Reserved For PSMap Name");
        beneficiaryId2.setReservedForStateId(1);
        beneficiaryId2.setReservedForStateName("Reserved For State Name");
        beneficiaryId2.setReservedOn(mock(Timestamp.class));
        beneficiaryId2.setReservedUntil(mock(Timestamp.class));

        // Act and Assert
        assertNotEquals(beneficiaryId, beneficiaryId2);
    }

    /**
     * Method under test: {@link BeneficiaryId#equals(Object)}
     */
    @Test
    void testEquals21() {
        // Arrange
        BeneficiaryId beneficiaryId = new BeneficiaryId();
        beneficiaryId.setBenRegId(BigInteger.valueOf(1L));
        beneficiaryId.setBeneficiaryId(BigInteger.valueOf(1L));
        beneficiaryId.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        beneficiaryId.setCreatedDate(mock(Timestamp.class));
        beneficiaryId.setProvisioned(true);
        beneficiaryId.setProvisionedBy("Provisioned By");
        beneficiaryId.setProvisionedById(1);
        beneficiaryId.setProvisionedOn(mock(Timestamp.class));
        beneficiaryId.setReserved(true);
        beneficiaryId.setReservedById(1);
        beneficiaryId.setReservedByName("Reserved By Name");
        beneficiaryId.setReservedForCountryId(1);
        beneficiaryId.setReservedForCountryName("GB");
        beneficiaryId.setReservedForDistrictId(2);
        beneficiaryId.setReservedForDistrictName("Reserved For District Name");
        beneficiaryId.setReservedForId(1);
        beneficiaryId.setReservedForName("Reserved For Name");
        beneficiaryId.setReservedForPSMapId(1);
        beneficiaryId.setReservedForPSMapName("Reserved For PSMap Name");
        beneficiaryId.setReservedForStateId(1);
        beneficiaryId.setReservedForStateName("Reserved For State Name");
        beneficiaryId.setReservedOn(mock(Timestamp.class));
        beneficiaryId.setReservedUntil(mock(Timestamp.class));

        BeneficiaryId beneficiaryId2 = new BeneficiaryId();
        beneficiaryId2.setBenRegId(BigInteger.valueOf(1L));
        beneficiaryId2.setBeneficiaryId(BigInteger.valueOf(1L));
        beneficiaryId2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        beneficiaryId2.setCreatedDate(mock(Timestamp.class));
        beneficiaryId2.setProvisioned(true);
        beneficiaryId2.setProvisionedBy("Provisioned By");
        beneficiaryId2.setProvisionedById(1);
        beneficiaryId2.setProvisionedOn(mock(Timestamp.class));
        beneficiaryId2.setReserved(true);
        beneficiaryId2.setReservedById(1);
        beneficiaryId2.setReservedByName("Reserved By Name");
        beneficiaryId2.setReservedForCountryId(1);
        beneficiaryId2.setReservedForCountryName("GB");
        beneficiaryId2.setReservedForDistrictId(1);
        beneficiaryId2.setReservedForDistrictName("Reserved For District Name");
        beneficiaryId2.setReservedForId(1);
        beneficiaryId2.setReservedForName("Reserved For Name");
        beneficiaryId2.setReservedForPSMapId(1);
        beneficiaryId2.setReservedForPSMapName("Reserved For PSMap Name");
        beneficiaryId2.setReservedForStateId(1);
        beneficiaryId2.setReservedForStateName("Reserved For State Name");
        beneficiaryId2.setReservedOn(mock(Timestamp.class));
        beneficiaryId2.setReservedUntil(mock(Timestamp.class));

        // Act and Assert
        assertNotEquals(beneficiaryId, beneficiaryId2);
    }

    /**
     * Method under test: {@link BeneficiaryId#equals(Object)}
     */
    @Test
    void testEquals22() {
        // Arrange
        BeneficiaryId beneficiaryId = new BeneficiaryId();
        beneficiaryId.setBenRegId(BigInteger.valueOf(1L));
        beneficiaryId.setBeneficiaryId(BigInteger.valueOf(1L));
        beneficiaryId.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        beneficiaryId.setCreatedDate(mock(Timestamp.class));
        beneficiaryId.setProvisioned(true);
        beneficiaryId.setProvisionedBy("Provisioned By");
        beneficiaryId.setProvisionedById(1);
        beneficiaryId.setProvisionedOn(mock(Timestamp.class));
        beneficiaryId.setReserved(true);
        beneficiaryId.setReservedById(1);
        beneficiaryId.setReservedByName("Reserved By Name");
        beneficiaryId.setReservedForCountryId(1);
        beneficiaryId.setReservedForCountryName("GB");
        beneficiaryId.setReservedForDistrictId(null);
        beneficiaryId.setReservedForDistrictName("Reserved For District Name");
        beneficiaryId.setReservedForId(1);
        beneficiaryId.setReservedForName("Reserved For Name");
        beneficiaryId.setReservedForPSMapId(1);
        beneficiaryId.setReservedForPSMapName("Reserved For PSMap Name");
        beneficiaryId.setReservedForStateId(1);
        beneficiaryId.setReservedForStateName("Reserved For State Name");
        beneficiaryId.setReservedOn(mock(Timestamp.class));
        beneficiaryId.setReservedUntil(mock(Timestamp.class));

        BeneficiaryId beneficiaryId2 = new BeneficiaryId();
        beneficiaryId2.setBenRegId(BigInteger.valueOf(1L));
        beneficiaryId2.setBeneficiaryId(BigInteger.valueOf(1L));
        beneficiaryId2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        beneficiaryId2.setCreatedDate(mock(Timestamp.class));
        beneficiaryId2.setProvisioned(true);
        beneficiaryId2.setProvisionedBy("Provisioned By");
        beneficiaryId2.setProvisionedById(1);
        beneficiaryId2.setProvisionedOn(mock(Timestamp.class));
        beneficiaryId2.setReserved(true);
        beneficiaryId2.setReservedById(1);
        beneficiaryId2.setReservedByName("Reserved By Name");
        beneficiaryId2.setReservedForCountryId(1);
        beneficiaryId2.setReservedForCountryName("GB");
        beneficiaryId2.setReservedForDistrictId(1);
        beneficiaryId2.setReservedForDistrictName("Reserved For District Name");
        beneficiaryId2.setReservedForId(1);
        beneficiaryId2.setReservedForName("Reserved For Name");
        beneficiaryId2.setReservedForPSMapId(1);
        beneficiaryId2.setReservedForPSMapName("Reserved For PSMap Name");
        beneficiaryId2.setReservedForStateId(1);
        beneficiaryId2.setReservedForStateName("Reserved For State Name");
        beneficiaryId2.setReservedOn(mock(Timestamp.class));
        beneficiaryId2.setReservedUntil(mock(Timestamp.class));

        // Act and Assert
        assertNotEquals(beneficiaryId, beneficiaryId2);
    }

    /**
     * Method under test: {@link BeneficiaryId#equals(Object)}
     */
    @Test
    void testEquals23() {
        // Arrange
        BeneficiaryId beneficiaryId = new BeneficiaryId();
        beneficiaryId.setBenRegId(BigInteger.valueOf(1L));
        beneficiaryId.setBeneficiaryId(BigInteger.valueOf(1L));
        beneficiaryId.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        beneficiaryId.setCreatedDate(mock(Timestamp.class));
        beneficiaryId.setProvisioned(true);
        beneficiaryId.setProvisionedBy("Provisioned By");
        beneficiaryId.setProvisionedById(1);
        beneficiaryId.setProvisionedOn(mock(Timestamp.class));
        beneficiaryId.setReserved(true);
        beneficiaryId.setReservedById(1);
        beneficiaryId.setReservedByName("Reserved By Name");
        beneficiaryId.setReservedForCountryId(1);
        beneficiaryId.setReservedForCountryName("GB");
        beneficiaryId.setReservedForDistrictId(1);
        beneficiaryId.setReservedForDistrictName("Reserved For District Name");
        beneficiaryId.setReservedForId(2);
        beneficiaryId.setReservedForName("Reserved For Name");
        beneficiaryId.setReservedForPSMapId(1);
        beneficiaryId.setReservedForPSMapName("Reserved For PSMap Name");
        beneficiaryId.setReservedForStateId(1);
        beneficiaryId.setReservedForStateName("Reserved For State Name");
        beneficiaryId.setReservedOn(mock(Timestamp.class));
        beneficiaryId.setReservedUntil(mock(Timestamp.class));

        BeneficiaryId beneficiaryId2 = new BeneficiaryId();
        beneficiaryId2.setBenRegId(BigInteger.valueOf(1L));
        beneficiaryId2.setBeneficiaryId(BigInteger.valueOf(1L));
        beneficiaryId2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        beneficiaryId2.setCreatedDate(mock(Timestamp.class));
        beneficiaryId2.setProvisioned(true);
        beneficiaryId2.setProvisionedBy("Provisioned By");
        beneficiaryId2.setProvisionedById(1);
        beneficiaryId2.setProvisionedOn(mock(Timestamp.class));
        beneficiaryId2.setReserved(true);
        beneficiaryId2.setReservedById(1);
        beneficiaryId2.setReservedByName("Reserved By Name");
        beneficiaryId2.setReservedForCountryId(1);
        beneficiaryId2.setReservedForCountryName("GB");
        beneficiaryId2.setReservedForDistrictId(1);
        beneficiaryId2.setReservedForDistrictName("Reserved For District Name");
        beneficiaryId2.setReservedForId(1);
        beneficiaryId2.setReservedForName("Reserved For Name");
        beneficiaryId2.setReservedForPSMapId(1);
        beneficiaryId2.setReservedForPSMapName("Reserved For PSMap Name");
        beneficiaryId2.setReservedForStateId(1);
        beneficiaryId2.setReservedForStateName("Reserved For State Name");
        beneficiaryId2.setReservedOn(mock(Timestamp.class));
        beneficiaryId2.setReservedUntil(mock(Timestamp.class));

        // Act and Assert
        assertNotEquals(beneficiaryId, beneficiaryId2);
    }

    /**
     * Method under test: {@link BeneficiaryId#equals(Object)}
     */
    @Test
    void testEquals24() {
        // Arrange
        BeneficiaryId beneficiaryId = new BeneficiaryId();
        beneficiaryId.setBenRegId(BigInteger.valueOf(1L));
        beneficiaryId.setBeneficiaryId(BigInteger.valueOf(1L));
        beneficiaryId.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        beneficiaryId.setCreatedDate(mock(Timestamp.class));
        beneficiaryId.setProvisioned(true);
        beneficiaryId.setProvisionedBy("Provisioned By");
        beneficiaryId.setProvisionedById(1);
        beneficiaryId.setProvisionedOn(mock(Timestamp.class));
        beneficiaryId.setReserved(true);
        beneficiaryId.setReservedById(1);
        beneficiaryId.setReservedByName("Reserved By Name");
        beneficiaryId.setReservedForCountryId(1);
        beneficiaryId.setReservedForCountryName("GB");
        beneficiaryId.setReservedForDistrictId(1);
        beneficiaryId.setReservedForDistrictName("Reserved For District Name");
        beneficiaryId.setReservedForId(null);
        beneficiaryId.setReservedForName("Reserved For Name");
        beneficiaryId.setReservedForPSMapId(1);
        beneficiaryId.setReservedForPSMapName("Reserved For PSMap Name");
        beneficiaryId.setReservedForStateId(1);
        beneficiaryId.setReservedForStateName("Reserved For State Name");
        beneficiaryId.setReservedOn(mock(Timestamp.class));
        beneficiaryId.setReservedUntil(mock(Timestamp.class));

        BeneficiaryId beneficiaryId2 = new BeneficiaryId();
        beneficiaryId2.setBenRegId(BigInteger.valueOf(1L));
        beneficiaryId2.setBeneficiaryId(BigInteger.valueOf(1L));
        beneficiaryId2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        beneficiaryId2.setCreatedDate(mock(Timestamp.class));
        beneficiaryId2.setProvisioned(true);
        beneficiaryId2.setProvisionedBy("Provisioned By");
        beneficiaryId2.setProvisionedById(1);
        beneficiaryId2.setProvisionedOn(mock(Timestamp.class));
        beneficiaryId2.setReserved(true);
        beneficiaryId2.setReservedById(1);
        beneficiaryId2.setReservedByName("Reserved By Name");
        beneficiaryId2.setReservedForCountryId(1);
        beneficiaryId2.setReservedForCountryName("GB");
        beneficiaryId2.setReservedForDistrictId(1);
        beneficiaryId2.setReservedForDistrictName("Reserved For District Name");
        beneficiaryId2.setReservedForId(1);
        beneficiaryId2.setReservedForName("Reserved For Name");
        beneficiaryId2.setReservedForPSMapId(1);
        beneficiaryId2.setReservedForPSMapName("Reserved For PSMap Name");
        beneficiaryId2.setReservedForStateId(1);
        beneficiaryId2.setReservedForStateName("Reserved For State Name");
        beneficiaryId2.setReservedOn(mock(Timestamp.class));
        beneficiaryId2.setReservedUntil(mock(Timestamp.class));

        // Act and Assert
        assertNotEquals(beneficiaryId, beneficiaryId2);
    }

    /**
     * Method under test: {@link BeneficiaryId#equals(Object)}
     */
    @Test
    void testEquals25() {
        // Arrange
        BeneficiaryId beneficiaryId = new BeneficiaryId();
        beneficiaryId.setBenRegId(BigInteger.valueOf(1L));
        beneficiaryId.setBeneficiaryId(BigInteger.valueOf(1L));
        beneficiaryId.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        beneficiaryId.setCreatedDate(mock(Timestamp.class));
        beneficiaryId.setProvisioned(true);
        beneficiaryId.setProvisionedBy("Provisioned By");
        beneficiaryId.setProvisionedById(1);
        beneficiaryId.setProvisionedOn(mock(Timestamp.class));
        beneficiaryId.setReserved(true);
        beneficiaryId.setReservedById(1);
        beneficiaryId.setReservedByName("Reserved By Name");
        beneficiaryId.setReservedForCountryId(1);
        beneficiaryId.setReservedForCountryName("GB");
        beneficiaryId.setReservedForDistrictId(1);
        beneficiaryId.setReservedForDistrictName("Reserved For District Name");
        beneficiaryId.setReservedForId(1);
        beneficiaryId.setReservedForName("Reserved For Name");
        beneficiaryId.setReservedForPSMapId(2);
        beneficiaryId.setReservedForPSMapName("Reserved For PSMap Name");
        beneficiaryId.setReservedForStateId(1);
        beneficiaryId.setReservedForStateName("Reserved For State Name");
        beneficiaryId.setReservedOn(mock(Timestamp.class));
        beneficiaryId.setReservedUntil(mock(Timestamp.class));

        BeneficiaryId beneficiaryId2 = new BeneficiaryId();
        beneficiaryId2.setBenRegId(BigInteger.valueOf(1L));
        beneficiaryId2.setBeneficiaryId(BigInteger.valueOf(1L));
        beneficiaryId2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        beneficiaryId2.setCreatedDate(mock(Timestamp.class));
        beneficiaryId2.setProvisioned(true);
        beneficiaryId2.setProvisionedBy("Provisioned By");
        beneficiaryId2.setProvisionedById(1);
        beneficiaryId2.setProvisionedOn(mock(Timestamp.class));
        beneficiaryId2.setReserved(true);
        beneficiaryId2.setReservedById(1);
        beneficiaryId2.setReservedByName("Reserved By Name");
        beneficiaryId2.setReservedForCountryId(1);
        beneficiaryId2.setReservedForCountryName("GB");
        beneficiaryId2.setReservedForDistrictId(1);
        beneficiaryId2.setReservedForDistrictName("Reserved For District Name");
        beneficiaryId2.setReservedForId(1);
        beneficiaryId2.setReservedForName("Reserved For Name");
        beneficiaryId2.setReservedForPSMapId(1);
        beneficiaryId2.setReservedForPSMapName("Reserved For PSMap Name");
        beneficiaryId2.setReservedForStateId(1);
        beneficiaryId2.setReservedForStateName("Reserved For State Name");
        beneficiaryId2.setReservedOn(mock(Timestamp.class));
        beneficiaryId2.setReservedUntil(mock(Timestamp.class));

        // Act and Assert
        assertNotEquals(beneficiaryId, beneficiaryId2);
    }

    /**
     * Method under test: {@link BeneficiaryId#equals(Object)}
     */
    @Test
    void testEquals26() {
        // Arrange
        BeneficiaryId beneficiaryId = new BeneficiaryId();
        beneficiaryId.setBenRegId(BigInteger.valueOf(1L));
        beneficiaryId.setBeneficiaryId(BigInteger.valueOf(1L));
        beneficiaryId.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        beneficiaryId.setCreatedDate(mock(Timestamp.class));
        beneficiaryId.setProvisioned(true);
        beneficiaryId.setProvisionedBy("Provisioned By");
        beneficiaryId.setProvisionedById(1);
        beneficiaryId.setProvisionedOn(mock(Timestamp.class));
        beneficiaryId.setReserved(true);
        beneficiaryId.setReservedById(1);
        beneficiaryId.setReservedByName("Reserved By Name");
        beneficiaryId.setReservedForCountryId(1);
        beneficiaryId.setReservedForCountryName("GB");
        beneficiaryId.setReservedForDistrictId(1);
        beneficiaryId.setReservedForDistrictName("Reserved For District Name");
        beneficiaryId.setReservedForId(1);
        beneficiaryId.setReservedForName("Reserved For Name");
        beneficiaryId.setReservedForPSMapId(null);
        beneficiaryId.setReservedForPSMapName("Reserved For PSMap Name");
        beneficiaryId.setReservedForStateId(1);
        beneficiaryId.setReservedForStateName("Reserved For State Name");
        beneficiaryId.setReservedOn(mock(Timestamp.class));
        beneficiaryId.setReservedUntil(mock(Timestamp.class));

        BeneficiaryId beneficiaryId2 = new BeneficiaryId();
        beneficiaryId2.setBenRegId(BigInteger.valueOf(1L));
        beneficiaryId2.setBeneficiaryId(BigInteger.valueOf(1L));
        beneficiaryId2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        beneficiaryId2.setCreatedDate(mock(Timestamp.class));
        beneficiaryId2.setProvisioned(true);
        beneficiaryId2.setProvisionedBy("Provisioned By");
        beneficiaryId2.setProvisionedById(1);
        beneficiaryId2.setProvisionedOn(mock(Timestamp.class));
        beneficiaryId2.setReserved(true);
        beneficiaryId2.setReservedById(1);
        beneficiaryId2.setReservedByName("Reserved By Name");
        beneficiaryId2.setReservedForCountryId(1);
        beneficiaryId2.setReservedForCountryName("GB");
        beneficiaryId2.setReservedForDistrictId(1);
        beneficiaryId2.setReservedForDistrictName("Reserved For District Name");
        beneficiaryId2.setReservedForId(1);
        beneficiaryId2.setReservedForName("Reserved For Name");
        beneficiaryId2.setReservedForPSMapId(1);
        beneficiaryId2.setReservedForPSMapName("Reserved For PSMap Name");
        beneficiaryId2.setReservedForStateId(1);
        beneficiaryId2.setReservedForStateName("Reserved For State Name");
        beneficiaryId2.setReservedOn(mock(Timestamp.class));
        beneficiaryId2.setReservedUntil(mock(Timestamp.class));

        // Act and Assert
        assertNotEquals(beneficiaryId, beneficiaryId2);
    }

    /**
     * Method under test: {@link BeneficiaryId#equals(Object)}
     */
    @Test
    void testEquals27() {
        // Arrange
        BeneficiaryId beneficiaryId = new BeneficiaryId();
        beneficiaryId.setBenRegId(BigInteger.valueOf(1L));
        beneficiaryId.setBeneficiaryId(BigInteger.valueOf(1L));
        beneficiaryId.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        beneficiaryId.setCreatedDate(mock(Timestamp.class));
        beneficiaryId.setProvisioned(true);
        beneficiaryId.setProvisionedBy("Provisioned By");
        beneficiaryId.setProvisionedById(1);
        beneficiaryId.setProvisionedOn(mock(Timestamp.class));
        beneficiaryId.setReserved(true);
        beneficiaryId.setReservedById(1);
        beneficiaryId.setReservedByName("Reserved By Name");
        beneficiaryId.setReservedForCountryId(1);
        beneficiaryId.setReservedForCountryName("GB");
        beneficiaryId.setReservedForDistrictId(1);
        beneficiaryId.setReservedForDistrictName("Reserved For District Name");
        beneficiaryId.setReservedForId(1);
        beneficiaryId.setReservedForName("Reserved For Name");
        beneficiaryId.setReservedForPSMapId(1);
        beneficiaryId.setReservedForPSMapName("Reserved For PSMap Name");
        beneficiaryId.setReservedForStateId(2);
        beneficiaryId.setReservedForStateName("Reserved For State Name");
        beneficiaryId.setReservedOn(mock(Timestamp.class));
        beneficiaryId.setReservedUntil(mock(Timestamp.class));

        BeneficiaryId beneficiaryId2 = new BeneficiaryId();
        beneficiaryId2.setBenRegId(BigInteger.valueOf(1L));
        beneficiaryId2.setBeneficiaryId(BigInteger.valueOf(1L));
        beneficiaryId2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        beneficiaryId2.setCreatedDate(mock(Timestamp.class));
        beneficiaryId2.setProvisioned(true);
        beneficiaryId2.setProvisionedBy("Provisioned By");
        beneficiaryId2.setProvisionedById(1);
        beneficiaryId2.setProvisionedOn(mock(Timestamp.class));
        beneficiaryId2.setReserved(true);
        beneficiaryId2.setReservedById(1);
        beneficiaryId2.setReservedByName("Reserved By Name");
        beneficiaryId2.setReservedForCountryId(1);
        beneficiaryId2.setReservedForCountryName("GB");
        beneficiaryId2.setReservedForDistrictId(1);
        beneficiaryId2.setReservedForDistrictName("Reserved For District Name");
        beneficiaryId2.setReservedForId(1);
        beneficiaryId2.setReservedForName("Reserved For Name");
        beneficiaryId2.setReservedForPSMapId(1);
        beneficiaryId2.setReservedForPSMapName("Reserved For PSMap Name");
        beneficiaryId2.setReservedForStateId(1);
        beneficiaryId2.setReservedForStateName("Reserved For State Name");
        beneficiaryId2.setReservedOn(mock(Timestamp.class));
        beneficiaryId2.setReservedUntil(mock(Timestamp.class));

        // Act and Assert
        assertNotEquals(beneficiaryId, beneficiaryId2);
    }

    /**
     * Method under test: {@link BeneficiaryId#equals(Object)}
     */
    @Test
    void testEquals28() {
        // Arrange
        BeneficiaryId beneficiaryId = new BeneficiaryId();
        beneficiaryId.setBenRegId(BigInteger.valueOf(1L));
        beneficiaryId.setBeneficiaryId(BigInteger.valueOf(1L));
        beneficiaryId.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        beneficiaryId.setCreatedDate(mock(Timestamp.class));
        beneficiaryId.setProvisioned(true);
        beneficiaryId.setProvisionedBy("Provisioned By");
        beneficiaryId.setProvisionedById(1);
        beneficiaryId.setProvisionedOn(mock(Timestamp.class));
        beneficiaryId.setReserved(true);
        beneficiaryId.setReservedById(1);
        beneficiaryId.setReservedByName("Reserved By Name");
        beneficiaryId.setReservedForCountryId(1);
        beneficiaryId.setReservedForCountryName("GB");
        beneficiaryId.setReservedForDistrictId(1);
        beneficiaryId.setReservedForDistrictName("Reserved For District Name");
        beneficiaryId.setReservedForId(1);
        beneficiaryId.setReservedForName("Reserved For Name");
        beneficiaryId.setReservedForPSMapId(1);
        beneficiaryId.setReservedForPSMapName("Reserved For PSMap Name");
        beneficiaryId.setReservedForStateId(null);
        beneficiaryId.setReservedForStateName("Reserved For State Name");
        beneficiaryId.setReservedOn(mock(Timestamp.class));
        beneficiaryId.setReservedUntil(mock(Timestamp.class));

        BeneficiaryId beneficiaryId2 = new BeneficiaryId();
        beneficiaryId2.setBenRegId(BigInteger.valueOf(1L));
        beneficiaryId2.setBeneficiaryId(BigInteger.valueOf(1L));
        beneficiaryId2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        beneficiaryId2.setCreatedDate(mock(Timestamp.class));
        beneficiaryId2.setProvisioned(true);
        beneficiaryId2.setProvisionedBy("Provisioned By");
        beneficiaryId2.setProvisionedById(1);
        beneficiaryId2.setProvisionedOn(mock(Timestamp.class));
        beneficiaryId2.setReserved(true);
        beneficiaryId2.setReservedById(1);
        beneficiaryId2.setReservedByName("Reserved By Name");
        beneficiaryId2.setReservedForCountryId(1);
        beneficiaryId2.setReservedForCountryName("GB");
        beneficiaryId2.setReservedForDistrictId(1);
        beneficiaryId2.setReservedForDistrictName("Reserved For District Name");
        beneficiaryId2.setReservedForId(1);
        beneficiaryId2.setReservedForName("Reserved For Name");
        beneficiaryId2.setReservedForPSMapId(1);
        beneficiaryId2.setReservedForPSMapName("Reserved For PSMap Name");
        beneficiaryId2.setReservedForStateId(1);
        beneficiaryId2.setReservedForStateName("Reserved For State Name");
        beneficiaryId2.setReservedOn(mock(Timestamp.class));
        beneficiaryId2.setReservedUntil(mock(Timestamp.class));

        // Act and Assert
        assertNotEquals(beneficiaryId, beneficiaryId2);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link BeneficiaryId#equals(Object)}
     *   <li>{@link BeneficiaryId#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode() {
        // Arrange
        BeneficiaryId beneficiaryId = new BeneficiaryId();
        beneficiaryId.setBenRegId(BigInteger.valueOf(1L));
        beneficiaryId.setBeneficiaryId(BigInteger.valueOf(1L));
        beneficiaryId.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        beneficiaryId.setCreatedDate(mock(Timestamp.class));
        beneficiaryId.setProvisioned(true);
        beneficiaryId.setProvisionedBy("Provisioned By");
        beneficiaryId.setProvisionedById(1);
        beneficiaryId.setProvisionedOn(mock(Timestamp.class));
        beneficiaryId.setReserved(true);
        beneficiaryId.setReservedById(1);
        beneficiaryId.setReservedByName("Reserved By Name");
        beneficiaryId.setReservedForCountryId(1);
        beneficiaryId.setReservedForCountryName("GB");
        beneficiaryId.setReservedForDistrictId(1);
        beneficiaryId.setReservedForDistrictName("Reserved For District Name");
        beneficiaryId.setReservedForId(1);
        beneficiaryId.setReservedForName("Reserved For Name");
        beneficiaryId.setReservedForPSMapId(1);
        beneficiaryId.setReservedForPSMapName("Reserved For PSMap Name");
        beneficiaryId.setReservedForStateId(1);
        beneficiaryId.setReservedForStateName("Reserved For State Name");
        beneficiaryId.setReservedOn(mock(Timestamp.class));
        beneficiaryId.setReservedUntil(mock(Timestamp.class));

        // Act and Assert
        assertEquals(beneficiaryId, beneficiaryId);
        int expectedHashCodeResult = beneficiaryId.hashCode();
        assertEquals(expectedHashCodeResult, beneficiaryId.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>default or parameterless constructor of {@link BeneficiaryId}
     *   <li>{@link BeneficiaryId#setBenRegId(BigInteger)}
     *   <li>{@link BeneficiaryId#setBeneficiaryId(BigInteger)}
     *   <li>{@link BeneficiaryId#setCreatedBy(String)}
     *   <li>{@link BeneficiaryId#setCreatedDate(Timestamp)}
     *   <li>{@link BeneficiaryId#setProvisioned(Boolean)}
     *   <li>{@link BeneficiaryId#setProvisionedBy(String)}
     *   <li>{@link BeneficiaryId#setProvisionedById(Integer)}
     *   <li>{@link BeneficiaryId#setProvisionedOn(Timestamp)}
     *   <li>{@link BeneficiaryId#setReserved(Boolean)}
     *   <li>{@link BeneficiaryId#setReservedById(Integer)}
     *   <li>{@link BeneficiaryId#setReservedByName(String)}
     *   <li>{@link BeneficiaryId#setReservedForCountryId(Integer)}
     *   <li>{@link BeneficiaryId#setReservedForCountryName(String)}
     *   <li>{@link BeneficiaryId#setReservedForDistrictId(Integer)}
     *   <li>{@link BeneficiaryId#setReservedForDistrictName(String)}
     *   <li>{@link BeneficiaryId#setReservedForId(Integer)}
     *   <li>{@link BeneficiaryId#setReservedForName(String)}
     *   <li>{@link BeneficiaryId#setReservedForPSMapId(Integer)}
     *   <li>{@link BeneficiaryId#setReservedForPSMapName(String)}
     *   <li>{@link BeneficiaryId#setReservedForStateId(Integer)}
     *   <li>{@link BeneficiaryId#setReservedForStateName(String)}
     *   <li>{@link BeneficiaryId#setReservedOn(Timestamp)}
     *   <li>{@link BeneficiaryId#setReservedUntil(Timestamp)}
     *   <li>{@link BeneficiaryId#toString()}
     *   <li>{@link BeneficiaryId#getBenRegId()}
     *   <li>{@link BeneficiaryId#getBeneficiaryId()}
     *   <li>{@link BeneficiaryId#getCreatedBy()}
     *   <li>{@link BeneficiaryId#getCreatedDate()}
     *   <li>{@link BeneficiaryId#getProvisioned()}
     *   <li>{@link BeneficiaryId#getProvisionedBy()}
     *   <li>{@link BeneficiaryId#getProvisionedById()}
     *   <li>{@link BeneficiaryId#getProvisionedOn()}
     *   <li>{@link BeneficiaryId#getReserved()}
     *   <li>{@link BeneficiaryId#getReservedById()}
     *   <li>{@link BeneficiaryId#getReservedByName()}
     *   <li>{@link BeneficiaryId#getReservedForCountryId()}
     *   <li>{@link BeneficiaryId#getReservedForCountryName()}
     *   <li>{@link BeneficiaryId#getReservedForDistrictId()}
     *   <li>{@link BeneficiaryId#getReservedForDistrictName()}
     *   <li>{@link BeneficiaryId#getReservedForId()}
     *   <li>{@link BeneficiaryId#getReservedForName()}
     *   <li>{@link BeneficiaryId#getReservedForPSMapId()}
     *   <li>{@link BeneficiaryId#getReservedForPSMapName()}
     *   <li>{@link BeneficiaryId#getReservedForStateId()}
     *   <li>{@link BeneficiaryId#getReservedForStateName()}
     *   <li>{@link BeneficiaryId#getReservedOn()}
     *   <li>{@link BeneficiaryId#getReservedUntil()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        BeneficiaryId actualBeneficiaryId = new BeneficiaryId();
        actualBeneficiaryId.setBenRegId(BigInteger.valueOf(1L));
        actualBeneficiaryId.setBeneficiaryId(BigInteger.valueOf(1L));
        actualBeneficiaryId.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        Timestamp createdDate = mock(Timestamp.class);
        actualBeneficiaryId.setCreatedDate(createdDate);
        actualBeneficiaryId.setProvisioned(true);
        actualBeneficiaryId.setProvisionedBy("Provisioned By");
        actualBeneficiaryId.setProvisionedById(1);
        Timestamp provisionedOn = mock(Timestamp.class);
        actualBeneficiaryId.setProvisionedOn(provisionedOn);
        actualBeneficiaryId.setReserved(true);
        actualBeneficiaryId.setReservedById(1);
        actualBeneficiaryId.setReservedByName("Reserved By Name");
        actualBeneficiaryId.setReservedForCountryId(1);
        actualBeneficiaryId.setReservedForCountryName("GB");
        actualBeneficiaryId.setReservedForDistrictId(1);
        actualBeneficiaryId.setReservedForDistrictName("Reserved For District Name");
        actualBeneficiaryId.setReservedForId(1);
        actualBeneficiaryId.setReservedForName("Reserved For Name");
        actualBeneficiaryId.setReservedForPSMapId(1);
        actualBeneficiaryId.setReservedForPSMapName("Reserved For PSMap Name");
        actualBeneficiaryId.setReservedForStateId(1);
        actualBeneficiaryId.setReservedForStateName("Reserved For State Name");
        Timestamp reservedOn = mock(Timestamp.class);
        actualBeneficiaryId.setReservedOn(reservedOn);
        Timestamp reservedUntil = mock(Timestamp.class);
        actualBeneficiaryId.setReservedUntil(reservedUntil);
        actualBeneficiaryId.toString();
        BigInteger actualBenRegId = actualBeneficiaryId.getBenRegId();
        BigInteger actualBeneficiaryId2 = actualBeneficiaryId.getBeneficiaryId();
        String actualCreatedBy = actualBeneficiaryId.getCreatedBy();
        Timestamp actualCreatedDate = actualBeneficiaryId.getCreatedDate();
        Boolean actualProvisioned = actualBeneficiaryId.getProvisioned();
        String actualProvisionedBy = actualBeneficiaryId.getProvisionedBy();
        Integer actualProvisionedById = actualBeneficiaryId.getProvisionedById();
        Timestamp actualProvisionedOn = actualBeneficiaryId.getProvisionedOn();
        Boolean actualReserved = actualBeneficiaryId.getReserved();
        Integer actualReservedById = actualBeneficiaryId.getReservedById();
        String actualReservedByName = actualBeneficiaryId.getReservedByName();
        Integer actualReservedForCountryId = actualBeneficiaryId.getReservedForCountryId();
        String actualReservedForCountryName = actualBeneficiaryId.getReservedForCountryName();
        Integer actualReservedForDistrictId = actualBeneficiaryId.getReservedForDistrictId();
        String actualReservedForDistrictName = actualBeneficiaryId.getReservedForDistrictName();
        Integer actualReservedForId = actualBeneficiaryId.getReservedForId();
        String actualReservedForName = actualBeneficiaryId.getReservedForName();
        Integer actualReservedForPSMapId = actualBeneficiaryId.getReservedForPSMapId();
        String actualReservedForPSMapName = actualBeneficiaryId.getReservedForPSMapName();
        Integer actualReservedForStateId = actualBeneficiaryId.getReservedForStateId();
        String actualReservedForStateName = actualBeneficiaryId.getReservedForStateName();
        Timestamp actualReservedOn = actualBeneficiaryId.getReservedOn();
        Timestamp actualReservedUntil = actualBeneficiaryId.getReservedUntil();

        // Assert that nothing has changed
        assertEquals("GB", actualReservedForCountryName);
        assertEquals("Jan 1, 2020 8:00am GMT+0100", actualCreatedBy);
        assertEquals("Provisioned By", actualProvisionedBy);
        assertEquals("Reserved By Name", actualReservedByName);
        assertEquals("Reserved For District Name", actualReservedForDistrictName);
        assertEquals("Reserved For Name", actualReservedForName);
        assertEquals("Reserved For PSMap Name", actualReservedForPSMapName);
        assertEquals("Reserved For State Name", actualReservedForStateName);
        assertEquals(1, actualProvisionedById.intValue());
        assertEquals(1, actualReservedById.intValue());
        assertEquals(1, actualReservedForCountryId.intValue());
        assertEquals(1, actualReservedForDistrictId.intValue());
        assertEquals(1, actualReservedForId.intValue());
        assertEquals(1, actualReservedForPSMapId.intValue());
        assertEquals(1, actualReservedForStateId.intValue());
        assertTrue(actualProvisioned);
        assertTrue(actualReserved);
        assertSame(actualBenRegId, actualBeneficiaryId2);
        assertSame(actualBeneficiaryId2.ONE, actualBenRegId);
        assertSame(createdDate, actualCreatedDate);
        assertSame(provisionedOn, actualProvisionedOn);
        assertSame(reservedOn, actualReservedOn);
        assertSame(reservedUntil, actualReservedUntil);
    }
}
