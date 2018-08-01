package com.tositteach.service;

import com.tositteach.domain.entity.Clazz;

import java.util.List;

public interface ClazzService {

    //查询clazz表中所有信息
    public List<Clazz> queryAllClazz();

    //根据班级id查询相关班级信息
    public Clazz queryClazzById(String clazId);

    //根据选中班级的cla_id删除班级信息
    public Integer removeClazz(String clazId);

    //增加新的班级及其信息，返回添加条数
    public Integer addClazz(Clazz clazz);

    public void getRightId(Clazz clazz);

}
