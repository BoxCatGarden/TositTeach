package com.tositteach.domain.mapper;

import com.tositteach.domain.entity.EngDoc;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EngDocMapper {

    EngDoc get(@Param("di")String docId);

    String getMaxId();
    int add(EngDoc doc);

    int setUrl(@Param("di")String docId, @Param("url")String url);

    int del(@Param("di")String docId);

}
