package com.buka.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "auth")
public class AuthProperties {
	private String ttl;
	private String clientId;
	private String clientSecret;
	private String cookieDomain;
	private String cookieMaxAge;


}
