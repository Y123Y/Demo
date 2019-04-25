package com.gz.example.demo.controller;

import com.gz.example.demo.common.Constant;
import com.gz.example.demo.common.ResultGenerator;
import com.gz.example.demo.common.ResultMsg;
import com.gz.example.demo.pojo.User;
import com.gz.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @GetMapping("/main")
    public String mainPage(){
        return "main";
    }

    @PostMapping("/login")
    @ResponseBody
    public ResultMsg login(@RequestParam("username") String username,
                           @RequestParam("password") String password, HttpSession session){
        User user = userService.login(username, password);
        if (user != null) {
            session.setAttribute(Constant.USER_SESSION, user);
            return ResultGenerator.genSuccessResultMsg();
        }
        return ResultGenerator.genFailResultMsg("用户名或密码错误");
    }

}
