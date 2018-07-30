package com.tositteach.controller;

import com.tositteach.domain.entity.Engineer;
import com.tositteach.domain.entity.Stu;
import com.tositteach.service.EngineerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class EngineerController {
    @Autowired
    EngineerService engineerService;
    @RequestMapping("/showAllEngineer.html")
    public ModelAndView showAllEngineer(){
        ModelAndView modelAndView = new ModelAndView();
        List<Engineer> engineers  = engineerService.queryAllEngineer();
        modelAndView.addObject("engineer1",engineers);
        modelAndView.setViewName("index");
        return modelAndView;
    }

}
