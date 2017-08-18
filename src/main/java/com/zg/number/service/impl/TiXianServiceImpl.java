package com.zg.number.service.impl;

import com.zg.number.bean.Captail;
import com.zg.number.mapper.TiXianMapper;
import com.zg.number.service.TiXianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by 任彩雨 on 2017/8/14.
 */
@Component
@Service
public class TiXianServiceImpl implements TiXianService {
    @Autowired
    private TiXianMapper tiXianMapper;

    @Override
    public void tiXianAddCard(Captail captail) {
        tiXianMapper.tiXianAddCard(captail);
    }

    @Override
    public void balanceReduce(Captail captail) {
        tiXianMapper.balanceReduce(captail);
    }
}
