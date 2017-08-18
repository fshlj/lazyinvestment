package com.zg.number.utils;

/**
 * Created by 任彩雨 on 2017/8/7.
 * 手机验证码工具类
 */

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.io.IOException;

public class PhoneCheckCode {

    private static String Url = "http://106.ihuyi.cn/webservice/sms.php?method=Submit";

    public static String sendPhoneCheakCode(String phone) {
        HttpClient client = new HttpClient();
        PostMethod method = new PostMethod(Url);
        client.getParams().setContentCharset("UTF-8");
        method.setRequestHeader("ContentType", "application/x-www-form-urlencoded;charset=GBK");
        int mobile_code = (int) ((Math.random() * 9 + 1) * 100000);
        String content = new String("您的验证码是：" + mobile_code + "。请不要把验证码泄露给其他人。");
        NameValuePair[] data = {//提交短信
                new NameValuePair("account", "C05915740"),
                new NameValuePair("password", "9b02416e58542df6f139d7ba3146a41a"), //查看密码请登录用户中心->验证码、通知短信->帐户及签名设置->APIKEY
                // new NameValuePair("password", util.StringUtil.MD5Encode("密码")),
                new NameValuePair("mobile", phone),
                new NameValuePair("content", content),
        };
        method.setRequestBody(data);
        try {
            //client.excuteMethod(method);
            client.executeMethod(method);
            String SubmitResult = method.getResponseBodyAsString();
            //System.out.println(SubmitResult);
            Document doc = DocumentHelper.parseText(SubmitResult);
            Element root = doc.getRootElement();
            String code = root.elementText("code");
            String msg = root.elementText("msg");
            String smsid = root.elementText("smsid");
            System.out.println(code);
            System.out.println(msg);
            System.out.println(smsid);
            if ("2".equals(code)) {
                System.out.println("短信提交成功");
                return String.valueOf(mobile_code);
            }
        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return "";
    }
}
