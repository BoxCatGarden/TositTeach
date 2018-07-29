package com.tositteach.controller;

import com.tositteach.service.UploadService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;

@Controller
public class UploadTest {

    @Resource
    private UploadService up;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public int uploadTest(@RequestParam("file")CommonsMultipartFile file) {
        try {
            up.saveFile(file);
            return 1;
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
