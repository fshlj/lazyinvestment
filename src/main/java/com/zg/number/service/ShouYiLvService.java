package com.zg.number.service;

import com.zg.number.bean.ShouYi;

import java.util.List;

/**
 * Created by lenovo on 2017/8/16.
 */
public interface ShouYiLvService {
    /**
     * 根据投资的日期进行分组,并计算出每天的投资总额
     */
    public List<ShouYi> sumSY(Integer uid);

}
