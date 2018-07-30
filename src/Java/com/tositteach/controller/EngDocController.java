package com.tositteach.controller;

import com.tositteach.domain.entity.EngDoc;
import com.tositteach.domain.entity.User;
import com.tositteach.service.EngDocService;
import com.tositteach.util.LayuiTableResBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
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
    public int getTotalNum(@RequestParam(value = "pn", required = false, defaultValue = "") String pn) {
        if (pn.equals("")) pn = null;
        return engDocService.total(pn);
    }

    /* return the recordset*/
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public List<EngDoc> getList(@RequestParam(value = "pn", required = false, defaultValue = "") String pn,
                                @RequestParam(value = "st", required = false, defaultValue = "0") int st,
                                @RequestParam(value = "nm", required = false, defaultValue = "10") int nm) {
        if (pn.equals("")) pn = null;
        if (st < 0) st = 0;
        if (nm < 0) nm = 10;
        return engDocService.query(pn, st, nm);

    }

    /* return the recordset*/
    @RequestMapping(value = "/layui", method = RequestMethod.GET)
    @ResponseBody
    public LayuiTableResBody getListLayui(@RequestParam(value = "pn", required = false, defaultValue = "") String pn,
                                          @RequestParam(value = "pg", required = false, defaultValue = "1") int pg,
                                          @RequestParam(value = "nm", required = false, defaultValue = "10") int nm) {
        if (pn.equals("")) pn = null;
        if (pg < 1) pg = 1;
        if (nm < 0) nm = 10;
        int total = engDocService.total(pn);
        List<EngDoc> data = engDocService.query(pn, (pg-1)*nm, nm);
        LayuiTableResBody body = new LayuiTableResBody();
        body.setCode(200);
        body.setCount(total);
        body.setData(data);
        return body;
    }

    /* upload the doc file,
     *  return: 0, fail;
     *          1, succeed;
     *          2, duplicate doc_id*/
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public int upload(@RequestParam("file") CommonsMultipartFile file,
                      @RequestParam(value = "pi", required = false, defaultValue = "") String pi,
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
    public int del(@RequestParam(value = "di", required = true) String di) throws IOException {
        return engDocService.del(di);
    }
}
