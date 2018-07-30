package com.tositteach.service;

import com.tositteach.domain.entity.Engineer;

import java.util.List;

public interface EngineerService {
    public List<Engineer> queryAllEngineer();
    public Engineer queryById(String id);
    public List<Engineer> queryByName(String name);
    public int insertOneEngineer();
}
