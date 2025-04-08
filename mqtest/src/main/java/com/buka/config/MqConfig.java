package com.buka.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqConfig {
	@Bean
	public String rabbitMq(){
		return "rabbit";
	}
}
