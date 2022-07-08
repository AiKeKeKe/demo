package org.example.service.websocket;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.Message;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
public class WebSocketMgrImpl implements WebSocketMgr {

    private static final ConcurrentHashMap<String, WebSocketSession> sessionMap = new ConcurrentHashMap();

    private Gson gson = new GsonBuilder().disableHtmlEscaping().create();

    @Override
    public void registerSession(WebSocketSession session) {
        try {
            session.sendMessage(new TextMessage("websocket 连接成功"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handlerMessage(WebSocketSession session, String payload) throws IOException {
        Message message = gson.fromJson(payload, Message.class);
        log.info("message : {}", message);
        String command = "收到" + message.getCommand() ;
        WebSocketSession webSocketSession = sessionMap.get(message.getToken());
        if (Objects.isNull(webSocketSession)) {
            sessionMap.put(message.getToken(), session);
            session.sendMessage(new TextMessage(command));
        } else {
            if (webSocketSession.isOpen()) {
                webSocketSession.sendMessage(new TextMessage(command));
            } else {
                webSocketSession.close();
            }
        }
    }

    @Override
    public void closeSession(WebSocketSession session) {

    }
}
