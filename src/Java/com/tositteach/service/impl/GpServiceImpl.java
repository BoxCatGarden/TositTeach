package com.tositteach.service.impl;

import com.tositteach.domain.mapper.GpMapper;
import com.tositteach.domain.entity.Gp;
import com.tositteach.service.GpService;
import com.tositteach.util.YearIdBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class GpServiceImpl implements GpService {
    @Resource
    private GpMapper gpMapper;

    @Override
    public byte create(String groName, String claId, String proId) {
        Gp gp = new Gp();
        gp.setClaId(claId);
        gp.setGroName(groName);
        gp.setProId(proId);
        synchronized (this) {
            byte groId = (byte)(gpMapper.getMaxId(claId)+1);
            gp.setGroId(groId);
            if (gpMapper.add(gp) != 0) return groId;
        }
        return -1;
    }

    @Override
    public int addStuInto(String claId, byte groId, List<String> stuIds) {
        return gpMapper.addstu(claId, groId, stuIds);
    }

}
