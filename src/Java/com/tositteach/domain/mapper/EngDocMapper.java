package com.tositteach.domain.mapper;

import com.tositteach.domain.entity.EngDoc;

import java.util.List;

public interface EngDocMapper {

    public int getEngDocTotalNum(String pn);

    public List<EngDoc> getEngDocList(String pn, int st, int nm);

    public int addEngDoc(EngDoc engDoc);

    public int updateEngDoc(EngDoc engDoc);

    public int delEngDoc(String docId);

    public String getMaxEngDocId();

    public EngDoc getEngDoc(String docId);

}
