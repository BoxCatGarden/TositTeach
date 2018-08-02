package com.tositteach.service.impl;

import com.tositteach.domain.mapper.ClazzMapper;
import com.tositteach.domain.entity.Clazz;
import com.tositteach.service.ClazzService;
import com.tositteach.util.PagingBody;
import com.tositteach.util.YearIdBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService{
    @Resource
    private ClazzMapper clazzMapper;

    @Override
    public PagingBody query(int st, int nm) {
        PagingBody body = new PagingBody();
        body.setTotal(clazzMapper.total());
        body.setData(clazzMapper.query(st, nm));
        return body;
    }

    @Override
    public int add(String claName, String room, String engId) {
        Clazz cla = new Clazz();
        cla.setClaName(claName);
        cla.setRoom(room);
        cla.setUserId(engId);
        synchronized (this) {
            cla.setClaId(YearIdBuilder.build(clazzMapper.getMaxId()));
            return clazzMapper.add(cla);
        }
    }

    @Override
    public int del(List<String> claIds) {
        return clazzMapper.del(claIds);
    }

    @Override
    public int addstu(String claId, List<String> stuIds) {
        return clazzMapper.addstu(claId, stuIds);
    }


}
