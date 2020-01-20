package com.tangwh.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 消费者
 */
@Component
public class HeaderConsumer {

    @RabbitListener(queues = "name-queue")
    public void handler1(byte[] msg){
        System.out.println("HeaderConsumer-->handler1-->"+new String(msg,0,msg.length));

    }

    @RabbitListener(queues = "age-queue")
    public void handler2(byte[] msg){
        System.out.println("HeaderConsumer-->handler2-->"+new String(msg,0,msg.length));

    }
}
