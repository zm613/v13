package com.zm.tempspringbootrabbitmq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue initQueue(){
        return new Queue("springboot_simple_queue",false,false,false);
    }
}
