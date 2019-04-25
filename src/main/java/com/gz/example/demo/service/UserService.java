package com.gz.example.demo.service;

import com.gz.example.demo.pojo.User;

public interface UserService {
    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    User login(String username, String password);

    /**
     * 退出登录
     * @param username
     */
    void logout(String username);
}
