package com.tositteach.controller;

import com.tositteach.domain.entity.Clazz;
import com.tositteach.service.ClazzService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ClazzController {
    @Autowired
    ClazzService clazzService;

    //查询clazz表中所有信息
    @RequestMapping("/viewClazz.html")
    public ModelAndView viewClazz() {
        ModelAndView viewClazz = new ModelAndView();
        List<Clazz> clazzs = clazzService.queryAllClazz();
        viewClazz.addObject("clazz",clazzs);
        viewClazz.setViewName("viewClazz");
        return viewClazz;
    }

    //根据选中班级的cla_id删除班级信息
    @RequestMapping("/removeClazz.html")
    public @ResponseBody String removeClazz(@Param("clazId") String clazId) {
        String res = "删除失败";

        int row = clazzService.removeClazz(clazId);
        if (row > 0) {
            res = "删除成功";
        }
        return res;
    }

    //增加新的班级及其信息，返回添加条数
    @RequestMapping("/addClazz.html")
    public @ResponseBody String addClazz(Clazz clazz) {
        String res = "添加失败";
        clazzService.getRightId(clazz);//获取id
        int row = clazzService.addClazz(clazz);
        if (row > 0) {
            res = "添加成功";
        }
        return res;
    }

}
