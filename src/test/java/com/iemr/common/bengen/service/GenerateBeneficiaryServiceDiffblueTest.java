package com.iemr.common.bengen.service;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {GenerateBeneficiaryService.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class GenerateBeneficiaryServiceDiffblueTest {
    @Autowired
    private GenerateBeneficiaryService generateBeneficiaryService;

    @Mock
    private JdbcTemplate jdbcTemplate;

    /**
     * Method under test:
     * {@link GenerateBeneficiaryService#generateBeneficiaryIDs()}
     */
    @Test
    void testGenerateBeneficiaryIDs() throws Exception {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        GenerateBeneficiaryService generateBeneficiaryService = new GenerateBeneficiaryService();

        // Act
        generateBeneficiaryService.generateBeneficiaryIDs();

        // Assert that nothing has changed
        assertNull(generateBeneficiaryService.beneficiaryIdRepo);
        assertNull(generateBeneficiaryService.jdbcTemplate);
    }

    /**
     * Method under test:
     * {@link GenerateBeneficiaryService#generateBeneficiaryIDs()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGenerateBeneficiaryIDs2() throws Exception {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.concurrent.RejectedExecutionException: Task java.util.concurrent.FutureTask@3df9a373[Not completed, task = java.util.concurrent.Executors$RunnableAdapter@3d63463[Wrapped task = com.iemr.common.bengen.service.GenerateBeneficiaryService$$Lambda$2920/0x00000008019eae38@46d4ba7d]] rejected from java.util.concurrent.ThreadPoolExecutor@77d85f0a[Terminated, pool size = 0, active threads = 0, queued tasks = 0, completed tasks = 0]
        //       at java.base/java.util.concurrent.ThreadPoolExecutor$AbortPolicy.rejectedExecution(ThreadPoolExecutor.java:2065)
        //       at java.base/java.util.concurrent.ThreadPoolExecutor.reject(ThreadPoolExecutor.java:833)
        //       at java.base/java.util.concurrent.ThreadPoolExecutor.execute(ThreadPoolExecutor.java:1365)
        //       at java.base/java.util.concurrent.AbstractExecutorService.submit(AbstractExecutorService.java:123)
        //       at com.iemr.common.bengen.service.GenerateBeneficiaryService.generateBeneficiaryIDs(GenerateBeneficiaryService.java:63)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange and Act
        generateBeneficiaryService.generateBeneficiaryIDs();
    }

    /**
     * Method under test: {@link GenerateBeneficiaryService#createFile()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCreateFile() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Temporary files were created but not deleted.
        //   The method under test created the following temporary files without deleting
        //   them:
        //     C:\Users\AN2027~1\AppData\Local\Temp\171091481068815348422067987164408.csv
        //   Please ensure that temporary files are deleted in the method under test.
        //   See https://diff.blue/R020

        // Arrange and Act
        generateBeneficiaryService.createFile();
    }

    /**
     * Method under test: {@link GenerateBeneficiaryService#createQuery(Integer)}
     */
    @Test
    void testCreateQuery() throws DataAccessException {
        // Arrange
        doNothing().when(jdbcTemplate).execute(Mockito.<String>any());

        // Act
        generateBeneficiaryService.createQuery(10);

        // Assert
        verify(jdbcTemplate).execute(eq(
                "INSERT INTO `db_identity`.`m_beneficiaryregidmapping` (`BeneficiaryID`,`Provisioned`,`Deleted`,`CreatedDate`,`CreatedBy`) VALUES ( 491605907335,b'0',b'0','1970-01-01 00:00:00.111111111','admin-batch' ), ( 926742233981,b'0',b'0','1970-01-01 00:00:00.111111111','admin-batch' ), ( 529995851301,b'0',b'0','1970-01-01 00:00:00.111111111','admin-batch' ), ( 901086995186,b'0',b'0','1970-01-01 00:00:00.111111111','admin-batch' ), ( 817976750365,b'0',b'0','1970-01-01 00:00:00.111111111','admin-batch' ), ( 266593571398,b'0',b'0','1970-01-01 00:00:00.111111111','admin-batch' ), ( 547092277953,b'0',b'0','1970-01-01 00:00:00.111111111','admin-batch' ), ( 907056617151,b'0',b'0','1970-01-01 00:00:00.111111111','admin-batch' ), ( 778201199303,b'0',b'0','1970-01-01 00:00:00.111111111','admin-batch' ), ( 784039928849,b'0',b'0','1970-01-01 00:00:00.111111111','admin-batch' ) "));
    }

    /**
     * Method under test: {@link GenerateBeneficiaryService#testLoopGenr()}
     */
    @Test
    void testTestLoopGenr() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Diffblue AI was unable to find a test

        // Arrange and Act
        generateBeneficiaryService.testLoopGenr();
    }

    /**
     * Method under test: {@link GenerateBeneficiaryService#testPMDAvoidGenr()}
     */
    @Test
    void testTestPMDAvoidGenr() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     GenerateBeneficiaryService.beneficiaryIdRepo
        //     GenerateBeneficiaryService.executor
        //     GenerateBeneficiaryService.jdbcTemplate

        // Arrange and Act
        (new GenerateBeneficiaryService()).testPMDAvoidGenr();
    }

    /**
     * Method under test:
     * {@link GenerateBeneficiaryService#getBeneficiaryIDs(Long, Integer)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetBeneficiaryIDs() throws DataAccessException {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.iemr.common.bengen.repo.BeneficiaryIdRepo.getBenIDGenerated(java.lang.Integer, java.lang.Long)" because "<local4>.beneficiaryIdRepo" is null
        //       at com.iemr.common.bengen.service.GenerateBeneficiaryService.getBeneficiaryIDs(GenerateBeneficiaryService.java:180)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange
        doNothing().when(jdbcTemplate).execute(Mockito.<String>any());

        // Act
        generateBeneficiaryService.getBeneficiaryIDs(1L, 1);
    }
}
