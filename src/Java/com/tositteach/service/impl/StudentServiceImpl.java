package com.tositteach.service.impl;

import com.tositteach.domain.mapper.StudentMapper;
import com.tositteach.domain.entity.Student;
import com.tositteach.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{
    @Autowired
    StudentMapper studentMapper;

    @Override
    public List<Student> queryAllStudents(){
        return studentMapper.selectAllStudents();
    }

    @Override
    public Integer addStudent(Student student) {
        return studentMapper.insertStudent(student);
    }

    @Override
    public Integer addStudentClass(Student student){
        return studentMapper.updateStudentClass(student);
    }

    @Override
    public Integer addStudentGroup(Student student){ return studentMapper.updateStudentGroup(student);}

    @Override
    public List<Student> queryStudentsByClassId(String studClas){return studentMapper.selectStudentsByClassId(studClas);}

    @Override
    public List<Student> queryStudentNoClass(){return studentMapper.selectStudentNoClass();}

}
