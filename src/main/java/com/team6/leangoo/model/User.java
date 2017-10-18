package com.team6.leangoo.model;

import lombok.ToString;

import javax.persistence.*;
import java.util.*;
import java.util.List;

@ToString //Lombok库定义的注解,自动生成toString方法;
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(name = "user_account")
    private String userAccount;

    @Column(name = "user_password")
    private String userPassword;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "user_sex")
    private Integer userSex;

    @Column(name = "user_intro")
    private String userIntro;

    @Column(name = "user_avatar")
    private String userAvatar;
    @Transient//在model里自定义的属性一定要加上这个注解,表明这个属性不是表里的字段
    private java.util.List<Role> roles=new ArrayList<>();//这个变量,根据表里的对应关系手写的
    @Transient
    private java.util.List<Board> personalBoardList=new ArrayList<>();

    public List<Board> getPersonalBoardList() {
        return personalBoardList;
    }

    public void setPersonalBoardList(List<Board> personalBoardList) {
        this.personalBoardList = personalBoardList;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    /**
     * @return user_id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * @return user_account
     */
    public String getUserAccount() {
        return userAccount;
    }

    /**
     * @param userAccount
     */
    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    /**
     * @return user_password
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * @param userPassword
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    /**
     * @return user_email
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * @param userEmail
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    /**
     * @return user_sex
     */
    public Integer getUserSex() {
        return userSex;
    }

    /**
     * @param userSex
     */
    public void setUserSex(Integer userSex) {
        this.userSex = userSex;
    }

    /**
     * @return user_intro
     */
    public String getUserIntro() {
        return userIntro;
    }

    /**
     * @param userIntro
     */
    public void setUserIntro(String userIntro) {
        this.userIntro = userIntro;
    }

    /**
     * @return user_avatar
     */
    public String getUserAvatar() {
        return userAvatar;
    }

    /**
     * @param userAvatar
     */
    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }
}