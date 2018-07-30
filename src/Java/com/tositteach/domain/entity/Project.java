package com.tositteach.domain.entity;

import javax.xml.soap.Text;
import java.sql.Date;
import java.sql.Timestamp;


public class Project {
    private String projId;
    private String projName;
    private Date projStti;
    private Date projEdti;
    private String projDisp;
    private Byte projStat;
    private Timestamp projTime;
    private String projUserId;

    public String getProjId() {
        return projId;
    }

    public void setProjId(String projId) {
        this.projId = projId;
    }

    public String getProjName() {
        return projName;
    }

    public void setProjName(String projName) {
        this.projName = projName;
    }

    public Date getProjStti() {
        return projStti;
    }

    public void setProjStti(Date projStti) {
        this.projStti = projStti;
    }

    public Date getProjEdti() {
        return projEdti;
    }

    public void setProjEdti(Date projEdti) {
        this.projEdti = projEdti;
    }

    public String getProjDisp() {
        return projDisp;
    }

    public void setProjDisp(String projDisp) {
        this.projDisp = projDisp;
    }

    public Byte getProjStat() {
        return projStat;
    }

    public void setProjStat(Byte projStat) {
        this.projStat = projStat;
    }

    public Timestamp getProjTime() {
        return projTime;
    }

    public void setProjTime(Timestamp projTime) {
        this.projTime = projTime;
    }

    public String getProjUserId() {
        return projUserId;
    }

    public void setProjUserId(String projUserId) {
        this.projUserId = projUserId;
    }
}
