package com.team6.leangoo.controller;

import com.team6.leangoo.model.User;
import com.team6.leangoo.service.LoginService;
import com.team6.leangoo.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * Created by AgZou on 2017/9/5.
 */
@RestController
@RequestMapping("/Login")
public class LoginController {
    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @RequestMapping(value = "/getUserInfo", method = RequestMethod.POST)
    public AjaxResult getUserInfo(String UserAccount) {
        AjaxResult ajaxResult = new AjaxResult();
       ajaxResult.setData(loginService.getUserInfo("agzou"));
        return ajaxResult;
    }

}
