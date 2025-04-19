package com.buka.Listener;

import com.buka.entity.MqConstant;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class SeckillListener {
	@RabbitListener(queues = MqConstant.SECKILL_QUEUE)
	public void lisenSeckill(){
		System.out.println("成功监听");
		//去mysql中减少库存
	}
}
