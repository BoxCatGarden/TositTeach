package com.tositteach.service;

import com.tositteach.domain.entity.Project;
import com.tositteach.util.PagingBody;

public interface ProjectService {
    PagingBody query(int state,
                     String proName,
                     String engName, String engId,
                     int hasGroup, int st, int nm);
    Project get(String proId);
    String add(String proName,
               String stTime, String edTime, String disp,
               String engId);
    int del(String proId);
    int check(String proId, byte state);
    int mod(String proId, String proName,
            String edTime, String disp);

}
