package com.ddy.spide.acquire_web_data.config;

import com.ddy.spide.acquire_web_data.filter.AuthHandshakeInterceptor;
import com.ddy.spide.acquire_web_data.handler.MyDefaultHandshakeHandler;
import com.ddy.spide.acquire_web_data.handler.MyTextWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

/**
 * WebSocket的配置文件
 * */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer{

    @Autowired
    private MyDefaultHandshakeHandler myHandshakeHandler;
    @Autowired
    private AuthHandshakeInterceptor authHandshakeInterceptor;

    /**定义消息代理，通俗一点讲就是设置消息连接请求的各种规范信息*/
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        //表示客户端订阅地址的前缀信息，也就是客户端接收服务端消息的地址的前缀信息
        config.enableSimpleBroker("/topic");
        //指服务端接收地址的前缀，意思就是说客户端给服务端发消息的地址的前缀
        config.setApplicationDestinationPrefixes("/app");

    }

    /**添加一个服务端点，来接收客户端的连接*/
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //表示添加了一个/gs-guide-websocket端点，客户端就可以通过这个端点来进行连接
        registry.addEndpoint("/gs-guide-websocket")
//                .addInterceptors(authHandshakeInterceptor)
//                .setHandshakeHandler(myHandshakeHandler)
                .withSockJS();
    }

}
