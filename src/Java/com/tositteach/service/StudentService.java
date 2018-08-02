package com.tositteach.service;

import com.tositteach.domain.entity.Student;
import com.tositteach.util.PagingBody;

import java.sql.Struct;
import java.util.List;
public interface StudentService {
    PagingBody query(String claId, int st, int nm);
    Student get(String stuId);
    int add(String school, String id, String name, byte sex, String grade);
    //del
    //mod

}
