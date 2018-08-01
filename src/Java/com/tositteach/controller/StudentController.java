package com.tositteach.controller;

import com.tositteach.domain.entity.Student;
import com.tositteach.service.ClazzService;
import com.tositteach.service.GpService;
import com.tositteach.service.StudentService;
import com.tositteach.service.UserService;
import com.tositteach.util.PagingBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/stu")
public class StudentController {
    @Resource
    StudentService studentService;

    //查询学生列表，分页，claId=班级id（班级学员）|"n"（未分班）|null全部
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public PagingBody query(@RequestParam(value = "ci", required = false, defaultValue = "")String claId,
                            @RequestParam(value = "st", required = false, defaultValue = "0")int st,
                            @RequestParam(value = "nm", required = false, defaultValue = "10")int nm){
        if (claId.length() == 0) claId = null;
        if (st < 0) st = 0;
        if (nm < 0) nm = 10;
        return null;
    }

    //添加单个学生
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String add(@RequestBody Student stu){  //页面中填写的内容即可
        return "id";
    }

    //添加单个学生
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public int del(@RequestBody Student stu){  //stu_id
        return 0;
    }

    //修改学员信息
    @RequestMapping(value = "/mod", method = RequestMethod.POST)
    @ResponseBody
    public int mod(@RequestBody Student stu){
        return 0;
    }
}
