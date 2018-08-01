package com.tositteach.domain.mapper;

import com.tositteach.domain.entity.Project;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProjectMapper {
    public List<Project> selectAllProjectS();
    public List<Project> selectAllProjectT();
    public List<Project> selectAllProjectM();
    public List<Project> selectAllProject();
    public List<Project> selectByEngineerId(@Param("projUserId") String uid);
    public int insertProject(Project project);
    public int deleteProject(@Param("projId") String id);
    public int updateProject(Project project);
    public int updateProjectState(@Param("projId") String id);
    public Project selectById(@Param("projId") String id);
}
