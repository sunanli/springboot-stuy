package com.tangwh.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitHeaderConfig {

    public static final String HEADERNAME = "javaboy-header";

    @Bean
    HeadersExchange headersExchange() {

        return new HeadersExchange(HEADERNAME, true, false);

    }

    /**
     * 队列一
     * @return
     */
    @Bean
    Queue queueName() {
        return new Queue("name-queue");
    }

    /**
     * 队列二
     * @return
     */
    @Bean
    Queue queueAge() {

        return new Queue("age-queue");
    }

    /**
     * 绑定队列
     * @return
     */
    @Bean
    Binding bindingName(){
        Map<String,Object> map = new HashMap<>();
        map.put("name", "javaboy");

        // 放在map中匹配 如果有就放在相应的路由上
        return BindingBuilder.bind(queueName()).to(headersExchange()).whereAll(map).match();


    }
    @Bean
    Binding bindingAge(){

        // 只要有age这个字段就给你放在相对应的 路由上
        return BindingBuilder.bind(queueName()).to(headersExchange()).where("age").exists();


    }
}
