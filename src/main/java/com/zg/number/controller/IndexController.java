package com.zg.number.controller;

import com.zg.number.bean.Captail;
import com.zg.number.bean.Invest;
import com.zg.number.service.IndexService;
import com.zg.number.service.LanRenService;
import com.zg.number.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by WangHongChuan on 2017/8/8.
 */
@Controller
public class IndexController {
    @Autowired
    private IndexService indexService;
    @Autowired
    private LoginService loginService;
    @Autowired
    private LanRenService lanRenService;

    @RequestMapping("index")
    public String index(Model model) {
        List<Invest> list = indexService.findIndexData();
        for (Invest invest : list) {
            System.out.print(invest.toString());
        }
        Invest invest = list.get(0);
        Invest invest1 = list.get(1);
        Invest invest2 = list.get(2);
        model.addAttribute("data1", invest);
        model.addAttribute("data2", invest1);
        model.addAttribute("data3", invest2);
        return "index";
    }

    @RequestMapping("findInvestData")
    public String findInvestData(Model model, Integer id,HttpSession session,ModelMap modelMap) {
        Invest oneInvestData = indexService.findOneInvestData(id);
        model.addAttribute("oneInvestData", oneInvestData);
        Object obj = session.getAttribute("uId");
        int ids = 0;
        if(obj!=null){
            ids = Integer.parseInt(String.valueOf(obj));
        }
        Captail captail1 = lanRenService.selectCaptail(ids);
        modelMap.addAttribute("captail1",captail1);
        System.out.print("拿到id了。。。。。" + oneInvestData.getInvestId() + oneInvestData.getPlanName());
        return "lanrenjihua/mashangtouzi";
    }

    @RequestMapping("toLoginSuccess")
    public String toLoginSuccess(Model model, HttpSession session){
        List<Invest> list = indexService.findIndexData();
        for (Invest invest : list) {
            System.out.print(invest.toString());
        }
        Object uId = session.getAttribute("uId");
        int id = 0;
        if (uId!=null){
            id=Integer.parseInt(String.valueOf(uId));
        }
        Captail captail = loginService.findcurrentbalance(id);
        session.setAttribute("captail",captail);
        Invest invest = list.get(0);
        Invest invest1 = list.get(1);
        Invest invest2 = list.get(2);
        session.setAttribute("data1", invest);
        session.setAttribute("data2", invest1);
        session.setAttribute("data3", invest2);
        return "top/loginSuccess.html";
    }


}