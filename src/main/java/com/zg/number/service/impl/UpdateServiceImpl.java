package com.zg.number.service.impl;

import com.zg.number.bean.User;
import com.zg.number.mapper.UpdatePasswordMapper;
import com.zg.number.service.UpdatePasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 任彩雨 on 2017/8/10.
 */
@Service
@Component
@Transactional
public class UpdateServiceImpl implements UpdatePasswordService {
    @Autowired
    private UpdatePasswordMapper updatePassword;

    @Override
    public User checkPhone(String phone) {
        User user = updatePassword.checkPhone(phone);
        return user;
    }

    @Override
    public void updatePassword(User user) {
        updatePassword.updatePassword(user);
    }
}
