package com.tositteach.service;

import com.tositteach.domain.entity.Gp;

import java.util.List;

public interface GpService {
    byte create(String groName, String claId, String proId);
    int addStuInto(String claId, byte groId, List<String> stuIds);

}
