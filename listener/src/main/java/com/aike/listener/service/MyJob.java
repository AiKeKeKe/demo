package com.aike.listener.service;


import lombok.Data;
import org.springframework.context.ApplicationContext;

@Data
public class MyJob {

    private Integer status;

    private ApplicationContext applicationContext;

    public MyJob(ApplicationContext applicationContext, Integer status) {
        this.applicationContext = applicationContext;
        this.status = status;
    }

    public void updatedCallback(Integer status){
        MyEvent event = new MyEvent(this, status);
        applicationContext.publishEvent(event);
    }

    public void finishedCallback(){
        MyEvent event = new MyEvent(this, 3);
        applicationContext.publishEvent(event);
    }
}
