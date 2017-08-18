package com.zg.number.service.impl;

import com.zg.number.bean.Captail;
import com.zg.number.bean.Invest;
import com.zg.number.bean.Record;
import com.zg.number.mapper.RealizeMapper;
import com.zg.number.service.RealizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2017/8/8.
 */
@Service
@Component
public class RealizrServiceImpl implements RealizeService {

    @Autowired
    private RealizeMapper realizeMapper;

    /**
     * 查询一锤定音表:实现变现计划
     */
    @Override
    public List<Invest> selectInvest() {
        List<Invest> invests = realizeMapper.selectInvest();
        return invests;
    }
    /**
     * 马上投资:根据id查询项目的详细信息
     */
    @Override
    public Invest projectstail(Integer id) {
        Invest project = realizeMapper.projectstail(id);
        System.out.println("查询项目的详细信息喽!!!"+project);

        return project;
    }
    /**
     * 余额查询
     */
    @Override
    public Captail selectYvE(Integer id) {
        System.out.println("查询余额的id为:"+id);
        Captail captail = realizeMapper.selectYvE(id);
        System.out.println("查询余额喽!!!"+captail);
        return captail;
    }

    /**
     * 将投资信息注入到投资记录表
     */
    @Override
    public void addRecord(Record record) {
        realizeMapper.addRecord(record);
    }

    /**
     * 修改资产表的账户余额
     */
    @Override
    public void updateCaptail(Captail captail) {
        realizeMapper.updateCaptail(captail);
    }
    /**
     * 修改一锤定音表的剩余金额
     */
    @Override
    public void updateSurplusMoney(Invest invest) {
        realizeMapper.updateSurplusMoney(invest);
    }
    /**
     * 根据月份模糊查询项目的信息,传递多个参数进行查询 #{0}代表第一个参数,#{1}代表第二个参数
     */
    @Override
    public List<Invest> selectProjectByTime(Map<String,Integer> map){
        List<Invest> invests =  realizeMapper.selectProjectByTime(map);
        for(Invest t :invests){
            System.out.println(t);
        }
        return invests;
    }

    /**
     * 根据项目期限升序
     */
    @Override
    public List<Invest> descBySurplusMoney() {
        List<Invest> ascInvests = realizeMapper.descBySurplusMoney();
        for(Invest t :ascInvests){
            System.out.println(t);
        }
        return ascInvests;
    }
    /**
     * 根据项目期限降序
     */
    @Override
    public List<Invest> descByProjectTime() {
        List<Invest> descInvests = realizeMapper.descByProjectTime();
        for(Invest t :descInvests){
            System.out.println(t);
        }
        return descInvests;
    }
    /**
     * 获取用户的id,对该项目的投资人进行添加
     */
    @Override
    public void addTouZiRen(Invest invest) {
        realizeMapper.addTouZiRen(invest);
    }
    /**
     * 马上投资:根据用户id查询他所投资项目的详细信息
     */
    @Override
    public Invest projectstailByUserId(Integer id) {
        Invest invest = realizeMapper.projectstailByUserId(id);
        System.out.println("=========根据用户id查询他所投资项目的详细信息=================");
        System.out.println(invest);
        return invest;
    }

}
