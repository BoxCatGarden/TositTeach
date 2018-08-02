package com.tositteach.service.impl;

import com.tositteach.domain.mapper.StudentMapper;
import com.tositteach.domain.entity.Student;
import com.tositteach.service.StudentService;
import com.tositteach.util.PagingBody;
import com.tositteach.util.YearIdBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{
    @Resource
    StudentMapper studentMapper;

    @Override
    public PagingBody query(String claId, int st, int nm) {
        PagingBody body = new PagingBody();
        body.setTotal(studentMapper.total(claId));
        body.setData(studentMapper.query(claId, st, nm));
        return body;
    }

    @Override
    public Student get(String stuId) {
        return studentMapper.get(stuId);
    }

    @Override
    public int add(String school, String id,
                   String name, byte sex, String grade) {
        Student stu = new Student();
        stu.setSchool(school);
        stu.setId(id);
        stu.setName(name);
        stu.setSex(sex);
        stu.setGrade(grade);
        synchronized (this) {
            stu.setUserId(YearIdBuilder.build(studentMapper.getMaxId()));
            return studentMapper.add(stu);
        }
    }

}
