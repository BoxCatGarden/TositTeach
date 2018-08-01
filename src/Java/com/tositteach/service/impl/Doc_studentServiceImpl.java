package com.tositteach.service.impl;

import com.tositteach.domain.mapper.Doc_studentMapper;
import com.tositteach.domain.entity.Doc_student;
import com.tositteach.service.Doc_studentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class Doc_studentServiceImpl implements Doc_studentService {
    @Autowired
    Doc_studentMapper doc_studentMapper;

    //查询doc_student表中所有信息
    @Override
    public List<Doc_student> queryAllDoc_student() {
        return doc_studentMapper.selectAllDoc_student();
    }

    //按文档名称搜索，列出信息
    @Override
    public List<Doc_student> queryDoc_studentName(String docsName) {
        return doc_studentMapper.selectDoc_studentName(docsName);
    }

    //按小组名称搜索，列出信息
    //这里根据小组id来选择，需要根据小组名称联系到小组id（gp表）
    @Override
    public List<Doc_student> queryDoc_studentByGpName(String docsGroId, String docsClaId) {
        return doc_studentMapper.selectDoc_studentByGpName(docsGroId, docsClaId);
    }


    //下载获取url
    @Override
    public String queryDoc_student(String docsId) {
        return doc_studentMapper.selectDoc_student(docsId);
    }

    //删除文档
    @Override
    public Integer removeDoc_student(String docsId) {
        return doc_studentMapper.deleteDoc_student(docsId);
    }

    //修改文档分数
    @Override
    public Integer modifyDoc_studentScore(String docsId) {
        return doc_studentMapper.updateDoc_studentScore(docsId);
    }

    //修改已上传文档
    @Override
    public Integer modifyDoc_studentUrl(String docsId) {
        return doc_studentMapper.updateDoc_studentUrl(docsId);
    }

    //文档添加
    @Override
    public Integer addDoc_student(Doc_student doc_student) {
        return doc_studentMapper.insertDoc_student(doc_student);
    }

    //初次上传文档
    @Override
    public Integer modifyDoc_studentFurl(String docsId) {
        return doc_studentMapper.updateDoc_studentFurl(docsId);
    }

    @Override
    public void getRightId(Doc_student doc_student) {
        List<Doc_student> doc_students = doc_studentMapper.selectAllDoc_student();
        if(doc_students.size()==0){
            doc_student.setDocsId("1");
        }
        else {
            ArrayList<Integer> iid = new ArrayList<Integer>();
            //最后一个元素
            for (Doc_student doc_student1 : doc_students) {
                iid.add(new Integer(doc_student1.getDocsId()));
            }
            //排序从小到大
            Collections.sort(iid);
            int id1 = iid.get(iid.size() - 1) + 1;
            System.out.println(id1 + "\n\n\n\n\n\n");
            String id = String.valueOf(id1);
            doc_student.setDocsId(id);
        }
    }

    @Override
    public Doc_student queryAllByDocsId(String docsId) {
        return doc_studentMapper.selectAllByDocsId(docsId);
    }
}
