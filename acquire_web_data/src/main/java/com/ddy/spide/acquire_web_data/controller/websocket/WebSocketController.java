package com.ddy.spide.acquire_web_data.controller.websocket;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class WebSocketController {
    @GetMapping("/websocket")
    public String indexPage(){
        return "web-socket-index";
    }
}
