package com.zg.number.controller;

import com.zg.number.bean.*;
import com.zg.number.service.CardService;
import com.zg.number.service.LoginService;
import com.zg.number.service.RealizeService;
import com.zg.number.service.ShouYiLvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.text.NumberFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by ${hanwenjian} on 2017/8/9.
 */
@Controller
@RequestMapping("hwj")
public class CardController {

    @Autowired
    private ShouYiLvService shouYiLvService;//收益service

    @Autowired
    private RealizeService realizeService;//变现service

    @Autowired
    private LoginService loginService;


    /*
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
        } */


    @Autowired
    private CardService cardService;
    //我的账户页面
    @RequestMapping("myCard")
    public String myCard(HttpSession session,Map<String ,Object> map) {
        User loginUser = (User) session.getAttribute("loginUser");

            int userId = loginUser.getUserId();
            System.out.println(userId);
            //调用添加银行卡的方法
            AddCard addCard = cardService.selectAddCard(userId);
            if(addCard!=null){
                session.setAttribute("yinhang",", 你已经添加过银行卡！");
            }

            //获取用户的id
            int id = 0;
            Object uId = session.getAttribute("uId");
            if(uId!=null){
                 id=Integer.parseInt(String.valueOf(uId));
            }
             Captail captail = loginService.findcurrentbalance(id);
            session.setAttribute("captail",captail);


        //根据id查询项目的详细信息==>年化收益率
            Invest projectstail = realizeService.projectstailByUserId(Integer.valueOf(String.valueOf(uId)));
            System.out.println(Integer.valueOf(String.valueOf(uId)));
            System.out.println("项目的详细信息为:"+projectstail);
            if(projectstail!=null){

                List<ShouYi> slist = shouYiLvService.sumSY(Integer.valueOf(String.valueOf(uId)));
                Integer sum=0;
                if(slist!=null&&slist.size()>0){
                    for(ShouYi s:slist){
                        //计算该用户投资的总额
                        Integer dayMoney = s.getDayMoney();
                        sum+=dayMoney;
                    }
                    System.out.println("该用户投资的总额为:"+sum);


                    ShouYi shouYi = slist.get(0);
                    System.out.println(shouYi);
                    Date stardTime = shouYi.getIrecordTime();
                    //将date类型转换成毫秒数
                    long hm = stardTime.getTime();
                    System.out.println(hm);
                    long cha = 0;
                    long day ;
                    if(slist.get(slist.size()-1)!=null&&slist.size()>1){
                        Date endTime = slist.get(slist.size()-1).getIrecordTime();
                        long time1 = endTime.getTime();

                        cha = time1 - hm;
                        if(cha!=0){
                            System.out.println(cha+"==="+time1);
                            day = (cha/1000)/3600/24;//投资的天数
                            if(day<1){
                                day = 1;
                            }
                            System.out.println(day);
                        }else {
                            day =1;//投资的天数
                        }

                    }else{
                        day = 1;
                    }

                    System.out.println("计算该用户一共用了"+cha+"毫秒,一共"+day+"天");
                    double revenueMoney = projectstail.getRevenueMoney();//年化收益率
                    int projectTime = projectstail.getProjectTime();//项目的期限
                    System.out.println("该用户投资的项目的年化收益率为:"+revenueMoney+",项目的期限为:"+projectTime);

                    //计算懒投资已为该用户赚取的收益
                    double v = sum * (revenueMoney / 100 / 365) * day;
                    System.out.println(v);
                    NumberFormat ddf1=NumberFormat.getNumberInstance() ;
                    ddf1.setMaximumFractionDigits(2);//设置两位小数
                    String s= ddf1.format(v) ;
                    System.out.println("计算懒投资已为该用户赚取的收益:"+s);

                    //将收益推送到前台
                    map.put("s",s);

                }

            }else{
                map.put("s",0.00);
                //map.put("captailMoney",0.00);
            }


        return "menu/wodezhanghu";
    }
    //添加银行卡页面
    @RequestMapping("addCard")
    public String addCard(HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        int userId = loginUser.getUserId();
        System.out.println(userId);
        AddCard addCard = cardService.selectAddCard(userId);
        if(addCard==null){
            return "centre/yinhang";
        }else {
            session.setAttribute("yinhang",", 你已经添加过银行卡！");
            return "menu/wodezhanghu";
        }
    }
    //添加银行卡信息
    @RequestMapping("addcardxinxi")
    public String addCardXinXi(AddCard addCard, HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        int userId = loginUser.getUserId();
        System.out.println(userId);
        addCard.setUid(userId);
        System.out.println(addCard);
        cardService.addcardxinxi(addCard);
        return "forward:myCard";
    }


    //个人信息 根据用户名查询用户 根据用户id查询银行卡姓名
    @RequestMapping("xinxi")
    public String xinXi(Model model, HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        String userName = loginUser.getUserName();
        int userId = loginUser.getUserId();
        User user = cardService.selectUserAll(userName);
        AddCard addCard = cardService.selectAddCard(userId);
        model.addAttribute("xinxiuser",user);
        model.addAttribute("xinxiaddcard",addCard);
        System.out.println(user);
        System.out.println(addCard);
        return "menu/gerenxinxixiangqing";
    }

}
