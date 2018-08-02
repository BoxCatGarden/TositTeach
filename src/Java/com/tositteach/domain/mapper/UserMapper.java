package com.tositteach.domain.mapper;

import com.tositteach.domain.entity.Engineer;
import com.tositteach.domain.entity.Student;
import com.tositteach.domain.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    String getPwd(@Param("ui") String userId);
    User getUser(@Param("ui") String userId);

    int setPwd(@Param("ui") String userId,
               @Param("op") String op, @Param("np") String np);

    Student getStuInfo(@Param("ui") String stuId);

    Engineer getEngInfo(@Param("ui") String engId);

}
