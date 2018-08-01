package com.tositteach.controller;

import com.tositteach.domain.entity.User;
import com.tositteach.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    UserService userService;

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    @ResponseBody
    public int signIn(@RequestBody UserReqBody req) {
        return 0;
    }

    @RequestMapping(value = "/signout", method = RequestMethod.POST)
    @ResponseBody
    public int signOut() {
        return 0;
    }

    @RequestMapping(value = "/chgpwd", method = RequestMethod.POST)
    @ResponseBody
    public int changePwd(@RequestBody UserReqBody req) {
        return 0;
    }

}

class UserReqBody {
    String ui;
    String p;
    String np;

    public void setUi(String ui) {
        this.ui = ui;
    }

    public void setP(String p) {
        this.p = p;
    }

    public void setNp(String np) {
        this.np = np;
    }
}
