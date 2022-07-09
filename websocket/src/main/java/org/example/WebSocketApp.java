package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableWebSocket
public class WebSocketApp {
    public static void main(String[] args) {
        SpringApplication.run(WebSocketApp.class, args);
    }
}
