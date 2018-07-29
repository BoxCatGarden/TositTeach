package com.tositteach.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import java.io.*;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;

@Service
public class UploadService {

    public void saveFile(CommonsMultipartFile file) throws IOException {
        String path = "D:/Documents/TositTeachUploadFiles/" + UUID.randomUUID().toString().replace("-","") + file.getOriginalFilename();
        File newFile = new File(path);
        file.transferTo(newFile);
    }
}
