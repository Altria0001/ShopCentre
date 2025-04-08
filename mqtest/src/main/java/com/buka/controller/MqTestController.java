package com.buka.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class MqTestController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequestMapping("test1")
    public void test1(String addStr){
        //向mq发送消息
        rabbitTemplate.convertAndSend("javaDirectExchange","xxx",addStr);
    }

}
