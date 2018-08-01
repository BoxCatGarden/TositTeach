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
        test.put("gpGroId", gp.getGroId());
        test.put("gpClaId", gp.getClaId());
        test.put("gpGroName", gp.getGroName());
        test.put("gpProId", gp.getProId());
        row = gpMapper.insertGroup(test);
        return row;
    }

    private void getRightId(Gp gp) {
        List<Gp> gps = gpMapper.selectByClaId(gp.getClaId());
        if(gps.size()==0){
            gp.setGroId("1");
        }
        else {
            ArrayList<Integer> iid = new ArrayList<Integer>();
            //最后一个元素
            for (Gp gp1 : gps) {
                iid.add(new Integer(gp1.getGroId()));
            }
            //排序从小到大
            Collections.sort(iid);
            int id1 = iid.get(iid.size() - 1) + 1;
            System.out.println(id1);
            String id = String.valueOf(id1);
            gp.setGroId(id);
        }
    }

    @Override
    public Gp queryGpById(String cid, String gid) {
        List<Gp> gps = gpMapper.selectAllGp();
        for (Gp gp : gps){
            if(gp.getClaId().equals(cid)&&gp.getGroId().equals(gid)){
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

    @Override
    public int deleteOneByClaId(String id) {
        int row = gpMapper.deleteByClaId(id);
        return row;
    }

    @Override
    public String queryByName(String cid, String name) {
        Gp gp= gpMapper.selectByClaidName(cid,name);
        return gp.getGroId();
    }
}
