package com.buka.config;

import com.buka.entity.MqConstant;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class Mqconfig {
	@Bean(MqConstant.PAY_PROVIDER_DEATHQUEUE)//生产者队列，数据传到队列中，设置过期时间，将队列消息发送到交换机
	public Queue payqueue(){
		HashMap<String,Object> param=new HashMap<>();
		param.put("x-dead-letter-exchange",MqConstant.PAY_EXCHANGE);   //消息过期之后发送到哪个交换机
		param.put("x-message-ttl",3000);
		return new Queue(MqConstant.PAY_PROVIDER_DEATHQUEUE, true, false, false, param);
	}
	@Bean(MqConstant.PAY_EXCHANGE)
	public Exchange exchange(){
		return ExchangeBuilder.fanoutExchange(MqConstant.PAY_EXCHANGE).durable(true).build();
	}
	@Bean(MqConstant.PAY_CUNSUMMER_DEATHQUEUE)
	public Queue PayCunQueue(){
		return new Queue(MqConstant.PAY_CUNSUMMER_DEATHQUEUE,true,false,false);
	}


	@Bean("payDeathBind")
	public Binding bind(@Qualifier(MqConstant.PAY_EXCHANGE) Exchange exchange,@Qualifier(MqConstant.PAY_CUNSUMMER_DEATHQUEUE) Queue queue){
		return BindingBuilder.bind(queue).to(exchange).with("").noargs();
	}


}
