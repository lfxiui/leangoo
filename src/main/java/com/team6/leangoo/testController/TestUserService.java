package com.team6.leangoo.testController;


import com.team6.leangoo.mapper.UserMapper;
import com.team6.leangoo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by AgZou on 2017/8/24.
 */
//@Service
//@Transactional
public class TestUserService {
    @Autowired private UserMapper userMapper;
    //IDEA报错是因为没有找到mapper的实现类,实际上mapper实现类是在编译(不确定?)中生成的,不影响
    public User findUserByName(String username){
        return userMapper.findByUserName(username);
    }
}
