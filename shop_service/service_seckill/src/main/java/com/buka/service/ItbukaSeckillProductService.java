package com.buka.service;

import com.buka.domain.ItbukaSeckillProduct;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author Altria
* @description 针对表【itbuka_seckill_product(秒杀商品表)】的数据库操作Service
* @createDate 2025-04-19 14:48:19
*/
public interface ItbukaSeckillProductService extends IService<ItbukaSeckillProduct> {
	void updateProductQuantity(Long productId, String productNum);
}
