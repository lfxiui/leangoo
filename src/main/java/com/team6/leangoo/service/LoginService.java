package com.team6.leangoo.service;

import com.team6.leangoo.mapper.UserMapper;
import com.team6.leangoo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by AgZou on 2017/9/5.
 */
@Service
@Transactional
public class LoginService {
    @Autowired private UserMapper userMapper;

    public void getUserInfo(String UserAccount){
        User user=new User();
        user.setUserAccount("agzou");
        userMapper.deleteByPrimaryKey(2);
        userMapper.insert(user);
        userMapper.delete(user);

    }
}
