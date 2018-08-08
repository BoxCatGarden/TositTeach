package com.tositteach.service;

import com.tositteach.util.PagingBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public interface StudentService {
    PagingBody query(String claId, int st, int nm);
    //Student get(String stuId);
    int add(String school, String id, String name, byte sex, int grade);

    //use .xls|.xlsx file to add a batch of students
    int batch(CommonsMultipartFile file);
    //del
    //mod

}
