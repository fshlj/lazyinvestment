package com.zg.number.controller;

import com.zg.number.bean.Record;
import com.zg.number.service.CapitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by WangHongChuan on 2017/8/14.
 */
@Controller
public class CapitalController {
    @Autowired
    private CapitalService capitalService;


    @RequestMapping("findCapital")
    public String findCapital(HttpSession session,  Model model){
        Object uId = session.getAttribute("uId");
        int id = 0;
        if (uId!=null){
            id=Integer.parseInt(String.valueOf(uId));
        }
        List<Record> list = capitalService.findCapital(id);
        for (Record record : list) {
            System.out.println(record);
        }
        model.addAttribute("recordlist",list);
        return "/menu/findCapital";
    }


}
