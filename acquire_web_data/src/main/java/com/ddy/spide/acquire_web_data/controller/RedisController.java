package com.ddy.spide.acquire_web_data.controller;

import com.alibaba.fastjson.JSON;
import com.ddy.spide.acquire_web_data.model.ControllerStatus;
import com.ddy.spide.acquire_web_data.model.RedisProperty;
import com.ddy.spide.acquire_web_data.service.RedisService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Redis操作页面
 * */
@RestController
public class RedisController {

    @Autowired
    private RedisService redisService;

    @ApiOperation("redis")
    @GetMapping("/redis_look_all")
    public String redisLookAll() {
        return JSON.toJSON(redisService.getAll()).toString();
    }

    @ApiOperation("redis")
    @GetMapping("/redis_delete_all")
    public String redisDeleteAll() {
        String result=ControllerStatus.SUCCESS;
        redisService.deleteAll();
        return result;
    }

    @ApiOperation("redis")
    @GetMapping("/redis_delete")
    public String redisDelete(RedisProperty redisProperty ) {
        String result=ControllerStatus.SUCCESS;
        String key=redisProperty.getKey();
        if(redisService.deleteKey(key)){
            result= ControllerStatus.DEFEATED;
        }
        return result;
    }

    @ApiOperation("redis")
    @GetMapping("/redis_set")
    public String redisSet(RedisProperty redisProperty) {
        String result=ControllerStatus.SUCCESS;
        String key=redisProperty.getKey();
        String value=redisProperty.getValue();
        redisService.setKey(key,value);
        return result;
    }


}
