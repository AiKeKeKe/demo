package service;

import org.springframework.context.ApplicationListener;

public class MyListener implements ApplicationListener<MyEvent> {
    @Override
    public void onApplicationEvent(MyEvent myEvent) {

    }
}
