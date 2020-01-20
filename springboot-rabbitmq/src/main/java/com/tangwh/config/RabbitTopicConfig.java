package com.tangwh.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitTopicConfig {


    public static final String TOPICNAME = "javabo-topic";

    @Bean
    TopicExchange topicExchange() {

        return new TopicExchange(TOPICNAME, true, false);
    }

    /**
     * 队列一
     *
     * @return
     */
    @Bean
    Queue xiaomi() {
        return new Queue("xiaomi");
    }


    /**
     * 队列二
     *
     * @return
     */
    @Bean
    Queue huawei() {

        return new Queue("huawei");
    }

    /**
     * 队列二
     *
     * @return
     */
    @Bean
    Queue phone() {

        return new Queue("phone");
    }


    /**
     * 绑定小米队列
     *
     * @return
     */
    @Bean
    Binding xiaomiBinding() {
        // 如果消息routingKey已小米开头的都会绑定在
        return BindingBuilder.bind(xiaomi()).to(topicExchange()).with("xiaomi.#");
    }

    /**
     * 绑定华为队列
     *
     * @return
     */
    @Bean
    Binding huaweiBinding() {
        return BindingBuilder.bind(huawei()).to(topicExchange()).with("huawei.#");

    }


    /**
     * 绑定手机队列
     * @return
     */
    @Bean
    Binding phoneBinding() {
        // 包含phone 决定队列
        return BindingBuilder.bind(phone()).to(topicExchange()).with("#.phone.#");

    }


}
