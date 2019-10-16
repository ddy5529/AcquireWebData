package com.ddy.spide.acquire_web_data.controller.websocket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.spring.web.json.Json;

import java.util.HashMap;
import java.util.Map;


@Controller
public class WebSocketController {

    @Autowired
    private SimpMessagingTemplate template;

    @GetMapping("/websocket")
    public String indexPage(){

        return "web-socket-index";
    }

    @PostMapping("/sendmessage")
    @ResponseBody
    public String sendMessage(String msg, String name){
        Map<String,String> map=new HashMap<>();
        map.put("content",msg);
//        template.convertAndSendToUser(name, "/topic/greetings", JSON.toJSONString(map));
        template.convertAndSend("/topic/greetings", JSON.toJSONString(map));
        return "OK";
    }

}
