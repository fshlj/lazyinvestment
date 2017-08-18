package com.zg.number.bean;

import lombok.Data;

/**
 * Created by 任彩雨 on 2017/8/5.
 *
 * @Action 收货地址类
 * 一个用户有多个收货地址，多方
 */
@Data
public class Address {
    private int addressId;
    private String addressName;
    private User user;//User 实体类
}
