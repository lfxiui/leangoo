package com.team6.leangoo.testController;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by AgZou on 2017/8/25.
 * 测试shiro框架用 不含业务逻辑
 */
//@Controller
public class HomeController {

    @RequestMapping("/")
    public String index(){
        return "index";

    }

   // @RequestMapping(value = "/login")
    public ModelAndView login(HttpServletRequest request) throws Exception{
        System.out.println(request.getAttribute("username"));
        ModelAndView mv=new ModelAndView();
        mv.setViewName("login");
        String exception= (String) request.getAttribute("shiroLoginFailure");//在AuthReam类中(自己写)验证登录用户,假如信息不对,可以在这里取得抛出错误
        String msg="";
        if(exception!=null){
            if(UnknownAccountException.class.getName().equals(exception)){
                System.out.println("UnknownAccountException-->账号不存在");
                msg="UnknownAccountException-->账号不存在";
            }else if(IncorrectCredentialsException.class.getName().equals(exception)){
                System.out.println("IncorrectCredentialsException-->密码不正确");
                msg="IncorrectCredentialsException-->密码不正确";
            }else {
                msg="else >>"+exception;
                System.out.println(msg);
            }
        }
        mv.addObject("msg",msg);
        return mv;
    }
}
