package com.ddy.spide.acquire_web_data.service;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MonitoringRedisServiceImpl implements MonitoringRedisService {

    @Autowired
    private SimpMessagingTemplate template;

    @Override
    public void pushWebServiceMessage(String msg) {
        Map<String,String> map=new HashMap<>();
        map.put("content",msg);
        template.convertAndSend("/topic/greetings", JSON.toJSONString(map));
    }
}
