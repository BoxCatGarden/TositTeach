package com.tositteach.service.impl;

import com.tositteach.domain.mapper.ProjectMapper;
import com.tositteach.domain.entity.Project;
import com.tositteach.service.ProjectService;
import com.tositteach.util.DateChecker;
import com.tositteach.util.PagingBody;
import com.tositteach.util.YearIdBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {
    @Resource
    private ProjectMapper projectMapper;

    @Override
    public PagingBody query(int state,
                            String proName,
                            String engName,
                            String engId,
                            int hasGroup, int st, int nm) {
        PagingBody body = new PagingBody();
        body.setTotal(projectMapper.total(state, proName, engName, engId, hasGroup));
        body.setData(projectMapper.query(state, proName, engName, engId, hasGroup, st, nm));
        return body;
    }

    @Override
    public Project get(String proId) {
        return projectMapper.get(proId);
    }

    @Override
    public String add(String proName,
                      String stTime, String edTime, String disp,
                      String engId) {
        Project pro = new Project();
        pro.setProName(proName);
        pro.setStTime(stTime);
        pro.setEdTime(edTime);
        pro.setDisp(disp);
        pro.setUserId(engId);
        synchronized (this) {
            String proId = YearIdBuilder.build(projectMapper.getMaxId());
            pro.setProId(proId);
            if (projectMapper.add(pro) != 0) return proId;
        }
        return null;
    }

    @Override
    public int del(String proId) {
        return projectMapper.del(proId);
    }

    @Override
    public int check(String proId, byte state) {
        return projectMapper.setState(proId, state);
    }

    @Override
    public int mod(String proId,
                   String proName,
                   String edTime, String disp) {
        Project pro = projectMapper.get(proId);
        if (pro == null) return 0;
        if (!DateChecker.valiDuration(pro.getStTime(),edTime)) return 0;
        pro.setProName(proName);
        pro.setEdTime(edTime);
        pro.setDisp(disp);
        return projectMapper.mod(pro);
    }

}
