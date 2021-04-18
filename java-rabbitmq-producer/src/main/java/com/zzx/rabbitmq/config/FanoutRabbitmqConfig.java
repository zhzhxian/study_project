package com.zzx.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FanoutRabbitmqConfig {
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("my_fanout_exchange",true,false);
    }

    @Bean
    public Queue fanoutQueue1() {
        return new Queue("fanout_queue1",true);
    }

    @Bean
    public Queue fanoutQueue2() {
        return new Queue("fanout_queue2",true);
    }

    @Bean
    public Queue fanoutQueue3() {
        return new Queue("fanout_queue3",true);
    }

    @Bean
    public Binding fanoutBinding1() {
        return BindingBuilder.bind(fanoutQueue1()).to(fanoutExchange());
    }

    @Bean
    public Binding fanoutBinding2() {
        return BindingBuilder.bind(fanoutQueue2()).to(fanoutExchange());
    }

    @Bean
    public Binding fanoutBinding3() {
        return BindingBuilder.bind(fanoutQueue3()).to(fanoutExchange());
    }

}
