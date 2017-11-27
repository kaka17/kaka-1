package com.kaiser.administrator.mymvprxjavaretrofit.bean;

/**
 * Created by Administrator on 2017/8/17.
 */
public class GPInFo {
    private GPInFoDataBean data;
    private GPInfoPic gopicture;
    private GpInfoDapandataBean dapandata;

    public GPInFoDataBean getData() {
        return data;
    }

    public void setData(GPInFoDataBean data) {
        this.data = data;
    }

    public GPInfoPic getGopicture() {
        return gopicture;
    }

    public void setGopicture(GPInfoPic gopicture) {
        this.gopicture = gopicture;
    }

    public GpInfoDapandataBean getDapandata() {
        return dapandata;
    }

    public void setDapandata(GpInfoDapandataBean dapandata) {
        this.dapandata = dapandata;
    }

    @Override
    public String toString() {
        return "GPInFo{" +
                "data=" + data +
                ", gopicture=" + gopicture +
                ", dapandata=" + dapandata +
                '}';
    }
}
