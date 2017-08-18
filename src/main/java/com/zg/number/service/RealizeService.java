package com.zg.number.service;

import com.zg.number.bean.Captail;
import com.zg.number.bean.Invest;
import com.zg.number.bean.Record;

import java.util.List;
import java.util.Map;

/**
 * Created by wrx on 2017/8/7.
 */
public interface RealizeService {

    /**
     * 查询一锤定音表:实现变现计划
     */
    public List<Invest> selectInvest();
    /**
     * 马上投资:根据id查询项目的详细信息
     */
    public Invest projectstail(Integer id);
    /**
     * 余额查询
     */
    public Captail selectYvE(Integer id);
    /**
     * 将投资信息注入到投资记录表
     */
    public void addRecord(Record record);
    /**
     * 修改资产表的账户余额
     */
    public void updateCaptail(Captail captail);
    /**
     * 修改一锤定音表的剩余金额
     */
    public void updateSurplusMoney(Invest invest);
    /**
     * 根据月份模糊查询项目的信息,传递多个参数进行查询 #{0}代表第一个参数,#{1}代表第二个参数
     */
    public List<Invest> selectProjectByTime(Map<String,Integer> map);
    /**
     * 根据项目期限升序
     */
    public List<Invest> descBySurplusMoney();
    /**
     * 根据项目期限降序
     */
    public List<Invest> descByProjectTime();
    /**
     * 获取用户的id,对该项目的投资人进行添加
     */
    public void addTouZiRen(Invest invest);
    /**
     * 马上投资:根据用户id查询他所投资项目的详细信息
     */
    public Invest projectstailByUserId(Integer id);



}
