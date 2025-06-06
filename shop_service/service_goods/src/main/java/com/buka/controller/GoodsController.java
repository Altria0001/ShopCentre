package com.buka.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.buka.domain.GoodsProduct;
import com.buka.domain.GoodsProductDetails;
import com.buka.entity.R;
import com.buka.service.GoodsProductDetailsService;
import com.buka.service.GoodsProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/goods")
public class GoodsController {
	@Autowired
	private GoodsProductService ProductService;
	@Autowired
	private GoodsProductDetailsService ProductDetailsService;

	@RequestMapping("/goodslist")
	public R goodsList(@RequestBody GoodsProduct goodsProduct) {
		boolean save = ProductService.save(goodsProduct);
		return R.trueOrFalse(save);
	}
	@RequestMapping("/goodsinfo")
	public R goodsInfo() {
		return  ProductService.selectIsPremmit();
	}
	@PostMapping("/updatesuccess")
	public R updateSuccess(@RequestBody Long id) {
		return ProductService.updateSuccess(id);
	}
	@PostMapping("/updatefail")
	public R updatefail(@RequestBody Long id) {
		return ProductService.updateFail(id);
	}
	@RequestMapping("/goodsdetail")
	public R goodsDetail(@RequestBody GoodsProductDetails goodsProductDetails) {
		boolean save = ProductDetailsService.save(goodsProductDetails);
		return R.trueOrFalse(save);
	}
	@PostMapping("/goodsdetaillist")
	public R goodsDetailList(@RequestBody Long id) {
		return ProductDetailsService.selectByGoodsId(id);
	}
	@RequestMapping("/selectById")
	public R<GoodsProduct> selectById(String id){
		GoodsProduct byId = ProductService.getById(id);
		System.out.println(byId);
		System.out.println("执行完成");
		return R.ok(byId);
	}
	@RequestMapping("/getinventory")
	public Integer getinventory(Long pid){
		return  ProductDetailsService.getById(pid).getInventory();
	}
	@RequestMapping("/getPrice")
	public BigDecimal getPrice(Long productid){

		return ProductDetailsService.getById(productid).getPrice();
	}
	@RequestMapping("/getGoodsInfo")
	public R <Map<Long,GoodsProduct>> getGoodsInfo(@RequestParam("productIds") List<Long> productIds){
		if (productIds == null || productIds.isEmpty()) {
			return R.fail("产品为空");
		}

		LambdaQueryWrapper<GoodsProduct> queryWrapper = new LambdaQueryWrapper<>();
		queryWrapper.in(GoodsProduct::getId, productIds);
		List<GoodsProduct> products = ProductService.list(queryWrapper);

		if (products == null || products.isEmpty()) {
			return R.ok(Collections.emptyMap());
		}

		Map<Long, GoodsProduct> result = new HashMap<>(products.size());
		for (GoodsProduct product : products) {
			result.put(product.getId(), product);
		}
		return R.ok(result);
	}
}
