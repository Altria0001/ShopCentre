package com.buka.feinconfig;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


@Configuration
//fein的拦截器，发送前 将token加入到请求中
public class FeinInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        //找到
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();  //
        if (requestAttributes!=null){
            HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
            if (request!=null){
                String authorization = request.getHeader("Authorization");  //token
                requestTemplate.header("Authorization",authorization);
            }
        }

    }
}
