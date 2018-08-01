package com.tositteach.domain.mapper;

import org.apache.ibatis.annotations.Param;
import com.tositteach.domain.entity.Task;
import com.tositteach.domain.entity.User;

import java.util.List;

public interface TaskMapper {
    public List<Task> selectAllTasks();

    public Integer insertTask(Task task);

    public Task selectOnetask(@Param("taskId") String taskId);

    public Integer updateTaskPlan(Task task);
}
