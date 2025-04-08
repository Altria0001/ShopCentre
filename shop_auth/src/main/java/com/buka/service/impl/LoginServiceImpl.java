package com.buka.service.impl;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.buka.entity.AuthProperties;
import com.buka.entity.R;
import com.buka.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;


@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthProperties authProperties;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public R login(String userName, String passWord) {
        String url="localhost:9301/oauth/token";
        HashMap<String, Object> map = new HashMap<>();
        map.put("username",userName);
        map.put("password",passWord);
        map.put("client_id",authProperties.getClientId());
        map.put("client_secret",authProperties.getClientSecret());
        map.put("grant_type","password");
        String post = HttpUtil.post(url, map);
        JSONObject resJson = JSONObject.parseObject(post);
        String accessToken = resJson.getString("access_token");
        String jti = resJson.getString("jti");
        redisTemplate.opsForValue().set(jti,accessToken);
        return R.ok(post);
    }
}
