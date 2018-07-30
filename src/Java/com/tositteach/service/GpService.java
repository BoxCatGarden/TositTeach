package com.tositteach.service;

import com.tositteach.domain.entity.Gp;

import java.util.List;

public interface GpService {
    public List<Gp> queryAllGp();
    public int insertOneGroup(Gp gp);
    public Gp queryGpById(String cid, String gid);
    public List<Gp> queryGpById(String cid);
    public List<Gp> queryGpByName(String name);
}
