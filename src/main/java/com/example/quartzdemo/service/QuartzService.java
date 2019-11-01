package com.example.quartzdemo.service;

import org.quartz.Job;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;
import java.util.Map;

public interface QuartzService {
    String DEFAULT_JOB_GROUP_NAME = "defaultJobGroup";
    String DEFAULT_TRIGGER_GROUP_NAME = "defaultTriggerGroup";

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
    void addSimpleTask(String jobName, Class<? extends Job> jobClass, String triggerName,
                       Date startTime, Date endTime,
                       int intervalSeconds, int repeatCount,
                       Map<?, ?> dataMap);

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
    void addSimpleTask(String jobName, String jobGroupName, Class<? extends Job> jobClass,
                       String triggerName, String triggerGroupName,
                       Date startTime, Date endTime,
                       int intervalSeconds, int repeatCount,
                       Map<?, ?> dataMap);

    /**
     * 修改简单任务
     * @param triggerName 触发器名称
     * @param startTime 任务开始时间
     * @param endTime 任务停止时间
     * @param intervalSeconds 循环间隔(s)
     * @param repeatCount 复次数(小于0表示无限重复,等于0表示不重复)
     */
    void modifySimpleTask(String triggerName,
                          Date startTime, Date endTime,
                          int intervalSeconds, int repeatCount,
                          Map<?, ?> dataMap);

    /**
     * 修改简单任务
     * @param triggerName 触发器名称
     * @param triggerGroupName 触发器分组名称
     * @param startTime 任务开始时间
     * @param endTime 任务停止时间
     * @param intervalSeconds 循环间隔(s)
     * @param repeatCount 复次数(小于0表示无限重复,等于0表示不重复)
     */
    void modifySimpleTask(String triggerName, String triggerGroupName,
                          Date startTime, Date endTime,
                          int intervalSeconds, int repeatCount,
                          Map<?, ?> dataMap);

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
    void saveSimpleTask(String jobName, Class<? extends Job> jobClass, String triggerName,
                        Date startTime, Date endTime,
                        int intervalSeconds, int repeatCount,
                        Map<?, ?> dataMap);

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
    void saveSimpleTask(String jobName, String jobGroupName, Class<? extends Job> jobClass,
                        String triggerName, String triggerGroupName,
                        Date startTime, Date endTime,
                        int intervalSeconds, int repeatCount,
                        Map<?, ?> dataMap);

    /**
     * 添加cron任务
     * @param jobName 任务名称
     * @param jobClass 任务执行类
     * @param triggerName 触发器名称
     * @param startTime 任务开始时间
     * @param endTime 任务停止时间
     * @param cron cron表达式
     */
    void addCronTask(String jobName, Class<? extends Job> jobClass,
                     String triggerName, Date startTime, Date endTime,
                     String cron, Map<?, ?> dataMap);

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
    void addCronTask(String jobName, String jobGroupName, Class<? extends Job> jobClass,
                     String triggerName, String triggerGroupName,
                     Date startTime, Date endTime,
                     String cron, Map<?, ?> dataMap);

    /**
     * 修改cron任务
     * @param triggerName 触发器名称
     * @param startTime 任务开始时间
     * @param endTime 任务停止时间
     * @param cron cron表达式
     */
    void modifyCronTask(String triggerName, Date startTime, Date endTime, String cron, Map<?, ?> dataMap);

    /**
     * 修改cron任务
     * @param triggerName 触发器名称
     * @param triggerGroupName 触发器分组名称
     * @param startTime 任务开始时间
     * @param endTime 任务停止时间
     * @param cron cron表达式
     */
    void modifyCronTask(String triggerName, String triggerGroupName, Date startTime, Date endTime,
                        String cron, Map<?, ?> dataMap);

    /**
     * 保存cron任务
     * @param jobName 任务名称
     * @param jobClass 任务执行类
     * @param triggerName 触发器名称
     * @param startTime 任务开始时间
     * @param endTime 任务停止时间
     * @param cron cron表达式
     */
    void saveCronTask(String jobName, Class<? extends Job> jobClass,
                      String triggerName,
                      Date startTime, Date endTime,
                      String cron, Map<?, ?> dataMap);

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
    void saveCronTask(String jobName, String jobGroupName, Class<? extends Job> jobClass,
                      String triggerName, String triggerGroupName,
                      Date startTime, Date endTime,
                      String cron, Map<?, ?> dataMap);

    /**
     * 移除任务
     * @param jobName 任务名称
     * @param triggerName 触发器名称
     */
    void removeTask(String jobName, String triggerName);

    /**
     *
     * @param jobName 任务名称
     * @param jobGroupName 任务分组名称
     * @param triggerName 触发器名称
     * @param triggerGroupName 触发器分组名称
     */
    void removeTask(String jobName, String jobGroupName, String triggerName, String triggerGroupName);
}
