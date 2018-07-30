package com.tositteach.service;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.IOException;

public interface UploadService {
    String saveFile(CommonsMultipartFile file) throws IOException;
    boolean removeFile(String url);
}
