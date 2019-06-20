package com.zm.rabbitmq.publish;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class MyConsumer1 {
    private static String exchange_name = "fanout_exchange";
    private static String queue_name = "fanout_exchange_queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        //创建链接对象
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.254.128");
        factory.setPort(5672);
        factory.setVirtualHost("/zmVirtualHosts");
        factory.setUsername("zm");
        factory.setPassword("123456");

        Connection connection = factory.newConnection();
        //创建本次交互的通道
        Channel channel = connection.createChannel();
        //声明队列
        channel.queueDeclare(queue_name,false,false,false,null);
        //消息者监听队列，获取消息
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body);
                System.out.println("接收到消息1" + msg);
            }
        };
        //绑定队列跟交换机
        channel.queueBind(queue_name,exchange_name,"");

        //消费者需要来监听队列
        channel.basicConsume(queue_name, false, consumer);
    }
}
