package com.team6.leangoo.service;

import com.team6.leangoo.mapper.BoardMapper;
import com.team6.leangoo.mapper.ProjectMapper;
import com.team6.leangoo.mapper.ProjectUserMapper;
import com.team6.leangoo.mapper.UserMapper;
import com.team6.leangoo.model.Project;
import com.team6.leangoo.model.ProjectUser;
import com.team6.leangoo.model.User;
import com.team6.leangoo.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Map;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private ProjectUserMapper projectUserMapper;

    public User getUserInfoById(User user){
        return userMapper.selectOne(user);
    }
    public int changeUserInfo(User user){
        return userMapper.updateByPrimaryKeySelective(user);
    }
    public User selectUserByAccount(User user){
        return userMapper.selectOne(user);
    }
    public User findUserByAccount(String account){return userMapper.findByUserName(account);}
    public User selectUserPersonalBoardList(Integer userId){
        return userMapper.selectPersonalBoardList(userId);
    }
    public Integer insertUser(User user){
        userMapper.insert(user);
        Project project=new Project();
        project.setProjectIsArchive(0);
        project.setProjectEndDate(DateUtil.LocalDateToDate(LocalDate.now().plusDays(7)));
        project.setProjectStartDate(DateUtil.LocalDateToDate(LocalDate.now()));
        project.setProjectName("个人看板");
        projectMapper.insert(project);
        ProjectUser projectUser=new ProjectUser();
        projectUser.setProjectId(project.getProjectId());
        projectUser.setUserId(user.getUserId());
        projectUser.setIsPersonal(1);
        projectUserMapper.insert(projectUser);
        return user.getUserId();
    }
}
