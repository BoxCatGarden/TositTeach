package com.tositteach.service;

import com.tositteach.domain.entity.StuDoc;
import com.tositteach.util.PagingBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public interface StuDocService {

    PagingBody query(String docName, String groName, String proName,
                     int st, int nm);
    StuDoc get(String docId);
    int add(CommonsMultipartFile file,
            String docName, String disp,
            String stuId);
    int mod(CommonsMultipartFile file, String docId, String stuId);
    int del(String docId);
    int score(String docId, byte score);

}
