package com.buka.domain;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "alipay")
public class AliPayConfigClass {
	private String privateKey;
	private String alipayPublicKey;
	private String appId;
	private String serverUrl;
	private String returnUrl;
	private String notifyUrl;
}
