package com.tositteach.domain.mapper;

import com.tositteach.domain.entity.Gp;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface GpMapper {
    public List<Gp> selectAllGp();
//  public int insertGroup(Gp gp);
    public int insertGroup(Map<String, Object> a);
    public Gp selectById(@Param("gpClaId") String cid, @Param("gpGroId") String gid);
    public List<Gp> selectByClaId(@Param("gpClaId") String cid);
    public List<Gp> selectByName(@Param("gpGroName") String name);
    public int deleteByClaId(@Param("gpClaId") String id);
    public Gp selectByClaidName(@Param("gpClaId") String cid, @Param("gpGroName") String name);
}
