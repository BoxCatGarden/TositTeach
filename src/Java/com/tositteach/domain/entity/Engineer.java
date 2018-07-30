package com.tositteach.domain.entity;

public class Engineer {
    private String engiId;//user_id
    private String engiName;
    private Byte engiSex;

    public String getEngiId() {
        return engiId;
    }

    public void setEngiId(String engiId) {
        this.engiId = engiId;
    }

    public String getEngiName() {
        return engiName;
    }

    public void setEngiName(String engiName) {
        this.engiName = engiName;
    }

    public Byte getEngiSex() {
        return engiSex;
    }

    public void setEngiSex(Byte engiSex) {
        this.engiSex = engiSex;
    }
}
