package com.iemr.common.bengen.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;

import java.sql.Timestamp;

import org.junit.jupiter.api.Test;

class M_BeneficiaryRegidMappingDiffblueTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>
     * {@link M_BeneficiaryRegidMapping#M_BeneficiaryRegidMapping(Long, Long, Timestamp, String)}
     *   <li>{@link M_BeneficiaryRegidMapping#toString()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange
        Timestamp createdDate = mock(Timestamp.class);

        // Act
        M_BeneficiaryRegidMapping actualM_BeneficiaryRegidMapping = new M_BeneficiaryRegidMapping(1L, 1L, createdDate,
                "Jan 1, 2020 8:00am GMT+0100");
        actualM_BeneficiaryRegidMapping.toString();

        // Assert
        assertEquals("Jan 1, 2020 8:00am GMT+0100", actualM_BeneficiaryRegidMapping.getCreatedBy());
        assertNull(actualM_BeneficiaryRegidMapping.getProvisioned());
        assertNull(actualM_BeneficiaryRegidMapping.getReserved());
        assertNull(actualM_BeneficiaryRegidMapping.getVanID());
        assertNull(actualM_BeneficiaryRegidMapping.getBenIDRequired());
        assertEquals(1L, actualM_BeneficiaryRegidMapping.getBenRegId().longValue());
        assertEquals(1L, actualM_BeneficiaryRegidMapping.getBeneficiaryId().longValue());
        assertSame(createdDate, actualM_BeneficiaryRegidMapping.getCreatedDate());
    }
}
