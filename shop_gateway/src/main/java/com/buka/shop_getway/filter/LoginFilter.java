package com.buka.shop_getway.filter;
import com.buka.shop_getway.config.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Map;
@Component
public class LoginFilter implements GlobalFilter {
	@Autowired
	private RedisTemplate redisTemplate;
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		ServerHttpRequest request = exchange.getRequest();
		ServerHttpResponse response = exchange.getResponse();
		String url= request.getURI().toString();
		if(request.getURI().getPath().contains("login")){
			return chain.filter(exchange);
		}
		String authorization = request.getHeaders().getFirst("Authorization");  //令牌
		String jti = request.getHeaders().getFirst("jti");  //jti
		authorization = authorization.replaceAll("Bearer ", "");
		Object value = redisTemplate.opsForValue().get(jti);
		if (value!=null){
			if (!value.toString().equals(authorization)){
				response.setStatusCode(HttpStatus.UNAUTHORIZED);  //未认证
				return response.setComplete();  //终止当前请求
			}
		}
		return chain.filter(exchange);
	}
}