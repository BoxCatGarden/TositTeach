package com.tositteach.controller;

import com.tositteach.service.StudentService;
import com.tositteach.util.PagingBody;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/stu")
public class StudentController {
    @Resource
    private StudentService studentService;

    //查询学生列表，分页，claId=班级id（班级学员）|"n"（未分班）|null全部
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public PagingBody query(@RequestParam(value = "ci", required = false)String claId,
                            @RequestParam(value = "st", required = false, defaultValue = "0")int st,
                            @RequestParam(value = "nm", required = false, defaultValue = "10")int nm){
        if (st < 0) st = 0;
        if (nm < 0) nm = 10;
        return studentService.query(claId, st, nm);
    }

    private final Pattern idPattern = Pattern.compile("^[0-9]+$");
    //添加单个学生
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public int add(@RequestBody StuReqBody stu) {
        if (stu.school==null||stu.id==null||stu.name==null||stu.sex==null||stu.grade==null) return 0;
        if (stu.sex < 0 || 2 < stu.sex) return 0;
        if (!idPattern.matcher(stu.id).matches()) return 0;
        if (stu.grade < 0 || 3 < stu.grade) return 0;
        return studentService.add(stu.school, stu.id, stu.name, stu.sex, stu.grade);
    }

    //从.xls|.xlsx文件批量导入学生
    @RequestMapping(value = "/bat", method = RequestMethod.POST)
    @ResponseBody
    public int bat(@RequestParam("file")CommonsMultipartFile file) {
        if (file.getSize()==0) return 0;
        if (!file.getOriginalFilename().matches(".*\\.xls$|.*\\.xlsx$")) return 0;
        return studentService.batch(file);
    }

    /*//目前不需要
    //删除单个学生
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public int del(@RequestBody StuReqBody stu){  //stu_id
        if (stu.userId==null) return 0;
        return 0;
    }

    //修改学员信息
    @RequestMapping(value = "/mod", method = RequestMethod.POST)
    @ResponseBody
    public int mod(@RequestBody StuReqBody stu){
        return 0;
    }*/
}

class StuReqBody {
    String userId;
    String school;
    String id;
    String name;
    Byte sex;
    Integer grade;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }
}