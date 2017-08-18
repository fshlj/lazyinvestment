package com.zg.number.controller;

import com.zg.number.bean.AddCard;
import com.zg.number.bean.Captail;
import com.zg.number.bean.Invest;
import com.zg.number.bean.User;
import com.zg.number.service.ChongZhiService;
import com.zg.number.service.IndexService;
import com.zg.number.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

/**
 * Created by 任彩雨 on 2017/8/11.
 */
@Controller
@RequestMapping("rcy")
public class ChongZhiController {

    @Autowired
    private ChongZhiService chongZhiService;

    @Autowired
    private LoginService loginService;
    @Autowired
    private IndexService indexService;


    @RequestMapping("toChongZhi")
    private String toChongZhi(HttpSession session, Model model) {
        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser != null) {
            System.out.println("登录的用户：" + loginUser.getUserId());
            AddCard addCard = chongZhiService.selectCardYue(loginUser.getUserId());
            if (addCard == null) {
                System.out.println("这个用户没有银行卡");
                return "centre/yinhang";
            }
            System.out.println("登录的用户的银行卡余额：" + addCard.getCardYue());
            model.addAttribute("cardAllYue", addCard.getCardYue());
            Captail captail = loginService.findcurrentbalance(loginUser.getUserId());


            System.out.println(captail.getCaptailMoney());
            NumberFormat format = NumberFormat.getIntegerInstance();
            format.setGroupingUsed(false);
            System.out.println("----:"+format.format(captail.getCaptailMoney()));


            session.setAttribute("captail", captail);
            return "top/chongzhi";
        }
        return "top/login";
    }

    @RequestMapping("chongzhi")
    private String chongzhi(HttpSession session, Captail captail, Model model) {
        List<Invest> list = indexService.findIndexData();
        User loginUser = (User) session.getAttribute("loginUser");//登录的用户
        captail.setUserId(loginUser.getUserId());
        if (captail != null) {
            chongZhiService.insertCaptailMoney(captail);
            chongZhiService.cardReduce(captail);
            Captail findcurrentbalance = loginService.findcurrentbalance(loginUser.getUserId());
            session.setAttribute("captail", findcurrentbalance);
            Invest invest = list.get(0);
            Invest invest1 = list.get(1);
            Invest invest2 = list.get(2);
            session.setAttribute("data1", invest);
            session.setAttribute("data2", invest1);
            session.setAttribute("data3", invest2);
            return "top/loginSuccess";
        }
        return "forward:toChongZhi";
    }

}
