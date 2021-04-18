package com.zzx.rabbitmq.service.direct;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RabbitListener(queues = {"direct_queue2"})
public class DirectConsumerService2 {
    @RabbitHandler
    public void reciveMsg(String message) {
        System.out.println("direct_queue2接收到的消息是：" + message);

    }
}
