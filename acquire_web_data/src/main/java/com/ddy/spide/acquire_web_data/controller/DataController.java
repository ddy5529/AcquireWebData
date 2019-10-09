package com.ddy.spide.acquire_web_data.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataController {

    @ApiOperation("hello")
    @GetMapping("/hello")
    public String getHello(){
        return "hi";
    }



}
