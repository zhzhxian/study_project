package com.zzx.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class TtlDirectRabbitmqConfig {
    @Bean
    public DirectExchange ttldirectExchange() {
        return new DirectExchange("my_ttl_direct_exchange", true, false);
    }

    @Bean
    public Queue ttldirectQueue1() {
        Map<String, Object> ttlArgs = new HashMap<>();
        ttlArgs.put("x-message-ttl", 5000);
        ttlArgs.put("x-max-length", 5);
        ttlArgs.put("x-dead-letter-exchange", "my_dead_direct_exchange");
        ttlArgs.put("x-dead-letter-routing-key", "dead");
        return new Queue("ttl_direct_queue1", true, false, true, ttlArgs);
    }


    @Bean
    public Queue ttlMessagedirectQueue1() {
        return new Queue("ttl_message_direct_queue1", true);
    }

    @Bean
    public Binding ttldirectBinding1() {
        return BindingBuilder.bind(ttldirectQueue1()).to(ttldirectExchange()).with("email");
    }

    @Bean
    public Binding ttlMessagedirectBinding1() {
        return BindingBuilder.bind(ttlMessagedirectQueue1()).to(ttldirectExchange()).with("ttlmessage");
    }


}
