package com.tositteach.domain.mapper;

import com.tositteach.domain.entity.Clazz;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClazzMapper {


    //查询班级表中所有信息
    public List<Clazz> selectAllClazz();

    //根据选中班级的cla_id删除班级信息
    public Integer deleteClazz(@Param("clazId") String clazId);

    //增加新的班级及其信息，返回添加条数
    public Integer insertClazz(Clazz clazz);



}
