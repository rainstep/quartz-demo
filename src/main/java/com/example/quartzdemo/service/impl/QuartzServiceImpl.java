package com.example.quartzdemo.service.impl;

import com.example.quartzdemo.service.QuartzService;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class QuartzServiceImpl implements QuartzService {

    @Autowired
    private Scheduler scheduler;

    /**
     * 添加简单任务
     * @param jobName 任务名称
     * @param jobClass 任务执行类
     * @param triggerName 触发器名称
     * @param startTime 任务开始时间
     * @param endTime 任务停止时间
     * @param intervalSeconds 循环间隔(s)
     * @param repeatCount 复次数(小于0表示无限重复,等于0表示不重复)
     */
    @Override
    public void addSimpleTask(String jobName, Class<? extends Job> jobClass, String triggerName,
                              Date startTime, Date endTime,
                              int intervalSeconds, int repeatCount,
                              Map<?, ?> dataMap) {
        this.addSimpleTask(jobName, DEFAULT_JOB_GROUP_NAME, jobClass,
                triggerName, DEFAULT_TRIGGER_GROUP_NAME,
                startTime, endTime,
                intervalSeconds, repeatCount,
                dataMap);
    }

    /**
     * 添加简单任务
     * @param jobName 任务名称
     * @param jobGroupName 任务分组名称
     * @param jobClass 任务执行类
     * @param triggerName 触发器名称
     * @param triggerGroupName 触发器分组名称
     * @param startTime 任务开始时间
     * @param endTime 任务停止时间
     * @param intervalSeconds 循环间隔(s)
     * @param repeatCount 复次数(小于0表示无限重复,等于0表示不重复)
     */
    @Override
    public void addSimpleTask(String jobName, String jobGroupName, Class<? extends Job> jobClass,
                              String triggerName, String triggerGroupName,
                              Date startTime, Date endTime,
                              int intervalSeconds, int repeatCount,
                              Map<?, ?> dataMap) {
        SimpleScheduleBuilder scheduleBuilder = this.createSimpleScheduleBuilder(intervalSeconds, repeatCount);
        this.scheduleJob(jobName, jobGroupName, jobClass,
                triggerName, triggerGroupName,
                startTime, endTime, dataMap, scheduleBuilder);
    }

    /**
     * 修改简单任务
     * @param triggerName 触发器名称
     * @param startTime 任务开始时间
     * @param endTime 任务停止时间
     * @param intervalSeconds 循环间隔(s)
     * @param repeatCount 复次数(小于0表示无限重复,等于0表示不重复)
     */
    @Override
    public void modifySimpleTask(String triggerName,
                                 Date startTime, Date endTime,
                                 int intervalSeconds, int repeatCount,
                                 Map<?, ?> dataMap) {
        this.modifySimpleTask(triggerName, DEFAULT_TRIGGER_GROUP_NAME,
                startTime, endTime,
                intervalSeconds, repeatCount,
                dataMap);
    }

    /**
     * 修改简单任务
     * @param triggerName 触发器名称
     * @param triggerGroupName 触发器分组名称
     * @param startTime 任务开始时间
     * @param endTime 任务停止时间
     * @param intervalSeconds 循环间隔(s)
     * @param repeatCount 复次数(小于0表示无限重复,等于0表示不重复)
     */
    @Override
    public void modifySimpleTask(String triggerName, String triggerGroupName,
                                 Date startTime, Date endTime,
                                 int intervalSeconds, int repeatCount,
                                 Map<?, ?> dataMap) {
        SimpleScheduleBuilder scheduleBuilder = this.createSimpleScheduleBuilder(intervalSeconds, repeatCount);
        this.rescheduleJob(triggerName, triggerGroupName, startTime, endTime, scheduleBuilder);
    }

    /**
     * 保存简单任务
     * @param jobName 任务名称
     * @param jobClass 任务执行类
     * @param triggerName 触发器名称
     * @param startTime 任务开始时间
     * @param endTime 任务停止时间
     * @param intervalSeconds 循环间隔(s)
     * @param repeatCount 复次数(小于0表示无限重复,等于0表示不重复)
     */
    @Override
    public void saveSimpleTask(String jobName, Class<? extends Job> jobClass, String triggerName,
                               Date startTime, Date endTime,
                               int intervalSeconds, int repeatCount,
                               Map<?, ?> dataMap) {
        this.saveSimpleTask(jobName, DEFAULT_JOB_GROUP_NAME, jobClass,
                triggerName, DEFAULT_TRIGGER_GROUP_NAME,
                startTime, endTime,
                intervalSeconds, repeatCount,
                dataMap);
    }

    /**
     * 保存简单任务
     * @param jobName 任务名称
     * @param jobGroupName 任务分组名称
     * @param jobClass 任务执行类
     * @param triggerName 触发器名称
     * @param triggerGroupName 触发器分组名称
     * @param startTime 任务开始时间
     * @param endTime 任务停止时间
     * @param intervalSeconds 循环间隔(s)
     * @param repeatCount 复次数(小于0表示无限重复,等于0表示不重复)
     */
    @Override
    public void saveSimpleTask(String jobName, String jobGroupName, Class<? extends Job> jobClass,
                               String triggerName, String triggerGroupName,
                               Date startTime, Date endTime,
                               int intervalSeconds, int repeatCount,
                               Map<?, ?> dataMap) {
        SimpleScheduleBuilder scheduleBuilder = this.createSimpleScheduleBuilder(intervalSeconds, repeatCount);
        this.saveScheduleJob(jobName, jobGroupName, jobClass,
                triggerName, triggerGroupName,
                startTime, endTime, dataMap, scheduleBuilder);
    }

    /**
     * 添加cron任务
     * @param jobName 任务名称
     * @param jobClass 任务执行类
     * @param triggerName 触发器名称
     * @param startTime 任务开始时间
     * @param endTime 任务停止时间
     * @param cron cron表达式
     */
    @Override
    public void addCronTask(String jobName, Class<? extends Job> jobClass,
                            String triggerName, Date startTime, Date endTime,
                            String cron, Map<?, ?> dataMap) {
        this.addCronTask(jobName, DEFAULT_JOB_GROUP_NAME, jobClass,
                triggerName, DEFAULT_TRIGGER_GROUP_NAME,
                startTime, endTime,
                cron, dataMap);
    }

    /**
     * 添加cron任务
     * @param jobName 任务名称
     * @param jobGroupName 任务分组名称
     * @param jobClass 任务执行类
     * @param triggerName 触发器名称
     * @param triggerGroupName 触发器分组名称
     * @param startTime 任务开始时间
     * @param endTime 任务停止时间
     * @param cron cron表达式
     */
    @Override
    public void addCronTask(String jobName, String jobGroupName, Class<? extends Job> jobClass,
                            String triggerName, String triggerGroupName,
                            Date startTime, Date endTime,
                            String cron, Map<?, ?> dataMap) {
        CronScheduleBuilder scheduleBuilder = this.createCronScheduleBuilder(cron);
        this.scheduleJob(jobName, jobGroupName, jobClass,
                triggerName, triggerGroupName,
                startTime, endTime, dataMap, scheduleBuilder);
    }


    /**
     * 修改cron任务
     * @param triggerName 触发器名称
     * @param startTime 任务开始时间
     * @param endTime 任务停止时间
     * @param cron cron表达式
     */
    @Override
    public void modifyCronTask(String triggerName,
                               Date startTime, Date endTime, String cron, Map<?, ?> dataMap) {
        this.modifyCronTask(triggerName, DEFAULT_TRIGGER_GROUP_NAME, startTime, endTime, cron, dataMap);
    }

    /**
     * 修改cron任务
     * @param triggerName 触发器名称
     * @param triggerGroupName 触发器分组名称
     * @param startTime 任务开始时间
     * @param endTime 任务停止时间
     * @param cron cron表达式
     */
    @Override
    public void modifyCronTask(String triggerName, String triggerGroupName,
                               Date startTime, Date endTime,
                               String cron, Map<?, ?> dataMap) {
        CronScheduleBuilder scheduleBuilder = this.createCronScheduleBuilder(cron);
        rescheduleJob(triggerName, triggerGroupName, startTime, endTime, scheduleBuilder);
    }

    /**
     * 保存cron任务
     * @param jobName 任务名称
     * @param jobClass 任务执行类
     * @param triggerName 触发器名称
     * @param startTime 任务开始时间
     * @param endTime 任务停止时间
     * @param cron cron表达式
     */
    @Override
    public void saveCronTask(String jobName, Class<? extends Job> jobClass,
                             String triggerName,
                             Date startTime, Date endTime,
                             String cron, Map<?, ?> dataMap) {
        this.saveCronTask(jobName, DEFAULT_JOB_GROUP_NAME, jobClass,
                triggerName, DEFAULT_TRIGGER_GROUP_NAME,
                startTime, endTime, cron, dataMap);
    }

    /**
     * 保存cron任务
     * @param jobName 任务名称
     * @param jobGroupName 任务分组名称
     * @param jobClass 任务执行类
     * @param triggerName 触发器名称
     * @param triggerGroupName 触发器分组名称
     * @param startTime 任务开始时间
     * @param endTime 任务停止时间
     * @param cron cron表达式
     */
    @Override
    public void saveCronTask(String jobName, String jobGroupName, Class<? extends Job> jobClass,
                             String triggerName, String triggerGroupName,
                             Date startTime, Date endTime,
                             String cron, Map<?, ?> dataMap) {
        CronScheduleBuilder scheduleBuilder = this.createCronScheduleBuilder(cron);
        this.saveScheduleJob(jobName, jobGroupName, jobClass,
                triggerName, triggerGroupName,
                startTime, endTime, dataMap, scheduleBuilder);
    }

    /**
     * 移除任务
     * @param jobName 任务名称
     * @param triggerName 触发器名称
     */
    @Override
    public void removeTask(String jobName, String triggerName) {
        this.removeTask(jobName, DEFAULT_JOB_GROUP_NAME, triggerName, DEFAULT_TRIGGER_GROUP_NAME);
    }

    /**
     * 移除任务
     * @param jobName 任务名称
     * @param jobGroupName 任务分组名称
     * @param triggerName 触发器名称
     * @param triggerGroupName 触发器分组名称
     */
    @Override
    public void removeTask(String jobName, String jobGroupName,
                           String triggerName, String triggerGroupName) {
        if (jobGroupName == null) jobGroupName = DEFAULT_JOB_GROUP_NAME;
        if (triggerGroupName == null) triggerGroupName = DEFAULT_TRIGGER_GROUP_NAME;
        JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
        TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
        try {
//            scheduler.pauseJob(jobKey);
            scheduler.pauseTrigger(triggerKey);
            scheduler.unscheduleJob(triggerKey);
            scheduler.deleteJob(jobKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

    }


    /**
     * 创建JobDetail
     * @param jobName 任务名称
     * @param jobGroupName 任务分组名称
     * @param jobClass 任务执行类
     * @param dataMap 任务数据
     * @return JobDetail
     */
    private JobDetail createJobDetail(String jobName, String jobGroupName, Class<? extends Job> jobClass, Map<?, ?> dataMap) {
        JobBuilder jobBuilder = JobBuilder.newJob(jobClass)
                .withIdentity(jobName, jobGroupName);
        if (dataMap != null) {
            jobBuilder.setJobData(new JobDataMap(dataMap));
        }
        return jobBuilder.build();
    }

    /**
     * 创建简单定时器
     * @param intervalSeconds 循环间隔(s)
     * @param repeatCount 复次数(小于0表示无限重复,等于0表示不重复)
     * @return SimpleScheduleBuilder
     */
    private SimpleScheduleBuilder createSimpleScheduleBuilder(int intervalSeconds, int repeatCount) {
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

    /**
     * 创建cron定时器
     * @param cron cron表达式
     * @return CronScheduleBuilder
     */
    private CronScheduleBuilder createCronScheduleBuilder(String cron) {
        return CronScheduleBuilder.cronSchedule(cron);
    }

    /**
     * 创建触发器
     * @param triggerName 触发器名称
     * @param triggerGroupName 触发器分组名称
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param scheduleBuilder 定时器
     * @return Trigger
     */
    private Trigger createTrigger(String triggerName, String triggerGroupName,
                                  Date startTime, Date endTime,
                                  ScheduleBuilder<? extends Trigger> scheduleBuilder) {
        TriggerBuilder<? extends Trigger> triggerBuilder = TriggerBuilder.newTrigger()
                .withIdentity(triggerName, triggerGroupName)
                .withSchedule(scheduleBuilder);
        if (startTime != null) triggerBuilder.startAt(startTime);
        if (endTime != null) triggerBuilder.endAt(endTime);
        return triggerBuilder.build();
    }

    /**
     * 更新触发器
     * @param trigger 源触发器
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param scheduleBuilder 定时器
     * @return 新触发器
     */
    private Trigger updateTrigger(Trigger trigger,
                                  Date startTime, Date endTime,
                                  ScheduleBuilder scheduleBuilder) {
        TriggerBuilder triggerBuilder = trigger.getTriggerBuilder().withSchedule(scheduleBuilder);
        if (startTime != null) triggerBuilder.startAt(startTime);
        if (endTime != null) triggerBuilder.endAt(endTime);
        return triggerBuilder.build();
    }

    /**
     * 执行任务
     * @param jobName 任务名称
     * @param jobGroupName 任务分组名称
     * @param jobClass 任务执行类
     * @param triggerName 触发器名称
     * @param triggerGroupName 触发器分组名称
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param scheduleBuilder 定时器
     */
    private void scheduleJob(String jobName, String jobGroupName, Class<? extends Job> jobClass,
                             String triggerName, String triggerGroupName,
                             Date startTime, Date endTime, Map<?, ?> dataMap, ScheduleBuilder<? extends Trigger> scheduleBuilder) {
        if (jobGroupName == null) jobGroupName = DEFAULT_JOB_GROUP_NAME;
        if (triggerGroupName == null) triggerGroupName = DEFAULT_TRIGGER_GROUP_NAME;
        JobDetail jobDetail = this.createJobDetail(jobName, jobGroupName, jobClass, dataMap);
        Trigger trigger = this.createTrigger(triggerName, triggerGroupName, startTime, endTime, scheduleBuilder);
        try {
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新任务
     * @param triggerName 触发器名称
     * @param triggerGroupName 触发器分组名称
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param scheduleBuilder 定时器
     */
    private void rescheduleJob(String triggerName, String triggerGroupName,
                               Date startTime, Date endTime, ScheduleBuilder scheduleBuilder) {
        if (triggerGroupName == null) triggerGroupName = DEFAULT_TRIGGER_GROUP_NAME;
        TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
        try {
            Trigger trigger = scheduler.getTrigger(triggerKey);
            if (trigger == null) return;
            trigger = this.updateTrigger(trigger, startTime, endTime, scheduleBuilder);
            scheduler.rescheduleJob(triggerKey, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 执行或更新任务
     * @param jobName 任务名称
     * @param jobGroupName 任务分组名称
     * @param jobClass 任务执行类
     * @param triggerName 触发器名称
     * @param triggerGroupName 触发器分组名称
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param scheduleBuilder 定时器
     */
    private void saveScheduleJob(String jobName, String jobGroupName, Class<? extends Job> jobClass,
                            String triggerName, String triggerGroupName,
                            Date startTime, Date endTime, Map<?, ?> dataMap, ScheduleBuilder<? extends Trigger> scheduleBuilder) {
        if (jobGroupName == null) jobGroupName = DEFAULT_JOB_GROUP_NAME;
        if (triggerGroupName == null) triggerGroupName = DEFAULT_TRIGGER_GROUP_NAME;
        TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
        try {
            Trigger trigger = scheduler.getTrigger(triggerKey);
            if (trigger == null)  {
                JobDetail jobDetail = this.createJobDetail(jobName, jobGroupName, jobClass, dataMap);
                trigger = this.createTrigger(triggerName, triggerGroupName, startTime, endTime, scheduleBuilder);
                scheduler.scheduleJob(jobDetail, trigger);
            } else {
                trigger = this.updateTrigger(trigger, startTime, endTime, scheduleBuilder);
                scheduler.rescheduleJob(triggerKey, trigger);
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
