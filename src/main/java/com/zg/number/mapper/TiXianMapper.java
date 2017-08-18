package com.zg.number.mapper;

import com.zg.number.bean.Captail;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * Created by 任彩雨 on 2017/8/14.
 */
@Component
@Mapper
public interface TiXianMapper {

    void tiXianAddCard(Captail captail);//提现到银行卡中 ,银行卡余额增加

    void balanceReduce(Captail captail);//账户余额减少
}
