package com.kaiser.administrator.mymvprxjavaretrofit.bean;

/**
 * Created by Administrator on 2017/8/17.
 */
public class GPInfoBean<T> {
    private String resultcode;
    private String reason;
    private String error_code;
    private T result;

    @Override
    public String toString() {
        return "GPInfoBean{" +
                "resultcode='" + resultcode + '\'' +
                ", reason='" + reason + '\'' +
                ", error_code='" + error_code + '\'' +
                ", result=" + result +
                '}';
    }

    public String getResultcode() {
        return resultcode;
    }

    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
