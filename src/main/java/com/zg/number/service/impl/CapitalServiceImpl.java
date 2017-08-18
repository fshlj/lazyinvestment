package com.zg.number.service.impl;

import com.zg.number.bean.Record;
import com.zg.number.mapper.CapitalMapper;
import com.zg.number.service.CapitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by WangHongChuan on 2017/8/14.
 */
@Service
public class CapitalServiceImpl implements CapitalService{
    @Autowired
    private CapitalMapper capitalMapper;


    @Override
    public List<Record> findCapital(int uid) {
        return capitalMapper.findCapital(uid);
    }
}
