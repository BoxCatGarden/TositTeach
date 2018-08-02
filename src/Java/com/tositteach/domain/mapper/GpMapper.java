package com.tositteach.domain.mapper;

import com.tositteach.domain.entity.Gp;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface GpMapper {

    Byte getMaxId(@Param("ci")String claId);
    int add(Gp gp);

    int addstu(@Param("ci")String claId, @Param("gi")byte groId,
               @Param("sis")List<String>stuIds);
}
