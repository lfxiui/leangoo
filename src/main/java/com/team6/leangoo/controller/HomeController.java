package com.team6.leangoo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * Created by AgZou on 2017/10/16.
 */
@Controller
public class HomeController {
    @RequestMapping("/")
    public String index(){
        return "index";
    }
    @RequestMapping("/logout")
    public String logOut(HttpSession session){
        session.removeAttribute("userId");
        return "redirect:/";
    }
}
