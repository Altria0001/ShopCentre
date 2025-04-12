package com.buka.api;

import com.buka.User;
import com.buka.entity.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@FeignClient(name = "user")//fallback服务降级
public interface UserFeinAplService {
	@RequestMapping("/user/selectUserbyname")
	public R<User> selectbyUsername(@RequestParam("username") String username);
}
