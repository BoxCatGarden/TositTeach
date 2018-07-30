package com.tositteach.service.impl;

import com.tositteach.domain.mapper.ProjectMapper;
import com.tositteach.domain.entity.Engineer;
import com.tositteach.domain.entity.Project;
import com.tositteach.service.EngineerService;
import com.tositteach.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    ProjectMapper projectMapper;
    EngineerService engineerService;
    @Override
    public List<Project> queryAllProject() {
        return projectMapper.selectAllProject();
    }

    @Override
    public Project queryByName (String name) {
        List<Project> projects = projectMapper.selectAllProject();
        for(Project project:projects){
            if(project.getProjName().equals(name)){
                return project;
            }
        }
        return null;
    }
    @Override
    public Project queryById (String id) {
        List<Project> projects = projectMapper.selectAllProject();
        for(Project project:projects){
            if(project.getProjName().equals(id)){
                return project;
            }
        }
        return null;
    }

    @Override
    public int insertOneProject(Project project) {
        getRightId(project);
        int row = projectMapper.insertProject(project);
        return row;
    }

    @Override
    public int deleteOneProjectById(String id) {
        int row = projectMapper.deleteProject(id);
        return row;
    }

    @Override
    public int updateOneProjectById(Project project) {
        int row = projectMapper.updateProject(project);
        return row;
    }

    @Override
    public List<Project> queryByEngineerName(String ename) {
        List<Engineer> engineers = engineerService.queryByName(ename);
        List<Project> projects = projectMapper.selectAllProject();
        List<Project> selected = new ArrayList<Project>();
        for(Project project1:projects){
            for(Engineer engineer1:engineers){
                if(engineer1.getEngiId().equals(project1.getProjUserId())){
                    selected.add(project1);
                }
            }
        }
        return selected;
    }

    private void getRightId(Project project){
        List<Project> projects = projectMapper.selectAllProject();
        if(projects.size()==0){
            project.setProjId("1");
        }
        else {
            ArrayList<Integer> iid = new ArrayList<Integer>();
            //最后一个元素
            for (Project project1 : projects) {
                iid.add(new Integer(project1.getProjId()));
            }
            //排序从小到大
            Collections.sort(iid);
            int id1 = iid.get(iid.size() - 1) + 1;

            String id = String.valueOf(id1);
            project.setProjId(id);
        }

    }
}
