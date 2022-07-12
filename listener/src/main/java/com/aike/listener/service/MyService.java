package com.aike.listener.service;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MyService {

    @Resource
    private ApplicationContext applicationContext;

    @Resource
    private MyMonitorService myMonitorService;

    public void beginTask(Integer status) {
        MyJob myJob = new MyJob(applicationContext, status);
        myMonitorService.addJob(myJob);
    }

}
