package com.tangwh.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 路由策略二
 */
@Configuration
public class RabbitFanoutConfig {


    public static final String FANOUTNAME = "javaboy-fanout";

    /**
     * 队列一
     *
     * @return
     */
    @Bean
    Queue queueOne() {

        return new Queue("queue-one");

    }

    /**
     * 队列二
     *
     * @return
     */
    @Bean
    Queue queueTwo() {

        return new Queue("queue-two");
    }


    @Bean
    FanoutExchange fanoutExchange() {

// 参数一:发送的消息 参数二:重启之后是否有效  参数三:长期未使用 是否删除
        return new FanoutExchange(FANOUTNAME, true, false);
    }


    @Bean
    Binding bindingOne(){
        // 绑定到哪去
        return BindingBuilder.bind(queueOne()).to(fanoutExchange());
    }

    @Bean
    Binding bindingTwo(){

        return BindingBuilder.bind(queueTwo()).to(fanoutExchange());
    }

}
