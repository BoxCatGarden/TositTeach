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
    public int add(CommonsMultipartFile file) throws IOException {
        EngDoc engDoc = new EngDoc();
        engDoc.setUrl(uploadService.saveFile(file));
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

    // WAIT TO CHECK
    // WAIT TO CHECK
    // WAIT TO CHECK
    @Override
    public int reupload(String doc_id, CommonsMultipartFile file) throws IOException {
        //delete file
        EngDoc doc = engDocMapper.getEngDoc(doc_id);
        if (doc == null || !uploadService.removeFile(doc.getUrl())) return 0;
        //save new file
        doc.setUrl(uploadService.saveFile(file));
        //update record
        return engDocMapper.updateEngDoc(doc);
    }

    @Override
    public int del(String doc_id) throws IOException {
        EngDoc doc = engDocMapper.getEngDoc(doc_id);
        if (doc == null || !uploadService.removeFile(doc.getUrl())) return 0;
        return engDocMapper.delEngDoc(doc_id);
    }
}
