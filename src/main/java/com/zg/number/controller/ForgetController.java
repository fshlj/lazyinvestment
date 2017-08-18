package com.zg.number.controller;

import com.zg.number.bean.User;
import com.zg.number.service.UpdatePasswordService;
import com.zg.number.utils.CheckCode;
import com.zg.number.utils.PhoneCheckCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by 任彩雨 on 2017/8/8.
 * 忘记密码
 */
@Controller
@RequestMapping("rcy")
public class ForgetController {

    @Autowired
    private UpdatePasswordService updatePasswordService;

    @RequestMapping("toForgetPassword")
    private String toForgetPassword(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("----------到找回密码界面-----------");
        return "top/findPassword";
    }

    @RequestMapping("getImgCheck")
    private String getImgCheck(HttpServletRequest request, HttpServletResponse response) {
        try {
            CheckCode.getImage(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @ResponseBody
    @RequestMapping("checkPhone")
    private String checkPhone(String phone) {
        User user = updatePasswordService.checkPhone(phone);
        if (user != null) {
            return "1";
        }
        System.err.println("不存在的时候");
        return "2";
    }

    @RequestMapping("toUpdatePassword")
    private String toUpdatePassword(String code, HttpSession session, String phone, Model model) {
        String codeValue = (String) session.getAttribute("codeValue");
        if (code != null && codeValue != null && codeValue != "" && code.equalsIgnoreCase(codeValue)) {
            System.err.println("-----验证码无误------");
            // PhoneCheckCode.sendPhoneCheakCode(phone);//需要放开向用户发短信
            return "top/updatePassword";
        } else {
            model.addAttribute("checkCodeMsg", "验证码有误");
            return "top/findpassword";
        }
    }

    @RequestMapping("updatePassword")
    private String updatePassword(String code, String phoneCheck, User user, HttpSession session, Model model) {
        String sendPhoneCheakCode = (String) session.getAttribute("sendPhoneCheakCode");
        String codeValue = (String) session.getAttribute("codeValue");
        if (sendPhoneCheakCode != null && codeValue != null && code != null && phoneCheck != null && user != null) {
            if (!phoneCheck.equalsIgnoreCase(sendPhoneCheakCode)) {
                System.err.println("手机验证码不等的时候");
                model.addAttribute("phoneCheckError", "手机验证码有误");
                return "top/findpassword";
            }
            if (!code.equalsIgnoreCase(codeValue)) {
                System.err.println("验证码不等的时候");
                model.addAttribute("codeError", "验证码有误");
                return "top/findpassword";
            }
            System.out.println("修改的对象：" + user.getPhone() + "--" + user.getPassword());
            updatePasswordService.updatePassword(user);
            return "top/login";
        }
        return "top/findpassword";
    }

    @RequestMapping("sendPhoneCheckCode")
    @ResponseBody
    private String sendPhoneCheckCode(String phoneCheck, HttpSession session) {
        System.out.println(phoneCheck);
        String sendPhoneCheakCode = PhoneCheckCode.sendPhoneCheakCode(phoneCheck);//发送短信
        System.out.println("发送的短信:" + sendPhoneCheakCode);
        session.setAttribute("sendPhoneCheakCode", sendPhoneCheakCode);
        return null;
    }
}


