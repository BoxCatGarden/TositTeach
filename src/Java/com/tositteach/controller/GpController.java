package com.tositteach.controller;

import com.tositteach.domain.entity.Gp;
import com.tositteach.service.GpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GpController {
    @Autowired
    GpService gpService;

    @RequestMapping(value = "/addGp.html")
    public @ResponseBody String addGp(Gp gp){
        String res = "添加失败";
        int row = gpService.insertOneGroup(gp);
        if(row>0){
               res = "添加成功";
        }
        return res;
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
