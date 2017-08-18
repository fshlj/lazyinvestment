package com.zg.number.service;

import com.zg.number.bean.Captail;

/**
 * Created by 任彩雨 on 2017/8/14.
 */

public interface TiXianService {
    void tiXianAddCard(Captail captail);//提现到银行卡中 ,银行卡余额增加

    void balanceReduce(Captail captail);//账户余额减少
}
