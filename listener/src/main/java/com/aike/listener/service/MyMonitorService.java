package com.aike.listener.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

@Slf4j
@Service
public class MyMonitorService {

    /**
     * 存放MyJob的队列
     */
    private final LinkedBlockingDeque<MyJob> jobQueue = new LinkedBlockingDeque<>(4096);

    private final ExecutorService threadPool = Executors.newFixedThreadPool(4);

    private static final Integer FINISH_STATUS = 3;

    @PostConstruct
    public void init() {
        threadPool.execute(this::executeMonitor);
    }

    private void executeMonitor() {
        while (true) {
            try {
                MyJob myJob = jobQueue.take();
                if (Objects.nonNull(myJob)) {
                    if (!Objects.equals(FINISH_STATUS, myJob.getStatus())) {
                        log.info("myJob status is {}", myJob.getStatus());
                        myJob.updatedCallback(myJob.getStatus());
                        if (myJob.getStatus() < FINISH_STATUS) {
                            myJob.setStatus(myJob.getStatus() + 1);
                            addJob(myJob);
                        }
                    } else {
                        myJob.finishedCallback();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void addJob(MyJob myJob) {
        log.info("jobQueue add job status {}", myJob.getStatus());
        jobQueue.add(myJob);
    }


}
