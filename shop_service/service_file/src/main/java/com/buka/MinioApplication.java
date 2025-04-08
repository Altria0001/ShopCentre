package com.buka;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.validation.constraints.Min;

@SpringBootApplication
public class MinioApplication {

    public static void main(String[] args) {
        SpringApplication.run(MinioApplication.class,args);
    }

}
