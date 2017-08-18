package com.zg.number.bean;

import lombok.Data;

import java.util.List;

/**
 * Created by 任彩雨 on 2017/8/5.
 * 市区表，一个市区有多个地区
 */
@Data
public class City {
    private int cityId;
    private String cityName;
    private int provinceId;
    private Province province;//省份实体类
    private List<Town> towns;//地区集合

}
