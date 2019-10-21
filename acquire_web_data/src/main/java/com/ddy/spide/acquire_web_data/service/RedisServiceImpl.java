package com.ddy.spide.acquire_web_data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
public class RedisServiceImpl implements RedisService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private final String ProjectName = "AcquireWebData_";

    @Override
    public int setKey(String name, String value) {
        name = ProjectName + name;
        stringRedisTemplate.opsForValue().set(name, value);
        return 0;
    }

    @Override
    public int setDefaultTimeKey(String name, String value) {
        return setKey(name,value,new Long(3600 * 18));
    }



    @Override
    public int setKey(String name, String value, Long timeOut) {
        name = ProjectName + name;
        if (timeOut == null) {
            timeOut = new Long(3600 * 18);
        }
        Duration duration = Duration.ofSeconds(timeOut);
        stringRedisTemplate.opsForValue().set(name, value, duration);
        return 0;
    }

    @Override
    public String getKey(String name) {
        name = ProjectName + name;
        String value = stringRedisTemplate.opsForValue().get(name);
        value = "null".equals(value + "") ? "" : value;
        return value;
    }

    @Override
    public Object getObjKey(String name) {
        return stringRedisTemplate.opsForValue().get(name);
    }

    @Override
    public Map getAll() {
        Map<String,Object> resultMap=new HashMap<String,Object>();
        Set<String> keySets =stringRedisTemplate.keys("*");
        for (String keySet : keySets) {
            Object value= getObjKey(keySet);
            resultMap.put(keySet,value);
        }
        return resultMap;
    }

    @Override
    public boolean deleteKey(String name) {
        name = ProjectName + name;
        return stringRedisTemplate.delete(name);
    }

    @Override
    public void deleteAll() {
        Set<String> keySets =stringRedisTemplate.keys("*");
        for (String keySet : keySets) {
            deleteKey(keySet);
        }
    }


}
