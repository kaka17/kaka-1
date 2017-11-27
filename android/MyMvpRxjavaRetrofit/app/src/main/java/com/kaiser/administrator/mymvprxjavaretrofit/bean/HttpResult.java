package com.kaiser.administrator.mymvprxjavaretrofit.bean;

/**
 * Created by Administrator on 2017/8/15.
 */
public class HttpResult<T> {
    private String msg;
    private String code;
    private T result;

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

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
