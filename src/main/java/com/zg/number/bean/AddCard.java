package com.zg.number.bean;

import lombok.Data;

/**
 * Created by 任彩雨 on 2017/8/5.
 *
 * @Action 银行卡类--
 * 一个用户可以有多个银行卡，一对多关系的多发
 */
@Data
public class AddCard {
    private int cardId;//id
    private String cardName;//客户姓名
    private String caerdNum;//证件号
    private String cardCard;//银行卡
    private String cardPhone;//预留手机号
    private String cardPwd;//交易密码
    private double cardYue;//银行卡里的余额
    private User user;//User实体类
    private int uid;//用户id
}

