package com.ddy.spide.acquire_web_data.handler;

import com.ddy.spide.acquire_web_data.utils.LogUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.BinaryWebSocketHandler;

@Component
public class MyBinaryWebSocketHandler extends BinaryWebSocketHandler {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        try {
            super.handleTextMessage(session,message);
            LogUtils.printLog(logger,message.toString());
        } catch (Exception e) {
            LogUtils.printErrorLog(logger,e.getMessage());
        }
    }
}
