package com.team6.leangoo.service;

import com.team6.leangoo.mapper.UserFriendMapper;
import com.team6.leangoo.mapper.UserMapper;
import com.team6.leangoo.model.User;
import com.team6.leangoo.model.UserFriend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: lfxiui
 * Date: 2017/9/7
 * Time: 16:18
 */
@Service
@Transactional
public class UserFriendService {
    @Autowired private UserFriendMapper userFriendMapper;

    public List getFriendList(User user){
        List<User> friends = userFriendMapper.getFriendList(user.getUserId());
        List friendList = new ArrayList();
        Map map = null;
        for (User temp:friends){
            map = new HashMap();
            map.put("name",temp.getUserAccount());
            map.put("email",temp.getUserEmail());
            map.put("avatar",temp.getUserAvatar());
            friendList.add(map);
        }
        return friendList;
    }

    public int addFriend(UserFriend userFriend){
        if (userFriend.getUserId() == userFriend.getFriendTo()) return 0;
        if (userFriendMapper.selectOne(userFriend) == null)
        return userFriendMapper.insert(userFriend);
        else return 0;
    }

    public int deleteFriend(UserFriend userFriend){
        if (userFriendMapper.selectOne(userFriend) != null)
            return userFriendMapper.delete(userFriend);
        else return 0;
    }
}
