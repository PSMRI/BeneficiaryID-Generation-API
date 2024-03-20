package com.iemr.common.bengen.config.quartz;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.SchedulerException;
import org.quartz.ee.jmx.jboss.JBoss4RMIRemoteMBeanScheduler;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.JobExecutionContextImpl;
import org.quartz.impl.calendar.AnnualCalendar;
import org.quartz.impl.triggers.CalendarIntervalTriggerImpl;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.iemr.common.bengen.service.GenerateBeneficiaryService;

@ContextConfiguration(classes = {ScheduleJobServiceForBenGen.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class ScheduleJobServiceForBenGenDiffblueTest {
    @Mock
    private GenerateBeneficiaryService generateBeneficiaryService;

    @Autowired
    private ScheduleJobServiceForBenGen scheduleJobServiceForBenGen;

    /**
     * Method under test:
     * {@link ScheduleJobServiceForBenGen#execute(JobExecutionContext)}
     */
    @Test
    void testExecute() throws SchedulerException {
        // Arrange
        JBoss4RMIRemoteMBeanScheduler scheduler = new JBoss4RMIRemoteMBeanScheduler();
        JobDetailImpl job = new JobDetailImpl();
        CalendarIntervalTriggerImpl trigger = new CalendarIntervalTriggerImpl();
        AnnualCalendar cal = new AnnualCalendar();
        Date fireTime = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        Date scheduledFireTime = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        Date prevFireTime = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        Date nextFireTime = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        JobExecutionContextImpl arg0 = new JobExecutionContextImpl(scheduler,
                new TriggerFiredBundle(job, trigger, cal, true, fireTime, scheduledFireTime, prevFireTime, nextFireTime),
                mock(Job.class));

        // Act
        scheduleJobServiceForBenGen.execute(arg0);

        // Assert that nothing has changed
        assertEquals(-1L, arg0.getJobRunTime());
        assertEquals(0, arg0.getRefireCount());
        assertTrue(arg0.isRecovering());
        assertTrue(arg0.getMergedJobDataMap().isEmpty());
        assertSame(scheduler, arg0.getScheduler());
        assertSame(job, arg0.getJobDetail());
        assertSame(cal, arg0.getCalendar());
        assertSame(fireTime, arg0.getFireTime());
        assertSame(nextFireTime, arg0.getNextFireTime());
        assertSame(prevFireTime, arg0.getPreviousFireTime());
        assertSame(scheduledFireTime, arg0.getScheduledFireTime());
    }
}
