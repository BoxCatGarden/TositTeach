package com.tositteach.service;

import com.tositteach.domain.entity.StuDoc;
import com.tositteach.util.PagingBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.List;

public interface StuDocService {

    PagingBody query(String docName, String groName, String proName,
                     int st, int nm);
    StuDoc get(String docId);
    int add(CommonsMultipartFile file,
            String docName, String disp,
            String stuId);
    int mod(String docId, CommonsMultipartFile file);
    int del(String docId);
    int score(String docId, byte score);



    //查询doc_student表中所有信息
    public List<StuDoc> queryAllDoc_student();

    //按文档名称搜索，列出信息
    public List<StuDoc> queryDoc_studentName(String docsName);

//    //按项目名称搜索，列出信息
//    //这里是根据项目id来选择，需要根据项目名称联系到项目id（project表）
//    public List<StuDoc> queryDoc_studentProj();
//
    //按小组名称搜索，列出信息
    //这里根据小组id来选择，需要根据小组名称联系到小组id（gp表）
    public List<StuDoc> queryDoc_studentByGpName(String docsGroId, String docsClaId);

    //下载获取url
    public String queryDoc_student(String docsId);

    //删除文档
    public Integer removeDoc_student(String docsId);

    //修改文档分数
    public Integer modifyDoc_studentScore(String docsId);

    //修改已上传文档
    public Integer modifyDoc_studentUrl(String docsId);

    //文档添加
    public Integer addDoc_student(StuDoc stuDoc);

    //初次上传文档
    public Integer modifyDoc_studentFurl(String docsId);

    public void getRightId(StuDoc stuDoc);

    //根据文档id获取该文档信息
    public StuDoc queryAllByDocsId(String docsId);
}
