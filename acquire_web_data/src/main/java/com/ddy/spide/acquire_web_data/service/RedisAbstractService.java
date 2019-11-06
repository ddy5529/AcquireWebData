package com.ddy.spide.acquire_web_data.service;

import java.util.Map;

public interface RedisAbstractService {
    int setKey(String name,String value);

    int setDefaultTimeKey(String name,String value);

    int setKey(String name,String value,Long timeOut);

    String getKey(String name);

    Object getObjKey(String name);

    Map getAll();

    boolean deleteKey(String name);

    void deleteAll();
}
