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
        // 查询本系统，订单状态是否支付
        // 查询支付宝的支付状态


        // 如果两个系统都没有支付，那么关闭订单
        System.out.println("队列被监听了！");
    }
}
