package com.zzx.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeadDirectRabbitmqConfig {
    @Bean
    public DirectExchange deadDirectExchange() {
        return new DirectExchange("my_dead_direct_exchange", true, false);
    }

    @Bean
    public Queue deadDirectQueue1() {
        return new Queue("dead_direct_queue1", true);
    }


    @Bean
    public Binding deadDirectBinding1() {
        return BindingBuilder.bind(deadDirectQueue1()).to(deadDirectExchange()).with("dead");
    }

}
