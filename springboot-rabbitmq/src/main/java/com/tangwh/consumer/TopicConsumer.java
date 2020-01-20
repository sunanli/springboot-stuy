package com.tangwh.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 *  消费者 路由策略三
 */
@Component
public class TopicConsumer {


    @RabbitListener(queues = "xiaomi")
    public void handler1(String msg){
        System.out.println("TopicConsumer-->handler1-->"+msg);
    }

    @RabbitListener(queues = "huawei")
    public void handler2(String msg){
        System.out.println("TopicConsumer-->handler2-->"+msg);
    }

    @RabbitListener(queues = "phone")
    public void handler3(String msg){
        System.out.println("TopicConsumer-->handler3-->"+msg);
    }
}
