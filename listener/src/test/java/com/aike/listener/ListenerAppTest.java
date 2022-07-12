package com.aike.listener;

import com.aike.listener.service.MyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ListenerAppTest {

    @Resource
    private MyService myService;

    @Test
    public void testListener() {
        myService.beginTask(0);
    }

}
