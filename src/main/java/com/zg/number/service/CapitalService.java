package com.zg.number.service;

import com.zg.number.bean.Record;

import java.util.List;

/**
 * Created by WangHongChuan on 2017/8/14.
 */
public interface CapitalService {
    //根据id查询当前用户投资记录
    List<Record> findCapital(int uid);
}
