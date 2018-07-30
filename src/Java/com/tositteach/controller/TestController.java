package com.tositteach.controller;

import com.tositteach.domain.entity.Stu;
import com.tositteach.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class TestController {
    @Autowired
    TestService testService;

    @RequestMapping("jumpToIndex.html")
    public ModelAndView jumpToIndex(){
        ModelAndView modelAndView = new ModelAndView();
        List<Stu> stus = testService.queryAll();
        modelAndView.addObject("stu1",stus);
        modelAndView.setViewName("login");
        return modelAndView;
    }

}
