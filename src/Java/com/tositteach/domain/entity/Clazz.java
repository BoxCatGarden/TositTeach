package com.tositteach.domain.entity;

public class Clazz {
    private String claId;
    private String claName;
    private String room;
    private String userId;

    private Engineer eng;

    public Engineer getEng() {
        return eng;
    }

    public void setEng(Engineer eng) {
        this.eng = eng;
    }

    public String getClaId() {
        return claId;
    }

    public void setClaId(String claId) {
        this.claId = claId;
    }

    public String getClaName() {
        return claName;
    }

    public void setClaName(String claName) {
        this.claName = claName;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
