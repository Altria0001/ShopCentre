package com.buka.service;

import com.buka.domain.GoodsProductDetails;
import com.baomidou.mybatisplus.extension.service.IService;
import com.buka.entity.R;

/**
* @author Altria
* @description 针对表【goods_product_details(商品详情表)】的数据库操作Service
* @createDate 2025-02-22 10:11:10
*/
public interface GoodsProductDetailsService extends IService<GoodsProductDetails> {

	R selectByGoodsId(Long id);
}
