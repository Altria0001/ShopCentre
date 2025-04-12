package com.buka.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.buka.domain.GoodsProduct;
import com.buka.entity.R;
import com.buka.mapper.GoodsProductMapper;
import com.buka.service.GoodsProductService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author Altria
* @description 针对表【goods_product(商品表)】的数据库操作Service实现
* @createDate 2025-02-22 10:10:37
*/
@Service
public class GoodsProductServiceImpl extends ServiceImpl<GoodsProductMapper, GoodsProduct>
    implements GoodsProductService{

	@Override
	public R selectIsPremmit() {
		LambdaQueryWrapper<GoodsProduct> queryWrapper = new LambdaQueryWrapper<>();
		queryWrapper.eq(GoodsProduct::getAudit, 0);
		List<GoodsProduct> list = list(queryWrapper);
		return R.ok(list);
	}

	@Override
	public R updateSuccess(Long id) {
		LambdaUpdateWrapper<GoodsProduct> updateWrapper = new LambdaUpdateWrapper<>();
		updateWrapper.set(GoodsProduct::getStatus, 1);
		updateWrapper.eq(GoodsProduct::getId, id);
		return R.ok(updateWrapper);
	}

	@Override
	public R updateFail(Long id) {
		LambdaUpdateWrapper<GoodsProduct> updateWrapper = new LambdaUpdateWrapper<>();
		updateWrapper.set(GoodsProduct::getStatus, 2);
		updateWrapper.eq(GoodsProduct::getId, id);
		return R.ok(updateWrapper);
	}
}




