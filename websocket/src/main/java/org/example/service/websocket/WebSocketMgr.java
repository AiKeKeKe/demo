package org.example.service.websocket;

import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

public interface WebSocketMgr {
    void registerSession(WebSocketSession session);

    void handlerMessage(WebSocketSession session, String payload) throws IOException;

    void closeSession(WebSocketSession session);
}
