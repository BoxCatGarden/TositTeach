package com.tositteach.controller;

import com.tositteach.domain.entity.User;
import com.tositteach.service.UserTestService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller("userTest")
@RequestMapping("/user")
public class UserTest {
    @Resource(name="userTestService")
    private UserTestService userTest;

    @RequestMapping(value = "/test/{id}", method = RequestMethod.GET)
    @ResponseBody
    public List<User> test(ModelMap model, @PathVariable String id) {
        User user = userTest.getUser(id);

        List<User> users = new ArrayList();

        if (user!=null) users.add(user);

        return users;
    }

    @RequestMapping(value = "/test2", method = RequestMethod.POST)
    @ResponseBody
    public User test2(@RequestBody User user) {
        return user;
    }
}
