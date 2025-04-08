package com.buka.controller;

import com.buka.domain.GoodsProduct;
import com.buka.domain.GoodsProductDetails;
import com.buka.entity.R;
import com.buka.service.GoodsProductDetailsService;
import com.buka.service.GoodsProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

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
}
