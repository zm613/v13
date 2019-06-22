package com.zm.v13centerweb.rabbitmqConfig;

import com.zm.v13.common.constant.RabbitMQConstant;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    //声明交换机
    @Bean
    public TopicExchange initExchange() {
        return new TopicExchange(RabbitMQConstant.CENTER_PRODUCT_EXCHANGE);
    }
}
