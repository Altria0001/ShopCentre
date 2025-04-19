package com.buka.api;

import com.buka.domain.GoodsProduct;
import com.buka.entity.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
@FeignClient(name = "goods")
public interface GoodsApi {

    @RequestMapping("/goods/selectById")
    public R<GoodsProduct> selectById(@RequestParam("id") String id);
    @RequestMapping("/goods/getinventory")
    public Integer getinventory(@RequestParam("pid") Long id);
    @RequestMapping("/goods/getPrice")
    public BigDecimal getPrice(@RequestParam("productid") Long productid);
    @RequestMapping("/goods/getGoodsInfo")
    public R<Map<Long,GoodsProduct>> getGoodsInfo(@RequestParam("productIds") List<Long> productIds);
}
