package com.tositteach.service.impl;
import com.tositteach.domain.entity.Task;

import com.tositteach.domain.mapper.TaskMapper;
import com.tositteach.service.TaskService;
import com.tositteach.util.PagingBody;
import com.tositteach.util.YearIdBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Service
@Transactional
public class TaskServiceImpl implements TaskService{
    @Resource
    private TaskMapper taskMapper;

    @Override
    public PagingBody query(String engId, int st, int nm) {
        PagingBody body = new PagingBody();
        body.setTotal(taskMapper.total(engId));
        body.setData(taskMapper.query(engId, st, nm));
        return body;
    }

    @Override
    public Task get(String tasId) {
        return taskMapper.get(tasId);
    }

    @Override
    public String add(String tasName,
                      String stTime, String edTime,
                      String disp,
                      String engId) {
        Task task = new Task();
        task.setTasName(tasName);
        task.setStTime(stTime);
        task.setEdTime(edTime);
        task.setDisp(disp);
        task.setUserId(engId);
        synchronized (this) {
            String tasId = YearIdBuilder.build(taskMapper.getMaxId());
            task.setTasId(tasId);
            if (taskMapper.add(task) != 0) return tasId;
        }
        return null;
    }

    @Override
    public int mod(String tasId, String plan) {
        return taskMapper.setPlan(tasId, plan);
    }

    @Override
    public int del(String tasId) {
        return taskMapper.del(tasId);
    }


}
