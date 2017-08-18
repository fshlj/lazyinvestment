package com.zg.number.service;

import com.zg.number.bean.Captail;
import com.zg.number.bean.User;

/**
 * Created by 任彩雨 on 2017/8/7.
 */
public interface LoginService {

    User login(User user);

    //根据id查询当前用户账户余额
    Captail findcurrentbalance(int userid);
}
