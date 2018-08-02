package com.tositteach.service.impl;

import com.tositteach.domain.mapper.ProjectMapper;
import com.tositteach.domain.entity.Engineer;
import com.tositteach.domain.entity.Project;
import com.tositteach.service.EngineerService;
import com.tositteach.service.ProjectService;
import com.tositteach.util.PagingBody;
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
    public PagingBody query(int state, String proName, String engName, String engId, int st, int nm) {
        return null;
    }

    @Override
    public Project get(String proId) {
        return null;
    }

    @Override
    public String add(String proName, String stTime, String edTime, String disp, String engId) {
        return null;
    }

    @Override
    public int del(String proId) {
        return 0;
    }

    @Override
    public int check(String proId, byte state) {
        return 0;
    }

    @Override
    public int mod(String proId, String proName, String edTime, String disp) {
        return 0;
    }

    @Override
    public List<Project> queryAllProjectS() {
        return projectMapper.selectAllProjectS();
    }

    @Override
    public List<Project> queryAllProjectT() {
        return projectMapper.selectAllProjectT();
    }

    @Override
    public List<Project> queryAllProjectM() {
        return projectMapper.selectAllProjectM();
    }

    @Override
    public List<Project> queryAllProject() {
        return projectMapper.selectAllProject();
    }

    @Override
    public List<Project> queryByName (String name) {
        List<Project> projects = projectMapper.selectAllProjectT();
        List<Project> selected = new ArrayList<>();
        for(Project project:projects){
            if(project.getProName().equals(name)){
                selected.add(project);
            }
        }
        return selected;
    }
    @Override
    public Project queryById (String id) {
        List<Project> projects = projectMapper.selectAllProjectT();
        for(Project project:projects){
            if(project.getProName().equals(id)){
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
    public int updateOneProjectById(String id) {
        Project project = projectMapper.selectById(id);
        int row = projectMapper.updateProject(project);
        return row;
    }

    @Override
    public List<Project> queryByEngineerName(String ename) {
        List<Engineer> engineers = engineerService.queryByName(ename);
        List<Project> projects = projectMapper.selectAllProjectT();
        List<Project> selected = new ArrayList<Project>();
        for(Project project1:projects){
            for(Engineer engineer1:engineers){
                if(engineer1.getUserId().equals(project1.getUserId())){
                    selected.add(project1);
                }
            }
        }
        return selected;
    }

    //更新状态
    @Override
    public int updateStateById(String id) {
        return projectMapper.updateProjectState(id);
    }

    @Override
    public List<Project> queryByEngineerId(String uid) {
        List<Project> projects = projectMapper.selectByEngineerId(uid);
        return projects;
    }

    private void getRightId(Project project){
        List<Project> projects = projectMapper.selectAllProject();
        if(projects.size()==0){
            project.setProId("1");
        }
        else {
            ArrayList<Integer> iid = new ArrayList<Integer>();
            //最后一个元素
            for (Project project1 : projects) {
                iid.add(new Integer(project1.getProId()));
            }
            //排序从小到大
            Collections.sort(iid);
            int id1 = iid.get(iid.size() - 1) + 1;

            String id = String.valueOf(id1);
            project.setProId(id);
        }

    }
}
