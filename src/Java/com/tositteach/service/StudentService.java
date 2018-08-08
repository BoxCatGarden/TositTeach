package com.tositteach.service;

import com.tositteach.util.PagingBody;

public interface StudentService {
    PagingBody query(String claId, int st, int nm);
    //Student get(String stuId);
    int add(String school, String id, String name, byte sex, int grade);
    //del
    //mod

}
