package com.tositteach.service.impl;

import com.tositteach.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.*;
import java.util.UUID;

@Transactional
public class FileServiceImpl implements FileService {

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
        String path = realPath + url.substring(virPath.length());
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
