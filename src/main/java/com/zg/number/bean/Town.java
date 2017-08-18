package com.zg.number.bean;

import lombok.Data;

/**
 * Created by 任彩雨 on 2017/8/5.
 * 地区类 一个市区有多个地区
 */
@Data
public class Town {
    private Integer townId;
    private String townName;
    private Integer cityId;
    private City city;
}
