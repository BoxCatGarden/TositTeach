package com.tositteach.controller;

import com.tositteach.domain.entity.User;
import com.tositteach.service.EngDocService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/engdoc")
public class EngDocController {
    @Resource
    private EngDocService engDocService;

    /* upload the doc file,
     *  return: 0, fail;
     *          1, succeed;
     *          2, duplicate doc_id*/
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public int upload(@RequestParam("file") CommonsMultipartFile file,
                      @RequestParam("pi") String proId,
                      HttpSession session) {
        if (file.getSize() == 0) return 0;
        if (proId.length() != 11) return 0;
        String engId = ((User) session.getAttribute(UserController.USER)).getUserId();
        return engDocService.upload(file, proId, engId);
    }

    /* re-upload the file and update its record
     *  if succeed, return 1; else 0*/
    @RequestMapping(value = "/reupload", method = RequestMethod.POST)
    @ResponseBody
    public int reupload(@RequestParam("file") CommonsMultipartFile file,
                        @RequestParam("di") String docId,
                        HttpSession session) {
        if (file.getSize() == 0) return 0;
        if (docId.length() != 11) return 0;
        String engId = ((User) session.getAttribute(UserController.USER)).getUserId();
        return engDocService.reupload(file, docId, engId);
    }

    /* delete the file and its record
     *  if succeed, return 1; else 0*/
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public int del(@RequestBody EngDocReqBody req) {
        return req.di != null && req.di.length() == 11 ? engDocService.del(req.di) : 0;
    }
}

class EngDocReqBody {
    String di; //docId

    public void setDi(String di) {
        this.di = di;
    }
}
