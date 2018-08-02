package com.tositteach.service;

import com.tositteach.domain.entity.Clazz;
import com.tositteach.util.PagingBody;

import java.util.List;

public interface ClazzService {

    PagingBody query(int st, int nm);
    int add(String claName, String room, String engId);
    int del(List<String> claIds);
    int addstu(String claId, List<String> stuIds);

}
