package com.team6.leangoo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 * User: lfxiui
 * Date: 2017/9/22
 * Time: 15:02
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @RequestMapping("/")
    public String indexTo(){
        return "index";
    }
}
