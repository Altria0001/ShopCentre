package com.buka.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.buka.domain.GoodsBrand;
import com.buka.entity.R;
import com.buka.mapper.GoodsBrandMapper;
import com.buka.service.GoodsBrandService;
import org.springframework.stereotype.Service;

/**
* @author 86182
* @description 针对表【goods_brand(品牌表)】的数据库操作Service实现
* @createDate 2025-01-18 10:55:11
*/
@Service
public class GoodsBrandServiceImpl extends ServiceImpl<GoodsBrandMapper, GoodsBrand>
    implements GoodsBrandService{
    @Override
    public R brandList(String brandName,Integer pageNum) {
        LambdaQueryWrapper<GoodsBrand> goodsBrandLambdaQueryWrapper = new LambdaQueryWrapper<>();
        goodsBrandLambdaQueryWrapper.like(GoodsBrand::getBrandName,brandName);
        goodsBrandLambdaQueryWrapper.eq(GoodsBrand::getIsDelete,0);
        Page<GoodsBrand> page = new Page<>();
        page.setSize(10);
        page.setCurrent(pageNum);
        Page<GoodsBrand> page1 = page(page, goodsBrandLambdaQueryWrapper);
        return R.ok(page1);
    }

    @Override
    public Integer insertBrand(GoodsBrand goodsBrand) {
        return baseMapper.insert(goodsBrand);
    }

    @Override
    public Integer updateBrand(GoodsBrand goodsBrand) {
        return baseMapper.updateById(goodsBrand);
    }

    @Override
    public Integer deleteBrand(Long id) {
        String ids=id.toString();
        try{
            String[] split=ids.split(",");
            for(String s:split){
               GoodsBrand goodsBrand=new GoodsBrand();
               goodsBrand.setId(Long.parseLong(s));
               goodsBrand.setIsDelete(1);
               baseMapper.updateById(goodsBrand);
            }
            return 1;
        }catch (Exception e){
            return -1;
            }

    }

    @Override
    public Integer statusBrand(Long id, Integer status) {
        GoodsBrand goodsBrand=new GoodsBrand();
        goodsBrand.setId(id);
        goodsBrand.setStatus(status);
        return baseMapper.updateById(goodsBrand);
    }

    @Override
    public Integer status(Long id) {
        GoodsBrand goodsBrand=new GoodsBrand();
        if(goodsBrand==null){
            return -1;
        }
        if (goodsBrand.getStatus()==1){
            goodsBrand.setStatus(0);
        }else {
            goodsBrand.setStatus(1);
        }
        return baseMapper.updateById(goodsBrand);
    }

}




