package com.tositteach.service.impl;
import com.tositteach.domain.entity.Task;

import com.tositteach.domain.mapper.TaskMapper;
import com.tositteach.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Service
public class TaskServiceImpl implements TaskService{
    @Autowired
    TaskMapper taskMapper;
    @Override
    public List<Task> queryAllTask(){
        return taskMapper.selectAllTasks();
    }

    @Override
    public Integer addTask(Task task){
        getRightId(task);
        return taskMapper.insertTask(task);
    }

    @Override
    public Task queryOneTaskById(String taskId){
        return taskMapper.selectOnetask(taskId);
    }

    @Override
    public Integer modifyTaskPlan(Task task){return taskMapper.updateTaskPlan(task);}

    @Override
    public void getRightId(Task task){
        List<Task> tasks = taskMapper.selectAllTasks();
        if(tasks.size()==0){
            task.setTasId("1");
        }
        else {
            ArrayList<Integer> iid = new ArrayList<Integer>();
            //最后一个元素
            for (Task task1 : tasks) {
                iid.add(new Integer(task1.getTasId()));
            }
            //排序从小到大
            Collections.sort(iid);
            int id1 = iid.get(iid.size() - 1) + 1;
            System.out.println(id1 + "\n\n\n\n\n\n");
            String id = String.valueOf(id1);
            task.setTasId(id);
        }
    }

}
