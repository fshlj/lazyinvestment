package com.zg.number.controller;

import com.zg.number.bean.User;
import com.zg.number.service.RegisterService;
import com.zg.number.utils.PhoneCheckCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by 任彩雨 on 2017/8/7.
 */
@Controller
@RequestMapping("hwj")
public class RegisterController {
    @Autowired
    private RegisterService registerService;




    //注册跳转
    @RequestMapping("toregister")
    public String toRegister() {
        System.out.println("-------到注册界面的方法-------");
        return "top/register";
    }

    //登录跳转
    @RequestMapping("toLogin")
    public String toLogin() {
        System.out.println("------fdfd");
        return "top/login";
    }

    //注册
    @RequestMapping("register")
    public String register(User user, HttpSession session) {
        Object validate1 = session.getAttribute("checkValidate");
        String validate = user.getValidate();
        System.out.println(validate);
        if (validate.equals(validate1)) {
            System.out.println("---------register1------");
            registerService.register(user);

            //addcaptail 注册过将用户的余额添加到余额表
            System.out.print("拿到手机号了.................."+user.getPhone());
            registerService.addcaptail(user.getPhone());

            System.out.println("---------register2------");
            return "forward:toLogin";
        }
        System.out.println("---------register3------");
        session.setAttribute("say", "手机验证码错误！");
        return "forward:toregister";
    }

    //用户名验证唯一
    @RequestMapping("usernameOnly")
    @ResponseBody
    public String usernameOnly(String username) {
        System.out.println("---------usernameOnly------");
        List<User> only = registerService.userNameOnly(username);
        for (User user : only) {
            System.out.println(user.getUrgentName());
        }
        if (only.size() > 0) {
            return "true";
        } else {
            return "false";
        }
    }

    //手机号验证唯一
    @RequestMapping("phoneOnly")
    @ResponseBody
    public String phoneOnly(String phone) {
        System.out.println("---------only------");
        List<User> only = registerService.phoneOnly(phone);
        for (User user : only) {
            System.out.println(user.getPhone());
        }
        if (only.size() > 0) {
            return "true";
        } else {
            return "false";
        }
    }

    //手机验证
    @RequestMapping("validate")
    @ResponseBody
    public String validate(User user, HttpSession session) {
        String sendPhoneCheckCode = PhoneCheckCode.sendPhoneCheakCode(user.getPhone());
        System.out.println("向用户手机发送的验证码：" + sendPhoneCheckCode);
        session.setAttribute("checkValidate", sendPhoneCheckCode);
        return null;
    }


}
