package com.buka.Listener;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.buka.controller.OrderController;
import com.buka.domain.ItbukaOrder;
import com.buka.entity.MqConstant;
import com.buka.mapper.ItbukaOrderMapper;
import com.buka.service.AliPayService;
import com.buka.service.ItbukaOrderService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.List;

@Component
public class PayLisent {
    @Autowired
    private AliPayService aliPayService;
    @Autowired
    private ItbukaOrderMapper itbukaOrderMapper;
    @RabbitListener(queues = MqConstant.PAY_CUNSUMMER_DEATHQUEUE)
    public void payListen(Long orderId) throws AlipayApiException {
        LambdaQueryWrapper<ItbukaOrder> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(ItbukaOrder::getId,orderId);
        ItbukaOrder itbukaOrders = itbukaOrderMapper.selectOne(lambdaQueryWrapper);
        Integer status = itbukaOrders.getStatus();
        HttpServletRequest httpServletRequest = null;
        httpServletRequest.setAttribute("out_trade_no",orderId);
        String s = aliPayService.AlipayTradeQuery(httpServletRequest);
       if(s.contains("\"msg\":\"Success\"")){
            //"支付成功";

        }

    }
}
