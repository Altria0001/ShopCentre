package com.buka.service;

import com.buka.domain.GoodsSpec;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author Altria
* @description 针对表【goods_spec(规格表)】的数据库操作Service
* @createDate 2025-01-21 12:35:37
*/
public interface GoodsSpecService extends IService<GoodsSpec> {
	Integer insertGoodsSpec(GoodsSpec goodsSpec);

}
