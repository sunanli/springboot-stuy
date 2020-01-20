package com.tangwh.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 消费者
 */
@Component
public class DirectConsumer {


    // queus="之前订的队列"
    @RabbitListener(queues = "hello.javaboy")
    public void handler1(String msg){

        System.out.println("handler1>>>"+msg);

    }
}
