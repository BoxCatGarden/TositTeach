package com.tositteach.controller;

import com.tositteach.domain.entity.Project;
import com.tositteach.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ProjectController {
    @Autowired
    ProjectService projectService;

    @RequestMapping(value = "/showAllProject.html")
    public ModelAndView showAllProject(){
        ModelAndView modelAndView = new ModelAndView();
        List<Project> projects = projectService.queryAllProject();
        System.out.println(projects.toString());
        modelAndView.addObject("project1",projects);
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(value = "/insertProject.html")
    public @ResponseBody Boolean insertProject(Project project){
       Boolean res = false;
       int row = projectService.insertOneProject(project);
       if(row>0){
           res = true;
       }
       return res;
    }

    @RequestMapping(value = "/deleteProject.html")
    public @ResponseBody Boolean deleteProject(String id){
        Boolean res = false;
        int row = projectService.deleteOneProjectById(id);
        if(row>0){
            res = true;
        }
        return res;
    }

    @RequestMapping(value = "/updateProject.html")
    public @ResponseBody Boolean updateProject(Project project){
        Boolean res = false;
        int row = projectService.updateOneProjectById(project);
        if(row>0){
            res = true;
        }
        return res;
    }

}
