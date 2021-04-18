package com.zzx.study.mq.routing;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer {
    static Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Channel channel = null;
            Connection connection = null;
            try {
                ConnectionFactory connectionFactory = new ConnectionFactory();
                connectionFactory.setHost("localhost");
                connectionFactory.setPort(5672);
                connectionFactory.setUsername("admin");
                connectionFactory.setPassword("admin");
                connectionFactory.setVirtualHost("/");

                connection = connectionFactory.newConnection("消费者");
                channel = connection.createChannel();
                String queueName = Thread.currentThread().getName();
                channel.basicConsume(queueName, true, new DeliverCallback() {
                    public void handle(String s, Delivery delivery) throws IOException {
                        System.out.println("收到的消息是：" + queueName + "-->" + new String(delivery.getBody(), "UTF-8"));
                    }
                }, new CancelCallback() {
                    public void handle(String s) throws IOException {
                        System.out.println("接收消息失败");
                    }
                });
                System.out.println("消息接收完毕");
                System.in.read();
            } catch (Exception e) {

            } finally {
                try {
                    channel.close();
                    connection.close();

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    public static void main(String[] args) throws Exception {
        new Thread(runnable, "q1").start();
        new Thread(runnable, "q2").start();
        new Thread(runnable, "q3").start();
    }
}
