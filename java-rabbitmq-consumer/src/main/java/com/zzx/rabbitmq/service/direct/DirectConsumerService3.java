package com.zzx.rabbitmq.service.direct;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RabbitListener(queues = {"direct_queue3"})
public class DirectConsumerService3 {
    @RabbitHandler
    public void reciveMsg(String message) {
        System.out.println("direct_queue3接收到的消息是：" + message);

    }
}
