package com.tositteach.controller;

import com.tositteach.domain.entity.Gp;
import com.tositteach.service.GpService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Controller
public class GpController {
    @Resource
    GpService gpService;

    //插入新的group的时候返回一个group的id
    @RequestMapping(value = "/addGp.html")
    public @ResponseBody  Gp addGp(Gp gp){
         Gp gp1 = new Gp();
        int row = gpService.insertOneGroup(gp);
        if(row>0){
               gp1 = gp;
        }
        return gp1;
    }

    @RequestMapping(value = "/getById.html")
    public @ResponseBody Gp getById(Gp gp){
      /*  ModelAndView modelAndView = new ModelAndView();*/
        Gp gpp = gpService.queryGpById(gp.getGpClaId(),gp.getGpGroId());
/*        modelAndView.addObject("gp1",gpp);
        modelAndView.setViewName("1");*/
        /*return modelAndView;*/
        return gpp;
    }
}
