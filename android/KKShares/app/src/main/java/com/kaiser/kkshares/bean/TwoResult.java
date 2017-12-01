package com.kaiser.kkshares.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/16.
 */
public class TwoResult {
    private String totalCount;
    private String page;
    private String num;
    private List<ThirdData> data;

    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public List<ThirdData> getData() {
        return data;
    }

    public void setData(List<ThirdData> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "TwoResult{" +
                "totalCount='" + totalCount + '\'' +
                ", page='" + page + '\'' +
                ", num='" + num + '\'' +
                ", data=" + data +
                '}';
    }
}
