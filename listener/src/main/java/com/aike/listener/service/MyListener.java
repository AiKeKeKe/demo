package com.aike.listener.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
public class MyListener implements ApplicationListener<MyEvent> {

    private static final Integer FINISH_STATUS = 3;

    @Override
    public void onApplicationEvent(MyEvent myEvent) {
        if (Objects.equals(FINISH_STATUS, myEvent.getStatus())) {
            log.info("任务结束, myEvent status is {}", myEvent.getStatus());
        } else {
            log.info("任务进行中, myEvent status is {}", myEvent.getStatus());
        }
    }
}
