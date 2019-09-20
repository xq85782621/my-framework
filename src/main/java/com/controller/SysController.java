package com.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Enumeration;

@RestController
public class SysController {


    //用户登录
    @RequestMapping(value = "/login")
    public String login(String username, String password) {
        //构造登录令牌
        try {

            /**
             * 密码加密：
             *     shiro提供的md5加密
             *     Md5Hash:
             *      参数一：加密的内容
             *              111111   --- abcd
             *      参数二：盐（加密的混淆字符串）（用户登录的用户名）
             *              111111+混淆字符串
             *      参数三：加密次数
             *
             */
            // password = new Md5Hash(password,username,3).toString();

            UsernamePasswordToken upToken = new UsernamePasswordToken(username, password);
            //1.获取subject
            Subject subject = SecurityUtils.getSubject();

            //获取session
            String sid = (String) subject.getSession().getId();

            //2.调用subject进行登录
            subject.login(upToken);


            return "登录成功"+ sid;
        } catch (Exception e) {
            return "用户名或密码错误";
        }
    }


    /**
     * 这个接口提供登录用户的个人信息,所以是只要登录就能访问
     */
    @RequiresAuthentication
    @RequestMapping(value = "/home")
    public String home() {
        return "访问个人主页成功";
    }



    /**
     *  登录成功后，打印所有session内容
     *  可见session可token是完全两种方式,session是把登录成功的用户信息存到服务器里,
     *  token是把把用户信息通过加密算法加密后给前端,前端每次请求带上该token,然后服务器通过特定算法验证该token,解析token
     */
    @RequestMapping(value = "/show")
    public String show(HttpSession session) {
        // 获取session中所有的键值
        Enumeration<?> enumeration = session.getAttributeNames();
        // 遍历enumeration中的
        while (enumeration.hasMoreElements()) {
            // 获取session键值
            String name = enumeration.nextElement().toString();
            // 根据键值取session中的值
            Object value = session.getAttribute(name);
            // 打印结果
            System.out.println("<B>" + name + "</B>=" + value + "<br>/n");
        }
        return "查看session成功";
    }

}
