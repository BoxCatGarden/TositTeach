package com.tositteach.service.impl;

import com.tositteach.service.UploadService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import java.io.*;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;

@Service
public class UploadServiceImpl implements UploadService {

    private String realPath = "D:/Documents/TositTeachUploadFiles/";
    private String virPath = "/doc/";

    /* return the url of the file, or null if the file size is 0*/
    public String saveFile(CommonsMultipartFile file) throws IOException {
        if (file.getSize() == 0) return null;
        String name = UUID.randomUUID().toString().replace("-","") + file.getOriginalFilename();
        String path = realPath + name;
        File newFile = new File(path);
        file.transferTo(newFile);
        return virPath + name; //url
    }

    public boolean removeFile(String url) {
        if (!url.startsWith(virPath)) return false;
        String path = realPath + url.substring(5);
        File file = new File(path);
        return file.exists() && file.delete();
    }

    public String getRealPath() {
        return realPath;
    }

    public void setRealPath(String realPath) {
        this.realPath = realPath;
    }

    public String getVirPath() {
        return virPath;
    }

    public void setVirPath(String virPath) {
        this.virPath = virPath;
    }
}
