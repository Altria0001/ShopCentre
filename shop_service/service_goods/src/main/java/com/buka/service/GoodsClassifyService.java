package com.buka.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.buka.domain.GoodsClassify;

import java.util.List;

/**
* @author 86182
* @description 针对表【goods_classify(分类表)】的数据库操作Service
* @createDate 2025-01-19 12:55:56
*/
public interface GoodsClassifyService extends IService<GoodsClassify> {

	List<GoodsClassify> selectAll();

	void insert(GoodsClassify goodsClassify);

	void update(GoodsClassify goodsClassify);

	void delete(Long ids);
}
