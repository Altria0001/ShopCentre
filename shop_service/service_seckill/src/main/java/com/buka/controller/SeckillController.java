package com.buka.controller;

import com.buka.entity.R;
import com.buka.service.ItbukaSeckillProductService;
import com.buka.service.ItbukaSeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("seckill")
public class SeckillController {
	@Autowired
	private ItbukaSeckillService seckillService;
	@Autowired
	private ItbukaSeckillProductService seckillProductService;
	@RequestMapping("list")
	public R seckillGoodsList(){
		return R.ok(seckillService.slect());
	}
	@RequestMapping("buy")
	public R seckillBuyGoods(Long productid){
		boolean add = seckillService.buyGoods(productid);
		if(add){
			return R.ok("秒杀成功，已经锁单，请及时付款");
		}
		return R.fail("秒杀失败，库存不足");
	}

}
