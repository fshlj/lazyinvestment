package com.zg.number.controller;

import com.zg.number.bean.Captail;
import com.zg.number.bean.Invest;
import com.zg.number.bean.User;
import com.zg.number.service.ChongZhiService;
import com.zg.number.service.IndexService;
import com.zg.number.service.LoginService;
import com.zg.number.service.TiXianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by 任彩雨 on 2017/8/14.
 */
@Controller
@RequestMapping("rcy")
public class TiXIanController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private TiXianService tiXianService;
    @Autowired
    private IndexService indexService;
    @Autowired
    private ChongZhiService chongZhiService;


    @RequestMapping("toTiXian")
    private String toTiXian(HttpSession session, Model model) {
        System.out.println("---------------到提现界面------------");
        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser != null) {
            Captail userBalance = loginService.findcurrentbalance(loginUser.getUserId());
            model.addAttribute("userBalance", userBalance);
        }
        return "top/tixian";
    }

    @RequestMapping("tiXian")
    private String tiXian(Captail captail, HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        List<Invest> list = indexService.findIndexData();
        if (captail != null) {
            captail.setUserId(loginUser.getUserId());
            System.err.println("提现的时候的captail;" + captail);
            tiXianService.tiXianAddCard(captail);//银行余额增加
            tiXianService.balanceReduce(captail);//账户余额减少
        }
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
}
