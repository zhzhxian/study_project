package com.zzx.rabbitmq.service.fanout;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RabbitListener(queues = {"fanout_queue1"})
public class FanoutConsumerService1 {
    @RabbitHandler
    public void reciveMsg(String message) {
        System.out.println("接收到的消息是：" + message);

    }
}
