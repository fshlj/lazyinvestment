package com.zg.number.mapper;

import com.zg.number.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * Created by 任彩雨 on 2017/8/10.
 */
@Mapper
@Component
public interface UpdatePasswordMapper {

    User checkPhone(String phone);//验证手机号是否存在

    void updatePassword(User user);//修改密码
}
