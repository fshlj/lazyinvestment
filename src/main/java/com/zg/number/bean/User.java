package com.zg.number.bean;

import lombok.Data;

import java.util.List;

/**
 * Created by 任彩雨 on 2017/8/5.
 */
@Data
public class User {
    private int userId;
    private String userName;//用户名
    private String phone;//手机号
    private String password;
    private String idNumber;//身份证号
    private String email;
    private String receiverName;//收货人姓名
    private String receiverPhone;//收货人手机号
    private String urgentName;//紧急联系人姓名
    private String urgentPhone;//紧急联系人手机号
    private double balance;//用户的余额
    private List<AddCard> cards;//多个银行卡
    private List<Address> addresses;//收货地址集合
    private List<Record> records;//投资记录的集合
    private Record record;
    private String validate; //手机验证码


}
