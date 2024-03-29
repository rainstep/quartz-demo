package com.example.quartzdemo.service;

import org.quartz.Job;
import org.quartz.SchedulerException;

import java.util.Date;
import java.util.Map;

public interface QuartzService {
    String DEFAULT_JOB_GROUP_NAME = "defaultJobGroup";
    String DEFAULT_TRIGGER_GROUP_NAME = "defaultTriggerGroup";

    boolean addSimpleTask(String jobName,
                          Class<? extends Job> jobClass, Map<?, ?> jobData,
                          String triggerName,
                          Date startTime, Date endTime,
                          int intervalSeconds, int repeatCount) throws SchedulerException;

    boolean addSimpleTask(String jobName, String jobGroupName,
                          Class<? extends Job> jobClass, Map<?, ?> jobData,
                          String triggerName, String triggerGroupName,
                          Date startTime, Date endTime,
                          int intervalSeconds, int repeatCount) throws SchedulerException;

    boolean updateSimpleTask(String triggerName,
                             Date startTime, Date endTime,
                             int intervalSeconds, int repeatCount) throws SchedulerException;

    boolean updateSimpleTask(String triggerName, String triggerGroupName,
                             Date startTime, Date endTime,
                             int intervalSeconds, int repeatCount) throws SchedulerException;

    boolean saveSimpleTask(String jobName,
                           Class<? extends Job> jobClass, Map<?, ?> jobData,
                           String triggerName,
                           Date startTime, Date endTime,
                           int intervalSeconds, int repeatCount) throws SchedulerException;

    boolean saveSimpleTask(String jobName, String jobGroupName,
                           Class<? extends Job> jobClass, Map<?, ?> jobData,
                           String triggerName, String triggerGroupName,
                           Date startTime, Date endTime,
                           int intervalSeconds, int repeatCount) throws SchedulerException;

    boolean addCronTask(String jobName,
                        Class<? extends Job> jobClass, Map<?, ?> jobData,
                        String triggerName,
                        Date startTime, Date endTime,
                        String cron) throws SchedulerException;

    boolean addCronTask(String jobName, String jobGroupName,
                        Class<? extends Job> jobClass, Map<?, ?> jobData,
                        String triggerName, String triggerGroupName,
                        Date startTime, Date endTime,
                        String cron) throws SchedulerException;

    boolean updateCronTask(String triggerName,
                           Date startTime, Date endTime, String cron) throws SchedulerException;

    boolean updateCronTask(String triggerName, String triggerGroupName,
                           Date startTime, Date endTime, String cron) throws SchedulerException;

    boolean saveCronTask(String jobName,
                         Class<? extends Job> jobClass, Map<?, ?> jobData,
                         String triggerName,
                         Date startTime, Date endTime,
                         String cron) throws SchedulerException;

    boolean saveCronTask(String jobName, String jobGroupName,
                         Class<? extends Job> jobClass, Map<?, ?> jobData,
                         String triggerName, String triggerGroupName,
                         Date startTime, Date endTime,
                         String cron) throws SchedulerException;

    boolean pauseTrigger(String triggerName) throws SchedulerException;

    boolean pauseTrigger(String triggerName, String triggerGroupName) throws SchedulerException;

    boolean pauseJob(String jobName) throws SchedulerException;

    boolean pauseJob(String jobName, String jobGroupName) throws SchedulerException;

    boolean pauseAll() throws SchedulerException;

    boolean resumeTrigger(String triggerName) throws SchedulerException;

    boolean resumeTrigger(String triggerName, String triggerGroupName) throws SchedulerException;

    boolean resumeJob(String jobName) throws SchedulerException;

    boolean resumeJob(String jobName, String jobGroupName) throws SchedulerException;

    boolean resumeAll() throws SchedulerException;

    boolean removeTrigger(String triggerName) throws SchedulerException;

    boolean removeTrigger(String triggerName, String triggerGroupName) throws SchedulerException;

    boolean removeJob(String jobName) throws SchedulerException;

    boolean removeJob(String jobName, String jobGroupName) throws SchedulerException;

}
