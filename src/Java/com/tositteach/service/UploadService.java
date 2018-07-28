package com.tositteach.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

@Service
public class UploadService {

    public void saveFile(CommonsMultipartFile file) throws IOException {
        String realPath = this.getClass().getClassLoader().getResource("/").getPath()+"../";
        String path = realPath + "docs/" + new Date().getTime() + file.getOriginalFilename();
        File newFile = new File(path);
        file.transferTo(newFile);
    }
}
