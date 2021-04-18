package com.zzx.study.mq.simple;

import com.rabbitmq.client.*;

import java.io.IOException;

public class Consumer {
    public static void main(String[] args) throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin");
        connectionFactory.setVirtualHost("/");

        Connection connection = connectionFactory.newConnection("消费者");
        Channel channel = connection.createChannel();
        String queueName = "simpleQueue";
        channel.basicConsume(queueName, true, new DeliverCallback() {
            public void handle(String s, Delivery delivery) throws IOException {
                System.out.println("收到的消息是：" + new String(delivery.getBody(), "UTF-8"));
            }
        }, new CancelCallback() {
            public void handle(String s) throws IOException {
                System.out.println("接收消息失败");
            }
        });
        System.out.println("消息接收完毕");
        System.in.read();

        channel.close();
        connection.close();
    }
}
