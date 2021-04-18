package com.zzx.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DirectRabbitmqConfig {
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("my_direct_exchange", true, false);
    }

    @Bean
    public Queue directQueue1() {
        return new Queue("direct_queue1", true);
    }

    @Bean
    public Queue directQueue2() {
        return new Queue("direct_queue2", true);
    }

    @Bean
    public Queue directQueue3() {
        return new Queue("direct_queue3", true);
    }

    @Bean
    public Binding directBinding1() {
        return BindingBuilder.bind(directQueue1()).to(directExchange()).with("email");
    }

    @Bean
    public Binding directBinding2() {
        return BindingBuilder.bind(directQueue2()).to(directExchange()).with("sms");
    }

    @Bean
    public Binding directBinding3() {
        return BindingBuilder.bind(directQueue3()).to(directExchange()).with("wx");
    }

}
