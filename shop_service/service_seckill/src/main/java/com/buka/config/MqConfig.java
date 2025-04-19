package com.buka.config;

import com.buka.entity.MqConstant;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqConfig {
	@Bean(MqConstant.SECKILL_QUEUE)
	public Queue pauConsummerQueue(){
		Queue queue = new Queue(MqConstant.SECKILL_QUEUE,
				true, false, false);
		return queue;
	}
}
