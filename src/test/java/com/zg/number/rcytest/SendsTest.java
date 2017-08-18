package com.zg.number.rcytest;

import com.zg.number.bean.AddCard;
import com.zg.number.bean.Captail;
import com.zg.number.bean.User;
import com.zg.number.service.ChongZhiService;
import com.zg.number.service.LoginService;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.DecimalFormat;

/**
 * Created by 任彩雨 on 2017/8/7.
 */
public class SendsTest {




    public static void main(String[] args) {

        Double double1 = 123456789.123456789;
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");//格式化设置
        System.out.println(decimalFormat.format(double1));
        String format = decimalFormat.format(double1);
        System.out.println(double1);
    }




}
