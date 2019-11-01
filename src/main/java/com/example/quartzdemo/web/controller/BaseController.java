package com.example.quartzdemo.web.controller;

import com.example.quartzdemo.service.job.MyJob;
import com.example.quartzdemo.service.QuartzService;
import org.quartz.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class BaseController {
    @Autowired
    private QuartzService quartzService;

    @PostMapping("/saveSimpleJob")
    public String saveSimpleJob(String jobName, String triggerName, int intervalSeconds, int repeatCount, Date startTime, Date endTime) {
        Class<? extends Job> jobClass = MyJob.class;
        Map<String, Object> dataMap =  new HashMap<>();
        dataMap.put("jobType", "simple");
        quartzService.saveSimpleTask(jobName, jobClass, triggerName, startTime, endTime, intervalSeconds, repeatCount, dataMap);
        return "success";
    }

    @PostMapping("/saveCronJob")
    public String saveCronJob(String jobName, String triggerName, String cron, Date startTime, Date endTime) {
        Class<? extends Job> jobClass = MyJob.class;
        Map<String, Object> dataMap =  new HashMap<>();
        dataMap.put("jobType", "cron");
        quartzService.saveCronTask(jobName, jobClass, triggerName, startTime, endTime, cron, dataMap);
        return "success";
    }

}
