package com.tositteach.service.impl;

import com.tositteach.domain.entity.Gp;
import com.tositteach.domain.entity.StuDoc;
import com.tositteach.domain.entity.Student;
import com.tositteach.domain.mapper.StuDocMapper;
import com.tositteach.service.FileService;
import com.tositteach.service.StuDocService;
import com.tositteach.service.StudentService;
import com.tositteach.util.PagingBody;
import com.tositteach.util.YearIdBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class StuDocServiceImpl implements StuDocService {
    @Resource
    StuDocMapper stuDocMapper;
    @Resource
    FileService fileService;
    @Resource
    StudentService studentService;

    @Override
    public PagingBody query(String docName,
                            String groName,
                            String proName,
                            int st, int nm) {
        PagingBody body = new PagingBody();
        body.setTotal(stuDocMapper.total(docName, groName, proName));
        body.setData(stuDocMapper.query(docName, groName, proName, st, nm));
        return body;
    }

    @Override
    public StuDoc get(String docId) {
        return stuDocMapper.get(docId);
    }

    @Override
    public int add(CommonsMultipartFile file,
                   String docName, String disp,
                   String stuId) {
        String url;
        try {
            url = fileService.saveFile(file);
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
        if (url == null) return 0;

        StuDoc doc = new StuDoc();
        doc.setDocName(docName);
        doc.setDisp(disp);
        doc.setUrl(url);

        Gp stuGp = studentService.get(stuId).getGp();
        doc.setClaId(stuGp.getClaId());
        doc.setGroId(stuGp.getGroId());
        doc.setProId(stuGp.getProId());

        synchronized (this) {
            doc.setDocId(YearIdBuilder.build(stuDocMapper.getMaxId()));
            return stuDocMapper.add(doc);
        }
    }

    @Override
    public int mod(String docId, CommonsMultipartFile file) {

        StuDoc doc = stuDocMapper.get(docId);
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
        return stuDocMapper.setUrl(docId, url);
    }

    @Override
    public int del(String docId) {
        StuDoc doc = stuDocMapper.get(docId);
        if (doc == null) return 0;
        String url = doc.getUrl();
        if (!fileService.removeFile(url)) return 0;
        return stuDocMapper.del(docId);
    }

    @Override
    public int score(String docId, byte score) {
        return stuDocMapper.setScore(docId, score);
    }

}
