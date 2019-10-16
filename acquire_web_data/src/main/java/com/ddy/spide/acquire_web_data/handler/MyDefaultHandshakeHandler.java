package com.ddy.spide.acquire_web_data.handler;

import com.alibaba.druid.util.StringUtils;
import com.ddy.spide.acquire_web_data.model.MyPrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Map;
import java.util.Set;


/** WebSocket 句柄 */
@Component
public class MyDefaultHandshakeHandler extends DefaultHandshakeHandler {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {

        HttpSession httpSession = getSession(request);
        String user = (String)httpSession.getAttribute("loginName");

        if(StringUtils.isEmpty(user)){
            logger.error("未登录系统，禁止登录websocket!");
            return null;
        }
        logger.info(" MyDefaultHandshakeHandler login = " + user);

        Set<String> keySet= attributes.keySet();


        return new MyPrincipal(user);
    }

    private HttpSession getSession(ServerHttpRequest request) {
        if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest serverRequest = (ServletServerHttpRequest) request;
            return serverRequest.getServletRequest().getSession(true);
        }
        return null;
    }

}
