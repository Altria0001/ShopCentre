package com.buka.task;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.buka.Ad;
import com.buka.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdTask implements CommandLineRunner {
	@Autowired
	private AdService adService;
	@Autowired
	private RedisTemplate redisTemplate;
	private static final String AD_LIST_KEY = "ad_list";

	@Override
	public void run(String... args) throws Exception {
		redisTemplate.delete(AD_LIST_KEY);//先删除之前的再插入现在的
		LambdaQueryWrapper<Ad> ad = new LambdaQueryWrapper<>();
		ad.eq(Ad::getStatus, 0);
		List<Ad> list=adService.list(ad);
		for (Ad ad1 : list) {
			redisTemplate.opsForList().leftPush("AD_LIST_KEY", ad1);
		}
	}
}
