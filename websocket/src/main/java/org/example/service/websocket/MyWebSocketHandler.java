package org.example.service.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import javax.annotation.Resource;

@Component
@Slf4j
public class MyWebSocketHandler extends TextWebSocketHandler {

    @Resource
    private WebSocketMgr webSocketMgr;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        webSocketMgr.registerSession(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        log.info("message : {}", message);
        webSocketMgr.handlerMessage(session, message.getPayload());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        webSocketMgr.closeSession(session);
    }
}
