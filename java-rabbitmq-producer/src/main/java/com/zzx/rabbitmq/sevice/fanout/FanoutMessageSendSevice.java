package com.zzx.rabbitmq.sevice.fanout;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FanoutMessageSendSevice {
    @Autowired
    private RabbitTemplate rabbitTemplate;


    public void senMsg() {
        String msg = UUID.randomUUID().toString();
        System.out.println("开始发送Fanout消息：" + msg);
        rabbitTemplate.convertAndSend("my_fanout_exchange", "", msg);
    }
}
