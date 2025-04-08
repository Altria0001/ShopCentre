package com.buka.service;

import com.buka.domain.GoodsProduct;
import com.baomidou.mybatisplus.extension.service.IService;
import com.buka.entity.R;

/**
* @author Altria
* @description 针对表【goods_product(商品表)】的数据库操作Service
* @createDate 2025-02-22 10:10:37
*/
public interface GoodsProductService extends IService<GoodsProduct> {

	R selectIsPremmit();

	R updateSuccess(Long id);

	R updateFail(Long id);
}
