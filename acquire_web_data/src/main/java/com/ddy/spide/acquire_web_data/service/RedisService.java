package com.ddy.spide.acquire_web_data.service;

public interface RedisService {

    int setKey(String name,String value);

    String getKey(String name);

    int deleteKey(String name);
}
