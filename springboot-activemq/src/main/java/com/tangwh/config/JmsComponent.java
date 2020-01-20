package com.tangwh.config;

import com.tangwh.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;


import javax.jms.Queue;

@Component
public class JmsComponent {
    // 消息发送的模板
    @Autowired
    JmsMessagingTemplate jmsMessagingTemplate;



    @Autowired
    Queue queue;

    /**
     * 消息的生产者
     * @param message
     */
    public void send(Message message){

        // 发送消息  参数一 :队列 参数二消息对象
        jmsMessagingTemplate.convertAndSend(this.queue,message);

    }

    /**
     * 消息消费者
     * @param message
     */
    // destination=队列的名字
    @JmsListener(destination = "hello.javaboy")
    public void receive(Message message){

        System.out.println(message);

    }
}
