package com.tositteach.domain.mapper;

import com.tositteach.domain.entity.Engineer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EngineerMapper {
    int total();
    List<Engineer> query(@Param("st") int st, @Param("nm") int nm);
}
