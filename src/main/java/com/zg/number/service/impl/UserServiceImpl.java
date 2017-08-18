package com.zg.number.service.impl;

import com.zg.number.mapper.UserMapper;
import com.zg.number.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by 任彩雨 on 2017/8/5.
 */
@Service
@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


}
