package com.team6.leangoo.controller;

import com.team6.leangoo.model.User;
import com.team6.leangoo.service.LoginService;
import com.team6.leangoo.service.UserService;
import com.team6.leangoo.util.AjaxResult;
import com.team6.leangoo.util.AvatarUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


/**
 * Created by AgZou on 2017/9/5.
 */
@RestController
@RequestMapping("/Login")
public class LoginController {
    private final LoginService loginService;
    private final UserService userService;

    @Autowired
    public LoginController(LoginService loginService, UserService userService) {
        this.loginService = loginService;
        this.userService = userService;
    }
    @RequestMapping(value = "/getUserInfo", method = RequestMethod.POST)
    public AjaxResult getUserInfo(HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.setData(loginService.getUserInfo(userId));
        return ajaxResult;
    }

    @RequestMapping(value = "/doLogin")
    public AjaxResult doLogin(@RequestBody User user, HttpSession session) {
        String pwd = user.getUserPassword();
        user.setUserPassword(null);
        User dbUser = userService.selectUserByAccount(user);
        AjaxResult ajaxResult = new AjaxResult();
        if (dbUser == null) {
            user.setUserPassword(pwd);
            user.setUserAvatar(AvatarUtil.getAvatar());
            ajaxResult.setData(userService.insertUser(user));
            session.setAttribute("userId", user.getUserId());
            return ajaxResult;
        } else if (pwd.equals(dbUser.getUserPassword())) {
            ajaxResult.setData(dbUser.getUserId());
            session.setAttribute("userId", dbUser.getUserId());
            return ajaxResult;
        } else {
            ajaxResult.setData(-1);
            return ajaxResult;
        }
    }

}
