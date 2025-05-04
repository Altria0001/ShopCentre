package com.buka.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.buka.domain.ItbukaSeckillProduct;
import com.buka.service.ItbukaSeckillProductService;
import com.buka.mapper.ItbukaSeckillProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author Altria
* @description 针对表【itbuka_seckill_product(秒杀商品表)】的数据库操作Service实现
* @createDate 2025-04-19 14:48:19
*/
@Service
public class ItbukaSeckillProductServiceImpl extends ServiceImpl<ItbukaSeckillProductMapper, ItbukaSeckillProduct>
    implements ItbukaSeckillProductService{
	@Autowired
	ItbukaSeckillProductMapper ItbukaSeckillProductMapper;

	@Override
	public void updateProductQuantity(Long productId, String productNum) {
		Integer num = Integer.parseInt(productNum);
		UpdateWrapper<ItbukaSeckillProduct> updateWrapper = new UpdateWrapper<>();
		updateWrapper.eq("id", productId);
		updateWrapper.set("num", num);
		int updatedRows = ItbukaSeckillProductMapper.update(null, updateWrapper);
	}
}




