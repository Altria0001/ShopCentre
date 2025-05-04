package com.buka.task;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.buka.api.GoodsApi;
import com.buka.domain.GoodsProduct;
import com.buka.domain.ItbukaSeckill;
import com.buka.domain.ItbukaSeckillProduct;
import com.buka.domain.vo.GoodsVo;
import com.buka.domain.vo.SeckillGoodsVO;
import com.buka.service.ItbukaSeckillProductService;
import com.buka.service.ItbukaSeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Component
public class SeckillTask {


    @Autowired
    private ItbukaSeckillService seckillService;

    public static final String SECKILL_KEY="seckillKey";
    public static final String SECKILL_PRODUCT_NUM_KEY="seckillProductNumKey";

    @Autowired
    private ItbukaSeckillProductService productService;


    @Autowired
    private GoodsApi goodsApi;

    @Autowired
    private RedisTemplate redisTemplate;
    @Scheduled(cron = "0 */1 * * * *")
    //每天凌晨执行一次，更新秒杀商品信息
    public void SeckillProduct(){
        LambdaQueryWrapper<ItbukaSeckill>queryWrapper=new LambdaQueryWrapper<>();
        DateTime StartTime=DateUtil.beginOfDay(new Date());
        DateTime EndTime=DateUtil.endOfDay(new Date());
        queryWrapper.between(ItbukaSeckill::getStartTime,StartTime,EndTime);
        queryWrapper.between(ItbukaSeckill::getEndTime,StartTime,EndTime);
        //获取数据库中当天开始，当天结束的秒杀商品
        List<ItbukaSeckill> list = seckillService.list(queryWrapper);
        for (ItbukaSeckill itbukaSeckill:list){
            Long id=itbukaSeckill.getId();
            LambdaQueryWrapper<ItbukaSeckillProduct> queryWrapper1=new LambdaQueryWrapper<>();
            queryWrapper1.eq(ItbukaSeckillProduct::getProductId,id);
            List<ItbukaSeckillProduct> list1 = productService.list(queryWrapper1);
            //从数据库获取每一件秒杀商品的详情数据
            List<Long> collect = list1.stream().map(ItbukaSeckillProduct::getProductId).collect(Collectors.toList());
            //获取他们的id
            Map<Long, GoodsProduct> data = goodsApi.getGoodsInfo(collect).getData();
            ArrayList<GoodsVo> goodsVos=new ArrayList<>();
            for(ItbukaSeckillProduct itbukaSeckillProduct:list1){
                Integer num = itbukaSeckillProduct.getNum();
                redisTemplate.opsForValue().set(SECKILL_PRODUCT_NUM_KEY+":"+itbukaSeckillProduct.getId(),num.toString());
                //在redis中存入秒杀商品的id以及对应的库存
                GoodsVo goodsVo=new GoodsVo();
                Long productId = itbukaSeckillProduct.getProductId();
                goodsVo.setImgUrl(data.get(productId).getImg());
                goodsVo.setSeckillMoney(new BigDecimal(itbukaSeckillProduct.getSeckillMoney()));
                goodsVo.setProductName(itbukaSeckillProduct.getProductName());
                goodsVo.setProductMoney(new BigDecimal(itbukaSeckillProduct.getProductMoney()));
                goodsVos.add(goodsVo);
            }
            SeckillGoodsVO seckillGoodsVO =new SeckillGoodsVO();
            seckillGoodsVO.setEndTime(itbukaSeckill.getEndTime()+"");
            seckillGoodsVO.setStartTime(itbukaSeckill.getStartTime()+"");
            seckillGoodsVO.setGoodsVos(goodsVos);
            int endhour=DateUtil.hour(itbukaSeckill.getEndTime(),true);
            int starthour=DateUtil.hour(itbukaSeckill.getStartTime(),true);
            redisTemplate.opsForValue().set(SECKILL_KEY,JSONObject.toJSONString(seckillGoodsVO));//存入秒杀商品信息
        }
    }

}
