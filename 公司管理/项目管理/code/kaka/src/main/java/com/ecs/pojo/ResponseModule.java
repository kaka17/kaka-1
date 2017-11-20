package com.ecs.pojo;
/**
 * 数据响应module
 * @author A8509
 *
 */
public class ResponseModule {

	private Object code;
	private String msg;
	private Object data;
	public Object getCode() {
		return code;
	}
	public void setCode(Object code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "ResponseModule [code=" + code + ", msg=" + msg + ", data=" + data + "]";
	}
	
	
}
