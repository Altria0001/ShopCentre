package com.buka.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.buka.User;
import com.buka.mapper.UserMapper;
import com.buka.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
