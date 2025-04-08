package com.buka.config;

import com.buka.entity.MqConstant;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CanalConfig {
	//在mq添加一个队列
	@Bean(MqConstant.PRODDUCT_ADD_QUEUE)
	public Queue addQueue() {
		return new Queue(MqConstant.PRODDUCT_ADD_QUEUE,true);
	}
	@Bean(MqConstant.PRODDUCT_UPDATE_QUEUE)
	public Queue updateQueue() {
		return new Queue(MqConstant.PRODDUCT_UPDATE_QUEUE,true);
	}
	//在mq添加一个交换机
	@Bean(MqConstant.PRODDUCT_EXCHANGE)
	public Exchange exchange() {
		return ExchangeBuilder.directExchange(MqConstant.PRODDUCT_EXCHANGE).durable(true).build();
	}
	//对mq的交换机和队列进行绑定
	@Bean
	public Binding binadd(Exchange exchange, @Qualifier(MqConstant.PRODDUCT_ADD_QUEUE) Queue addQueue){
		return BindingBuilder.bind(addQueue).to(exchange).with(MqConstant.PRODDUCT_ADD_ROUKEY).noargs();
    }
	@Bean
	public Binding bindUpdate(Exchange exchange,@Qualifier(MqConstant.PRODDUCT_UPDATE_QUEUE) Queue queue){
		return BindingBuilder.bind(queue).to(exchange).with(MqConstant.PRODDUCT_UPDATE_ROUKEY).noargs();
	}

}
