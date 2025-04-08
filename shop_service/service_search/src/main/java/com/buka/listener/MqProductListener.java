package com.buka.listener;


import com.buka.api.GoodsApi;
import com.buka.domain.GoodsProduct;
import com.buka.domain.GoodsProductForEs;
import com.buka.entity.MqConstant;
import com.buka.repository.GoodsProductRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MqProductListener {
    @Autowired
    private GoodsApi goodsApi;
    @Autowired
    private GoodsProductRepository repository;
    @RabbitListener(queues = MqConstant.PRODDUCT_ADD_QUEUE )

    public void listenMqProduct(String productId){
        //通过商品服务，查询到对应的商品
        GoodsProduct data = goodsApi.selectById(productId).getData();
        //将查询到的商品，加入到es中
        GoodsProductForEs goodsProductForEs = new GoodsProductForEs();
        BeanUtils.copyProperties(data,goodsProductForEs);
        repository.save(goodsProductForEs);
    }

    @RabbitListener(queues = MqConstant.PRODDUCT_UPDATE_QUEUE)
    public void listenUpdateMqProduct(String idValue){
        String[] split = idValue.split(",");
        String beforId=split[0];
        String afterId=split[1];
        repository.deleteById(beforId);
        GoodsProduct data = goodsApi.selectById(afterId).getData();
        if (data!=null){
            GoodsProductForEs goodsProductForEs = new GoodsProductForEs();
            BeanUtils.copyProperties(data,goodsProductForEs);
            repository.save(goodsProductForEs);
        }
    }
}
