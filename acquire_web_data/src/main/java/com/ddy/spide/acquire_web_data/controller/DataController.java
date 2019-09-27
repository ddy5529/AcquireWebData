package com.ddy.spide.acquire_web_data.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataController {

    @GetMapping("/hello")
    public String getHello(){
        return "hi";
    }

}
