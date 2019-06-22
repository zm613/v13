package com.zm.v13searchweb.config;

import com.zm.v13.common.constant.RabbitMQConstant;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    //声明消息队列
    @Bean
    public Queue queue() {
        return new Queue(RabbitMQConstant.PRODUCT_SEARCH_QUEUE, true, false, false);
    }

    //声明交换机，如果交换机已经存在，则不需要做操作
    @Bean
    public TopicExchange initExchange() {
        return new TopicExchange(RabbitMQConstant.CENTER_PRODUCT_EXCHANGE);
    }

    //绑定交换机
    @Bean
    public Binding initBinding(Queue queue, TopicExchange topicExchange) {
        return BindingBuilder.bind(queue).to(topicExchange).with("product.add");
    }
}
