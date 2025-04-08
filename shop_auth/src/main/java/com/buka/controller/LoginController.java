package com.buka.controller;

import com.buka.entity.R;
import com.buka.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class LoginController {
	@Autowired
	private LoginService loginService;
	@RequestMapping("login")
	public R login(String username, String password, HttpServletResponse response){
		return loginService.login(username,password);
	}
}
