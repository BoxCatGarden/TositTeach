package com.tositteach.service.impl;

import com.tositteach.domain.mapper.EngineerMapper;
import com.tositteach.domain.mapper.ProjectMapper;
import com.tositteach.domain.entity.Engineer;
import com.tositteach.domain.entity.Project;
import com.tositteach.service.EngineerService;
import com.tositteach.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.Action;
import java.util.List;
@Service
public class EngineerServiceImpl implements EngineerService {
    @Autowired
    EngineerMapper engineerMapper;
    ProjectService projectService;
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

    @Override
    public Engineer queryByProId(String proid) {
        Project project = projectService.queryById(proid);
        Engineer engineer = engineerMapper.selectEngineerById(project.getProjUserId());
        return engineer;
    }
}
