package com.tositteach.domain.mapper;

import com.tositteach.domain.entity.Gp;
import com.tositteach.domain.entity.StuDoc;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StuDocMapper {

    int total(@Param("dn") String docName,
              @Param("gn") String groName,
              @Param("pn") String proName);
    List<StuDoc> query(@Param("dn") String docName,
                       @Param("gn") String groName,
                       @Param("pn") String proName,
                       @Param("st") int st, @Param("nm") int nm);

    StuDoc get(@Param("di") String docId);

    Gp getStuGp(@Param("si")String stuId);
    String getMaxId();
    int add(StuDoc doc);

    int setUrl(@Param("di")String docId, @Param("url")String url);

    int del(@Param("di")String docId);

    int setScore(@Param("di")String docId, @Param("sc")byte score);
}
