package com.team6.leangoo.service;

import com.team6.leangoo.mapper.UserMapper;
import com.team6.leangoo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Id;

/**
 * Created by AgZou on 2017/9/5.
 */
@Service
@Transactional
public class LoginService {
    @Autowired private UserMapper userMapper;

    public User getUserInfo(Integer userId){
        User user=new User();
        user.setUserId(userId);
        return userMapper.selectByPrimaryKey(user);

    }
}
