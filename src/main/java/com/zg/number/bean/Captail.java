package com.zg.number.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by lenovo on 2017/8/10.
 * 资产表
 */
@Data
public class Captail implements Serializable{
    private int captailId;
    private Double captailMoney;//余额
    private Double addCaptailMoney;//添加的金额
    private Double tiXianMoney;//   提现的金额
    private int userId;//用户外键
}
