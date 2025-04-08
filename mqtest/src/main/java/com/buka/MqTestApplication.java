package com.buka;


import com.xpand.starter.canal.annotation.EnableCanalClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableCanalClient
public class MqTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(MqTestApplication.class,args);
    }

}
