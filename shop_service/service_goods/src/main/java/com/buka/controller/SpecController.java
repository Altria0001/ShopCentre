package com.buka.controller;

import com.buka.domain.GoodsSpec;
import com.buka.service.GoodsSpecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/spec")
public class SpecController {
	@Autowired
	private GoodsSpecService specService;
	@PostMapping("/insert")
	public Integer insertGoodsSpec(@RequestBody GoodsSpec goodsSpec) {
		return specService.insertGoodsSpec(goodsSpec);
	}
}
