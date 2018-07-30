package com.tositteach.domain.mapper;

import com.tositteach.domain.entity.Gp;

import java.util.List;
import java.util.Map;

public interface GpMapper {
    public List<Gp> selectAllGp();
//    public int insertGroup(Gp gp);
    public int insertGroup(Map<String, Object> a);
    public Gp selectById(String cid, String gid);
    public List<Gp> selectByClaId(String cid);
    public List<Gp> selectByName(String name);
}
