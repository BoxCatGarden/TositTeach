package com.tositteach.domain.mapper;

import com.tositteach.domain.entity.StuDoc;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StuDocMapper {

//    StuDocMapper.xml中的方法名待写

    //查询doc_student表中所有信息
    public List<StuDoc> selectAllDoc_student();

    //按文档名称搜索，列出信息
    public List<StuDoc> selectDoc_studentName(@Param("docsName") String docsName);

    //按照文档id搜索，找到该项目
    public StuDoc selectAllByDocsId(@Param("docsId") String docsId);

//    //按项目名称搜索，列出信息
//    //这里是根据项目id来选择，需要根据项目名称联系到项目id（project表）
//    public List<StuDoc> selectDoc_studentProj(String docsProId);
//
    //按小组名称搜索，列出信息
    //这里根据小组id来选择，需要根据小组名称联系到小组id（gp表）
    public List<StuDoc> selectDoc_studentByGpName(@Param("docsGroId") String docsGroId, @Param("docsClaId") String docsClaId);

    //下载获取url
    public String selectDoc_student(@Param("docsId") String docsId);

    //删除文档
    public Integer deleteDoc_student(@Param("docsId") String docsId);

    //修改文档分数
    public Integer updateDoc_studentScore(@Param("docsId") String docsId);

    //修改已上传文档
    public Integer updateDoc_studentUrl(@Param("docsId") String docsId);

    //文档添加
    public Integer insertDoc_student(StuDoc stuDoc);

    //初次上传文档
    public Integer updateDoc_studentFurl(@Param("docsId") String docsId);


}
