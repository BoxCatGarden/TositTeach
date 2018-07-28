package com.tositteach.controller;

import com.tositteach.domain.entity.User;
import com.tositteach.service.UserTestService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller("userTest")
@RequestMapping("/user")
public class UserTest {
    @Resource(name="userTestService")
    private UserTestService userTest;

    @RequestMapping(value = "/test/{id}", method = RequestMethod.GET)
    @ResponseBody
    public User test(ModelMap model, @PathVariable String id) {
        User user = userTest.getUser(id);
        return user;
    }

    @RequestMapping(value = "/test2", method = RequestMethod.POST)
    @ResponseBody
    public User test2(@RequestBody User user) {
        return user;
    }
}
