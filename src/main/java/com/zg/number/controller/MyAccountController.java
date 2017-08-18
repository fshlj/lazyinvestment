package com.zg.number.controller;

import com.zg.number.bean.Invest;
import com.zg.number.bean.Record;
import com.zg.number.bean.ShouYi;
import com.zg.number.service.RealizeService;
import com.zg.number.service.ShouYiLvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.text.NumberFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by wrx on 2017/8/7.
 * 我的账户控制层 :计算收益
 */
@Controller
@RequestMapping("rcy")
public class MyAccountController {
    @Autowired
    private ShouYiLvService shouYiLvService;//收益service

    @Autowired
    private RealizeService realizeService;//变现service



    /**
     *  根据投资的日期进行分组,并计算出每天的投资总额
     */
    @RequestMapping("toMyAccount")
    public String sumSY(HttpSession session, Map<String,Object> map){
        //获取用户的id
        Object uId = session.getAttribute("uId");
        //根据id查询项目的详细信息==>年化收益率
        Invest projectstail = realizeService.projectstail(Integer.valueOf(String.valueOf(uId)));
        System.out.println("项目的详细信息为:"+projectstail);
        if(projectstail!=null){
            //map.put("projectstail",projectstail);
            //根据用户id
            List<ShouYi> slist = shouYiLvService.sumSY(Integer.valueOf(String.valueOf(uId)));
            if(slist!=null&&slist.size()>0){
                Integer sum=0;
                for(ShouYi s:slist){
                    //计算该用户投资的总额
                    Integer dayMoney = s.getDayMoney();
                    sum+=dayMoney;
                }
                System.out.println("该用户投资的总额为:"+sum);

                Date stardTime = slist.get(0).getIrecordTime();
                Date endTime = slist.get(slist.size()-1).getIrecordTime();
                //将date类型转换成毫秒数
                long time = stardTime.getTime();
                long time1 = endTime.getTime();
                long hm = time1 - time;
                long day = (hm/1000)/3600/24;//投资的天数
                System.out.println("计算该用户一共投资了"+hm+"毫秒,一共"+day+"天");
                double revenueMoney = projectstail.getRevenueMoney();//年化收益率
                int projectTime = projectstail.getProjectTime();//项目的期限
                System.out.println("该用户投资的项目的年化收益率为:"+revenueMoney+",项目的期限为:"+projectTime);

                //计算懒投资已为该用户赚取的收益
                double v = sum * (revenueMoney / 100 / 365) * day;
                NumberFormat ddf1=NumberFormat.getNumberInstance() ;
                ddf1.setMaximumFractionDigits(2);//设置两位小数
                String s= ddf1.format(v) ;
                System.out.println("计算懒投资已为该用户赚取的收益:"+s);

                //将收益推送到前台
                map.put("s",s);

            }
            return "menu/wodezhanghu";
        }else {
            map.put("s",0.00);
            map.put("captailMoney",0.00);
            return "menu/wodezhanghu";
        }

    }




}
