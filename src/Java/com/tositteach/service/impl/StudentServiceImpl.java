package com.tositteach.service.impl;

import com.tositteach.domain.mapper.StudentMapper;
import com.tositteach.domain.entity.Student;
import com.tositteach.service.StudentService;
import com.tositteach.util.PagingBody;
import com.tositteach.util.YearIdBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Calendar;

@Service
@Transactional
public class StudentServiceImpl implements StudentService{
    @Resource
    private StudentMapper studentMapper;

    @Override
    public PagingBody query(String claId, int st, int nm) {
        PagingBody body = new PagingBody();
        body.setTotal(studentMapper.total(claId));
        body.setData(studentMapper.query(claId, st, nm));
        return body;
    }

    /*@Override
    public Student get(String stuId) {
        return studentMapper.get(stuId);
    }*/

    @Override
    public int add(String school, String id,
                   String name, byte sex, int grade) {
        Student stu = new Student();
        stu.setSchool(school);
        stu.setId(id);
        stu.setName(name);
        stu.setSex(sex);
        stu.setGrade(grade2Year(grade));
        synchronized (this) {
            stu.setUserId(YearIdBuilder.build(studentMapper.getMaxId(),"01","0000000"));
            return studentMapper.add(stu);
        }
    }

    private String grade2Year(int grade) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        return ""+(month>7?year-grade:year-grade-1);
    }

}
