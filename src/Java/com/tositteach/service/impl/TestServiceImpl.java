package com.tositteach.service.impl;

import com.tositteach.domain.mapper.TestMapper;
import com.tositteach.domain.entity.Stu;
import com.tositteach.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TestServiceImpl implements TestService {
    @Autowired
    TestMapper testMapper;
    @Override
    public List<Stu> queryAll() {
        return testMapper.selectAll();
    }
}
