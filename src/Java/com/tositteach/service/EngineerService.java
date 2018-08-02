package com.tositteach.service;

import com.tositteach.domain.entity.Engineer;
import com.tositteach.util.PagingBody;

import java.util.List;

public interface EngineerService {
    PagingBody query(int st, int nm);

}
