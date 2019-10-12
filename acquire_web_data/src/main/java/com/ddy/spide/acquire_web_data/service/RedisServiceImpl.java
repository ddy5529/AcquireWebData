package com.ddy.spide.acquire_web_data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisServiceImpl implements RedisService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private final String ProjectName ="AcquireWebData_";

    @Override
    public int setKey(String name, String value) {
        name= ProjectName +name;
        stringRedisTemplate.opsForValue().set(name, value);
        return 0;
    }

    @Override
    public String getKey(String name) {
        name= ProjectName +name;
        String value=stringRedisTemplate.opsForValue().get(name);
        value="null".equals(value+"")?"":value;
        return value;
    }

    @Override
    public int deleteKey(String name) {
        name= ProjectName +name;
        stringRedisTemplate.delete(name);
        return 0;
    }
}
