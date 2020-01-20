package com.tangwh.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 模式一Direct 路由策列
 */
@Configuration
public class RabbitDirectConfig {


    public final static String DIRECTNAME = "javaboy-directname";


    /**
     * 定义的消息队列 注意导包
     * @return
     */
    @Bean
    Queue queue(){

        return new Queue("hello.javaboy");
    }

    /**
     * 如果是用的DIRECT这种模式 的话下面两个可以省略掉
     * @return
     */
    @Bean
    DirectExchange directExchange(){
          //                 参数一:发送的消息 参数二:重启之后是否有效  参数三:长期未使用 是否删除
        return new DirectExchange(DIRECTNAME,true,false);
    }

    @Bean
    Binding binding(){

        // queue和绑定在一起directExchange
        return BindingBuilder.bind(queue()).to(directExchange()).with("direct");

    }
}
