package com.tangwh;

import com.tangwh.config.RabbitFanoutConfig;
import com.tangwh.config.RabbitHeaderConfig;
import com.tangwh.config.RabbitTopicConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootRabbitmqApplicationTests {

    @Autowired
    RabbitTemplate rabbitTemplate;

    /**
     * 发送消息测试Direct
     */
    @Test
    public void contextLoads() {

        //Direct特点 根据routingKey 查找自己设置的队列名称                     发送的消息
        rabbitTemplate.convertAndSend("hello.javaboy", "hello javaboy!!!");
    }

    /**
     *路由策略 发送消息二Fanout
     */
    @Test
    public void test1() {
        rabbitTemplate.convertAndSend(RabbitFanoutConfig.FANOUTNAME,null,"hello fanout!");



    }


    /**
     *路由策略 发送消息三TOPIC
     */
    @Test
    public void test2(){
        rabbitTemplate.convertAndSend(RabbitTopicConfig.TOPICNAME,"xiaomi.news","小米新闻");
        rabbitTemplate.convertAndSend(RabbitTopicConfig.TOPICNAME,"vivo.phone","vivo手机");
        rabbitTemplate.convertAndSend(RabbitTopicConfig.TOPICNAME,"huawei.phone","华为手机");

    }


    /**
     *路由策略 发送消息四Header
     */
    @Test
    public void test3(){
        // 要发送的消息  设置头 必须是name 内容是javaboy
        Message nameMsg = MessageBuilder.withBody("hello javaboy！".getBytes()).setHeader("name", "javaboy").build();
        Message ageMsg = MessageBuilder.withBody("hello 99！".getBytes()).setHeader("age", "99").build();

        rabbitTemplate.send(RabbitHeaderConfig.HEADERNAME,null,nameMsg);
        rabbitTemplate.send(RabbitHeaderConfig.HEADERNAME,null,ageMsg);

    }

}
