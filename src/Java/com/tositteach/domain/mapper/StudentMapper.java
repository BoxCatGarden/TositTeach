package com.tositteach.domain.mapper;

import com.tositteach.domain.entity.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentMapper {
    public List<Student> selectAllStudents();

    public Integer insertStudent(Student student);

    public Integer updateStudentClass(Student student);

    public Integer updateStudentGroup(Student student);

    public List<Student> selectStudentsByClassId(@Param("studClas") String studClas);

    public List<Student> selectStudentNoClass();

    public Integer insertInfoBatch(List<Student> students);

}
