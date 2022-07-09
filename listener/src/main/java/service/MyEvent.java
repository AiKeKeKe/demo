package service;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * 具体事件
 */
@Getter
public class MyEvent extends ApplicationEvent {

    private Integer status;

    public MyEvent(Object source, Integer status) {
        super(source);
        this.status = status;
    }
}
