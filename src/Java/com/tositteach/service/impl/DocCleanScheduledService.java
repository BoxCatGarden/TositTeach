package com.tositteach.service.impl;

import com.tositteach.domain.entity.EngDoc;
import com.tositteach.domain.mapper.DocCleanMapper;
import com.tositteach.service.FileService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;

@Component
public class DocCleanScheduledService {
    @Resource
    private DocCleanMapper docCleanMapper;
    @Resource
    private FileService fileService;

    //每天0点清除失效文档
    @Scheduled(cron = "0 0 0 * * ?")
    public void doTask() {
        List<EngDoc> list = docCleanMapper.getInvaliDoc();
        if (list == null) return;
        for (EngDoc doc : list) {
            fileService.removeFile(doc.getUrl());
        }
        docCleanMapper.delInvaliStuDoc();
        docCleanMapper.delInvaliEngDoc();
    }

    /*@Scheduled(cron = "0 * * * * ?")
    public void do2() {
        System.out.println("task!!");
    }*/
}
