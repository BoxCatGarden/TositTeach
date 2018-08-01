package com.tositteach.service;

import com.tositteach.domain.entity.Project;

import java.util.List;

public interface ProjectService {
    //审核中
    public List<Project> queryAllProjectS();
    //通过
    public List<Project> queryAllProjectT();
    //未通过
    public List<Project> queryAllProjectM();
    //全部
    public List<Project> queryAllProject();
    public List<Project> queryByName(String name);
    public Project queryById(String id);
    public int insertOneProject(Project project);
    public int deleteOneProjectById(String id);
    public int updateOneProjectById(String id);
    public List<Project> queryByEngineerName(String ename);
    public int updateStateById(String id);
    public List<Project> queryByEngineerId(String uid);
}
