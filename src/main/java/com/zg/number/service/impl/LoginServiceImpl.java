package com.zg.number.service.impl;

import com.zg.number.bean.Captail;
import com.zg.number.bean.User;
import com.zg.number.mapper.LoginMapper;
import com.zg.number.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by 任彩雨 on 2017/8/7.
 */
@Service
@Component

public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginMapper loginMapper;

    @Override
    public User login(User user) {
        User loginUser = loginMapper.login(user);
        return loginUser;
    }

    @Override
    public Captail findcurrentbalance(int userid) {
        return loginMapper.findcurrentbalance(userid);
    }
}
