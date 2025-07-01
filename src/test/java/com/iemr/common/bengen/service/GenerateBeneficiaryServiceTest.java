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

import com.iemr.common.bengen.domain.M_BeneficiaryRegidMapping;
import com.iemr.common.bengen.repo.BeneficiaryIdRepo;
import com.iemr.common.bengen.utils.Generator;
import com.iemr.common.bengen.utils.config.ConfigProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedConstruction;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GenerateBeneficiaryServiceTest {

    @InjectMocks
    private GenerateBeneficiaryService generateBeneficiaryService;

    @Mock
    private JdbcTemplate jdbcTemplate;

    @Mock
    private BeneficiaryIdRepo beneficiaryIdRepo;

    @Mock
    private ExecutorService mockExecutorService;

    private static final BigInteger MOCKED_BENEFICIARY_ID = new BigInteger("12345678901");

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(generateBeneficiaryService, "executor", mockExecutorService);
    }

    @Test
    void testCreateQuery() {
        int n = 3;
        // Invoke real method
        StringBuffer result = generateBeneficiaryService.createQuery(n);

        // Capture executed SQL
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(jdbcTemplate, times(1)).execute(captor.capture());
        String sql = captor.getValue();

        // Validate SQL structure
        assertTrue(sql.startsWith("INSERT INTO `db_identity`.`m_beneficiaryregidmapping`"));
        // Expect n occurrences of "), "
        assertEquals(n, sql.split("\\), ").length);
        // Returned buffer equals executed SQL
        assertEquals(sql, result.toString());
    }

    @Test
    void testGetBeneficiaryIDs() {
        Long num = 2L;
        Integer vanID = 101;

        // Prepare mock repository result
        List<Object[]> repoResult = new ArrayList<>();
        repoResult.add(new Object[]{1L, 111L, new Timestamp(System.currentTimeMillis())});
        repoResult.add(new Object[]{2L, 222L, new Timestamp(System.currentTimeMillis())});

        when(beneficiaryIdRepo.getBenIDGenerated(vanID, num)).thenReturn(repoResult);
        doNothing().when(jdbcTemplate).execute(anyString());

        // Execute
        List<M_BeneficiaryRegidMapping> list = generateBeneficiaryService.getBeneficiaryIDs(num, vanID);

        // Verify
        verify(jdbcTemplate, times(1)).execute(anyString());
        verify(beneficiaryIdRepo, times(1)).getBenIDGenerated(vanID, num);

        assertNotNull(list);
        assertEquals(2, list.size());
        assertEquals(111L, list.get(0).getBeneficiaryId());
        assertEquals(1L,   list.get(0).getBenRegId());
        assertEquals(222L, list.get(1).getBeneficiaryId());
        assertEquals(2L,   list.get(1).getBenRegId());
    }

    @Test
    void testLoopGenerator() {
        try (MockedConstruction<Generator> genMock = Mockito.mockConstruction(Generator.class,
                (mock, ctx) -> when(mock.generateBeneficiaryId()).thenReturn(MOCKED_BENEFICIARY_ID))) {
            generateBeneficiaryService.testLoopGenr();
            Generator g = genMock.constructed().get(0);
            verify(g, atLeastOnce()).generateBeneficiaryId();
        }
    }

    @Test
    void testGenerateBeneficiaryIDs() throws Exception {
        Runnable[] task = new Runnable[1];
        doAnswer(inv -> { task[0] = inv.getArgument(0); return null; })
            .when(mockExecutorService).submit(any(Runnable.class));

        try (MockedStatic<ConfigProperties> cfg = Mockito.mockStatic(ConfigProperties.class)) {
            cfg.when(() -> ConfigProperties.getInteger("no-of-benID-to-be-generate")).thenReturn(1);
            doNothing().when(jdbcTemplate).execute(anyString());

            generateBeneficiaryService.generateBeneficiaryIDs();
            verify(mockExecutorService, times(1)).submit(any(Runnable.class));

            if (task[0] != null) {
                task[0].run();
                verify(jdbcTemplate, atLeastOnce()).execute(anyString());
            }
        }
    }

    @Test
    void testCreateFile() throws IOException {
        // Create a temporary file that we can control and verify
        File tempFile = File.createTempFile("test_bengen", ".csv");
        tempFile.deleteOnExit(); // Clean up after test
        
        try (MockedStatic<ConfigProperties> cfg = Mockito.mockStatic(ConfigProperties.class);
             MockedStatic<File> fileMock = Mockito.mockStatic(File.class)) {
            
            cfg.when(() -> ConfigProperties.getInteger("no-of-benID-to-be-generate")).thenReturn(2);
            
            // Mock File.createTempFile to return our controlled temp file
            fileMock.when(() -> File.createTempFile(anyString(), eq(".csv"))).thenReturn(tempFile);
            
            // Stub out JDBC execute
            doNothing().when(jdbcTemplate).execute(anyString());

            // Execute the method
            assertDoesNotThrow(() -> generateBeneficiaryService.createFile());

            // Verify that the SQL was executed
            verify(jdbcTemplate, times(1)).execute(anyString());
            
            // Verify file was created and exists
            assertTrue(tempFile.exists(), "Temporary file should exist");
            assertTrue(tempFile.length() > 0, "File should not be empty");
            
            // Verify file content structure (more flexible than exact string match)
            String fileContent = java.nio.file.Files.readString(tempFile.toPath());
            assertTrue(fileContent.contains("INSERT INTO `db_identity`.`m_beneficiaryregidmapping`"), 
                      "File should contain the INSERT statement");
            assertTrue(fileContent.contains("BeneficiaryID"), 
                      "File should contain BeneficiaryID column");
            assertTrue(fileContent.contains("admin-batch"), 
                      "File should contain admin-batch creator");
            
            // Count the number of value sets - should be 2 for our test
            // The SQL contains "VALUES (" followed by value sets like "( <values> ), ( <values> )"
            // So we count occurrences of "), (" which indicates multiple value sets
            long valueSetCount = fileContent.split("\\), \\(").length;
            assertEquals(2, valueSetCount, "Should contain 2 sets of values for 2 beneficiary IDs");
        }
    }
}
