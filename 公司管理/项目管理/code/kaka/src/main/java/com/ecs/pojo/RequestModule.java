package com.ecs.pojo;
/**
 * 数据请求module
 * @author A8509
 *
 */
public class RequestModule {

	private String accountId;
	private String onlineId;
	private String token;
	private Object data;
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getOnlineId() {
		return onlineId;
	}
	public void setOnlineId(String onlineId) {
		this.onlineId = onlineId;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "RequestModule [accountId=" + accountId + ", onlineId=" + onlineId + ", token=" + token + ", data=" + data
				+ "]";
	}
	
}
