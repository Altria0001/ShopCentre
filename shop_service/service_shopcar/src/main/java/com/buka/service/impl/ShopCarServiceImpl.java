package com.buka.service.impl;

import com.buka.api.GoodsApi;
import com.buka.domain.AddCarDTO;
import com.buka.entity.R;
import com.buka.service.ShopCarService;
import com.buka.util.TokenDecode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ShopCarServiceImpl implements ShopCarService {

	@Autowired
	private TokenDecode tokenDecode;


	@Autowired
	private RedisTemplate redisTemplate;


	@Autowired
	private GoodsApi goodsApi;


	private final static String CAR_KEY="car_key";

	@Override
	public R addCar(AddCarDTO addCarDTO) {
		Map<String, String> userInfo = tokenDecode.getUserInfo();
		String userId = userInfo.get("user_name");


		//确认一下当前登录用户
		// 需要确认加入购物车时，库存是否充足


// 将商品存入redis中，key为userId,field为productDetailId,value为数量
		Boolean hashProduct = redisTemplate.opsForHash().hasKey(CAR_KEY + userId, addCarDTO.getProductDetailId());
		if (hashProduct){
			//已经有这个字段了
			Object countObj = redisTemplate.opsForHash().get(CAR_KEY + userId, addCarDTO.getProductDetailId());
			Integer count = Integer.valueOf(countObj + "");
			count=count+addCarDTO.getCount();
			redisTemplate.opsForHash().put(CAR_KEY + userId,addCarDTO.getProductDetailId(),count);
		}else {
			//原来没有这个商品
			redisTemplate.opsForHash().put(CAR_KEY + userId,addCarDTO.getProductDetailId(),addCarDTO.getCount());
		}
		return R.ok();
	}
}
