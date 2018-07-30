package com.tositteach.service.impl;

import com.tositteach.domain.mapper.EngineerMapper;
import com.tositteach.domain.entity.Engineer;
import com.tositteach.service.EngineerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EngineerServiceImpl implements EngineerService {
    @Autowired
    EngineerMapper engineerMapper;
    @Override
    public List<Engineer> queryAllEngineer() {
        return engineerMapper.selectAllEngineer();
    }

    @Override
    public Engineer queryById(String id) {
        List<Engineer> engineers = engineerMapper.selectAllEngineer();
        for(Engineer engineer:engineers){
            if(engineer.getEngiId().equals(id)){
                return engineer;
            }
        }
        return null;
    }

    @Override
    public List<Engineer> queryByName(String name) {
        List<Engineer> engineers = engineerMapper.selectEngineerByName(name);
        return engineers;
    }

    @Override
    public int insertOneEngineer() {
        return engineerMapper.insertEngineer();
    }
}
