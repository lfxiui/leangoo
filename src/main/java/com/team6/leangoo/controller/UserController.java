package com.team6.leangoo.controller;

import com.team6.leangoo.model.User;
import com.team6.leangoo.service.UserService;
import com.team6.leangoo.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/User")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @RequestMapping(value = "/getUserInfoById",method = RequestMethod.POST)
    public AjaxResult getUserInfoById(HttpSession session){
        Integer userId = (Integer) session.getAttribute("userId");
        User user=new User();
        user.setUserId(userId);
        return new AjaxResult(userService.getUserInfoById(user));
    }

    @RequestMapping(value = "/changeUserInfo",method = RequestMethod.POST)
    public AjaxResult changeUserInfo(@RequestBody User userMsg,HttpSession session){
        Integer userId = (Integer) session.getAttribute("userId");
        AjaxResult ajaxResult = new AjaxResult();
        try {
            userMsg.setUserId(userId);
            if (userService.changeUserInfo(userMsg)==1) {
                ajaxResult.seterrcode(0);
            } else {
                ajaxResult.seterrcode(10);
                ajaxResult.setinfo("操作失败");
            }
            User user = new User();
            user.setUserId(userId);
            user = userService.getUserInfoById(user);
            ajaxResult.setData(user);
            return ajaxResult;
        } catch (Exception e) {
            e.printStackTrace();
            ajaxResult.seterrcode(10);
            ajaxResult.setinfo("请求失败");
            return ajaxResult;
        }
    }

    @RequestMapping(value = "/changeUserPassword",method = RequestMethod.POST)
    public AjaxResult changeUserPassword(@RequestBody Map map,HttpSession session){
        Integer userId = (Integer) session.getAttribute("userId");
        AjaxResult ajaxResult = new AjaxResult();
        try {
            User user = new User();
            user.setUserId(userId);
            user = userService.getUserInfoById(user);
            String oldPwd = map.get("oldPwd").toString();
            String newPwd = map.get("newPwd").toString();
            String newPwdConfirm = map.get("newPwdConfirm").toString();
            if (user.getUserPassword().equals(oldPwd) && newPwd.equals(newPwdConfirm)){
                user.setUserPassword(newPwd);
                if(userService.changeUserInfo(user) == 1) ajaxResult.seterrcode(0);
                else {
                    ajaxResult.seterrcode(0);
                    ajaxResult.setinfo("修改失败");
                }
            }else {
                ajaxResult.seterrcode(0);
                ajaxResult.setinfo("密码错误，请重新输入");
            }
            map = new HashMap();
            map.put("userPassword",user.getUserPassword());
            ajaxResult.setData(map);
            return ajaxResult;
        } catch (Exception e) {
            e.printStackTrace();
            ajaxResult.seterrcode(10);
            ajaxResult.setinfo("请求失败");
            return ajaxResult;
        }
    }

    @RequestMapping(value = "/changeAvatar",method = RequestMethod.POST)
    public AjaxResult changeAvatar(@RequestParam("userAvatar") MultipartFile userAvatar, HttpServletRequest request,HttpSession session){
        Integer userId = (Integer) session.getAttribute("userId");
        String sp=File.separator;
        AjaxResult ajaxResult = new AjaxResult();
        try {
            String fileName = userAvatar.getOriginalFilename();
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            String newFileName = userId + "avatar"+suffixName;
            String path = request.getSession().getServletContext().getRealPath("/");
            path=path+"WEB-INF"+sp+"classes"+sp+"static"+sp+"img"+sp;
            File f = new File(path);
            if (!f.exists())
                f.mkdirs();
            if (!userAvatar.isEmpty()) {
                try {
                    FileOutputStream fos = new FileOutputStream(path + newFileName);
                    InputStream in = userAvatar.getInputStream();
                    int b = 0;
                    while ((b = in.read()) != -1) {
                        fos.write(b);
                    }
                    fos.close();
                    in.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            System.out.println("文件保存地址:" + path + newFileName);

            User user = new User();
            user.setUserId(userId);
            user.setUserAvatar("./img/"+newFileName);//要不要 path+ ?
            if (userService.changeUserInfo(user)==1) {
                ajaxResult.seterrcode(0);
            } else {
                ajaxResult.seterrcode(10);
                ajaxResult.setinfo("操作失败");
            }
            Map map = new HashMap();
            map.put("userAvatar","./img/"+newFileName);
            ajaxResult.setData(map);
            return ajaxResult;
        } catch (Exception e) {
            e.printStackTrace();
            ajaxResult.seterrcode(10);
            ajaxResult.setinfo("请求失败");
            return ajaxResult;
        }
    }
}
