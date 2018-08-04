package com.tositteach.domain.mapper;

import com.tositteach.domain.entity.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentMapper {

    int total(@Param("ci")String claId);
    List<Student> query(@Param("ci")String claId,
                        @Param("st") int st, @Param("nm") int nm);

    //Student get(@Param("si")String stuId);

    String getMaxId();
    int add(Student stu);

}
