package com.example.quartzdemo.service.impl;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class QuartzServiceImpl2 {
    public static final Logger logger = LoggerFactory.getLogger(QuartzServiceImpl2.class);

    @Autowired
    private Scheduler scheduler;

    public boolean addSimpleTask(String jobName, String jobGroupName,
                              Class<? extends Job> jobClass, Map<?, ?> jobData,
                              String triggerName, String triggerGroupName,
                              Date startTime, Date endTime,
                              int intervalSeconds, int repeatCount) throws SchedulerException {
        // 获取或创建JobDetail
        JobDetail jobDetail = this.getOrCreateJobDetail(jobName, jobGroupName, jobClass, jobData);

        // 判断任务是否已存在
        TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
        if (scheduler.checkExists(triggerKey)) {
            logger.warn("Trigger({}.{})已存在", triggerName, triggerGroupName);
            return false;
        }

        // 创建Trigger
        SimpleTrigger trigger = this.createSimpleTrigger(triggerName, triggerGroupName,
                startTime, endTime,
                intervalSeconds, repeatCount, jobDetail);

        // 执行任务
        scheduler.scheduleJob(trigger);
        return true;
    }

    public boolean addCronTask(String jobName, String jobGroupName,
                            Class<? extends Job> jobClass, Map<?, ?> jobData,
                            String triggerName, String triggerGroupName,
                            Date startTime, Date endTime,
                            String cron) throws SchedulerException {
        // 获取或创建JobDetail
        JobDetail jobDetail = this.getOrCreateJobDetail(jobName, jobGroupName, jobClass, jobData);

        // 判断任务是否已存在
        TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
        if (scheduler.checkExists(triggerKey)) {
            logger.warn("Trigger({}.{})已存在", triggerName, triggerGroupName);
            return false;
        }

        // 创建Trigger
        CronTrigger trigger = this.createCronTrigger(triggerName, triggerGroupName, startTime, endTime, cron, jobDetail);

        // 执行任务
        scheduler.scheduleJob(trigger);
        return true;
    }


    public boolean updateSimpleTask(String triggerName, String triggerGroupName,
                                 Date startTime, Date endTime,
                                 int intervalSeconds, int repeatCount) throws SchedulerException {
        TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
        // 判断任务是否存在
        Trigger trigger = scheduler.getTrigger(triggerKey);
        if(trigger == null) {
            logger.warn("Trigger({}.{})不存在", triggerName, triggerGroupName);
            return false;
        }

        // 更新Trigger
        ScheduleBuilder<SimpleTrigger> scheduleBuilder = this.createSimpleScheduleBuilder(intervalSeconds, repeatCount);
        trigger = this.updateTrigger(trigger, startTime, endTime, scheduleBuilder);

        // 重启任务
        scheduler.pauseTrigger(triggerKey);
        scheduler.rescheduleJob(triggerKey, trigger);
        return true;
    }


    public boolean updateCronTask(String triggerName, String triggerGroupName,
                               Date startTime, Date endTime, String cron) throws SchedulerException {
        TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
        // 判断任务是否存在
        Trigger trigger = scheduler.getTrigger(triggerKey);
        if(trigger == null) {
            logger.warn("Trigger({}.{})不存在", triggerName, triggerGroupName);
            return false;
        }

        // 更新Trigger
        ScheduleBuilder<CronTrigger> scheduleBuilder = this.createCronScheduleBuilder(cron);
        trigger = this.updateTrigger(trigger, startTime, endTime, scheduleBuilder);

        // 重启任务
        scheduler.pauseTrigger(triggerKey);
        scheduler.rescheduleJob(triggerKey, trigger);
        return true;
    }

    public boolean saveSimpleTask(String jobName, String jobGroupName,
                               Class<? extends Job> jobClass, Map<?, ?> jobData,
                               String triggerName, String triggerGroupName,
                               Date startTime, Date endTime,
                               int intervalSeconds, int repeatCount) throws SchedulerException {
        JobDetail jobDetail = this.getOrCreateJobDetail(jobName, jobGroupName, jobClass, jobData);
        TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
        Trigger trigger = scheduler.getTrigger(triggerKey);
        if(trigger == null) {
            trigger = this.createSimpleTrigger(triggerName, triggerGroupName, startTime, endTime, intervalSeconds, repeatCount, jobDetail);
            scheduler.scheduleJob(trigger);
        } else {
            ScheduleBuilder<SimpleTrigger> scheduleBuilder = this.createSimpleScheduleBuilder(intervalSeconds, repeatCount);
            trigger = this.updateTrigger(trigger, startTime, endTime, scheduleBuilder);
            scheduler.rescheduleJob(triggerKey, trigger);
        }
        return true;
    }

//    public void saveCronTask(String jobName, String jobGroupName, Class<? extends Job> jobClass, String triggerName, String triggerGroupName,
//                             Date startTime, Date endTime, String cron, Map<?, ?> dataMap) {
//
//    }

    public boolean pauseTrigger(String triggerName, String triggerGroupName) throws SchedulerException {
        TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
        if (!scheduler.checkExists(triggerKey)) {
            logger.warn("Trigger({}.{})不存在", triggerName, triggerGroupName);
            return false;
        }
        scheduler.pauseTrigger(triggerKey);
        return true;
    }

    public boolean pauseJob(String jobName, String jobGroupName) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
        if (!scheduler.checkExists(jobKey)) {
            logger.warn("Job({}.{})不存在", jobName, jobGroupName);
            return false;
        }
        scheduler.pauseJob(jobKey);
        return true;
    }

    public boolean pauseAll() throws SchedulerException {
        scheduler.pauseAll();
        return true;
    }


    public boolean resumeTrigger(String triggerName, String triggerGroupName) throws SchedulerException {
        TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
        if (!scheduler.checkExists(triggerKey)) {
            logger.warn("Trigger({}.{})不存在", triggerName, triggerGroupName);
            return false;
        }
        scheduler.resumeTrigger(triggerKey);
        return true;
    }

    public boolean resumeJob(String jobName, String jobGroupName) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
        if (!scheduler.checkExists(jobKey)) {
            logger.warn("Job({}.{})不存在", jobName, jobGroupName);
            return false;
        }
        scheduler.resumeJob(jobKey);
        return true;
    }

    public boolean resumeAll() throws SchedulerException {
        scheduler.resumeAll();
        return true;
    }


    public boolean removeTrigger(String triggerName, String triggerGroupName) throws SchedulerException {
        TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
        if (!scheduler.checkExists(triggerKey)) {
            logger.warn("Trigger({}.{})不存在", triggerName, triggerGroupName);
            return false;
        }
        scheduler.pauseTrigger(triggerKey);
        scheduler.unscheduleJob(triggerKey);
        return true;
    }

    public boolean removeJob(String jobName, String jobGroupName) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
        if (!scheduler.checkExists(jobKey)) {
            logger.warn("Job({}.{})不存在", jobName, jobGroupName);
            return false;
        }
        scheduler.pauseJob(jobKey);
        scheduler.deleteJob(jobKey);
        return true;
    }



    /* Private Methods */

    /* Create JobDetail */
    private JobDetail getOrCreateJobDetail(String jobName, String jobGroupName,
                                           Class<? extends Job> jobClass, Map<?, ?> jobData) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        if (jobDetail == null) {
            JobBuilder jobBuilder = JobBuilder.newJob(jobClass)
                    .withIdentity(jobKey)
                    .storeDurably(); // 设置durably=true, 可以一个detail绑定多个trigger
            if (jobData != null) {
                jobBuilder.setJobData(new JobDataMap(jobData));
            }
            jobDetail = jobBuilder.build();
            scheduler.addJob(jobDetail, false);
        }
        return jobDetail;
    }

    /*  <-- Create ScheduleBuilder */
    private ScheduleBuilder<SimpleTrigger> createSimpleScheduleBuilder(int intervalSeconds, int repeatCount) {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder
                .simpleSchedule()
                .withIntervalInSeconds(intervalSeconds);
        if (repeatCount > 0) {
            scheduleBuilder.withRepeatCount(repeatCount);
        } else if (repeatCount < 0){
            scheduleBuilder.repeatForever();
        }
        return scheduleBuilder;
    }


    private ScheduleBuilder<CronTrigger> createCronScheduleBuilder(String cron) {
        return CronScheduleBuilder.cronSchedule(cron);
    }
    /* Create ScheduleBuilder --> */


    /*  <-- Create TriggerBuilder */
    private <T extends Trigger> TriggerBuilder<T> createTriggerBuilder(String triggerName, String triggerGroupName,
                                                                       Date startTime, Date endTime,
                                                                       ScheduleBuilder<T> scheduleBuilder, JobDetail jobDetail) {
        TriggerBuilder<T > triggerBuilder = TriggerBuilder.newTrigger()
                .withIdentity(triggerName, triggerGroupName)
                .withSchedule(scheduleBuilder);
        if (startTime != null) triggerBuilder.startAt(startTime);
        if (endTime != null) triggerBuilder.endAt(endTime);
        if (jobDetail != null) triggerBuilder.forJob(jobDetail);
        return triggerBuilder;
    }

    /* Create TriggerBuilder --> */


    /*  <-- Create Trigger */
    private SimpleTrigger createSimpleTrigger(String triggerName, String triggerGroupName,
                                              Date startTime, Date endTime,
                                              int intervalSeconds, int repeatCount, JobDetail jobDetail) {
        ScheduleBuilder<SimpleTrigger> scheduleBuilder = this.createSimpleScheduleBuilder(intervalSeconds, repeatCount);
        TriggerBuilder<SimpleTrigger> triggerBuilder = this.createTriggerBuilder(triggerName, triggerGroupName, startTime, endTime, scheduleBuilder, jobDetail);
        return triggerBuilder.build();
    }

    private CronTrigger createCronTrigger(String triggerName, String triggerGroupName,
                                              Date startTime, Date endTime,
                                              String cron, JobDetail jobDetail) {
        ScheduleBuilder<CronTrigger> scheduleBuilder = this.createCronScheduleBuilder(cron);
        TriggerBuilder<CronTrigger> triggerBuilder = this.createTriggerBuilder(triggerName, triggerGroupName, startTime, endTime, scheduleBuilder, jobDetail);
        return triggerBuilder.build();
    }
    /* Create Trigger --> */


    /*  <-- Update Trigger */
    private Trigger updateTrigger(Trigger trigger,
                                  Date startTime, Date endTime,
                                  ScheduleBuilder scheduleBuilder) {
        TriggerBuilder<? extends Trigger> triggerBuilder = trigger.getTriggerBuilder();
        triggerBuilder.withSchedule(scheduleBuilder);
        if (startTime != null) triggerBuilder.startAt(startTime);
        if (endTime != null) triggerBuilder.endAt(endTime);
        return triggerBuilder.build();
    }
    /* Update Trigger --> */






}
