package com.zg.number.service.impl;

import com.zg.number.bean.User;
import com.zg.number.mapper.RegisterMapper;
import com.zg.number.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 任彩雨 on 2017/8/8.
 */
@Service
@Component
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    private RegisterMapper registerMapper;

    //注册用户

    @Override
    public void register(User user) {

        User user1 = registerMapper.selectRegister(user);
        if (user1==null){
            registerMapper.register(user);
        }
    }

    //验证用户名唯一
    @Override
    public List<User> userNameOnly(String username) {
        List<User> userNameOnly = registerMapper.userNameOnly(username);
        return userNameOnly;
    }

    //验证手机号唯一
    @Override
    public List<User> phoneOnly(String phone) {
        List<User> phoneOnly = registerMapper.phoneOnly(phone);
        return phoneOnly;
    }

    @Override
    public void addcaptail(String phone) {
        registerMapper.addcaptail(phone);
    }



}
