package com.tositteach.domain.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Gp {
    private String claId;
    private Byte groId;
    private String groName;
    private String proId;

    private Clazz cla;
    private Project pro;

    public Clazz getCla() {
        return cla;
    }

    public void setCla(Clazz cla) {
        this.cla = cla;
    }

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

    public Byte getGroId() {
        return groId;
    }

    public void setGroId(Byte groId) {
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
