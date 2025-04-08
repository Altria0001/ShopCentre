package com.buka.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.buka.domain.GoodsSpec;
import com.buka.service.GoodsSpecService;
import com.buka.mapper.GoodsSpecMapper;
import org.springframework.stereotype.Service;

/**
* @author Altria
* @description 针对表【goods_spec(规格表)】的数据库操作Service实现
* @createDate 2025-01-21 12:35:37
*/
@Service
public class GoodsSpecServiceImpl extends ServiceImpl<GoodsSpecMapper, GoodsSpec>
    implements GoodsSpecService{

	@Override
	public Integer insertGoodsSpec(GoodsSpec goodsSpec) {
		return baseMapper.insert(goodsSpec);
	}
}




