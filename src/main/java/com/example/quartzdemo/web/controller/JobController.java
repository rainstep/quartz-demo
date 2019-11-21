package com.example.quartzdemo.web.controller;

import com.example.quartzdemo.service.QuartzService;
import com.example.quartzdemo.service.job.MyJob;
import com.example.quartzdemo.util.DateFormatUtils;
import org.quartz.Job;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/job")
public class JobController {
    @Autowired
    private QuartzService quartzService;

    @RequestMapping("/addSimpleTask")
    public boolean addSimpleTask(String jobName,
                          String triggerName,
                          Date startTime, Date endTime,
                          int intervalSeconds, int repeatCount) throws SchedulerException {
        Class<? extends Job> jobClass = MyJob.class;
        Map<String, Object> jobData = new HashMap<>();
        jobData.put("time", DateFormatUtils.format(new Date()));
        return quartzService.addSimpleTask(jobName,
                jobClass, jobData,
                triggerName,
                startTime, endTime,
                intervalSeconds, repeatCount);
    }

    @RequestMapping("/updateSimpleTask")
    public boolean updateSimpleTask(String triggerName,
                             Date startTime, Date endTime,
                             int intervalSeconds, int repeatCount) throws SchedulerException {
        return quartzService.updateSimpleTask(triggerName,
                startTime, endTime,
                intervalSeconds, repeatCount);
    }

    @RequestMapping("/saveSimpleTask")
    public boolean saveSimpleTask(String jobName,
                           String triggerName,
                           Date startTime, Date endTime,
                           int intervalSeconds, int repeatCount) throws SchedulerException {
        Class<? extends Job> jobClass = MyJob.class;
        Map<String, Object> jobData = new HashMap<>();
        jobData.put("time", DateFormatUtils.format(new Date()));
        return quartzService.saveSimpleTask(jobName,
                jobClass, jobData,
                triggerName,
                startTime, endTime,
                intervalSeconds, repeatCount);
    }


    @RequestMapping("/addCronTask")
    public boolean addCronTask(String jobName,
                        String triggerName,
                        Date startTime, Date endTime,
                        String cron) throws SchedulerException {
        Class<? extends Job> jobClass = MyJob.class;
        Map<String, Object> jobData = new HashMap<>();
        jobData.put("time", DateFormatUtils.format(new Date()));
        return quartzService.addCronTask(jobName,
                jobClass, jobData,
                triggerName,
                startTime, endTime,
                cron);
    }

    @RequestMapping("/updateCronTask")
    public boolean updateCronTask(String triggerName,
                           Date startTime, Date endTime, String cron) throws SchedulerException {
        return quartzService.updateCronTask(triggerName,
                startTime, endTime,
                cron);
    }


    @RequestMapping("/saveCronTask")
    public boolean saveCronTask(String jobName,
                         String triggerName,
                         Date startTime, Date endTime,
                         String cron) throws SchedulerException {
        Class<? extends Job> jobClass = MyJob.class;
        Map<String, Object> jobData = new HashMap<>();
        jobData.put("time", DateFormatUtils.format(new Date()));
        return quartzService.saveCronTask(jobName,
                jobClass, jobData,
                triggerName,
                startTime, endTime,
                cron);
    }

    @RequestMapping("/pauseTrigger")
    public boolean pauseTrigger(String triggerName) throws SchedulerException {
        return quartzService.pauseTrigger(triggerName);
    }

    @RequestMapping("/pauseJob")
    public boolean pauseJob(String jobName) throws SchedulerException{
        return quartzService.pauseJob(jobName);
    }

    @RequestMapping("/pauseAll")
    public boolean pauseAll() throws SchedulerException {
        return quartzService.pauseAll();
    }

    @RequestMapping("/resumeTrigger")
    public boolean resumeTrigger(String triggerName) throws SchedulerException {
        return quartzService.resumeTrigger(triggerName);
    }

    @RequestMapping("/resumeJob")
    public boolean resumeJob(String jobName) throws SchedulerException {
        return quartzService.removeJob(jobName);
    }

    @RequestMapping("/resumeAll")
    public boolean resumeAll() throws SchedulerException {
        return quartzService.resumeAll();
    }

    @RequestMapping("/removeTrigger")
    public boolean removeTrigger(String triggerName) throws SchedulerException {
        return quartzService.removeTrigger(triggerName);
    }

    @RequestMapping("/removeJob")
    public boolean removeJob(String jobName) throws SchedulerException {
        return quartzService.removeJob(jobName);
    }

}
