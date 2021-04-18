package com.zzx.rabbitmq.sevice.direct;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class DirectMessageSendSevice {
    @Autowired
    private RabbitTemplate rabbitTemplate;


    public Object senMsg(String routeKey) {
        String msg = UUID.randomUUID().toString();
        System.out.println("开始发送Direct消息：" + msg);
        rabbitTemplate.convertAndSend("my_direct_exchange", routeKey, msg);
        Map<String, String> result = new HashMap<>();
        result.put("routeKey", routeKey);
        result.put("msg", msg);
        return result;
    }

    public Object senTtlMsg(String routeKey) {
        String msg = UUID.randomUUID().toString();
        System.out.println("开始发送Direct消息：" + msg);
        rabbitTemplate.convertAndSend("my_ttl_direct_exchange", routeKey, msg);
        Map<String, String> result = new HashMap<>();
        result.put("routeKey", routeKey);
        result.put("msg", msg);
        return result;
    }

    public Object senTtlMessage(String routeKey) {
        String msg = UUID.randomUUID().toString();
        System.out.println("开始发送Direct消息：" + msg);
        Map<String, String> result = new HashMap<>();
        MessagePostProcessor messagePostProcessor = new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setExpiration("5000");
                message.getMessageProperties().setContentEncoding("UTF-8");
                return message;
            }
        };
        rabbitTemplate.convertAndSend("my_ttl_direct_exchange", routeKey, msg, messagePostProcessor);

        result.put("routeKey", routeKey);
        result.put("msg", msg);
        return result;
    }
}
