package com.tositteach.domain.entity;

import java.sql.Timestamp;

public class Doc_student {
    private String docsId;
    private String docsName;
    private String docsUrl;
    private Byte docsScor;
    private Timestamp docsTime;
    private String docsDisp;
    private String docsClaId;
    private String docsGroId;
    private String docsProId;

    public String getDocsId() {
        return docsId;
    }

    public void setDocsId(String docsId) {
        this.docsId = docsId;
    }

    public String getDocsName() {
        return docsName;
    }

    public void setDocsName(String docsName) {
        this.docsName = docsName;
    }

    public String getDocsUrl() {
        return docsUrl;
    }

    public void setDocsUrl(String docsUrl) {
        this.docsUrl = docsUrl;
    }

    public Byte getDocsScor() {
        return docsScor;
    }

    public void setDocsScor(Byte docsScor) {
        this.docsScor = docsScor;
    }

    public Timestamp getDocsTime() {
        return docsTime;
    }

    public void setDocsTime(Timestamp docsTime) {
        this.docsTime = docsTime;
    }

    public String getDocsDisp() {
        return docsDisp;
    }

    public void setDocsDisp(String docsDisp) {
        this.docsDisp = docsDisp;
    }

    public String getDocsClaId() {
        return docsClaId;
    }

    public void setDocsClaId(String docsClaId) {
        this.docsClaId = docsClaId;
    }

    public String getDocsGroId() {
        return docsGroId;
    }

    public void setDocsGroId(String docsGroId) {
        this.docsGroId = docsGroId;
    }

    public String getDocsProId() {
        return docsProId;
    }

    public void setDocsProId(String docsProId) {
        this.docsProId = docsProId;
    }
}
