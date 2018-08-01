package com.tositteach.domain.entity;


public class Project {
    private String proId;
    private String proName;
    private String stTime;
    private String edTime;
    private String disp;
    private Byte state;
    private String time;
    private String userId;  //publisher

    private Engineer eng;
    private EngDoc doc;

    public Engineer getEng() {
        return eng;
    }

    public void setEng(Engineer eng) {
        this.eng = eng;
    }

    public EngDoc getDoc() {
        return doc;
    }

    public void setDoc(EngDoc doc) {
        this.doc = doc;
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


    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }



    public String getDisp() {
        return disp;
    }

    public void setDisp(String disp) {
        this.disp = disp;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }



    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
