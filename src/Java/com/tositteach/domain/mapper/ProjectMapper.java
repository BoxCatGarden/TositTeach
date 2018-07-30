package com.tositteach.domain.mapper;

import com.tositteach.domain.entity.Project;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProjectMapper {
    public List<Project> selectAllProject();
    public int insertProject(Project project);
    public int deleteProject(@Param("projId") String id);
    public int updateProject(Project project);
}
