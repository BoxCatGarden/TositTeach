package com.tositteach.controller;

import com.tositteach.service.FileService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

@Controller
public class FileController {

    /*@Resource
    private FileService up;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public String upload(@RequestParam("file")CommonsMultipartFile file) {
        try {
            return up.saveFile(file);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }*/
}
