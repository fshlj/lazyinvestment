package com.zg.number.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zg.number.bean.Captail;
import com.zg.number.bean.Invest;
import com.zg.number.bean.Record;
import com.zg.number.bean.User;
import com.zg.number.service.IndexService;
import com.zg.number.service.LanRenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Created by fengshuo on 2017/8/8.
 */
@RequestMapping("fs")
@Controller
public class LanRenController {

    @Autowired
    private LanRenService lanRenService;

    @Autowired
    private IndexService indexService;

    //懒人计划页面分页
    @RequestMapping("lanRen")
    public String selectInvest(Map<String, Object> map, HttpSession session, Model model , PageInfo<Invest> page) {
        List<Invest> lanRenAll = lanRenService.selectLanRen();//查询所有数据的方法
        //获取list的长度//记录数
        int size = lanRenAll.size();
        //算出一共有多少页
        int pagenum = size/5+(size%5>0?1:0);
        //将记录数和总页数传到前台
        map.put("size",size);
        map.put("pagenum",pagenum);
        System.out.println("记录数:"+size+",总页数:"+pagenum);
        if(page==null){
            page.setPageNum(1);
        }
        int w = page.getPageNum();
        session.setAttribute("w",w);

        page.setPageSize(5);
        PageHelper.startPage(page.getPageNum(),page.getPageSize());
        List<Invest> lanRenAll2 = lanRenService.selectLanRen();//查询所有数据的方法
        PageInfo<Invest> pages=new PageInfo<Invest>(lanRenAll2);//PageInfo<T>中的含有list参数的抽象方法
        model.addAttribute("pagess",pages);
        model.addAttribute("listsss",lanRenAll2);

        /**
         * 变现计划的项目列表Projectlist 将项目列表分页查询
         */
        map.put("listsss", lanRenAll2);
        return "forward:selectUserAndAssest";
    }


    //投资风云榜
    @RequestMapping("selectUserAndAssest")
    private String selectUserAndAssest(ModelMap modelMap){
        List<User> list = lanRenService.selectUserAndRecord();
        modelMap.addAttribute("list",list);
        return "menu/lanren";
    }

    //投资详情页面数据获取
    @RequestMapping("touzi")
    public String findInvestData(Model model, Integer id,HttpSession session,ModelMap modelMap){
        session.setAttribute("id",id);
        Invest oneInvestData = indexService.findOneInvestData(id);
        model.addAttribute("oneInvestData",oneInvestData);
        Object obj = session.getAttribute("uId");
        int ids = 0;
        if(obj!=null){
            ids = Integer.parseInt(String.valueOf(obj));
        }
        Captail captail1 = lanRenService.selectCaptail(ids);
        modelMap.addAttribute("captail1",captail1);
        return "lanrenjihua/mashangtouzi";
    }

    //投资记录表添加数据
    @RequestMapping("insertRecord")
    private String insertRecord(Record record, HttpSession session){
        Object obj = session.getAttribute("uId");
        session.setAttribute("money",record.getRecordMoney());
        int id = 0;
        if(obj!=null){
            id = Integer.parseInt(String.valueOf(obj));
        }
        record.setUid(id);
        lanRenService.insertRecord(record);
        return "redirect:updateSurplusMoney";
    }

    @RequestMapping("updateSurplusMoney")
    private String selectRecordSurplusMoney(HttpSession session,Invest invest){
        Object obj = session.getAttribute("id");
        Object object = session.getAttribute("money");
        int id = 0;
        int money = 0;
        if(obj!=null){
            id = Integer.parseInt(String.valueOf(obj));
        }
        if(object!=null){
            money = Integer.parseInt(String.valueOf(object));
        }
        Invest in = lanRenService.selectRecordSurplusMoney(id);
        invest.setInvestId(id);
        invest.setSurplusMoney(money);
        lanRenService.updateRecordSurplusMoney(invest);
        return "redirect:updateCaptailMoney";
    }

    @RequestMapping("updateCaptailMoney")
    private String updateCaptail(HttpSession session, Captail captail){
        Object obj = session.getAttribute("uId");
        Object object = session.getAttribute("money");
        int id = 0;
        Double money = 0.0;
        if(obj!=null){
            id = Integer.parseInt(String.valueOf(obj));
        }
        if(object!=null){
            money = Double.parseDouble(String.valueOf(object));
        }
//        Captail captail1 = lanRenService.selectCaptail(id);
        captail.setUserId(id);
        captail.setCaptailMoney(money);
        lanRenService.updateCaptail(captail);
        return "redirect:lanRen";
    }


}
