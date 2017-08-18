package com.zg.number.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ${hanwenjian} on 2017/8/14.
 */
@Controller
public class TiaoZhuanController {


    @RequestMapping("lingqian")
    public String lingQian() {
        return "menu/lingqian";
    }

    @RequestMapping("xiangle")
    public String liangLe() {
        return "menu/xiangle";
    }

    @RequestMapping("qunxing")
    public String qunXing() {
        return "menu/qunxing";
    }

}
