package com.tangwh.controller;

import com.tangwh.bean.Chat;
import com.tangwh.bean.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping
public class IndexController {

    // 消息发送的模板  可以替换@SendTo注解
    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

//    @MessageMapping("/hello")
//    @SendTo("/topic/gteetings") // 处理后 转发给这个 和配置topic一样
//    public Message getIndex(Message message){
//        return message;
//    }

    /***
     * 群聊
     * @param message
     */
    @MessageMapping("/hello")
    public void getIndex(Message message){
        simpMessagingTemplate.convertAndSend("/topic/gteetings",message);
    }

    /**
     * 单聊
     * @param principal
     * @param chat
     */
    @MessageMapping("/chat")
    public void chat(Principal principal, Chat chat){
       // 设置这个消息从哪来
        chat.setFrom(principal.getName());
            // 参数: 1:给谁发送  2:发送的地址  3:发的消息对象
        simpMessagingTemplate.convertAndSendToUser(chat.getTo(), "queue/chat",chat );

    }


}
