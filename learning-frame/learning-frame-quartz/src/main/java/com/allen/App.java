package com.allen;


import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.KeyMatcher;
import org.quartz.listeners.JobListenerSupport;
import org.quartz.listeners.SchedulerListenerSupport;
import org.quartz.listeners.TriggerListenerSupport;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Slf4j
public class App {

    String group = "chan";
    JobKey jobKey = JobKey.jobKey(HelloJob.class.getName(), group);
    TriggerKey triggerKey = TriggerKey.triggerKey(HelloJob.class.getName(), group);

    @Test
    @SneakyThrows
    public void test4() {
        Scheduler scheduler = null;
        try {
            scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();

            scheduler.pauseJob(jobKey);

            Thread.sleep(10000);

            scheduler.resumeJob(jobKey);

            Thread.sleep(20000);
        } catch (SchedulerException e) {
            e.printStackTrace();
        } finally {
            if (scheduler != null && scheduler.isStarted()) {
                scheduler.shutdown(true);
            }
        }
    }

    @Test
    @SneakyThrows
    public void test3() {
        Scheduler scheduler = null;
        try {
            scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.getListenerManager().addSchedulerListener(new ChanSchedulerListener());
            scheduler.getListenerManager().addJobListener(new ChanJobListener(), KeyMatcher.keyEquals(jobKey));
            scheduler.getListenerManager().addTriggerListener(new ChanTriggerListener(), KeyMatcher.keyEquals(triggerKey));

        } catch (SchedulerException e) {
            e.printStackTrace();
        } finally {
            if (scheduler != null && scheduler.isStarted()) {
                scheduler.shutdown(true);
            }
        }
    }

    @Test
    @SneakyThrows
    public void test2() {
        Scheduler scheduler = null;
        try {
            scheduler = StdSchedulerFactory.getDefaultScheduler();

            scheduler.getListenerManager().addSchedulerListener(new ChanSchedulerListener());
            scheduler.getListenerManager().addJobListener(new ChanJobListener(), KeyMatcher.keyEquals(jobKey));
            scheduler.getListenerManager().addTriggerListener(new ChanTriggerListener(), KeyMatcher.keyEquals(triggerKey));

            scheduler.start();

            Thread.sleep(20000);

        } catch (SchedulerException e) {
            e.printStackTrace();
        } finally {
            if (scheduler != null && scheduler.isStarted()) {
                scheduler.shutdown(true);
            }
        }
    }

    @Test
    @SneakyThrows
    public void test1() {
        Scheduler scheduler = null;
        try {
            scheduler = StdSchedulerFactory.getDefaultScheduler();

            JobDataMap jobDataMap = new JobDataMap();
            jobDataMap.put("name", "chan");

            JobDetail jobDetail =
                    JobBuilder.newJob(HelloJob.class).setJobData(jobDataMap).withIdentity(jobKey).build();

            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/5 * * * * ?").withMisfireHandlingInstructionIgnoreMisfires();

            Trigger trigger =
                    TriggerBuilder.newTrigger().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

            scheduler.scheduleJob(jobDetail, trigger);

            scheduler.start();

            Thread.sleep(20000);

        } catch (SchedulerException e) {
            e.printStackTrace();
        } finally {
            if (scheduler != null && scheduler.isStarted()) {
                scheduler.shutdown(true);
            }
        }
    }

    @Data
    @DisallowConcurrentExecution
    public static class HelloJob implements Job {

        private String name;

        @Override
        public void execute(JobExecutionContext context) throws JobExecutionException {
            System.out.println("hello " + name);
            System.out.println(LocalDateTime.ofInstant(context.getScheduledFireTime().toInstant(), ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            try {
                Thread.sleep(7000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static class ChanSchedulerListener extends SchedulerListenerSupport {

    }

    public static class ChanJobListener extends JobListenerSupport {

        @Override
        public void jobToBeExecuted(JobExecutionContext context) {
            log.info("Job: {}, 执行开始", context.getJobDetail().getKey());
        }

        @Override
        public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
            log.info("Job: {}, 执行完成", context.getJobDetail().getKey());
        }

        @Override
        public String getName() {
            return "chan";
        }
    }

    public static class ChanTriggerListener extends TriggerListenerSupport {


        @Override
        public void triggerFired(Trigger trigger, JobExecutionContext context) {
            log.info("Trigger: {}, 执行开始", context.getTrigger().getKey());
        }

        @Override
        public void triggerComplete(Trigger trigger, JobExecutionContext context, Trigger.CompletedExecutionInstruction triggerInstructionCode) {
            log.info("Trigger: {}, 执行完成", context.getTrigger().getKey());
        }

        @Override
        public void triggerMisfired(Trigger trigger) {
            log.info("Trigger: {}, 错过执行 {} 时次", trigger.getKey(), LocalDateTime.ofInstant(trigger.getFinalFireTime().toInstant(),
                    ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        }

        @Override
        public String getName() {
            return "chan";
        }
    }
}
