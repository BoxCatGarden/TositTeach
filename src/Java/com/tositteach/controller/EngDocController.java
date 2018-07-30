package com.tositteach.controller;

import com.tositteach.domain.entity.EngDoc;
import com.tositteach.service.EngDocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/engdoc")
public class EngDocController {
    @Autowired
    EngDocService engDocService;

    /* return the total number of the matched records*/
    @RequestMapping(value = "/ttl", method = RequestMethod.GET)
    @ResponseBody
    public int getTotalNum(@RequestParam(required = false, defaultValue = "")String pn) {
        if(pn.equals("")) pn = null;
        return engDocService.total(pn);
    }

    /* return the recordset*/
    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public List<EngDoc> getList(@RequestParam(required = false, defaultValue = "")String pn,
                                      @RequestParam(required = false, defaultValue = "0")int st,
                                      @RequestParam(required = false, defaultValue = "0")int nm) {
        if (pn.equals("")) pn = null;
        return engDocService.query(pn, st, nm);

    }

    /* upload the doc file,
    *  return: 0, fail;
    *          1, succeed;
    *          2, duplicate doc_id*/
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public int upload(@RequestParam("file")CommonsMultipartFile file) throws IOException {
        return engDocService.add(file);
    }

    /* if succeed, return 1; else 0*/
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    @ResponseBody
    public int modify(EngDoc engDoc) {
        return engDocService.modify(engDoc);
    }

    /* re-upload the file and update its record
     *  if succeed, return 1; else 0*/
    @RequestMapping(value = "/reupload", method = RequestMethod.POST)
    @ResponseBody
    public int reupload(@RequestParam(required = true)String di,
                        @RequestParam("file")CommonsMultipartFile file) throws IOException {
        return engDocService.reupload(di, file);
    }

    /* delete the file and its record
    *  if succeed, return 1; else 0*/
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public int del(@RequestParam(required = true)String di) throws IOException {
        return engDocService.del(di);
    }
}
