package com.tositteach.service;

import com.tositteach.domain.entity.Engineer;
import com.tositteach.util.PagingBody;

import java.util.List;

public interface EngineerService {
    PagingBody query(int st, int nm);

    public List<Engineer> queryAllEngineer();
    public Engineer queryById(String id);
    public List<Engineer> queryByName(String name);
    public int insertOneEngineer();
    public  Engineer queryByProId(String id);
}
