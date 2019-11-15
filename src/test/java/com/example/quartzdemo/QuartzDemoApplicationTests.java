package com.example.quartzdemo;

import com.example.quartzdemo.service.QuartzService;
import com.example.quartzdemo.service.job.MyJob;
import org.junit.jupiter.api.Test;
import org.quartz.Job;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class QuartzDemoApplicationTests {
    @Autowired
    private QuartzService quartzService;

    @Test
    void contextLoads() throws SchedulerException {

    }

    @Test
    public void addJob() throws SchedulerException {
        String jobName = "job1";
        String jobGroupName = "jobGroup1";
        Class<? extends Job> jobClass = MyJob.class;
        String triggerName = "trigger1";
        String triggerGroupName = "triggerGroup1";
        int intervalSeconds = 5;
        int repeatCount = 200;
        Map<String, Object> jobData = new HashMap<>();
        jobData.put("age", 19);
        boolean result = quartzService.addSimpleTask(jobName, jobGroupName,
                jobClass, jobData,
                triggerName, triggerGroupName,
                null, null,
                intervalSeconds, repeatCount);
        assertTrue(result);
    }

    @Test
    public void updateJob() throws SchedulerException {
        String triggerName = "trigger1";
        String triggerGroupName = "triggerGroup1";
        int intervalSeconds = 10;
        int repeatCount = 200;
        boolean result = quartzService.updateSimpleTask(triggerName, triggerGroupName,
                null, null,
                intervalSeconds, repeatCount);
        assertTrue(result);
    }

    @Test
    public void saveJob() throws SchedulerException {
        String jobName = "job1";
        String jobGroupName = "jobGroup1";
        Class<? extends Job> jobClass = MyJob.class;
        String triggerName = "trigger1";
        String triggerGroupName = "triggerGroup1";
        int intervalSeconds = 7;
        int repeatCount = 200;
        Map<String, Object> jobData = new HashMap<>();
        jobData.put("age", 18);
        boolean result = quartzService.saveSimpleTask(jobName, jobGroupName,
                jobClass, jobData,
                triggerName, triggerGroupName,
                null, null,
                intervalSeconds, repeatCount);
        assertTrue(result);
    }

    @Test
    public void pauseTrigger() throws SchedulerException {
        String triggerName = "trigger2";
        String triggerGroupName = "triggerGroup1";
        boolean result = quartzService.pauseTrigger(triggerName, triggerGroupName);
        assertTrue(result);
    }

    @Test
    public void pauseJob() throws SchedulerException {
        String jobName = "job1";
        String jobGroupName = "jobGroup1";
        boolean result = quartzService.pauseJob(jobName, jobGroupName);
        assertTrue(result);
    }

    @Test
    public void resumeTrigger() throws SchedulerException {
        String triggerName = "trigger1";
        String triggerGroupName = "triggerGroup1";
        boolean result = quartzService.resumeTrigger(triggerName, triggerGroupName);
        assertTrue(result);
    }

    @Test
    public void resumeJob() throws SchedulerException {
        String jobName = "job1";
        String jobGroupName = "jobGroup1";
        boolean result = quartzService.resumeJob(jobName, jobGroupName);
        assertTrue(result);
    }

    @Test
    public void removeTrigger() throws SchedulerException {
        String triggerName = "trigger2";
        String triggerGroupName = "triggerGroup1";
        boolean result = quartzService.removeTrigger(triggerName, triggerGroupName);
        assertTrue(result);
    }

    @Test
    public void removeJob() throws SchedulerException {
        String jobName = "job1";
        String jobGroupName = "jobGroup1";
        boolean result = quartzService.removeJob(jobName, jobGroupName);
        assertTrue(result);
    }

}
