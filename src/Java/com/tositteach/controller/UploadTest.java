package com.tositteach.controller;

import com.tositteach.service.UploadService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.Date;

@Controller
public class UploadTest {

    @Resource
    private UploadService up;
    @RequestMapping(value = "uploadtest", method = RequestMethod.POST)
    public String uploadTest(@RequestParam("file")CommonsMultipartFile file) throws IOException {
        up.saveFile(file);
        return "success";
    }
}
