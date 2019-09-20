package com.controller;

import com.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 *  假设这是一个用户模块,包含用户curd
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;



    /**
     * 权限鉴定两种方式,验证失败的时候,逻辑不同
     * 1.过滤器：如果权限信息不匹配,则根据过滤器配置去setUnauthorizedUrl指定的地址
     * 2.注解：通过注解方式如果权限信息不匹配，抛出异常(可以用全局异常捕获,返回失败信息)
     */


    //个人主页
    //使用shiro注解鉴权
    //@RequiresPermissions()  -- 访问此方法必须具备的权限
    //@RequiresRoles() -- 访问此方法必须具备的角色




    //添加
    @RequiresPermissions("user-add")
    @RequestMapping(value = "/user",method = RequestMethod.POST)
    public String add() {
        return "添加用户成功";
    }
	
    //查询
    @RequiresPermissions("user-find")
    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public String find() {
        return "查询用户成功";
    }
	
    //更新
    @RequiresPermissions("user-update")
    @RequestMapping(value = "/user/{id}",method = RequestMethod.PUT)
    public String update(String id) {
        return "更新用户成功";
    }
	
    //删除
    @RequiresPermissions("user-delete")
    @RequestMapping(value = "/user/{id}",method = RequestMethod.DELETE)
    public String delete() {
        return "删除用户成功";
    }

//    /**
//     *  1.传统登录
//     *      前端发送登录请求 => 接口部分获取用户名密码 => 程序员在接口部分手动控制
//     *  2.shiro登录
//     *      前端发送登录请求 => 接口部分获取用户名密码 => 通过subject.login =>  realm域的认证方法
//     *
//     */
//	//用户登录
//	@RequestMapping(value="/login")
//    public String login(String username,String password) {
//	    //构造登录令牌
//        try {
//
//            /**
//             * 密码加密：
//             *     shiro提供的md5加密
//             *     Md5Hash:
//             *      参数一：加密的内容
//             *              111111   --- abcd
//             *      参数二：盐（加密的混淆字符串）（用户登录的用户名）
//             *              111111+混淆字符串
//             *      参数三：加密次数
//             *
//             */
//           // password = new Md5Hash(password,username,3).toString();
//
//            UsernamePasswordToken upToken = new UsernamePasswordToken(username,password);
//            //1.获取subject
//            Subject subject = SecurityUtils.getSubject();
//
//            //获取session
//            String sid = (String) subject.getSession().getId();
//
//            //2.调用subject进行登录
//            subject.login(upToken);
//            return "登录成功";
//        }catch (Exception e) {
//            return "用户名或密码错误";
//        }
//    }
}
