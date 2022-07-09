package service;


import org.springframework.context.ApplicationContext;

public class MyJob {

    private ApplicationContext applicationContext;

    public MyJob(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public void updatedCallback(Integer status){
        MyEvent event = new MyEvent(this, status);
        applicationContext.publishEvent(event);
    }
}
