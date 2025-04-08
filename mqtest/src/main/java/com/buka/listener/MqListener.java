package com.buka.listener;


import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqListener {

    @RabbitListener(queues = "javaQueque")
    public void listenJava(String data){
        System.out.println("监听方法被触发，接收到的数据为："+data);

    }

}
