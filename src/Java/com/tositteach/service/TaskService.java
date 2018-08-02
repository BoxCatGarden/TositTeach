package com.tositteach.service;

import com.tositteach.domain.entity.Task;
import com.tositteach.util.PagingBody;

import java.util.List;

public interface TaskService {
    PagingBody query(String engId, int st, int nm);
    Task get(String tasId);
    String add(String tasName, String stTime, String edTime, String disp, String engId);
    int mod(String tasId, String plan);
    int del(String tasId);

    public List<Task> queryAllTask();
    public Integer addTask(Task task);
    public Task queryOneTaskById(String taskId);
    public Integer modifyTaskPlan(Task task);
    public void getRightId(Task task);
}
