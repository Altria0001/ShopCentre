package com.buka.controller;


import com.buka.domain.GoodsBrand;
import com.buka.entity.R;
import com.buka.service.GoodsBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("brand")
public class BrandController {
    @Autowired
    private GoodsBrandService brandService;


    @RequestMapping("brandList")
    public R brandList(String brandName, Integer pageNum) {
        return brandService.brandList(brandName, pageNum);
    }

    @PostMapping("/insert")
    public R insertBrand(@RequestBody GoodsBrand goodsBrand) {
        brandService.insertBrand(goodsBrand);
        return R.ok();
    }

    @PostMapping("/update")
    public R updateBrand(@RequestBody GoodsBrand goodsBrand) {
        brandService.updateBrand(goodsBrand);
        return R.ok();
    }

    @PostMapping("/delete")
    public R deleteBrand(Long id) {
        brandService.deleteBrand(id);
        return R.ok();
    }

    @PostMapping("/status")
    public R statusBrand(@RequestParam Long id, @RequestParam Integer status) {
        if (id == null) {
            return R.fail("查询不到品牌");
        }
        Integer integer = null;
        if (status == null) {
            integer = brandService.status(id);

        } else {
            integer = brandService.statusBrand(id, status);
        }
        if (integer != 1) {
            return R.fail("修改失败");
        }
        return R.ok();

    }
}
