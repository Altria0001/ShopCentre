package com.buka.Listener;

import com.alibaba.fastjson.JSONObject;
import com.buka.domain.BuyProductDomain;
import com.buka.domain.ItbukaSeckill;
import com.buka.entity.MqConstant;
import com.buka.service.ItbukaSeckillProductService;
import com.buka.service.ItbukaSeckillService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import static com.buka.task.SeckillTask.SECKILL_PRODUCT_NUM_KEY;

@Component
public class SeckillListener {
	@Autowired
	private ItbukaSeckillProductService productService;
	@Autowired
	private RedisTemplate redisTemplate;
	@RabbitListener(queues = MqConstant.SECKILL_QUEUE)
	public void lisenSeckill(String message){
		System.out.println("成功监听");
		BuyProductDomain buyProductDomain = JSONObject.parseObject(message, BuyProductDomain.class);
		Long productId = buyProductDomain.getProductId();
		String productNum = (String) redisTemplate.opsForValue().get(SECKILL_PRODUCT_NUM_KEY +":"+ productId);
		if (productNum!= null) {
			Long Num = Long.parseLong(productNum);
			// 调用产品服务将商品数量写入数据库
			productService.updateProductQuantity(productId, productNum);
			System.out.println("商品数量已写入数据库: " + productId + "  " + productNum);
		}

	}
}
