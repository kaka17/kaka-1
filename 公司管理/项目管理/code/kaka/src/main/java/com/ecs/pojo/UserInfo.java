package com.ecs.pojo;

/**
 * 用户信息表实体类
 * @author A8509
 *
 */
public class UserInfo {

	public UserInfo(){}
	 /**用户编号*/
	private String accountId;
	/**用户姓名*/
	private String accountName;
	/**用户密码*/
	private String userPass;
	/**手机号码*/
	private String mobile;
	/**qq号码*/
	private String qqAccount;
	/**用户状态       0非锁定，1锁定*/
	private String userStatus;
	/**失败次数   */
	private Integer failCount;
	/**交易密码*/
	private String tradePass;
	/**用户等级*/
	private String level;
	/**创建时间*/
	private String createTime;
	/**修改时间*/
	private String modifyTime;
	/**修改说明*/
	private String modifyMark;
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getUserPass() {
		return userPass;
	}
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getQqAccount() {
		return qqAccount;
	}
	public void setQqAccount(String qqAccount) {
		this.qqAccount = qqAccount;
	}
	public String getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	public Integer getFailCount() {
		return failCount;
	}
	public void setFailCount(int failCount) {
		this.failCount = failCount;
	}
	public String getTradePass() {
		return tradePass;
	}
	public void setTradePass(String tradePass) {
		this.tradePass = tradePass;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}
	public String getModifyMark() {
		return modifyMark;
	}
	public void setModifyMark(String modifyMark) {
		this.modifyMark = modifyMark;
	}
	@Override
	public String toString() {
		return "UserInfo [accountId=" + accountId + ", accountName=" + accountName + ", userPass=" + userPass
				+ ", mobile=" + mobile + ", qqAccount=" + qqAccount + ", userStatus=" + userStatus + ", tradePass=" + tradePass
				+ ", createTime=" + createTime + ", modifyTime=" + modifyTime + ", modifyMark=" + modifyMark + "]";
	}
}
