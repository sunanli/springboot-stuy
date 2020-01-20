package com.tangwh;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.jms.Queue;


@SpringBootApplication
public class SpringbootActivemqApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootActivemqApplication.class, args);
    }

    /**
     * 注意不要导报错误
     * @return
     */

    @Bean
    Queue queue(){

        // 队列的名字
        return new ActiveMQQueue("hello.javaboy");

    }
}


