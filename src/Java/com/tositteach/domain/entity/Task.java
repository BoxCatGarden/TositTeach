package com.tositteach.domain.entity;

import java.sql.Date;
import java.sql.Timestamp;

public class Task {
    private String tasId;
    private String tasName;
    private String stTime;
    private String edTime;
    private String disp;
    private String plan;
    private String time;
    private String userId;
    private Engineer eng;

    public String getTasId() {
        return tasId;
    }

    public void setTasId(String tasId) {
        this.tasId = tasId;
    }

    public String getTasName() {
        return tasName;
    }

    public void setTasName(String tasName) {
        this.tasName = tasName;
    }

    public String getDisp() {
        return disp;
    }

    public void setDisp(String disp) {
        this.disp = disp;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStTime() {
        return stTime;
    }

    public void setStTime(String stTime) {
        this.stTime = stTime;
    }

    public String getEdTime() {
        return edTime;
    }

    public void setEdTime(String edTime) {
        this.edTime = edTime;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Engineer getEng() {
        return eng;
    }

    public void setEng(Engineer eng) {
        this.eng = eng;
    }
}
