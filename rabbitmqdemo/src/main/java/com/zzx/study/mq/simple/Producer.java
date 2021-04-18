package com.zzx.study.mq.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Producer {
    public static void main(String[] args) throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin");
        connectionFactory.setVirtualHost("/");

        Connection connection = connectionFactory.newConnection("生产者");
        Channel channel = connection.createChannel();
        String queueName = "simpleQueue";
        channel.queueDeclare(queueName, false, false, false, null);
        String message = "你好世界,hahahhah！！！";

        channel.basicPublish("", queueName, null, message.getBytes());

        channel.close();
        connection.close();
        System.out.println("消息发送完毕");
    }

}
