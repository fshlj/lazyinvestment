package com.zg.number.mapper;

import com.zg.number.bean.Captail;
import com.zg.number.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * Created by 任彩雨 on 2017/8/7.
 * 最后修改于whc on2017/08/11 14:48
 */
@Component
@Mapper
public interface LoginMapper {
    User login(User user);

    //根据id查询当前用户账户余额
    Captail findcurrentbalance(int userid);

}
