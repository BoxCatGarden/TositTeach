package com.tositteach.controller;

import com.tositteach.domain.entity.StuDoc;
import com.tositteach.service.Doc_studentService;
import com.tositteach.util.PagingBody;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;

@Controller
@RequestMapping("/studoc")
public class StuDocController {
    @Resource
    Doc_studentService doc_studentService;
    /*@Autowired
    GpService gpService;
    @Autowired
    ProjectService projectService;*/


    //获取学员文档列表，分页
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public PagingBody query(@RequestParam(value = "dn", required = false)String docName,
                            @RequestParam(value = "gn", required = false)String groName,
                            @RequestParam(value = "pn", required = false)String projName,
                            @RequestParam(value = "st", required = false, defaultValue = "0")int st,
                            @RequestParam(value = "nm", required = false, defaultValue = "10")int nm) {
        if (st < 0) st = 0;
        if (nm < 0) nm = 10;
//        List<StuDoc> doc_students = doc_studentService.queryAllDoc_student();
        return null;
    }

    //获取文档详情
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public StuDoc get(@RequestParam("di")String docId) {
        return null;
    }

    //获取下载的url，不包含域名，只供前端使用，用户不直接操作这个url
    /*@RequestMapping(value = "/geturl", method = RequestMethod.GET)
    @ResponseBody
    public String getUrl(@RequestParam("di") String docId) {
        return null;
    }*/

    //文档添加
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public int add(@RequestParam("file")CommonsMultipartFile file,
                   @RequestParam("dn")String docName,
                   @RequestParam("dp")String disp) { //session获取用户获取相应的组
        return 0;
    }

    //修改已上传文档
    @RequestMapping(value = "/mod", method = RequestMethod.POST)
    @ResponseBody
    public int mod(@RequestParam("di")String docId,
                   @RequestParam("file")CommonsMultipartFile file) {
        return 0;
    }

    //删除文档
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public int del(@RequestBody StuDocReqBody doc) { //docId
        return 0;
    }

    //修改文档分数
    @RequestMapping(value = "/score", method = RequestMethod.POST)
    @ResponseBody
    public int score(@RequestBody StuDocReqBody doc) { //docId,score
        return 0;
    }

}

class StuDocReqBody {
    String di;
    Byte sc;

    public void setDi(String di) {
        this.di = di;
    }

    public void setSc(Byte sc) {
        this.sc = sc;
    }
}
