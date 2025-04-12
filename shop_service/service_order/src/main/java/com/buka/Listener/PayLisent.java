package com.buka.Listener;

import com.buka.entity.MqConstant;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PayLisent {

    @RabbitListener(queues = MqConstant.PAY_CUNSUMMER_DEATHQUEUE)
    public void payListen(Long orderId){
            System.out.println("队列被监听了！");

    }
}
