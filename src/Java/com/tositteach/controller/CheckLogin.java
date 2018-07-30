package com.tositteach.controller;

import com.tositteach.domain.entity.User;
import com.tositteach.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
public class CheckLogin {
    @Autowired
    UserService userService;
    @RequestMapping(value = "/checkLogin.html")
    public @ResponseBody Boolean checkLogin(User user){
        Boolean rel = false;
        if(userService.queryById(user.getUserId())!=null){//首先用json中的userid
            rel = true;
        }
        return rel;
    }
}
