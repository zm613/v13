package com.zm.tempspringbootrabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "springboot_simple_queue")
public class Consume {
    @RabbitHandler
    public void process(String msg){
        System.out.println(msg);
    }
}
