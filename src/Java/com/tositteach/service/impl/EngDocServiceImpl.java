package com.tositteach.service.impl;

import com.tositteach.domain.entity.EngDoc;
import com.tositteach.domain.mapper.EngDocMapper;
import com.tositteach.service.EngDocService;
import com.tositteach.service.FileService;
import com.tositteach.util.YearIdBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

@Service
public class EngDocServiceImpl implements EngDocService {
    @Resource
    EngDocMapper engDocMapper;
    @Resource
    FileService fileService;

    @Override
    public int upload(CommonsMultipartFile file,
                      String proId, String engId) {
        String url;
        try {
            url = fileService.saveFile(file);
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
        if (url == null) return 0;

        EngDoc doc = new EngDoc();
        doc.setProId(proId);
        doc.setUserId(engId);
        doc.setUrl(url);

        synchronized (this) {
            doc.setDocId(YearIdBuilder.build(engDocMapper.getMaxId()));
            return engDocMapper.add(doc);
        }
    }

    @Override
    public int reupload(String docId, CommonsMultipartFile file) {
        EngDoc doc = engDocMapper.get(docId);
        if (doc == null) return 0;
        String url;
        try {
            url = fileService.saveFile(file);
            if (url == null) return 0;
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
        String oldUrl = doc.getUrl();
        if (!fileService.removeFile(oldUrl)) {
            fileService.removeFile(url);
            return 0;
        }
        return engDocMapper.setUrl(docId, url);
    }

    @Override
    public int del(String docId) {
        EngDoc doc = engDocMapper.get(docId);
        if (doc == null) return 0;
        String url = doc.getUrl();
        if (!fileService.removeFile(url)) return 0;
        return engDocMapper.del(docId);
    }
}
