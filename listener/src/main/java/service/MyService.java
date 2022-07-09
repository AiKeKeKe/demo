package service;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.DelayQueue;

@Service
public class MyService {

    /**
     * 存放MyJob的延时队列
     */
//    private final DelayQueue<MyJob> jobQueue = new DelayQueue();

    @Resource
    private ApplicationContext applicationContext;




}
