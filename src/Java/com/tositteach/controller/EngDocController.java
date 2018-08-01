package com.tositteach.controller;

import com.tositteach.domain.entity.EngDoc;
import com.tositteach.domain.entity.User;
import com.tositteach.service.EngDocService;
import com.tositteach.util.PagingBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/engdoc")
public class EngDocController {
    @Resource
    EngDocService engDocService;

    //获取项目的文档
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public EngDoc get(@RequestParam(value = "pi") String proId) {
        return null;
    }

    /* upload the doc file,
     *  return: 0, fail;
     *          1, succeed;
     *          2, duplicate doc_id*/
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public int upload(@RequestParam("file") CommonsMultipartFile file,
                      @RequestParam(value = "pi") String pi,
                      HttpServletRequest request) throws IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (pi.equals("")) pi = null;
        return engDocService.add(file, pi, user != null ? user.getUserId() : null);
    }

//    /* if succeed, return 1; else 0*/
//    @RequestMapping(value = "/modify", method = RequestMethod.POST)
//    @ResponseBody
//    public int modify(@RequestBody EngDoc engDoc) {
//        return engDocService.modify(engDoc);
//    }

    /* re-upload the file and update its record
     *  if succeed, return 1; else 0*/
    @RequestMapping(value = "/reupload", method = RequestMethod.POST)
    @ResponseBody
    public int reupload(@RequestParam(value = "di", required = true) String di,
                        @RequestParam("file") CommonsMultipartFile file) throws IOException {
        return engDocService.reupload(di, file);
    }

    /* delete the file and its record
     *  if succeed, return 1; else 0*/
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public int del(@RequestBody EngDocReqBody res) throws IOException {
        return res.getDi() != null ? engDocService.del(res.getDi()) : 0;
    }
}

class EngDocReqBody {
    private String di; //doc_id
    private String pi; //pro_id

    public String getDi() {
        return di;
    }

    public void setDi(String di) {
        this.di = di;
    }

    public String getPi() {
        return pi;
    }

    public void setPi(String pi) {
        this.pi = pi;
    }
}
