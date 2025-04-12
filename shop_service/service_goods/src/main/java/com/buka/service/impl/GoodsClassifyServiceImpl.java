package com.buka.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.buka.domain.GoodsClassify;
import com.buka.entity.R;
import com.buka.mapper.GoodsClassifyMapper;
import com.buka.service.GoodsClassifyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
* @author 86182
* @description 针对表【goods_classify(分类表)】的数据库操作Service实现
* @createDate 2025-01-19 12:55:56
*/
@Service
public class GoodsClassifyServiceImpl extends ServiceImpl<GoodsClassifyMapper, GoodsClassify>
    implements GoodsClassifyService{
    @Override
    public List<GoodsClassify> selectAll() {
        LambdaQueryWrapper<GoodsClassify> goodsClassifyLambdaQueryWrapper = new LambdaQueryWrapper<>();
        goodsClassifyLambdaQueryWrapper.eq(GoodsClassify::getIsDelete,0);
        List<GoodsClassify> list = list(goodsClassifyLambdaQueryWrapper);
        //获取一级的分类
        List<GoodsClassify> top1 = list.stream().filter(goodsClassify -> goodsClassify.getIsuperiorId().equals(0L))
                .collect(Collectors.toList());
        for (GoodsClassify goodsClassify : top1) {
            Long id = goodsClassify.getId();
            // goodsClassify 的子集
            List<GoodsClassify> collect = list.stream().filter(goodsClassify1 -> goodsClassify1.getIsuperiorId().equals(id))
                    .collect(Collectors.toList());
            goodsClassify.setChildren(collect);

        }
        return top1;
    }

    @Override
    public void insert(GoodsClassify goodsClassify) {

    }

    @Override
    public void update(GoodsClassify goodsClassify) {
       baseMapper.updateById(goodsClassify);
       R.ok();
    }

    @Override
    public void delete(Long ids) {

    }
}




