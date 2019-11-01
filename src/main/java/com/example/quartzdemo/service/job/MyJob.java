package com.example.quartzdemo.service.job;

import com.example.quartzdemo.service.BaseService;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class MyJob extends QuartzJobBean {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private BaseService baseService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) {
        JobDetail jobDetail = jobExecutionContext.getJobDetail();
        String name = jobDetail.getKey().getName();
        JobDataMap jobDataMap = jobDetail.getJobDataMap();
        logger.info("------{}执行------>", name);
        jobDataMap.forEach((key, val) -> {
            logger.info("key: {}, val: {}", key, val);
        });
        logger.info("<------{}执行------\n", name);
    }
}
