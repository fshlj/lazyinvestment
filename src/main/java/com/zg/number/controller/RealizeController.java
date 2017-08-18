package com.zg.number.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zg.number.bean.Captail;
import com.zg.number.bean.Invest;
import com.zg.number.bean.Record;
import com.zg.number.service.RealizeService;
import javafx.scene.media.SubtitleTrack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by wrx on 2017/8/8.
 * 变现计划
 */
@Controller
@RequestMapping("wrx")
public class RealizeController {
    @Autowired
    private RealizeService realizeService;


    /**
     * 首页
     */
    @RequestMapping("index")
    public String toindex() {
        return "index";
    }

    /**
     * 分页查询:通过pageHelper的的依赖包提供的分页实体类,将
     * 分页听到的参数都封装到一个实体类
     */

    /**
     * 分页的实体类
     */
    /*@Configuration
    public class MybatisConf {
        @Bean
        public PageHelper pageHelper() {
            System.out.println("MyBatisConfiguration.pageHelper()");
            PageHelper pageHelper = new PageHelper();
            Properties p = new Properties();
            p.setProperty("offsetAsPageNum", "true");
            p.setProperty("rowBoundsWithCount", "true");
            p.setProperty("reasonable", "true");
            pageHelper.setProperties(p);
            return pageHelper;
        }
    }*/

    /**
     * 分页+++根据月份模糊查询项目的信息,传递多个参数进行查询 #{0}代表第一个参数,#{1}代表第二个参数
     */
    @RequestMapping("selectProjectByTime")
    public String selectProjectByTime(String num,Map<String,Object> map,PageInfo<Invest> page,Model model,HttpSession session){
        List<Invest> list = realizeService.selectInvest();//查询所有数据的方法
        //获取list的长度//记录数
        int size = list.size();
        //算出一共有多少页
        int pagenum = size/7+(size%7>0?1:0);
        //将记录数和总页数传到前台
        map.put("size",size);
        map.put("pagenum",pagenum);
        System.out.println("记录数:"+size+",总页数:"+pagenum);

        Invest invest1 = list.get(0);
        System.out.println(invest1);
        map.put("invest1", invest1);
        Invest invest2 = list.get(1);
        System.out.println(invest2);
        session.setAttribute("surplusMoney",invest2.getSurplusMoney());
        map.put("invest2",invest2);

        System.out.println("===========根据月份模糊查询============"+num);
        //根据逗号分隔传来的天数
        List<Invest> invests;
        //创建一个map对象
        Map<String,Integer> mapp = new HashMap<String,Integer>();

        if(num!=null){
            String[] split = num.split(",");
            if(split[0]!=null){
                Integer one =Integer.parseInt(split[0]);
                mapp.put("one",one);
            }

            System.out.println("一年以上三年以下的参数二为:"+split[1]);
            if(split[1]!=null){
                Integer two =Integer.parseInt(split[1]);
                mapp.put("two",two);
            }

            //调用该方法
            invests = realizeService.selectProjectByTime(mapp);
        }else {
            invests = realizeService.selectInvest();//查询所有数据的方法
        }

        if(page==null){
            page.setPageNum(1);
        }
        int w = page.getPageNum();
        System.out.println("============="+w);
        map.put("w",w);

        page.setPageSize(7);
        PageHelper.startPage(page.getPageNum(),page.getPageSize());

        PageInfo<Invest> pages=new PageInfo<Invest>(invests);//PageInfo<T>中的含有list参数的抽象方法

        for (Invest invest : invests) {
            System.out.println(invest);
        }
        model.addAttribute("page",pages);
        model.addAttribute("list",invests);

        return "menu/bianxian";
    }




    /**
     * 查询一锤定音表:实现变现计划======变现计划的项目列表Projectlist=======分页操作
     * ctrl 点PageInfo, PageInfo<T>实现了Serializable , 如果该类为空,则可以设置他的当前页
     */
    @RequestMapping("selectInvest")
    public String selectInvest(Map<String, Object> map, HttpSession session, Model model , PageInfo<Invest> page,String sort) {

        List<Invest>  list = realizeService.selectInvest();//查询所有数据的方法
        System.out.println("---------存两个对象--------");
        Invest invest1 = list.get(0);
        System.out.println(invest1);
        map.put("invest1", invest1);
        Invest invest2 = list.get(1);
        System.out.println(invest2);
        session.setAttribute("surplusMoney",invest2.getSurplusMoney());
        map.put("invest2",invest2);

        //获取list的长度//记录数
        int size = list.size();
        //算出一共有多少页
        int pagenum = size/7+(size%7>0?1:0);
        //将记录数和总页数传到前台
        map.put("size",size);
        map.put("pagenum",pagenum);
        System.out.println("记录数:"+size+",总页数:"+pagenum);

        //判断当前页
        if(page==null){
            page.setPageNum(1);
        }
        int w = page.getPageNum();
        System.out.println("============="+w);
        map.put("w",w);

        page.setPageSize(7);//设置每页的记录数
        PageHelper.startPage(page.getPageNum(),page.getPageSize());

        //按照项目期限降序排列**************************************************************
       //获取session中的排序的值

        System.out.println("按什么排序呢?:"+sort);
        if(sort!=null&&sort.equals("descBySurplusMoney")){
            List<Invest> descBySurplusMoney = realizeService.descBySurplusMoney();
            PageInfo<Invest> pages=new PageInfo<Invest>(descBySurplusMoney);
            model.addAttribute("page",pages);
            model.addAttribute("list",descBySurplusMoney);
            for (Invest t : descBySurplusMoney) {
                System.out.println(t);
            }

            return "menu/bianxian";
        } else if(sort!=null&&sort.equals("descByProjectTime")){
            List<Invest> descByProjectTime = realizeService.descByProjectTime();
            PageInfo<Invest> pages=new PageInfo<Invest>(descByProjectTime);
            model.addAttribute("page",pages);
            model.addAttribute("list",descByProjectTime);
            for (Invest t : descByProjectTime) {
                System.out.println(t);
            }
            return "menu/bianxian";
        } else {
            List<Invest>  list2 = realizeService.selectInvest();//查询所有数据的方法
            PageInfo<Invest> pages=new PageInfo<Invest>(list2);//PageInfo<T>中的含有list参数的抽象方法

            model.addAttribute("page",pages);

            model.addAttribute("list",list2);
            for (Invest invest : list) {
                System.out.println(invest);
            }

            return "menu/bianxian";
        }

    }

    /**
     * 马上投资
     */
    @RequestMapping("mashang")
    public String projectstail(Integer id,Map<String ,Object> map,HttpSession session){
        //根据id查询某个项目的详情
        System.out.println("========= 马上投资============"+id);
        Invest project = realizeService.projectstail(id);
        int uuid = 0;
        Object uId = session.getAttribute("uId");
        if(uId!=null){
            uuid=Integer.valueOf(String.valueOf(uId));
        }

        //根据用户id查询余额
        Captail captail = realizeService.selectYvE(uuid);

        Double captailMoney = captail.getCaptailMoney();
        session.setAttribute("captailMoney",captailMoney);

        //获取用户的id,对该项目的投资人进行添加

        project.setUserId(uuid);
        Date date = new Date();
        SimpleDateFormat sdfdf = new SimpleDateFormat("yyyy-MM-dd");
        String format = sdfdf.format(date);
        System.out.println(format);

        project.setEndtime(format);
        System.out.println(project);
        //先查看该表中的某个项目是否已经有投资人的记录
        Invest invest = realizeService.projectstailByUserId(Integer.valueOf(String.valueOf(uId)));
        if(invest==null){
            //调用添加的方法
            realizeService.addTouZiRen(project);

        }

        map.put("project",project);
        map.put("captail",captail);
        return "bianxianjiahua/investment";

    }

    /**
     * 将投资人投资的信息添加到记录表中
     */
    @RequestMapping("addRecord")
    public String addRecord(Integer touzi, HttpSession session){
        System.out.println(touzi);
         //封装一个投资记录表的对象
        Record record = new Record();
        //创建一个资产表的对象
        Captail captail = new Captail();
        //创建一个投资表的对象
        Invest invest =new Invest();

        record.setIrecordTime(new Date());
        Object obj = session.getAttribute("uId");

        if(obj!=null){
            int i = Integer.parseInt(String.valueOf(obj));
            record.setUid(i);
            System.out.println(record.getUid());
            captail.setUserId(i);
            invest.setUserId(i);
        }
       // System.out.println("===========>>"+i);


        record.setRecordMoney(touzi);
        Object loginUser = session.getAttribute("loginUser");

        record.setRecordName(String.valueOf(loginUser));
        //将投资信息注入到投资记录表
        realizeService.addRecord(record);

        System.out.println("===========kk=============");
        //调用根据用户id修改资产表中的余额信息
        //获取session中的余额
        Object captailMoney1 = session.getAttribute("captailMoney");
        double captailMoney =   Double.parseDouble(String.valueOf(captailMoney1));
        //计算新的账户余额
        System.out.println("====>"+captailMoney);
        double ye = captailMoney-touzi;
        System.out.println(ye);
        captail.setCaptailMoney(ye);

        //修改该用户的资产信息
        realizeService.updateCaptail(captail);

        //获取session存取的surplusMoney剩余金额
        Object surplusMoney = session.getAttribute("surplusMoney");

        //修改一锤定音表的剩余金额
        invest.setSurplusMoney(Integer.valueOf(String.valueOf(surplusMoney))-touzi);
        System.out.println("修改后的剩余金额======>"+invest.getSurplusMoney());
        realizeService.updateSurplusMoney(invest);

        return "redirect:selectInvest";
    }



}
