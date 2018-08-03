package com.tositteach.domain.mapper;

import com.tositteach.domain.entity.EngDoc;

import java.util.List;

public interface DocCleanMapper {
    List<EngDoc> getInvaliDoc();

    int delInvaliStuDoc();

    int delInvaliEngDoc();
}
