package com.tositteach.controller;

import com.tositteach.domain.entity.Gp;
import com.tositteach.service.GpService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/gp")
public class GpController {
    @Resource
    private GpService gpService;

    //将传入的学员分组，传入序列[gro_name,cla_id,pro_id,stu_id[,stu_id[,..]]]
    @RequestMapping(value = "/group", method = RequestMethod.POST)
    @ResponseBody
    public int group(@RequestBody List<String> list) {
        if (list.size() < 4) return 0;
        String groName = list.get(0);
        String claId = list.get(1);
        String proId = list.get(2);
        List<String> stuIds = list.subList(3, list.size());
        return gpService.makeGroup(groName, claId, proId, stuIds);
    }
}
