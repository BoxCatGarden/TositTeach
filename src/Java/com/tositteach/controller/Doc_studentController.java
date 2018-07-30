package com.tositteach.controller;

import com.tositteach.domain.entity.Doc_student;
import com.tositteach.service.Doc_studentService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;

import static com.oracle.jrockit.jfr.ContentType.Timestamp;

@Controller
public class Doc_studentController {
    @Autowired
    Doc_studentService doc_studentService;


    //查询doc_student表中所有信息
    @RequestMapping("/viewDoc_student.html")
    public ModelAndView viewDoc_sttudent() {
        ModelAndView viewDoc_student = new ModelAndView();
        List<Doc_student> doc_students = doc_studentService.queryAllDoc_student();
        viewDoc_student.addObject("doc_student",doc_students);
        viewDoc_student.setViewName("viewDoc_student");
        return viewDoc_student;
    }

    //按文档名称搜索，列出信息
    @RequestMapping("/viewDoc_studentByName.html")
    public ModelAndView viewDoc_studentByName(String docsName) {
        ModelAndView viewDoc_studentByName = new ModelAndView();
        List<Doc_student> doc_students = doc_studentService.queryDoc_studentName(docsName);
        viewDoc_studentByName.addObject("doc_student",doc_students);
        viewDoc_studentByName.setViewName("viewDoc_studentByName");
        return viewDoc_studentByName;
    }
//
//    //按项目名称搜索，列出信息
//    //这里是根据项目id来选择，需要根据项目名称联系到项目id（project表）
//    @RequestMapping("viewDoc_studentProj")
//    public ModelAndView viewDoc_studentProj() {
//        ModelAndView viewDoc_studentProj = new ModelAndView();
//        List<Doc_student> doc_studentProjs = Doc_studentService.queryDoc_studentProj();
//        viewDoc_studentProj.addObject("doc_studentProj",doc_studentProjs);
//        viewDoc_studentProj.setViewName("viewDoc_studentProj");
//        return viewDoc_studentProj;
//    }
//

    //下载获取url
    @RequestMapping("/downloadDoc_student.html")
    public @ResponseBody String queryDoc_student(Doc_student doc_student) {
        String res = "获取失败";

        String url = doc_studentService.queryDoc_student(doc_student);
        if (url != null) {
            res = url;
        }
        return res;
    }

    //删除文档
    @RequestMapping("/removeDoc_student.html")
    public @ResponseBody String removeDoc_student(@Param("docsId") String docsId) {
        String res = "删除失败";

        int row = doc_studentService.removeDoc_student(docsId);
        if (row > 0) {
            res = "删除成功";
        }
        return res;
    }

    //修改文档分数
    @RequestMapping("modifyDoc_studentScore.html")
    public @ResponseBody String modifyDoc_studentScore(Doc_student doc_student) {
        String res = "修改失败";

        int row = doc_studentService.modifyDoc_studentScore(doc_student);
        if (row > 0) {
            res = "修改成功";
        }
        return res;
    }

    //修改已上传文档
    @RequestMapping("modifyDoc_studentUrl.html")
    public @ResponseBody String modifyDoc_studentUrl(Doc_student doc_student) {
        String res = "修改失败";

        int row = doc_studentService.modifyDoc_studentUrl(doc_student);
        if (row > 0) {
            res = "修改成功";
        }
        return res;
    }

    //文档添加
    @RequestMapping("addDoc_student.html")
    public @ResponseBody String addDoc_student(Doc_student doc_student) {
        String res = "添加失败";

        //Timestamp time = new Timestamp(System.currentTimeMillis());

        doc_studentService.getRightId(doc_student);

        int row = doc_studentService.addDoc_student(doc_student);
        if (row > 0) {
            res = "添加成功";
        }
        return res;
    }

    //初次上传文档
    @RequestMapping("modifyDoc_studentFurl.html")
    public @ResponseBody String modifyDoc_studentFurl(Doc_student doc_student) {
        String res = "上传失败";

        int row = doc_studentService.modifyDoc_studentFurl(doc_student);
        if (row > 0) {
            res = "上传成功";
        }
        return res;
    }


}
