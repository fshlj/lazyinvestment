package com.zg.number.service.impl;

import com.zg.number.bean.Invest;
import com.zg.number.mapper.IndexMapper;
import com.zg.number.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by WangHongChuan on 2017/8/8.
 */
@Service
public class IndexServiceImpl implements IndexService {
    @Autowired
    private IndexMapper indexMapper;


    @Override
    public List<Invest> findIndexData() {
        return indexMapper.findIndexData();
    }

    @Override
    public Invest findOneInvestData(Integer investId) {
        return indexMapper.findOneInvestData(investId);
    }
}
