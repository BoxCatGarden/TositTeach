package com.tositteach.domain.mapper;

import com.tositteach.domain.entity.Project;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProjectMapper {
    int total(@Param("s")int state,
              @Param("pn")String proName,
              @Param("en")String engName,
              @Param("ei")String engId,
              @Param("hg")int hasGroup);
    List<Project> query(@Param("s")int state,
                        @Param("pn")String proName,
                        @Param("en")String engName,
                        @Param("ei")String engId,
                        @Param("hg")int hasGroup,
                        @Param("st") int st, @Param("nm") int nm);

    Project get(@Param("pi")String proId);

    String getMaxId();
    int add(Project pro);

    int del(@Param("pi")String proId);

    int setState(@Param("pi")String proId, @Param("s")byte state);

    int mod(Project pro);
}
