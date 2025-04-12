package com.buka.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.buka.User;
import com.buka.entity.R;
import com.buka.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	@RequestMapping("/selectUserbyname")
	public R<User> selectbyUsername(String username) {
		LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
		queryWrapper.eq(User::getUsername, username);
		User one =  userService.getOne(queryWrapper);
		return R.ok(one);

	}
}
