package com.iemr.common.bengen.config.quartz;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.calendar.AnnualCalendar;
import org.quartz.impl.triggers.CalendarIntervalTriggerImpl;
import org.quartz.spi.TriggerFiredBundle;

class AutowiringSpringBeanJobFactoryDiffblueTest {
    /**
     * Method under test:
     * {@link AutowiringSpringBeanJobFactory#createJobInstance(TriggerFiredBundle)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCreateJobInstance() throws Exception {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.lang.Class.getDeclaredConstructor(java.lang.Class[])" because "clazz" is null
        //       at com.iemr.common.bengen.config.quartz.AutowiringSpringBeanJobFactory.createJobInstance(AutowiringSpringBeanJobFactory.java:44)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange
        AutowiringSpringBeanJobFactory autowiringSpringBeanJobFactory = new AutowiringSpringBeanJobFactory();
        JobDetailImpl job = new JobDetailImpl();
        CalendarIntervalTriggerImpl trigger = new CalendarIntervalTriggerImpl();
        AnnualCalendar cal = new AnnualCalendar();
        Date fireTime = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        Date scheduledFireTime = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        Date prevFireTime = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

        // Act
        autowiringSpringBeanJobFactory
                .createJobInstance(new TriggerFiredBundle(job, trigger, cal, true, fireTime, scheduledFireTime, prevFireTime,
                        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())));
    }
}
