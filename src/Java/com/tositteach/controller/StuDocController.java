package com.tositteach.controller;

import com.tositteach.domain.entity.StuDoc;
import com.tositteach.domain.entity.User;
import com.tositteach.service.StuDocService;
import com.tositteach.util.PagingBody;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/studoc")
public class StuDocController {
    @Resource
    private StuDocService stuDocService;
    /*@Autowired
    GpService gpService;
    @Autowired
    ProjectService projectService;*/


    //获取学员文档列表，分页
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public PagingBody query(@RequestParam(value = "dn", required = false)String docName,
                            @RequestParam(value = "gn", required = false)String groName,
                            @RequestParam(value = "pn", required = false)String proName,
                            @RequestParam(value = "st", required = false, defaultValue = "0")int st,
                            @RequestParam(value = "nm", required = false, defaultValue = "10")int nm) {
        if (st < 0) st = 0;
        if (nm < 0) nm = 10;
        return stuDocService.query(docName, groName, proName, st, nm);
    }

    //获取文档详情
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public StuDoc get(@RequestParam("di")String docId) {
        StuDoc doc = stuDocService.get(docId);
        return doc != null ? doc : new StuDoc();
    }

    //文档添加
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public int add(@RequestParam("file")CommonsMultipartFile file,
                   @RequestParam("dn")String docName,
                   @RequestParam("dp")String disp,
                   HttpSession session) { //session获取用户获取相应的组
        if (file.getSize()==0)return 0;
        String stuId = ((User) session.getAttribute("user")).getUserId();
        return stuDocService.add(file, docName, disp, stuId);
    }

    //修改已上传文档
    @RequestMapping(value = "/mod", method = RequestMethod.POST)
    @ResponseBody
    public int mod(@RequestParam("file")CommonsMultipartFile file,
                   @RequestParam("di")String docId,
                   HttpSession session) {
        if (file.getSize()==0) return 0;
        if (docId.length()!=11)return 0;
        String stuId = ((User) session.getAttribute("user")).getUserId();
        return stuDocService.mod(file, docId, stuId);
    }

    //删除文档
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public int del(@RequestBody StuDocReqBody doc) { //docId
        if (doc.di==null||doc.di.length()!=11)return 0;
        return stuDocService.del(doc.di);
    }

    //修改文档分数
    @RequestMapping(value = "/score", method = RequestMethod.POST)
    @ResponseBody
    public int score(@RequestBody StuDocReqBody doc) { //docId,score
        if (doc.di==null||doc.sc==null||doc.di.length()!=11||doc.sc<0||100<doc.sc)return 0;
        return stuDocService.score(doc.di, doc.sc);
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
