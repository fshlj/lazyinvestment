package com.zg.number.service.impl;

import com.zg.number.bean.AddCard;
import com.zg.number.bean.Captail;
import com.zg.number.mapper.ChongZhiMapper;
import com.zg.number.service.ChongZhiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by 任彩雨 on 2017/8/11.
 */
@Service
@Component
public class ChongZhiServiceImpl implements ChongZhiService {

    @Autowired
    private ChongZhiMapper chongZhiMapper;

    @Override
    public AddCard selectCardYue(Integer uid) {
        AddCard addCard = chongZhiMapper.selectCardYue(uid);
        return addCard;
    }

    @Override
    public void insertCaptailMoney(Captail captail) {
        chongZhiMapper.insertCaptailMoney(captail);
    }

    @Override
    public void cardReduce(Captail captail) {
        chongZhiMapper.cardReduce(captail);
    }


}
