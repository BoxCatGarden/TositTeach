package com.tositteach.domain.entity;

public class Gp {
    private String claId;
    private String groId;
    private String groName;
    private String proId;

    private Project pro;

    public Project getPro() {
        return pro;
    }

    public void setPro(Project pro) {
        this.pro = pro;
    }

    public String getClaId() {
        return claId;
    }

    public void setClaId(String claId) {
        this.claId = claId;
    }

    public String getGroId() {
        return groId;
    }

    public void setGroId(String groId) {
        this.groId = groId;
    }

    public String getGroName() {
        return groName;
    }

    public void setGroName(String groName) {
        this.groName = groName;
    }

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }
}
