package com.tositteach.service;

import com.tositteach.domain.entity.Student;
import com.tositteach.util.PagingBody;

import java.sql.Struct;
import java.util.List;
public interface StudentService {
    PagingBody query(String claId, int st, int nm);
    int add(String school, String id, String name, byte sex, String grade);
    //del
    //mod

    public List<Student> queryAllStudents();
    public Integer addStudent(Student student);
    public Integer addStudentClass(Student student);
    public Integer addStudentGroup(Student student);
    public List<Student> queryStudentsByClassId(String studClas);
    public List<Student> queryStudentNoClass();
}
