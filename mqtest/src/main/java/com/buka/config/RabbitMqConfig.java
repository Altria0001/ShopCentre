package com.buka.config;


import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
    @Bean("javaQueque")
    public Queue queue(){
        return new Queue("javaQueque",true);
    }
    @Bean("javaDirectExchange")
    public Exchange exchange(){
        return ExchangeBuilder.directExchange("javaDirectExchange").durable(true).build();
    }
    @Bean
    public Binding binding(Exchange exchange,Queue queue){
        return BindingBuilder.bind(queue).to(exchange).with("xxx").noargs();
    }


}
