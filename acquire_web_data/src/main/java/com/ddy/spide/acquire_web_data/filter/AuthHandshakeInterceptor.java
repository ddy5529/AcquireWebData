package com.ddy.spide.acquire_web_data.filter;

import com.alibaba.druid.util.StringUtils;
import com.ddy.spide.acquire_web_data.utils.LogUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**WebSocket的拦截器，验证模块*/
@Component
public class AuthHandshakeInterceptor implements HandshakeInterceptor {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        HttpSession httpSession = getSession(request);
        String user = (String)httpSession.getAttribute("loginName");

        if(StringUtils.isEmpty(user)){
            LogUtils.printErrorLog(logger,"未登录系统，禁止登录websocket!");
            return false;
        }
        LogUtils.printLog(logger,"login = " + user);

        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
    }

    // 参考 HttpSessionHandshakeInterceptor
    private HttpSession getSession(ServerHttpRequest request) {
        if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest serverRequest = (ServletServerHttpRequest) request;
            return serverRequest.getServletRequest().getSession(true);
        }
        return null;
    }
}
