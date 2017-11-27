package com.kaiser.administrator.mymvprxjavaretrofit.bean;

/**
 * Created by Administrator on 2017/8/14.
 */
public class SecondBean {
    private String ID;
    private String address;
    private String picture;
    private String emid;
    private String caption;

    private String msg;
    private String code;
    private String result;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getEmid() {
        return emid;
    }

    public void setEmid(String emid) {
        this.emid = emid;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "SecondBean{" +
                "ID='" + ID + '\'' +
                ", address='" + address + '\'' +
                ", picture='" + picture + '\'' +
                ", emid='" + emid + '\'' +
                ", caption='" + caption + '\'' +
                ", msg='" + msg + '\'' +
                ", code='" + code + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}
