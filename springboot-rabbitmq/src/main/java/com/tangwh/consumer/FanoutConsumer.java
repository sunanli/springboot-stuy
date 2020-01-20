package com.tangwh.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 消费者
 */
@Component
public class FanoutConsumer {

    //queues 队列的名字
    @RabbitListener(queues = "queue-one")
    public void hander1(String msg){

        System.out.println("FanoutConsumer>>hander1>>"+msg);

    }
    //queues 队列的名字
    @RabbitListener(queues = "queue-two")
    public void hander2(String msg){

        System.out.println("FanoutConsumer>>hander2>>"+msg);

    }
}
