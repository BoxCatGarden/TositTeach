package com.tositteach.domain.mapper;

import com.tositteach.domain.entity.Engineer;

import java.util.List;

public interface EngineerMapper {
    public List<Engineer> selectAllEngineer();
    public Engineer selectEngineerById(String id);
    public List<Engineer> selectEngineerByName(String name);
    public int insertEngineer();
}
