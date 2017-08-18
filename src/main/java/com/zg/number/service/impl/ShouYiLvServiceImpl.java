package com.zg.number.service.impl;

import com.zg.number.bean.Record;
import com.zg.number.bean.ShouYi;
import com.zg.number.mapper.ShouYiLvMapper;
import com.zg.number.service.ShouYiLvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lenovo on 2017/8/16.
 */
@Service
public class ShouYiLvServiceImpl implements ShouYiLvService {
    @Autowired
    private ShouYiLvMapper shouYiLvMapper;
    /**
     * 根据投资的日期进行分组,并计算出每天的投资总额
     */
    @Override
    public List<ShouYi> sumSY(Integer uid) {
        List<ShouYi> Slist = shouYiLvMapper.sumSY(uid);

        return Slist;
    }
}
