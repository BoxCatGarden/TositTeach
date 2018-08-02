package com.tositteach.service.impl;

import com.tositteach.domain.entity.StuDoc;
import com.tositteach.domain.mapper.StuDocMapper;
import com.tositteach.service.StuDocService;
import com.tositteach.util.PagingBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class StuDocServiceImpl implements StuDocService {
    @Autowired
    StuDocMapper stuDocMapper;

    @Override
    public PagingBody query(String docName, String groName, String proName, int st, int nm) {
        return null;
    }

    @Override
    public StuDoc get(String docId) {
        return null;
    }

    @Override
    public int add(CommonsMultipartFile file, String docName, String disp, String stuId) {
        return 0;
    }

    @Override
    public int mod(String docId, CommonsMultipartFile file) {
        return 0;
    }

    @Override
    public int del(String docId) {
        return 0;
    }

    @Override
    public int score(String docId, byte score) {
        return 0;
    }

    //查询doc_student表中所有信息
    @Override
    public List<StuDoc> queryAllDoc_student() {
        return stuDocMapper.selectAllDoc_student();
    }

    //按文档名称搜索，列出信息
    @Override
    public List<StuDoc> queryDoc_studentName(String docsName) {
        return stuDocMapper.selectDoc_studentName(docsName);
    }

    //按小组名称搜索，列出信息
    //这里根据小组id来选择，需要根据小组名称联系到小组id（gp表）
    @Override
    public List<StuDoc> queryDoc_studentByGpName(String docsGroId, String docsClaId) {
        return stuDocMapper.selectDoc_studentByGpName(docsGroId, docsClaId);
    }


    //下载获取url
    @Override
    public String queryDoc_student(String docsId) {
        return stuDocMapper.selectDoc_student(docsId);
    }

    //删除文档
    @Override
    public Integer removeDoc_student(String docsId) {
        return stuDocMapper.deleteDoc_student(docsId);
    }

    //修改文档分数
    @Override
    public Integer modifyDoc_studentScore(String docsId) {
        return stuDocMapper.updateDoc_studentScore(docsId);
    }

    //修改已上传文档
    @Override
    public Integer modifyDoc_studentUrl(String docsId) {
        return stuDocMapper.updateDoc_studentUrl(docsId);
    }

    //文档添加
    @Override
    public Integer addDoc_student(StuDoc stuDoc) {
        return stuDocMapper.insertDoc_student(stuDoc);
    }

    //初次上传文档
    @Override
    public Integer modifyDoc_studentFurl(String docsId) {
        return stuDocMapper.updateDoc_studentFurl(docsId);
    }

    @Override
    public void getRightId(StuDoc stuDoc) {
        List<StuDoc> stuDocs = stuDocMapper.selectAllDoc_student();
        if(stuDocs.size()==0){
            stuDoc.setDocId("1");
        }
        else {
            ArrayList<Integer> iid = new ArrayList<Integer>();
            //最后一个元素
            for (StuDoc stuDoc1 : stuDocs) {
                iid.add(new Integer(stuDoc1.getDocId()));
            }
            //排序从小到大
            Collections.sort(iid);
            int id1 = iid.get(iid.size() - 1) + 1;
            System.out.println(id1 + "\n\n\n\n\n\n");
            String id = String.valueOf(id1);
            stuDoc.setDocId(id);
        }
    }

    @Override
    public StuDoc queryAllByDocsId(String docsId) {
        return stuDocMapper.selectAllByDocsId(docsId);
    }
}
