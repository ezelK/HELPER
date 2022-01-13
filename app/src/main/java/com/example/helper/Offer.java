package com.example.helper;

public class Offer {
    private String userid;
    private String nameSurname;
    private String service;
    private String text;

    public Offer(String userid, String nameSurname, String service) {
        this.userid = userid;
        this.nameSurname = nameSurname;
        this.service = service;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Offer() {
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getNameSurname() {
        return nameSurname;
    }

    public void setNameSurname(String nameSurname) {
        this.nameSurname = nameSurname;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }
}
