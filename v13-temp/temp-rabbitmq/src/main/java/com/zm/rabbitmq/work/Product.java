package com.zm.rabbitmq.work;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Product {
    private static String queue_name = "work_queue";

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
        channel.queueDeclare(queue_name, false, false, false, null);
        //将消息发送到队列中
        for (int i = 0; i < 10; i++) {
            String message = "希望随着岁月的增长，改变的不只是你的年龄";
            channel.basicPublish("", queue_name, null, message.getBytes());
        }

        System.out.println("发送成功！");
    }
}
