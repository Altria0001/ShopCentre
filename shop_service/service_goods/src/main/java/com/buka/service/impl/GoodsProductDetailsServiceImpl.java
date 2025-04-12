package com.buka.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.buka.domain.GoodsProductDetails;
import com.buka.entity.R;
import com.buka.mapper.GoodsProductDetailsMapper;
import com.buka.service.GoodsProductDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author Altria
* @description 针对表【goods_product_details(商品详情表)】的数据库操作Service实现
* @createDate 2025-02-22 10:11:10
*/
@Service
public class GoodsProductDetailsServiceImpl extends ServiceImpl<GoodsProductDetailsMapper, GoodsProductDetails>
    implements GoodsProductDetailsService{

	@Override
	public R selectByGoodsId(Long id) {
		LambdaQueryWrapper<GoodsProductDetails> queryWrapper = new LambdaQueryWrapper<>();
		queryWrapper.eq(GoodsProductDetails::getId, id);
		List<GoodsProductDetails> goodsProductDetails = list(queryWrapper);
		return R.ok(goodsProductDetails);
	}
}




