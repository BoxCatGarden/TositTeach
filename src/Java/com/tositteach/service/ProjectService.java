package com.tositteach.service;

import com.tositteach.domain.entity.Project;

import java.util.List;

public interface ProjectService {
    public List<Project> queryAllProject();
    public Project queryByName(String name);
    public Project queryById(String id);
    public int insertOneProject(Project project);
    public int deleteOneProjectById(String id);
    public int updateOneProjectById(Project project);
    public List<Project> queryByEngineerName(String ename);
}
