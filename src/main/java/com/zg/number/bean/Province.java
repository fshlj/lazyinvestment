package com.zg.number.bean;

import lombok.Data;

import java.util.List;

/**
 * Created by 任彩雨 on 2017/8/5.
 * 省份实体类，一个省多个市区的集合
 */
@Data
public class Province {
    private int provinceId;
    private String provinceName;
    private List<City> cities;//市区集合

}
