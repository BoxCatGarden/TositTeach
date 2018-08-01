package com.tositteach.service;

import com.tositteach.domain.entity.Student;

import java.sql.Struct;
import java.util.List;
public interface StudentService {
    public List<Student> queryAllStudents();
    public Integer addStudent(Student student);
    public Integer addStudentClass(Student student);
    public Integer addStudentGroup(Student student);
    public List<Student> queryStudentsByClassId(String studClas);
    public List<Student> queryStudentNoClass();
}
