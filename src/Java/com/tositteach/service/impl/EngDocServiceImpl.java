package com.tositteach.service.impl;

import com.tositteach.domain.entity.EngDoc;
import com.tositteach.domain.mapper.EngDocMapper;
import com.tositteach.service.EngDocService;
import com.tositteach.service.UploadService;
import com.tositteach.util.YearIdBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;

@Service
public class EngDocServiceImpl implements EngDocService {
    @Resource
    EngDocMapper engDocMapper;
    @Resource
    UploadService uploadService;

    @Override
    public int total(String proName) {
        return engDocMapper.getEngDocTotalNum(proName);
    }

    @Override
    public List<EngDoc> query(String proName, int st, int nm) {
        List<EngDoc> list = engDocMapper.getEngDocList(proName, st, nm);
        return list != null ? list : new ArrayList<EngDoc>();
    }

    @Override
    public int add(CommonsMultipartFile file, String proId, String userId) throws IOException {
        String url = uploadService.saveFile(file);
        if (url == null) return 0;
        EngDoc engDoc = new EngDoc();
        engDoc.setUrl(url);
        engDoc.setProId(proId);
        engDoc.setUserId(userId);
        synchronized (this) {
            String id = YearIdBuilder.build(engDocMapper.getMaxEngDocId());
            engDoc.setDocId(id);
            return engDocMapper.addEngDoc(engDoc);
        }
    }

    @Override
    public int modify(EngDoc doc) {
        return engDocMapper.updateEngDoc(doc);
    }

    @Override
    public int reupload(String doc_id, CommonsMultipartFile file) throws IOException {
//        String url = uploadService.saveFile(file);
//        if (url == null) return 0;
//        EngDoc doc = engDocMapper.getEngDoc(doc_id);
//        if (doc == null || !uploadService.removeFile(doc.getUrl())) return 0;
//        doc.setUrl(url);
//        if (engDocMapper.updateEngDoc(doc) != 1) {
//            engDocMapper.delEngDoc(doc_id);
//            uploadService.removeFile(url);
//            return 0;
//        }
        return 1;
    }

    @Override
    public int del(String doc_id) throws IOException {
        EngDoc doc = engDocMapper.getEngDoc(doc_id);
        return (doc == null || engDocMapper.delEngDoc(doc_id) == 0 || !uploadService.removeFile(doc.getUrl())) ? 0 : 1;
    }
}
