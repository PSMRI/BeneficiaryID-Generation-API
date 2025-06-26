package com.iemr.common.bengen.config.quartz;

// import com.iemr.common.bengen.repo.BeneficiaryIdRepo;
// import com.iemr.common.bengen.service.GenerateBeneficiaryService;
// import com.iemr.common.bengen.utils.config.ConfigProperties;
import com.iemr.common.bengen.repo.BeneficiaryIdRepo;
import com.iemr.common.bengen.service.GenerateBeneficiaryService;
import com.iemr.common.bengen.utils.config.ConfigProperties;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.quartz.JobExecutionContext;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ScheduleJobServiceForBenGenTest {

    @Mock
    private BeneficiaryIdRepo beneficiaryIdRepo;

    @Mock
    private GenerateBeneficiaryService generateBeneficiaryService;

    @Mock
    private JobExecutionContext jobExecutionContext;

    @InjectMocks
    private ScheduleJobServiceForBenGen scheduleJobServiceForBenGen;

    @Test
    void execute_WhenTotalBenIDIsLessThanLowerLimit_ShouldGenerateBeneficiaryIDs() throws Exception {
        Long totalBenID = 10L;
        Integer lowerLimitOfBen = 100;

        when(beneficiaryIdRepo.countBenID()).thenReturn(totalBenID);

        try (MockedStatic<ConfigProperties> mockedConfigProperties = Mockito.mockStatic(ConfigProperties.class)) {
            mockedConfigProperties.when(() -> ConfigProperties.getInteger("lower-limit-of-beneficiary")).thenReturn(lowerLimitOfBen);

            scheduleJobServiceForBenGen.execute(jobExecutionContext);

            verify(beneficiaryIdRepo).countBenID();
            mockedConfigProperties.verify(() -> ConfigProperties.getInteger("lower-limit-of-beneficiary"));
            verify(generateBeneficiaryService).generateBeneficiaryIDs();
        }
    }

    @Test
    void execute_WhenTotalBenIDIsGreaterThanOrEqualToLowerLimit_ShouldNotGenerateBeneficiaryIDs() throws Exception {
        Long totalBenID = 100L;
        Integer lowerLimitOfBen = 100;

        when(beneficiaryIdRepo.countBenID()).thenReturn(totalBenID);

        try (MockedStatic<ConfigProperties> mockedConfigProperties = Mockito.mockStatic(ConfigProperties.class)) {
            mockedConfigProperties.when(() -> ConfigProperties.getInteger("lower-limit-of-beneficiary")).thenReturn(lowerLimitOfBen);

            scheduleJobServiceForBenGen.execute(jobExecutionContext);

            verify(beneficiaryIdRepo).countBenID();
            mockedConfigProperties.verify(() -> ConfigProperties.getInteger("lower-limit-of-beneficiary"));
            verify(generateBeneficiaryService, never()).generateBeneficiaryIDs();
        }
    }

    @Test
    void execute_WhenExceptionOccursDuringCountBenID_ShouldCatchExceptionAndNotGenerateIDs() throws Exception {
        when(beneficiaryIdRepo.countBenID()).thenThrow(new RuntimeException("DB connection error"));

        try (MockedStatic<ConfigProperties> mockedConfigProperties = Mockito.mockStatic(ConfigProperties.class)) {
            scheduleJobServiceForBenGen.execute(jobExecutionContext);

            verify(beneficiaryIdRepo).countBenID();
            mockedConfigProperties.verifyNoInteractions(); // ConfigProperties.getInteger should not be called
            verify(generateBeneficiaryService, never()).generateBeneficiaryIDs();
        }
    }

    @Test
    void execute_WhenExceptionOccursDuringGenerateBeneficiaryIDs_ShouldCatchException() throws Exception {
        Long totalBenID = 10L;
        Integer lowerLimitOfBen = 100;

        when(beneficiaryIdRepo.countBenID()).thenReturn(totalBenID);
        doThrow(new RuntimeException("Service generation error")).when(generateBeneficiaryService).generateBeneficiaryIDs();

        try (MockedStatic<ConfigProperties> mockedConfigProperties = Mockito.mockStatic(ConfigProperties.class)) {
            mockedConfigProperties.when(() -> ConfigProperties.getInteger("lower-limit-of-beneficiary")).thenReturn(lowerLimitOfBen);

            scheduleJobServiceForBenGen.execute(jobExecutionContext);

            verify(beneficiaryIdRepo).countBenID();
            mockedConfigProperties.verify(() -> ConfigProperties.getInteger("lower-limit-of-beneficiary"));
            verify(generateBeneficiaryService).generateBeneficiaryIDs();
            // The method should complete without throwing JobExecutionException as it catches generic Exception
        }
    }
}
