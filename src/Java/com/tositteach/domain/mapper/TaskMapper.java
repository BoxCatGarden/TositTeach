package com.tositteach.domain.mapper;

import org.apache.ibatis.annotations.Param;
import com.tositteach.domain.entity.Task;
import com.tositteach.domain.entity.User;

import java.util.List;

public interface TaskMapper {

    int total(@Param("ei") String engId);
    List<Task> query(@Param("ei") String engId,
              @Param("st") int st, @Param("nm") int nm);

    Task get(@Param("ti") String tasId);

    String getMaxId();
    int add(Task task);

    int setPlan(@Param("ti") String tasId, @Param("pl") String plan);

    int del(@Param("ti") String tasId);
}
