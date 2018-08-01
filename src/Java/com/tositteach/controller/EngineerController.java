package com.tositteach.controller;

import com.tositteach.domain.entity.Engineer;
import com.tositteach.service.EngineerService;
import com.tositteach.util.PagingBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/eng")
public class EngineerController {
    @Autowired
    EngineerService engineerService;

    //获取工程师列表，nm=0（查询全部记录）
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public PagingBody query(@RequestParam(value = "st", required = false, defaultValue = "0")int st,
                            @RequestParam(value = "nm", required = false, defaultValue = "0")int nm) {
        if (st < 0) st = 0;
        if (nm < 0) nm = 0;
        return null;
    }
}
