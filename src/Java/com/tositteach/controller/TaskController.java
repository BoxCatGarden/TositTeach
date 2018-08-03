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
    private TaskService taskService;

    //获取任务列表，分页
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public PagingBody query(@RequestParam(value = "ei", required = false)String engId,
                            @RequestParam(value = "st", required = false, defaultValue = "0")int st,
                            @RequestParam(value = "nm", required = false, defaultValue = "10")int nm){
        if (st < 0) st = 0;
        if (nm < 0) nm = 10;
        return taskService.query(engId, st, nm);
    }

    //根据任务id获取任务详情
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public Task get(@RequestParam(value = "ti") String tasId){
        Task task = taskService.get(tasId);
        return task!=null?task:new Task();
    }

    //添加任务
    @RequestMapping(value ="/add", method = RequestMethod.POST)
    @ResponseBody
    public String add(@RequestBody Task task){
        if (task.getTasName()==null||task.getStTime()==null||task.getEdTime()==null||task.getDisp()==null||task.getUserId()==null)
            return "";
        String re = taskService.add(task.getTasName(), task.getStTime(), task.getEdTime(), task.getDisp(), task.getUserId());
        return re!=null?re:"";
    }

    //修改教学计划
    @RequestMapping(value = "/mod", method = RequestMethod.POST)
    @ResponseBody
    public int mod(@RequestBody Task task){  //该实体中需要ajax中传入data：taskId、taskPlan
        if (task.getTasId()==null||task.getPlan()==null) return 0;
        return taskService.mod(task.getTasId(), task.getPlan());
    }

    //删除任务
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public int del(@RequestBody Task task){  //tasId
        if (task.getTasId()==null || task.getTasId().length()!=11)return 0;
        return taskService.del(task.getTasId());
    }

    //已废置
    /*@RequestMapping(value = "/getENameByTaskId.html")
    @ResponseBody
    public String getENameByTaskId(String taskId){
        Task task = taskService.queryOneTaskById(taskId);
        String engiId = task.getUserId();
        Engineer engineer = engineerService.queryById(engiId);
        return engineer.getName();
    }*/
}
