package com.tangwh.controller;

import com.tangwh.bean.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class IndexController {

    @MessageMapping("/hello")
    @SendTo("/topic/gteetings") // 处理后 转发给这个 和配置topic一样
    public Message getIndex(Message message){
        return message;
    }
}
