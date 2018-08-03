package com.tositteach.service.impl;

import com.tositteach.domain.mapper.EngineerMapper;
import com.tositteach.domain.entity.Engineer;
import com.tositteach.domain.entity.Project;
import com.tositteach.service.EngineerService;
import com.tositteach.service.ProjectService;
import com.tositteach.util.PagingBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
@Service
@Transactional
public class EngineerServiceImpl implements EngineerService {
    @Resource
    private EngineerMapper engineerMapper;

    @Override
    public PagingBody query(int st, int nm) {
        PagingBody body = new PagingBody();
        body.setTotal(engineerMapper.total());
        body.setData(engineerMapper.query(st, nm));
        return body;
    }

}
