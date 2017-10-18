package com.team6.leangoo.testController;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by AgZou on 2017/8/25.
 * 测试shiro框架用,不含业务逻辑
 */
//@Controller
//@RequestMapping("/userInfo")
public class TestUserController {
    /**
     * 用户查询
     *
     * @return the string
     */
    @RequestMapping("/userDel")
    @RequiresPermissions("delete")//shiro注解,只有拥有delete权限的用户才能使用(permission表中的name的值)
    //在GlobalExceptionHandler中配置了假如没权限返回的页面(index)
    public String userInfo() {
        return "userInfoDel";
    }

    @RequestMapping("/userAdd")
    @RequiresPermissions("add")//shiro注解,只有拥有add权限的用户才能使用(permission表中的name的值)
    public String userInfoAdd() {
        return "userInfoAdd";
    }
}
