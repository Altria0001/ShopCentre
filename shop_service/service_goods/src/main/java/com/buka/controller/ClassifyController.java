package com.buka.controller;

import com.buka.domain.GoodsClassify;
import com.buka.entity.R;
import com.buka.service.GoodsClassifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classify")
public class ClassifyController {
	@Autowired
	private GoodsClassifyService classifyService;
	@GetMapping("/selectAll")
	public R<List<GoodsClassify>> selectAll() {
	    List<GoodsClassify> classifyList = (List<GoodsClassify>) classifyService.selectAll();
	    return new  R<>("查询成功", classifyList);
	}
	@PostMapping("/insert")
	public R insert(@RequestBody GoodsClassify goodsClassify){
		classifyService.insert(goodsClassify);
		return new R<>("添加成功");
	}
	@PostMapping("/update")
	public R update(@RequestBody GoodsClassify goodsClassify){
		classifyService.update(goodsClassify);
		return new R<>("修改成功");
	}
	@PostMapping("/delete")
	public R delete(@RequestBody Long ids){
		classifyService.delete(ids);
		return new R<>("删除成功");
	}
}
