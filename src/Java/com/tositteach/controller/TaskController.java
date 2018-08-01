package com.tositteach.controller;

import com.tositteach.domain.entity.Task;
import com.tositteach.service.TaskService;
import com.tositteach.util.PagingBody;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
@RequestMapping("/task")
public class TaskController {
    @Resource
    TaskService taskService;

    //获取任务列表，分页
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public PagingBody query(@RequestParam(value = "ei", required = false, defaultValue = "")String engId,
                            @RequestParam(value = "st", required = false, defaultValue = "0")int st,
                            @RequestParam(value = "nm", required = false, defaultValue = "10")int nm){
        if (engId.length() == 0) engId = null;
        if (st < 0) st = 0;
        if (nm < 0) nm = 10;
//        String taskId;
//        List<Task> listTask = taskService.queryAllTask();
//        return listTask;
        return null;
    }

    //根据任务id获取任务详情
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public Task get(@RequestParam(value = "ti", required = true) String tasId){
//        Task task = taskService.queryOneTaskById(taskId);
//        return task; //乱写的返回
        return null;
    }

    //添加任务
    @RequestMapping(value ="/add", method = RequestMethod.POST)
    @ResponseBody
    public String add(@RequestBody Task task){
        return "id";
    }

    //修改教学计划
    @RequestMapping(value = "/mod", method = RequestMethod.POST)
    @ResponseBody
    public int mod(@RequestBody Task task){  //该实体中需要ajax中传入data：taskId、taskPlan
        return 0;
    }

    //删除任务
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public int del(@RequestBody Task task){  //tasId
        return 0;
    }

    //已废置
    /*@RequestMapping(value = "/getENameByTaskId.html")
    @ResponseBody
    public String getENameByTaskId(String taskId){
        Task task = taskService.queryOneTaskById(taskId);
        String engiId = task.getTaskUser();
        Engineer engineer = engineerService.queryById(engiId);
        return engineer.getEngiName();
    }*/
}