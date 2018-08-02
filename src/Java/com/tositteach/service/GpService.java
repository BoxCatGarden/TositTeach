package com.tositteach.service;

import com.tositteach.domain.entity.Gp;

import java.util.List;

public interface GpService {
    byte create(String groName, String claId, String proId);
    int addStuInto(String claId, byte groId, List<String> stuIds);

    public List<Gp> queryAllGp();
    public int insertOneGroup(Gp gp);
    public Gp queryGpById(String cid, String gid);
    public List<Gp> queryGpById(String cid);
    public List<Gp> queryGpByName(String name);
    public int deleteOneByClaId(String id);
    public String  queryByName(String cid, String name);//返回组id
}
