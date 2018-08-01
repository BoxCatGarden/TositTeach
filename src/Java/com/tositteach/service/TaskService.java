package com.tositteach.service;

import com.tositteach.domain.entity.Task;
import java.util.List;

public interface TaskService {
    public List<Task> queryAllTask();
    public Integer addTask(Task task);
    public Task queryOneTaskById(String taskId);
    public Integer modifyTaskPlan(Task task);
    public void getRightId(Task task);
}
