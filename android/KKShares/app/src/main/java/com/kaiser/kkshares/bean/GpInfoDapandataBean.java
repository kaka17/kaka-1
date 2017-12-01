package com.kaiser.kkshares.bean;

/**
 * Created by Administrator on 2017/8/17.
 */
public class GpInfoDapandataBean {
    private String dot;
    private String name;
    private String nowPic;
    private String rate;
    private String traAmount;
    private String traNumber;

    @Override
    public String toString() {
        return "GpInfoDapandataBean{" +
                "dot='" + dot + '\'' +
                ", name='" + name + '\'' +
                ", nowPic='" + nowPic + '\'' +
                ", rate='" + rate + '\'' +
                ", traAmount='" + traAmount + '\'' +
                ", traNumber='" + traNumber + '\'' +
                '}';
    }

    public String getDot() {
        return dot;
    }

    public void setDot(String dot) {
        this.dot = dot;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNowPic() {
        return nowPic;
    }

    public void setNowPic(String nowPic) {
        this.nowPic = nowPic;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getTraAmount() {
        return traAmount;
    }

    public void setTraAmount(String traAmount) {
        this.traAmount = traAmount;
    }

    public String getTraNumber() {
        return traNumber;
    }

    public void setTraNumber(String traNumber) {
        this.traNumber = traNumber;
    }
}
