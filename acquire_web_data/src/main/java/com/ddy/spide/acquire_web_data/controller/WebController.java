package com.ddy.spide.acquire_web_data.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("/mystock")
public class WebController {

    @ApiOperation("addPage")
    @GetMapping("/add")
    public String addMyStock(){

        return "addmystock";
    }
}
