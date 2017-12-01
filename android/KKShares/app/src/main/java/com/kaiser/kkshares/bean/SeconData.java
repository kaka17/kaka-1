package com.kaiser.kkshares.bean;

/**
 * Created by Administrator on 2017/8/15.
 */
public class SeconData {
    private String error_code;
    private String reason;
    private TwoResult result;

    public TwoResult getResult() {
        return result;
    }

    public void setResult(TwoResult result) {
        this.result = result;
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "SeconData{" +
                "error_code='" + error_code + '\'' +
                ", reason='" + reason + '\'' +
                ", result=" + result +
                '}';
    }
}
