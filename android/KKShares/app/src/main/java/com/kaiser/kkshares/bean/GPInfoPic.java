package com.kaiser.kkshares.bean;

/**
 * Created by Administrator on 2017/8/17.
 */
public class GPInfoPic {
    private String minurl;
    private String dayurl;
    private String weekurl;
    private String monthurl;

    @Override
    public String toString() {
        return "GPInfoPic{" +
                "minurl='" + minurl + '\'' +
                ", dayurl='" + dayurl + '\'' +
                ", weekurl='" + weekurl + '\'' +
                ", monthurl='" + monthurl + '\'' +
                '}';
    }

    public String getMinurl() {
        return minurl;
    }

    public void setMinurl(String minurl) {
        this.minurl = minurl;
    }

    public String getDayurl() {
        return dayurl;
    }

    public void setDayurl(String dayurl) {
        this.dayurl = dayurl;
    }

    public String getWeekurl() {
        return weekurl;
    }

    public void setWeekurl(String weekurl) {
        this.weekurl = weekurl;
    }

    public String getMonthurl() {
        return monthurl;
    }

    public void setMonthurl(String monthurl) {
        this.monthurl = monthurl;
    }
}
