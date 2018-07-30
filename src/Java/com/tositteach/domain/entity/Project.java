package com.tositteach.domain.entity;

import javax.xml.soap.Text;
import java.sql.Date;
import java.sql.Timestamp;


public class Project {
    private String projId;
    private String projName;
    private String projStti;
    private String projEdti;
    private String projDisp;
    private Byte projStat;

    public String getProjStti() {
        return projStti;
    }

    public void setProjStti(String projStti) {
        this.projStti = projStti;
    }

    public String getProjEdti() {
        return projEdti;
    }

    public void setProjEdti(String projEdti) {
        this.projEdti = projEdti;
    }

    public String getProjTime() {
        return projTime;
    }

    public void setProjTime(String projTime) {
        this.projTime = projTime;
    }

    private String projTime;
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

    public String getProjUserId() {
        return projUserId;
    }

    public void setProjUserId(String projUserId) {
        this.projUserId = projUserId;
    }


}
