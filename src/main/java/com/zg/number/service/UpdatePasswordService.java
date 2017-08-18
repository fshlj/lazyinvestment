package com.zg.number.service;

import com.zg.number.bean.User;

/**
 * Created by 任彩雨 on 2017/8/10.
 */
public interface UpdatePasswordService {
    User checkPhone(String phone);//验证手机号是否存在

    void updatePassword(User user);//修改密码
}
