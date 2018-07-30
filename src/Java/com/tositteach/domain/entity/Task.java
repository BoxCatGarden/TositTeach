package com.tositteach.domain.entity;

import java.sql.Date;
import java.sql.Timestamp;

public class Task {
    private String taskId;
    private String taskScho;
    private Date taskStti;
    private Date taskEdti;
    private String taskDisp;
    private String taskPlan;
    private Timestamp taskTime;
    private String taskUser;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskScho() {
        return taskScho;
    }

    public void setTaskScho(String taskScho) {
        this.taskScho = taskScho;
    }

    public Date getTaskStti() {
        return taskStti;
    }

    public void setTaskStti(Date taskStti) {
        this.taskStti = taskStti;
    }

    public Date getTaskEdti() {
        return taskEdti;
    }

    public void setTaskEdti(Date taskEdti) {
        this.taskEdti = taskEdti;
    }

    public String getTaskDisp() {
        return taskDisp;
    }

    public void setTaskDisp(String taskDisp) {
        this.taskDisp = taskDisp;
    }

    public String getTaskPlan() {
        return taskPlan;
    }

    public void setTaskPlan(String taskPlan) {
        this.taskPlan = taskPlan;
    }

    public Timestamp getTaskTime() {
        return taskTime;
    }

    public void setTaskTime(Timestamp taskTime) {
        this.taskTime = taskTime;
    }

    public String getTaskUser() {
        return taskUser;
    }

    public void setTaskUser(String taskUser) {
        this.taskUser = taskUser;
    }
}
