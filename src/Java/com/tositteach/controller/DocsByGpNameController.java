package com.tositteach.controller;

import com.tositteach.domain.entity.Doc_student;
import com.tositteach.domain.entity.Gp;
import com.tositteach.service.Doc_studentService;
import com.tositteach.service.GpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DocsByGpNameController {
    @Autowired
    Doc_studentService doc_studentService;
    @Autowired
    GpService GpService;

    //根据小组名称搜索相关文档信息
    @RequestMapping("/viewDoc_studentByGpName.html")
    public ModelAndView viewDoc_studentByGpName(String gpGroName) {
        ModelAndView viewDoc_studentByGpName = new ModelAndView();

        List<Doc_student> select = new ArrayList<Doc_student>();
        List<Doc_student> doc_students = doc_studentService.queryAllDoc_student();//
        List<Gp> gps = GpService.queryGpByName("一班第二组");//

        for(int i = 0; i < gps.size(); i++) {
            for (int j = 0; j < doc_students.size(); j++) {
                if ((doc_students.get(j).getDocsClaId().equals(gps.get(i).getGpClaId())) && (doc_students.get(j).getDocsGroId().equals(gps.get(i).getGpGroId()))) {
                    select.add(doc_students.get(j));
                }
            }
        }

        /*System.out.println(select.toString());*/
        for(Doc_student d:select){
            System.out.println(d.getDocsClaId()+" "+d.getDocsName());
        }
        viewDoc_studentByGpName.setViewName("test");
        return viewDoc_studentByGpName;
    }

}
