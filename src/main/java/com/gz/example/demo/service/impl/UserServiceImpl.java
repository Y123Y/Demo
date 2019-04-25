package com.gz.example.demo.service.impl;

import com.gz.example.demo.dao.UserDao;
import com.gz.example.demo.pojo.User;
import com.gz.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User login(String username, String password) {
        User user = userDao.selectUserByUsername(username);
        if (user != null && user.getPassword().equals(password)){
            user.setPassword(null);
            return user;
        }
        return null;
    }

    @Override
    public void logout(String username) {

    }
}
