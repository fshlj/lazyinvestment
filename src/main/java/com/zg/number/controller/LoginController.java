package com.zg.number.controller;

import com.zg.number.bean.Captail;
import com.zg.number.bean.Invest;
import com.zg.number.bean.User;
import com.zg.number.service.IndexService;
import com.zg.number.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by 任彩雨 on 2017/8/7.
 * 登录的Controller
 * 最后修改于2017/08/08 20:39 whc (添加功能:登录成功后页面主页数据从数据库中查处)
 */
@Controller
@RequestMapping("rcy")
public class LoginController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private IndexService indexService;

    //到登录界面
    @RequestMapping("toLogin")
    public String toLogin(HttpSession session) {
        session.invalidate();

        return "top/login";
    }

    @RequestMapping("login")
    public String login(User user, Model model, HttpSession session) {
        System.out.println("登陆的用户:" + user.getPhone() + "  " + user.getPassword());
        User loginUser = loginService.login(user);
        System.out.println(loginUser);
        if (loginUser != null) {
            session.setAttribute("uId", loginUser.getUserId());
            session.setAttribute("loginUser", loginUser);
            Captail captail = loginService.findcurrentbalance(loginUser.getUserId());
            session.setAttribute("captail", captail);
            System.out.println("lllllllllllllllllllllllllllllllllllll");
            //登录成功后主页懒人计划中的三条数据
            List<Invest> list = indexService.findIndexData();
            for (Invest invest : list) {
                System.out.print(invest.toString());
            }
            Invest invest = list.get(0);
            Invest invest1 = list.get(1);
            Invest invest2 = list.get(2);
            session.setAttribute("data1", invest);
            session.setAttribute("data2", invest1);
            session.setAttribute("data3", invest2);
            return "top/loginSuccess";
        }
        model.addAttribute("msg", "用户名或密码错误");
        return "forward:toLogin";
    }


}
