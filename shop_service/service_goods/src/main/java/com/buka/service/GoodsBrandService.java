package com.buka.service;

import com.buka.domain.GoodsBrand;
import com.baomidou.mybatisplus.extension.service.IService;
import com.buka.entity.R;

/**
* @author 86182
* @description 针对表【goods_brand(品牌表)】的数据库操作Service
* @createDate 2025-01-18 10:55:11
*/
public interface GoodsBrandService extends IService<GoodsBrand> {


    public R brandList(String brandName,Integer pageNum);
    Integer insertBrand(GoodsBrand goodsBrand);
    Integer updateBrand(GoodsBrand goodsBrand);
    Integer deleteBrand(Long id);
    Integer statusBrand(Long id,Integer status);
    Integer status(Long id);
}
