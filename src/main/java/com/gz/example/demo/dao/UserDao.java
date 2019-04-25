package com.gz.example.demo.dao;

import com.gz.example.demo.pojo.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
public interface UserDao {

    @Select("select * from user where username = #{username}")
    User selectUserByUsername(String username);
}
