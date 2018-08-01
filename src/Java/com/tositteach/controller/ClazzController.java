package com.tositteach.controller;

import com.tositteach.domain.entity.Clazz;
import com.tositteach.domain.entity.Engineer;
import com.tositteach.domain.entity.Student;
import com.tositteach.service.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ClazzController {
    @Autowired
    ClazzService clazzService;
    @Autowired
    StudentService studentService;
    @Autowired
    UserService userService;
    @Autowired
    GpService gpService;
    @Autowired
    EngineerService engineerService;

    //查询clazz表中所有信息
    @RequestMapping("/viewClazz.html")
    public @ResponseBody List<Clazz> viewClazz() {
        List<Clazz> clazzs = clazzService.queryAllClazz();

        return clazzs;
    }

    //根据选中班级的cla_id删除班级信息
    @RequestMapping("/removeClazz.html")
    public @ResponseBody String removeClazz(@Param("clazId") String clazId) {
        String res = "删除失败";

        List<Student> listStudent = studentService.queryStudentsByClassId(clazId);
        for(int i=0;i<listStudent.size();i++){
            userService.cutUserById(listStudent.get(i).getStudUser());
        }

        gpService.deleteOneByClaId(clazId);

        int row = clazzService.removeClazz(clazId);
        if (row > 0) {
            res = "删除成功";
        }
        return res;
    }

    //增加新的班级及其信息，返回添加条数
    @RequestMapping("/addClazz.html")
    public @ResponseBody String addClazz(Clazz clazz) {
        String res = "添加失败";
        clazzService.getRightId(clazz);//获取id
        int row = clazzService.addClazz(clazz);
        if (row > 0) {
            res = "添加成功";
        }
        return res;
    }

    //根据班级编号，找到工程师名字
    @RequestMapping("/getEngineerNameByClaUserId.html")
    public @ResponseBody String getEngineerNameByClaUserId(@Param("clazId") String clazId) {
        String res = "查询失败";
        Engineer engineer;

        String select = engineerService.queryById(clazzService.queryClazzById(clazId).getClazUserId()).getEngiName();

        if (select != null) {
            res.equals(select);
        }
        return res;
    }

    //根据班级id，获得班级名称
    @RequestMapping("/getClaNameByClaId.html")
    public @ResponseBody String getClaNameByClaId(@Param("clazId") String clazId) {
        String res = "查询失败";

        String select = clazzService.queryClazzById(clazId).getClazName();

        if (select != null) {
            res.equals(select);
        }
        return res;
    }

}
