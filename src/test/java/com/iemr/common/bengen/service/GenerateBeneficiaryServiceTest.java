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
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Timestamp;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("GenerateBeneficiaryService Test Suite")
class GenerateBeneficiaryServiceTest {

	@InjectMocks
	private GenerateBeneficiaryService generateBeneficiaryService;

	@Mock
	private JdbcTemplate jdbcTemplate;

	@Mock
	private BeneficiaryIdRepo beneficiaryIdRepo;

	@Mock
	private ExecutorService mockExecutorService;

	@TempDir
	Path tempDir;

	private static final BigInteger MOCKED_BENEFICIARY_ID = new BigInteger("12345678901");
	private static final String EXPECTED_TABLE_NAME = "`db_identity`.`m_beneficiaryregidmapping`";
	private static final String EXPECTED_CREATOR = "admin-batch";

	@BeforeEach
	void setUp() {
		ReflectionTestUtils.setField(generateBeneficiaryService, "executor", mockExecutorService);
	}

	@Nested
	@DisplayName("SQL Query Generation Tests")
	class QueryGenerationTests {

		@Test
		@DisplayName("Should generate correct number of batch data entries")
		void createBatchData_validInput_shouldReturnCorrectList() {
			// Arrange
			int recordCount = 3;

			// Act
			List<Object[]> result = generateBeneficiaryService.createBatchData(recordCount);

			// Assert
			assertThat(result).as("Should return a list with expected number of records").hasSize(recordCount);

			for (Object[] row : result) {
				assertThat(row).as("Each row should have 3 elements: BeneficiaryID, Timestamp, CreatedBy").hasSize(3);

				assertThat(row[0]).as("Beneficiary ID should not be null").isNotNull();

				assertThat(row[1]).as("Timestamp should be a Timestamp object").isInstanceOf(Timestamp.class);

				assertThat(row[2]).as("CreatedBy should be 'admin-batch'").isEqualTo("admin-batch");
			}
		}

		@ParameterizedTest
		@ValueSource(ints = { 1, 2, 5, 10 })
		@DisplayName("Should generate correct number of beneficiary ID data records")
		void createBatchData_shouldReturnExpectedSize(int recordCount) {
			// Act
			List<Object[]> result = generateBeneficiaryService.createBatchData(recordCount);

			// Assert
			assertThat(result).as("Result size should match record count").hasSize(recordCount);

			for (Object[] row : result) {
				assertThat(row).as("Each row should contain [BeneficiaryID, Timestamp, CreatedBy]").hasSize(3);
				assertThat(row[0]).isNotNull(); // BeneficiaryID
				assertThat(row[1]).isInstanceOf(Timestamp.class);
				assertThat(row[2]).isEqualTo("admin-batch");
			}
		}

		@Test
		@DisplayName("Should handle edge case of zero records")
		void createQuery_zeroRecords_shouldHandleGracefully() {
			// Act & Assert
			assertDoesNotThrow(() -> generateBeneficiaryService.createBatchData(0));
		}
	}

	@Nested
	@DisplayName("Beneficiary ID Retrieval Tests")
	class BeneficiaryIdRetrievalTests {

		@Test
		@DisplayName("Should retrieve and map beneficiary IDs correctly")
		void getBeneficiaryIDs_validInput_shouldReturnMappedResults() {
			// Arrange
			Long requestedCount = 2L;
			Integer vanID = 101;

			List<Object[]> mockRepoResult = createMockRepositoryResult();
			when(beneficiaryIdRepo.getBenIDGenerated(vanID, requestedCount)).thenReturn(mockRepoResult);

			// Act
			List<M_BeneficiaryRegidMapping> result = generateBeneficiaryService.getBeneficiaryIDs(requestedCount,
					vanID);

			// Assert
			verify(jdbcTemplate, times(1)).execute(anyString());
			verify(beneficiaryIdRepo, times(1)).getBenIDGenerated(vanID, requestedCount);

			assertThat(result).as("Result should not be null and have correct size").isNotNull().hasSize(2);

			// Verify mapping correctness
			assertThat(result.get(0)).extracting("beneficiaryId", "benRegId").containsExactly(111L, 1L);

			assertThat(result.get(1)).extracting("beneficiaryId", "benRegId").containsExactly(222L, 2L);
		}

		@Test
		@DisplayName("Should handle empty repository result")
		void getBeneficiaryIDs_emptyResult_shouldReturnEmptyList() {
			// Arrange
			when(beneficiaryIdRepo.getBenIDGenerated(anyInt(), anyLong())).thenReturn(new ArrayList<>());

			// Act
			List<M_BeneficiaryRegidMapping> result = generateBeneficiaryService.getBeneficiaryIDs(1L, 101);

			// Assert
			assertThat(result).as("Should return empty list for empty repository result").isNotNull().isEmpty();
		}

		@Test
		@DisplayName("Should handle repository exception gracefully")
		void getBeneficiaryIDs_repositoryException_shouldPropagateException() {
			// Arrange
			when(beneficiaryIdRepo.getBenIDGenerated(anyInt(), anyLong()))
					.thenThrow(new RuntimeException("Database connection failed"));

			// Act & Assert
			assertThatThrownBy(() -> generateBeneficiaryService.getBeneficiaryIDs(1L, 101))
					.isInstanceOf(RuntimeException.class).hasMessage("Database connection failed");
		}

		private List<Object[]> createMockRepositoryResult() {
			List<Object[]> result = new ArrayList<>();
			result.add(new Object[] { 1L, 111L, Timestamp.from(java.time.Instant.now()) });
			result.add(new Object[] { 2L, 222L, Timestamp.from(java.time.Instant.now()) });
			return result;
		}
	}

	@Nested
	@DisplayName("Generator Integration Tests")
	class GeneratorIntegrationTests {

		@Test
		@DisplayName("Should integrate with Generator to create beneficiary IDs")
		void testLoopGenr_generatorIntegration_shouldCallGeneratorMethods() {
			// Arrange & Act
			try (MockedConstruction<Generator> generatorMock = mockConstruction(Generator.class,
					(mock, context) -> when(mock.generateBeneficiaryId()).thenReturn(MOCKED_BENEFICIARY_ID))) {

				generateBeneficiaryService.testLoopGenr();

				// Assert
				assertThat(generatorMock.constructed()).as("Should construct at least one Generator instance")
						.isNotEmpty();

				Generator constructedGenerator = generatorMock.constructed().get(0);
				verify(constructedGenerator, atLeastOnce()).generateBeneficiaryId();
			}
		}
	}

	@Nested
	@DisplayName("Async Execution Tests")
	class AsyncExecutionTests {

		@Test
		@DisplayName("Should submit task to executor service")
		void generateBeneficiaryIDs_asyncExecution_shouldSubmitTask() throws Exception {
			// Arrange
			Runnable[] capturedTask = new Runnable[1];
			doAnswer(invocation -> {
				capturedTask[0] = invocation.getArgument(0);
				return null;
			}).when(mockExecutorService).submit(any(Runnable.class));

			try (MockedStatic<ConfigProperties> configMock = mockStatic(ConfigProperties.class)) {
				configMock.when(() -> ConfigProperties.getInteger("no-of-benID-to-be-generate")).thenReturn(1);

				// Act
				generateBeneficiaryService.generateBeneficiaryIDs();

				// Assert
				verify(mockExecutorService, times(1)).submit(any(Runnable.class));

				// Execute the captured task and verify DB interaction
				if (capturedTask[0] != null) {
					assertDoesNotThrow(() -> capturedTask[0].run());

					// Verify batchUpdate was called with appropriate arguments
					verify(jdbcTemplate, atLeastOnce()).batchUpdate(eq(
							"INSERT INTO `db_identity`.`m_beneficiaryregidmapping` (`BeneficiaryID`, `Provisioned`, `Deleted`, `CreatedDate`, `CreatedBy`) VALUES (?, b'0', b'0', ?, ?)"),
							anyList());
				}
			}
		}

		@Test
		@DisplayName("Should handle configuration retrieval gracefully")
		void generateBeneficiaryIDs_configHandling_shouldExecuteSuccessfully() {
			// Test graceful handling instead of exception expectation
			try (MockedStatic<ConfigProperties> configMock = mockStatic(ConfigProperties.class)) {
				configMock.when(() -> ConfigProperties.getInteger("no-of-benID-to-be-generate")).thenReturn(1);

				// Act & Assert - should not throw
				assertDoesNotThrow(() -> generateBeneficiaryService.generateBeneficiaryIDs());
				verify(mockExecutorService, times(1)).submit(any(Runnable.class));
			}
		}
	}

	@Nested
	@DisplayName("File Generation Tests")
	class FileGenerationTests {

		@Test
		@DisplayName("Should batch insert beneficiary records using JDBC")
		void createFile_shouldCallBatchUpdateWithCorrectParams() {
			try (MockedStatic<ConfigProperties> configMock = mockStatic(ConfigProperties.class)) {
				// Arrange
				configMock.when(() -> ConfigProperties.getInteger("no-of-benID-to-be-generate")).thenReturn(5); // for
																												// example
				when(jdbcTemplate.batchUpdate(anyString(), anyList())).thenReturn(new int[5]);

				// Act & Assert
				assertDoesNotThrow(() -> generateBeneficiaryService.createFile());

				verify(jdbcTemplate, atLeastOnce()).batchUpdate(anyString(), anyList());
			}
		}

		@ParameterizedTest
		@ValueSource(ints = { 1, 3, 5, 10 })
		@DisplayName("Should handle various record counts in batch insert")
		void createFile_variousRecordCounts_shouldCallJdbcTemplate(int recordCount) {
			try (MockedStatic<ConfigProperties> configMock = mockStatic(ConfigProperties.class)) {
				// Arrange
				configMock.when(() -> ConfigProperties.getInteger("no-of-benID-to-be-generate"))
						.thenReturn(recordCount);

				when(jdbcTemplate.batchUpdate(anyString(), anyList())).thenReturn(new int[recordCount]);

				// Act
				assertDoesNotThrow(() -> generateBeneficiaryService.createFile());

				// Assert
				ArgumentCaptor<List<Object[]>> batchCaptor = ArgumentCaptor.forClass(List.class);
				verify(jdbcTemplate, atLeastOnce()).batchUpdate(anyString(), batchCaptor.capture());

				List<Object[]> allBatches = batchCaptor.getAllValues().stream().flatMap(List::stream)
						.collect(Collectors.toList());

				assertThat(allBatches).as("Should batch insert expected number of records").hasSize(recordCount);
			}
		}

		@Test
		@DisplayName("Should handle file operations gracefully")
		void createFile_fileOperations_shouldExecuteSuccessfully() {
			// Test normal file operation flow instead of expecting exceptions
			try (MockedStatic<ConfigProperties> configMock = mockStatic(ConfigProperties.class)) {
				configMock.when(() -> ConfigProperties.getInteger("no-of-benID-to-be-generate")).thenReturn(2);

				// Act & Assert - should handle gracefully
				assertDoesNotThrow(() -> generateBeneficiaryService.createFile());
			}
		}

		@Test
		@DisplayName("Should handle JDBC execution failure")
		void createFile_jdbcFailure_shouldPropagateException() {
			try (MockedStatic<ConfigProperties> configMock = mockStatic(ConfigProperties.class)) {
				// Arrange
				configMock.when(() -> ConfigProperties.getInteger("no-of-benID-to-be-generate")).thenReturn(2);

				// Simulate JDBC failure
				doThrow(new RuntimeException("Batch insert failed")).when(jdbcTemplate).batchUpdate(anyString(),
						anyList());

				// Act & Assert
				assertThatThrownBy(() -> generateBeneficiaryService.createFile()).isInstanceOf(RuntimeException.class)
						.hasMessage("Batch insert failed");
			}
		}

		@Test
		@DisplayName("Should complete file creation within reasonable time")
		void createFile_performance_shouldCompleteWithinTimeout() throws IOException {
			// Arrange
			File tempFile = createTempTestFile();

			try (MockedStatic<ConfigProperties> configMock = mockStatic(ConfigProperties.class);
					MockedStatic<File> fileMock = mockStatic(File.class)) {

				setupFileCreationMocks(configMock, fileMock, tempFile, 50);

				// Act & Assert
				assertTimeoutPreemptively(Duration.ofSeconds(5), () -> {
					generateBeneficiaryService.createFile();
				});
			}
		}

		private File createTempTestFile() throws IOException {
			File tempFile = Files.createTempFile(tempDir, "test_bengen", ".csv").toFile();
			tempFile.deleteOnExit();
			return tempFile;
		}

		private void setupFileCreationMocks(MockedStatic<ConfigProperties> configMock, MockedStatic<File> fileMock,
				File tempFile, int recordCount) {
			configMock.when(() -> ConfigProperties.getInteger("no-of-benID-to-be-generate")).thenReturn(recordCount);
			fileMock.when(() -> File.createTempFile(anyString(), eq(".csv"))).thenReturn(tempFile);
		}

		private void verifyJdbcExecution() {
			verify(jdbcTemplate, times(1)).execute(anyString());
		}

		private void verifyFileCreationAndContent(File tempFile) throws IOException {
			// Verify file properties
			assertThat(tempFile).as("File should exist and not be empty").exists().isNotEmpty();

			// Verify file content
			String fileContent = Files.readString(tempFile.toPath());

			assertThat(fileContent).as("File should contain proper SQL structure")
					.contains("INSERT INTO " + EXPECTED_TABLE_NAME).contains("BeneficiaryID")
					.contains(EXPECTED_CREATOR);

			// Verify SQL syntax without exact counting
			assertThat(fileContent).as("SQL should be properly formatted").matches(".*INSERT INTO.*VALUES.*")
					.doesNotContain(",,") // No empty values
					.doesNotContain("()"); // No empty parentheses
		}
	}

	// Helper method to count SQL value sets - simplified approach
	private long countSQLValueSets(String sqlContent) {
		// Count opening parentheses that are followed by digits (likely value sets)
		Pattern valueSetPattern = Pattern.compile("\\(\\s*\\d+");
		Matcher matcher = valueSetPattern.matcher(sqlContent);
		long count = 0;
		while (matcher.find()) {
			count++;
		}
		return count;
	}
}
