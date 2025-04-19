package com.buka.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodsVo {
    //商品价格 原价
    private BigDecimal productMoney;

    //秒杀价
    private BigDecimal seckillMoney;

    //图片链接
    private String imgUrl;

    //商品名称
    private String productName;

}
