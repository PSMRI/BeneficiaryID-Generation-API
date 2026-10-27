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
package com.iemr.common.bengen.config.quartz;

import java.util.Properties;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.transaction.PlatformTransactionManager;

import com.iemr.common.bengen.utils.config.ConfigProperties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("QuartzConfig Test Suite")
class QuartzConfigTest {

    private static final String DEFAULT_CRON = "1 0 0 * * ?";

    @Mock
    private PlatformTransactionManager transactionManager;

    @Mock
    private ApplicationContext applicationContext;

    @Mock
    private AutowireCapableBeanFactory beanFactory;

    private QuartzConfig quartzConfig;
    private Properties originalConfigProperties;

    @BeforeEach
    @DisplayName("Wire the configuration with mocked Spring collaborators before each test")
    void setUp() {
        quartzConfig = new QuartzConfig();
        ReflectionTestUtils.setField(quartzConfig, "transactionManager", transactionManager);
        ReflectionTestUtils.setField(quartzConfig, "applicationContext", applicationContext);
        new ConfigProperties();
        originalConfigProperties = (Properties) ReflectionTestUtils.getField(ConfigProperties.class, "properties");
    }

    @AfterEach
    @DisplayName("Restore the shared ConfigProperties statics after each test")
    void tearDown() {
        ReflectionTestUtils.setField(ConfigProperties.class, "properties", originalConfigProperties);
    }

    @Nested
    @DisplayName("Job and trigger beans")
    class JobAndTriggerTests {

        @Test
        @DisplayName("processMQJobForBenGen should build the beneficiary generation job in the spring-quartz group")
        void processMQJobForBenGen_shouldBuildJobInSpringQuartzGroup() {
            JobDetailFactoryBean factory = quartzConfig.processMQJobForBenGen();
            factory.afterPropertiesSet();

            JobDetail jobDetail = factory.getObject();
            assertNotNull(jobDetail);
            assertEquals(ScheduleJobServiceForBenGen.class, jobDetail.getJobClass());
            assertEquals("spring-quartz", jobDetail.getKey().getGroup());
        }

        @Test
        @DisplayName("processMQTriggerForBenGen should use the midnight default when the scheduler is disabled")
        void processMQTriggerForBenGen_shouldUseDefaultCronWhenSchedulerDisabled() throws Exception {
            Properties stub = new Properties();
            stub.setProperty("start-bengen-scheduler", "false");
            ReflectionTestUtils.setField(ConfigProperties.class, "properties", stub);

            CronTriggerFactoryBean factory = quartzConfig.processMQTriggerForBenGen();
            factory.afterPropertiesSet();

            CronTrigger trigger = factory.getObject();
            assertEquals(DEFAULT_CRON, trigger.getCronExpression());
            assertEquals("spring-quartz", trigger.getKey().getGroup());
        }

        @Test
        @DisplayName("processMQTriggerForBenGen should use the configured cron when the scheduler is enabled")
        void processMQTriggerForBenGen_shouldUseConfiguredCronWhenSchedulerEnabled() throws Exception {
            Properties stub = new Properties();
            stub.setProperty("start-bengen-scheduler", "true");
            stub.setProperty("cron-scheduler-bengen", "0 0 2 * * ?");
            ReflectionTestUtils.setField(ConfigProperties.class, "properties", stub);

            CronTriggerFactoryBean factory = quartzConfig.processMQTriggerForBenGen();
            factory.afterPropertiesSet();

            assertEquals("0 0 2 * * ?", factory.getObject().getCronExpression());
        }
    }

    @Nested
    @DisplayName("Scheduler bean")
    class SchedulerTests {

        @Test
        @DisplayName("quartzScheduler should overwrite existing jobs and carry the configured scheduler name")
        void quartzScheduler_shouldOverwriteExistingJobsWithConfiguredName() {
            SchedulerFactoryBean scheduler = quartzConfig.quartzScheduler();

            assertNotNull(scheduler);
            assertEquals(Boolean.TRUE, ReflectionTestUtils.getField(scheduler, "overwriteExistingJobs"));
            assertEquals("jelies-quartz-scheduler", ReflectionTestUtils.getField(scheduler, "schedulerName"));
        }

        @Test
        @DisplayName("quartzScheduler should install an autowiring job factory bound to the application context")
        void quartzScheduler_shouldInstallAutowiringJobFactory() {
            when(applicationContext.getAutowireCapableBeanFactory()).thenReturn(beanFactory);

            SchedulerFactoryBean scheduler = quartzConfig.quartzScheduler();

            Object jobFactory = ReflectionTestUtils.getField(scheduler, "jobFactory");
            assertTrue(jobFactory instanceof AutowiringSpringBeanJobFactory);
            assertSame(beanFactory, ReflectionTestUtils.getField(jobFactory, "beanFactory"),
                    "the job factory must autowire jobs from the application context");
        }

        @Test
        @DisplayName("quartzScheduler should use the injected transaction manager")
        void quartzScheduler_shouldUseInjectedTransactionManager() {
            SchedulerFactoryBean scheduler = quartzConfig.quartzScheduler();

            assertSame(transactionManager, ReflectionTestUtils.getField(scheduler, "transactionManager"));
        }
    }

    @Nested
    @DisplayName("Quartz properties bean")
    class QuartzPropertiesTests {

        @Test
        @DisplayName("quartzProperties should load the Quartz thread pool settings from application.properties")
        void quartzProperties_shouldLoadThreadPoolSettings() {
            Properties properties = quartzConfig.quartzProperties();

            assertNotNull(properties);
            assertEquals("org.quartz.simpl.SimpleThreadPool",
                    properties.getProperty("org.quartz.threadPool.class"));
            assertEquals("true", properties.getProperty("org.quartz.threadPool.makeThreadsDaemons"));
        }
    }
}
