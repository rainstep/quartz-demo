package com.example.quartzdemo.service.impl;

import com.example.quartzdemo.service.QuartzService;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class QuartzServiceImpl implements QuartzService {
    private static final Logger logger = LoggerFactory.getLogger(QuartzService.class);

    @Autowired
    private Scheduler scheduler;

    @Override
    public boolean addSimpleTask(String jobName,
                                 Class<? extends Job> jobClass, Map<?, ?> jobData,
                                 String triggerName,
                                 Date startTime, Date endTime,
                                 int intervalSeconds, int repeatCount) throws SchedulerException {
        return this.addSimpleTask(jobName, DEFAULT_JOB_GROUP_NAME,
                jobClass, jobData,
                triggerName, DEFAULT_TRIGGER_GROUP_NAME,
                startTime, endTime,
                intervalSeconds, repeatCount);
    }

    @Override
    public boolean addSimpleTask(String jobName, String jobGroupName,
                              Class<? extends Job> jobClass, Map<?, ?> jobData,
                              String triggerName, String triggerGroupName,
                              Date startTime, Date endTime,
                              int intervalSeconds, int repeatCount) throws SchedulerException {
        // 创建JobDetail
        JobDetail jobDetail = this.createJobDetail(jobName, jobGroupName, jobClass, jobData);
        if (jobDetail == null) return false;

        // 创建Trigger
        SimpleTrigger trigger = this.createSimpleTrigger(triggerName, triggerGroupName,
                startTime, endTime,
                intervalSeconds, repeatCount);
        if (trigger == null) return false;

        // 执行任务
        scheduler.scheduleJob(jobDetail, trigger);
        return true;
    }

    @Override
    public boolean updateSimpleTask(String triggerName,
                                    Date startTime, Date endTime,
                                    int intervalSeconds, int repeatCount) throws SchedulerException {
        return this.updateSimpleTask(triggerName, DEFAULT_TRIGGER_GROUP_NAME,
                startTime, endTime,
                intervalSeconds, repeatCount);
    }

    @Override
    public boolean updateSimpleTask(String triggerName, String triggerGroupName,
                                    Date startTime, Date endTime,
                                    int intervalSeconds, int repeatCount) throws SchedulerException {

        TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);

        // 更新Trigger
        ScheduleBuilder<SimpleTrigger> scheduleBuilder = this.createSimpleScheduleBuilder(intervalSeconds, repeatCount);
        Trigger trigger = this.updateTrigger(triggerKey, startTime, endTime, scheduleBuilder);
        if (trigger == null) return false;

        // 重启任务
        scheduler.pauseTrigger(triggerKey);
        scheduler.rescheduleJob(triggerKey, trigger);
        return true;
    }

    @Override
    public boolean saveSimpleTask(String jobName,
                                  Class<? extends Job> jobClass, Map<?, ?> jobData,
                                  String triggerName,
                                  Date startTime, Date endTime,
                                  int intervalSeconds, int repeatCount) throws SchedulerException {
        return this.saveSimpleTask(jobName, DEFAULT_JOB_GROUP_NAME,
                jobClass, jobData,
                triggerName, DEFAULT_TRIGGER_GROUP_NAME,
                startTime, endTime,
                intervalSeconds, repeatCount);
    }

    @Override
    public boolean saveSimpleTask(String jobName, String jobGroupName,
                                  Class<? extends Job> jobClass, Map<?, ?> jobData,
                                  String triggerName, String triggerGroupName,
                                  Date startTime, Date endTime,
                                  int intervalSeconds, int repeatCount) throws SchedulerException {

        TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
        if(scheduler.checkExists(triggerKey)) {
            return this.updateSimpleTask(triggerName, triggerGroupName,
                    startTime, endTime,
                    intervalSeconds, repeatCount);
        } else {
            return this.addSimpleTask(jobName, jobGroupName,
                    jobClass, jobData,
                    triggerName, triggerGroupName,
                    startTime, endTime,
                    intervalSeconds, repeatCount);
        }
    }

    @Override
    public boolean addCronTask(String jobName,
                               Class<? extends Job> jobClass, Map<?, ?> jobData,
                               String triggerName,
                               Date startTime, Date endTime,
                               String cron) throws SchedulerException {
        return this.addCronTask(jobName, DEFAULT_JOB_GROUP_NAME,
                jobClass, jobData,
                triggerName, DEFAULT_TRIGGER_GROUP_NAME,
                startTime, endTime,
                cron);
    }

    @Override
    public boolean addCronTask(String jobName, String jobGroupName,
                               Class<? extends Job> jobClass, Map<?, ?> jobData,
                               String triggerName, String triggerGroupName,
                               Date startTime, Date endTime,
                               String cron) throws SchedulerException {
        // 创建JobDetail
        JobDetail jobDetail = this.createJobDetail(jobName, jobGroupName, jobClass, jobData);
        if (jobDetail == null) return false;

        // 创建Trigger
        CronTrigger trigger = this.createCronTrigger(triggerName, triggerGroupName, startTime, endTime, cron);
        if (trigger == null) return false;

        // 执行任务
        scheduler.scheduleJob(jobDetail, trigger);
        return true;
    }

    @Override
    public boolean updateCronTask(String triggerName,
                                  Date startTime, Date endTime, String cron) throws SchedulerException {
        return this.updateCronTask(triggerName, DEFAULT_TRIGGER_GROUP_NAME,
                startTime, endTime,
                cron);
    }

    @Override
    public boolean updateCronTask(String triggerName, String triggerGroupName,
                               Date startTime, Date endTime, String cron) throws SchedulerException {
        TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);

        // 更新Trigger
        ScheduleBuilder<CronTrigger> scheduleBuilder = this.createCronScheduleBuilder(cron);
        Trigger trigger = this.updateTrigger(triggerKey, startTime, endTime, scheduleBuilder);
        if (trigger == null) return false;

        // 重启任务
        scheduler.pauseTrigger(triggerKey);
        scheduler.rescheduleJob(triggerKey, trigger);
        return true;
    }

    @Override
    public boolean saveCronTask(String jobName,
                                Class<? extends Job> jobClass, Map<?, ?> jobData,
                                String triggerName,
                                Date startTime, Date endTime,
                                String cron) throws SchedulerException {
        return this.saveCronTask(jobName, DEFAULT_JOB_GROUP_NAME,
                jobClass, jobData,
                triggerName, DEFAULT_TRIGGER_GROUP_NAME,
                startTime, endTime,
                cron);
    }

    @Override
    public boolean saveCronTask(String jobName, String jobGroupName,
                             Class<? extends Job> jobClass, Map<?, ?> jobData,
                             String triggerName, String triggerGroupName,
                             Date startTime, Date endTime,
                             String cron) throws SchedulerException {

        TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
        if(scheduler.checkExists(triggerKey)) {
            return this.updateCronTask(triggerName, triggerGroupName,
                    startTime, endTime,
                    cron);
        } else {
            return this.addCronTask(jobName, jobGroupName,
                    jobClass, jobData,
                    triggerName, triggerGroupName,
                    startTime, endTime,
                    cron);
        }
    }

    @Override
    public boolean pauseTrigger(String triggerName) throws SchedulerException {
        return this.pauseTrigger(triggerName, DEFAULT_TRIGGER_GROUP_NAME);
    }

    @Override
    public boolean pauseTrigger(String triggerName, String triggerGroupName) throws SchedulerException {
        TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
        if (!scheduler.checkExists(triggerKey)) {
            logger.warn("Trigger({}.{})不存在", triggerName, triggerGroupName);
            return false;
        }
        scheduler.pauseTrigger(triggerKey);
        return true;
    }

    @Override
    public boolean pauseJob(String jobName) throws SchedulerException {
        return this.pauseJob(jobName, DEFAULT_JOB_GROUP_NAME);
    }

    @Override
    public boolean pauseJob(String jobName, String jobGroupName) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
        if (!scheduler.checkExists(jobKey)) {
            logger.warn("Job({}.{})不存在", jobName, jobGroupName);
            return false;
        }
        scheduler.pauseJob(jobKey);
        return true;
    }

    @Override
    public boolean pauseAll() throws SchedulerException {
        scheduler.pauseAll();
        return true;
    }

    @Override
    public boolean resumeTrigger(String triggerName) throws SchedulerException {
        return this.resumeTrigger(triggerName, DEFAULT_TRIGGER_GROUP_NAME);
    }

    @Override
    public boolean resumeTrigger(String triggerName, String triggerGroupName) throws SchedulerException {
        TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
        if (!scheduler.checkExists(triggerKey)) {
            logger.warn("Trigger({}.{})不存在", triggerName, triggerGroupName);
            return false;
        }
        scheduler.resumeTrigger(triggerKey);
        return true;
    }

    @Override
    public boolean resumeJob(String jobName) throws SchedulerException {
        return this.resumeJob(jobName, DEFAULT_JOB_GROUP_NAME);
    }

    @Override
    public boolean resumeJob(String jobName, String jobGroupName) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
        if (!scheduler.checkExists(jobKey)) {
            logger.warn("Job({}.{})不存在", jobName, jobGroupName);
            return false;
        }
        scheduler.resumeJob(jobKey);
        return true;
    }

    @Override
    public boolean resumeAll() throws SchedulerException {
        scheduler.resumeAll();
        return true;
    }

    @Override
    public boolean removeTrigger(String triggerName) throws SchedulerException {
        return this.removeTrigger(triggerName, DEFAULT_TRIGGER_GROUP_NAME);
    }

    @Override
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

    @Override
    public boolean removeJob(String jobName) throws SchedulerException {
        return this.removeJob(jobName, DEFAULT_JOB_GROUP_NAME);
    }

    @Override
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



    ///////////////////////////////////////////////////////////////////////////
    ///
    /// Private Methods
    ///
    ///////////////////////////////////////////////////////////////////////////

    // <-- Create JobDetail
    private JobDetail createJobDetail(String jobName, String jobGroupName,
                                      Class<? extends Job> jobClass, Map<?, ?> jobData) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
        if (scheduler.checkExists(jobKey)) {
            logger.warn("Job({}.{})已存在", jobName, jobGroupName);
            return null;
        }
        JobBuilder jobBuilder = JobBuilder.newJob(jobClass)
                .withIdentity(jobKey);
        if (jobData != null) {
            jobBuilder.setJobData(new JobDataMap(jobData));
        }
        return jobBuilder.build();
    }
    // Create JobDetail -->

    //  <-- Create ScheduleBuilder
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
    // Create ScheduleBuilder -->


    //  <-- Create TriggerBuilder
    private <T extends Trigger> TriggerBuilder<T> createTriggerBuilder(String triggerName, String triggerGroupName,
                                                                       Date startTime, Date endTime,
                                                                       ScheduleBuilder<T> scheduleBuilder) throws SchedulerException {
        // 判断任务是否已存在
        TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
        if (scheduler.checkExists(triggerKey)) {
            logger.warn("Trigger({}.{})已存在", triggerName, triggerGroupName);
            return null;
        }
        TriggerBuilder<T> triggerBuilder = TriggerBuilder.newTrigger()
                .withIdentity(triggerKey)
                .withSchedule(scheduleBuilder);
        if (startTime != null) triggerBuilder.startAt(startTime);
        if (endTime != null) triggerBuilder.endAt(endTime);
        return triggerBuilder;
    }
    // Create TriggerBuilder -->


    //  <-- Create Trigger
    private SimpleTrigger createSimpleTrigger(String triggerName, String triggerGroupName,
                                              Date startTime, Date endTime,
                                              int intervalSeconds, int repeatCount) throws SchedulerException {
        ScheduleBuilder<SimpleTrigger> scheduleBuilder = this.createSimpleScheduleBuilder(intervalSeconds, repeatCount);
        TriggerBuilder<SimpleTrigger> triggerBuilder = this.createTriggerBuilder(triggerName, triggerGroupName, startTime, endTime, scheduleBuilder);
        if (triggerBuilder == null) return null;
        return triggerBuilder.build();
    }

    private CronTrigger createCronTrigger(String triggerName, String triggerGroupName,
                                          Date startTime, Date endTime,
                                          String cron) throws SchedulerException {
        ScheduleBuilder<CronTrigger> scheduleBuilder = this.createCronScheduleBuilder(cron);
        TriggerBuilder<CronTrigger> triggerBuilder = this.createTriggerBuilder(triggerName, triggerGroupName, startTime, endTime, scheduleBuilder);
        if (triggerBuilder == null) return null;
        return triggerBuilder.build();
    }
    // Create Trigger -->


    //  <-- Update Trigger
    private Trigger updateTrigger(TriggerKey triggerKey,
                                  Date startTime, Date endTime,
                                  ScheduleBuilder scheduleBuilder) throws SchedulerException {
        // 判断任务是否存在
        Trigger trigger = scheduler.getTrigger(triggerKey);
        if(trigger == null) {
            logger.warn("Trigger({}.{})不存在", triggerKey.getName(), triggerKey.getGroup());
            return null;
        }
        TriggerBuilder<? extends Trigger> triggerBuilder = trigger.getTriggerBuilder();
        triggerBuilder.withSchedule(scheduleBuilder);
        if (startTime != null) triggerBuilder.startAt(startTime);
        if (endTime != null) triggerBuilder.endAt(endTime);
        return triggerBuilder.build();
    }
    // Update Trigger -->

}
