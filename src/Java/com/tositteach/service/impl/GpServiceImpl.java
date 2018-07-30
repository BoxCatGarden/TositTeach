package com.tositteach.service.impl;

import com.tositteach.domain.mapper.GpMapper;
import com.tositteach.domain.entity.Gp;
import com.tositteach.service.GpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GpServiceImpl implements GpService {
    @Autowired
    GpMapper gpMapper;
    @Override
    public List<Gp> queryAllGp() {
        return gpMapper.selectAllGp();
    }

    @Override
    public int insertOneGroup(Gp gp) {
        int row = 0;
        getRightId(gp);
        Map<String, Object> test = new HashMap<>();
        test.put("gpGroId", gp.getGpGroId());
        test.put("gpClaId", gp.getGpClaId());
        test.put("gpGroName", gp.getGpGroName());
        test.put("gpProId", gp.getGpProId());
        row = gpMapper.insertGroup(test);
        return row;
    }

    private void getRightId(Gp gp) {
        List<Gp> gps = gpMapper.selectByClaId(gp.getGpClaId());
        if(gps.size()==0){
            gp.setGpGroId("1");
        }
        else {
            ArrayList<Integer> iid = new ArrayList<Integer>();
            //最后一个元素
            for (Gp gp1 : gps) {
                iid.add(new Integer(gp1.getGpGroId()));
            }
            //排序从小到大
            Collections.sort(iid);
            int id1 = iid.get(iid.size() - 1) + 1;
            System.out.println(id1);
            String id = String.valueOf(id1);
            gp.setGpGroId(id);
        }
    }

    @Override
    public Gp queryGpById(String cid, String gid) {
        List<Gp> gps = gpMapper.selectAllGp();
        for (Gp gp : gps){
            if(gp.getGpClaId().equals(cid)&&gp.getGpGroId().equals(gid)){
                return gp;
            }
        }
        return null;
    }

    @Override
    public List<Gp> queryGpById(String cid) {
        return gpMapper.selectByClaId(cid);
    }

    @Override
    public List<Gp> queryGpByName(String name) {
        return gpMapper.selectByName(name);
    }
}
