package com.buka.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.buka.domain.BuyProductDomain;
import com.buka.domain.ItbukaSeckill;
import com.buka.entity.MqConstant;
import com.buka.entity.PageResult;
import com.buka.entity.R;
import com.buka.service.ItbukaSeckillService;
import com.buka.mapper.ItbukaSeckillMapper;
import com.buka.util.TokenDecode;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static com.buka.task.SeckillTask.SECKILL_PRODUCT_NUM_KEY;

/**
* @author Altria
* @description 针对表【itbuka_seckill(秒杀表)】的数据库操作Service实现
* @createDate 2025-04-19 14:47:30
*/
@Service
public class ItbukaSeckillServiceImpl extends ServiceImpl<ItbukaSeckillMapper, ItbukaSeckill>
    implements ItbukaSeckillService{
	@Autowired
	private TokenDecode tokenDecode;
	@Autowired
	private RedisTemplate redisTemplate;
	@Autowired
	private RabbitTemplate rabbitTemplate;
	@Override
	public boolean buyGoods(Long productid) {
		Map<String,String> userInfo=tokenDecode.getUserInfo();
		String userid = userInfo.get("user_name");
		BuyProductDomain buyProductDomain=new BuyProductDomain();
		buyProductDomain.setUserName(userid);
		buyProductDomain.setProductId(productid);
		Long num = redisTemplate.opsForValue().decrement(SECKILL_PRODUCT_NUM_KEY+":"+productid);
		if (num<0){
			redisTemplate.opsForValue().increment(SECKILL_PRODUCT_NUM_KEY+":"+productid);
			return false;
		}
		else {
			rabbitTemplate.convertAndSend("", MqConstant.SECKILL_QUEUE, JSONObject.toJSONString(buyProductDomain));
			return true;
		}
		
	}
}




