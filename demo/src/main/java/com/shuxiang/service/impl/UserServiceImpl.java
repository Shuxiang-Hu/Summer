package com.shuxiang.service.impl;

import com.shuxiang.dao.UserDao;
import com.shuxiang.service.UserService;

public class UserServiceImpl implements UserService {
    private String uId;

    private UserDao userDao;

    @Override
    public void queryUserInfo() {

        System.out.println("查询用户信息：" + userDao.queryUserName(uId));

    }





}