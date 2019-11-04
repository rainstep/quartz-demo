package com.example.quartzdemo;

import com.example.quartzdemo.service.QuartzService;

import com.example.quartzdemo.service.job.MyJobListener;
import com.example.quartzdemo.service.job.MyTriggerListener;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements CommandLineRunner {
    @Autowired
    private Scheduler scheduler;

    @Override
    public void run(String... args) throws Exception {
        scheduler.getListenerManager().addJobListener(new MyJobListener());
        scheduler.getListenerManager().addTriggerListener(new MyTriggerListener());
    }

}
