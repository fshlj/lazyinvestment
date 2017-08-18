package com.zg.number.service;

import com.zg.number.bean.Captail;
import com.zg.number.bean.Invest;
import com.zg.number.bean.Record;
import com.zg.number.bean.User;

import java.util.List;

/**
 * Created by fengshuo on 2017/8/8.
 */
public interface LanRenService {

    List<Invest> selectLanRen();//懒人计划总查询

    List<User> selectUserAndRecord();//投资风云榜

    void insertRecord(Record record);//投资记录表添加数据

    Invest selectRecordSurplusMoney(int uId);//根据id查询Record表剩余Money

    void updateRecordSurplusMoney(Invest invest);//根据id修改Record表Money

    Captail selectCaptail(int id);//根据id查询余额表余额

    void updateCaptail(Captail captail);//根据id修改余额表余额
}
