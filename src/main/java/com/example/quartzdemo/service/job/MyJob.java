package com.example.quartzdemo.service.job;

import com.example.quartzdemo.service.BaseService;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class MyJob extends QuartzJobBean {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private BaseService baseService;

    @Override
    protected void executeInternal(JobExecutionContext context) {
        JobDetail jobDetail = context.getJobDetail();
        JobKey jobKey = jobDetail.getKey();
        TriggerKey triggerKey = context.getTrigger().getKey();
        JobDataMap jobDataMap = jobDetail.getJobDataMap();
        logger.info("------ 任务开始 ------>");
        logger.info("{}.{} {}.{}", jobKey.getGroup(), jobKey.getName(), triggerKey.getGroup(), triggerKey.getName());
        jobDataMap.forEach((key, val) -> {
            logger.info("key: {}, val: {}", key, val);
        });
        logger.info("<------ 任务结束 ------\n");
    }
}
