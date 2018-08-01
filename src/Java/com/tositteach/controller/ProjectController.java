package com.tositteach.controller;

import com.tositteach.domain.entity.Engineer;
import com.tositteach.domain.entity.Project;
import com.tositteach.service.EngineerService;
import com.tositteach.service.ProjectService;
import com.tositteach.util.PagingBody;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Properties;

@Controller
@RequestMapping("/pro")
public class ProjectController {
    @Resource
    ProjectService projectService;

    //显示项目列表, state=0（待审批）|1（通过）|2（未通过）|3（全部）
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public PagingBody query(@RequestParam(value = "s", required = false, defaultValue = "1")int state,
                            @RequestParam(value = "pn", required = false)String proName,
                            @RequestParam(value = "en", required = false)String engName,
                            @RequestParam(value = "ei", required = false)String engId,
                            @RequestParam(value = "st", required = false, defaultValue = "0")int st,
                            @RequestParam(value = "nm", required = false, defaultValue = "10")int nm){
        if (state < 0 || 3 < state) state = 1;
        if (st < 0) st = 0;
        if (nm < 0) nm = 10;
        return null;
    }

    //获取项目详情
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public Project get(@RequestParam(value = "pi")String proId){
        /*Project project = projectService.queryById(id);
        Engineer engineer = engineerService.queryByProId(id);
        Doc_engineer doc_engineer = doc_engineerService.queryAllByProjId(id);
        String res = project.getProjName()+","
                +engineer.getEngiName()+","
                +project.getProjStti()+","
                +project.getProjEdti()+","
                +project.getProjDisp()+","
                +doc_engineer.getDoceUrl();
*/
        return null;
    }

    //添加新项目，返回项目id
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String add(@RequestBody Project project){
       return "id";
    }

    //删除项目
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public int del(@RequestBody Project project){ //proId
        return 0;
    }

    //审批项目
    @RequestMapping(value = "/check", method = RequestMethod.POST)
    @ResponseBody
    public int check(@RequestBody Project pro){ //pro_id,state
        if (pro.getProjId() == null || pro.getProjStat() < 1 || 2 < pro.getProjStat()) return 0;
        return 0;
    }

    //修改项目信息
    @RequestMapping(value = "/mod", method = RequestMethod.POST)
    @ResponseBody
    public int mod(@RequestBody Project pro){
        return 0;
    }

}
