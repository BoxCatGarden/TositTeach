package com.tositteach.domain.mapper;

import com.tositteach.domain.entity.Clazz;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClazzMapper {

    int total();
    List<Clazz> query(@Param("st") int st, @Param("nm") int nm);

    String getMaxId();
    int add(Clazz cla);

    int del(@Param("cis")List<String> claIds);

    int addstu(@Param("ci")String claId,
               @Param("sis")List<String> stuIds);
}
