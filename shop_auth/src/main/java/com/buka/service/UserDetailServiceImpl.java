package com.buka.service;

import com.buka.User;
import com.buka.api.UserFeinAplService;
import com.buka.entity.R;
import com.buka.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
	@Autowired
	private UserFeinAplService userFeinAplService;
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		R<User> userR = userFeinAplService.selectbyUsername(userName);
		UserInfo userInfo = new UserInfo();
		if (userR.isFlag()) {
			userInfo.setUser(userR.getData());
		}
		return userInfo;
	}
}
