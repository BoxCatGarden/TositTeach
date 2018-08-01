package com.tositteach.controller;

import com.tositteach.domain.entity.Doc_student;
import com.tositteach.domain.entity.Gp;
import com.tositteach.domain.entity.Project;
import com.tositteach.service.Doc_studentService;
import com.tositteach.service.GpService;
import com.tositteach.service.ProjectService;
import com.tositteach.util.PagingBody;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/studoc")
public class StuDocController {
    @Resource
    Doc_studentService doc_studentService;
    /*@Autowired
    GpService gpService;
    @Autowired
    ProjectService projectService;*/


    //查询doc_student表中所有信息，分页
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public PagingBody query(@RequestParam(value = "dn", required = false)String docName,
                            @RequestParam(value = "gn", required = false)String groName,
                            @RequestParam(value = "pn", required = false)String projName,
                            @RequestParam(value = "st", required = false, defaultValue = "0")int st,
                            @RequestParam(value = "nm", required = false, defaultValue = "10")int nm) {
        if (st < 0) st = 0;
        if (nm < 0) nm = 10;
//        List<Doc_student> doc_students = doc_studentService.queryAllDoc_student();
        return null;
    }

    //获取下载的url，不包含域名，只供前端使用，用户不直接操作这个url
    @RequestMapping(value = "/geturl", method = RequestMethod.GET)
    @ResponseBody
    public String getUrl(@RequestParam("di") String docId) {
        return null;
    }

    //文档添加，先单独上传文档，取得返回的url后，连带url一起添加记录
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public int add(@RequestBody Doc_student doc) { //name,url,claId,groId,proId
        return 0;
    }

    //修改已上传文档
    @RequestMapping(value = "/mod", method = RequestMethod.POST)
    @ResponseBody
    public int mod(@RequestBody Doc_student doc) {
        return 0;
    }

    //删除文档
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public int del(@RequestBody Doc_student doc) { //docId
        return 0;
    }

    //修改文档分数
    @RequestMapping(value = "/score", method = RequestMethod.POST)
    @ResponseBody
    public int score(@RequestBody Doc_student doc) { //docId,score
        return 0;
    }

}
