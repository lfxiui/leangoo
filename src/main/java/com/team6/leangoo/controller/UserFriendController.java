package com.team6.leangoo.controller;

import com.team6.leangoo.model.User;
import com.team6.leangoo.model.UserFriend;
import com.team6.leangoo.service.UserFriendService;
import com.team6.leangoo.service.UserService;
import com.team6.leangoo.util.AjaxResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: lfxiui
 * Date: 2017/9/7
 * Time: 17:00
 */
@RestController
@RequestMapping("/UserFriend")
public class UserFriendController {
    private final UserFriendService userFriendService;
    private final UserService userService;

    public UserFriendController(UserFriendService userFriendService, UserService userService){
        this.userFriendService = userFriendService;
        this.userService = userService;
    }

    @RequestMapping(value = "/getFriendList",method = RequestMethod.POST)
    public AjaxResult getFriendList(HttpSession session){
        Integer userId = (Integer) session.getAttribute("userId");
        AjaxResult ajaxResult = new AjaxResult();
        try {
            User user = new User();
            user.setUserId(userId);
            user = userService.getUserInfoById(user);
            List friendList = userFriendService.getFriendList(user);
            ajaxResult.seterrcode(0);
            ajaxResult.setData(friendList);
            return ajaxResult;
        } catch (Exception e) {
            e.printStackTrace();
            ajaxResult.seterrcode(10);
            ajaxResult.setinfo("请求失败");
            return ajaxResult;
        }
    }

    @RequestMapping(value = "/addFriend",method = RequestMethod.POST)
    public AjaxResult addFriend(@RequestBody Map map,HttpSession session){
        Integer userId = (Integer) session.getAttribute("userId");
        User user = new User();
        user.setUserId(userId);
        AjaxResult ajaxResult = new AjaxResult();
        try {
            String friendAccount = map.get("friendAccount").toString();
            user = userService.getUserInfoById(user);
            User friend = new User();
            friend.setUserAccount(friendAccount);
            friend = userService.selectUserByAccount(friend);
            UserFriend userFriend = new UserFriend();
            userFriend.setUserId(user.getUserId());
            userFriend.setFriendTo(friend.getUserId());
            if (userFriendService.addFriend(userFriend) == 1){
                ajaxResult.seterrcode(0);
                ajaxResult.setinfo("添加成功");
            }else {
                ajaxResult.seterrcode(2);
                ajaxResult.setinfo("添加失败，好友已存在!");
            }
            ajaxResult.setData(userFriendService.getFriendList(user));
            return ajaxResult;
        } catch (Exception e) {
            e.printStackTrace();
            ajaxResult.seterrcode(10);
            ajaxResult.setinfo("请求失败");
            return ajaxResult;
        }
    }

    @RequestMapping(value = "/deleteFriend",method = RequestMethod.POST)
    public AjaxResult deleteFriend(@RequestBody Map map,HttpSession session){
        Integer userId = (Integer) session.getAttribute("userId");
        User user = new User();
        user.setUserId(userId);
        AjaxResult ajaxResult = new AjaxResult();
        try {
            String friendAccount = map.get("friendAccount").toString();
            user = userService.getUserInfoById(user);
            User friend = new User();
            friend.setUserAccount(friendAccount);
            friend = userService.selectUserByAccount(friend);
            UserFriend userFriend = new UserFriend();
            userFriend.setUserId(user.getUserId());
            userFriend.setFriendTo(friend.getUserId());
            if (userFriendService.deleteFriend(userFriend) == 1){
                ajaxResult.seterrcode(0);
                ajaxResult.setinfo("删除成功");
            }else {
                ajaxResult.seterrcode(2);
                ajaxResult.setinfo("删除失败，好友不存在!");
            }
            ajaxResult.setData(userFriendService.getFriendList(user));
            return ajaxResult;
        } catch (Exception e) {
            e.printStackTrace();
            ajaxResult.seterrcode(10);
            ajaxResult.setinfo("请求失败");
            return ajaxResult;
        }
    }
}
