package com.ecs.global;

public class GlobalVar {

	//code常量
	
	/**操作成功*/
	public static final String RC_SUCCESS = "0000";
	/**操作失败*/
	public static final String RC_FAIL = "-0000";
	/**登录失败，重新登录*/
	public static final String RC_OUT_LOGIN = "-1111";
	
	//msg常量
	/**操作成功*/
	public static final String RM_SUCCESS = "操作成功 ";
	/**网络异常，系统繁忙*/
	public static final String RM_FAIL = "网络异常，系统繁忙 ";
	/**登录成功*/
	public static final String RM_LOGIN_SUCCESS = "登录成功 ";
	/**登录失败msg*/
	public static final String RM_OUT_ONLINE = "登录失败，请重新登录！ ";
	/**当前用户不存在*/
	public static final String RM_USER_NOT_EXIT = "当前用户不存在！ ";
	/**当前用户已被锁定*/
	public static final String RM_USER_LOCK = "当前用户已被锁定，请联系客服！ ";
	/**当前用户登录密码错误*/
	public static final String RM_USER_LOGIN_PASS_ERR = "当前用户登录密码错误！ ";
	/**用户注册成功*/
	public static final String RM_USER_REGIST_SUCCSS = "恭喜您，您已注册成功！ ";
	/**注册失败*/
	public static final String RM_USER_REGIST_ERROR = "很抱歉，您注册失败！ ";
	//key常量
	/**request请求数据key*/
	public static final String RK_REQ_DATA = "data";
	
	
	
	
	//value常量
	/**用户超时时间秒*/
	public static final int RV_LOGIN_TIME_OUT = 3600;
	/**用户状态  0非锁定*/
	public static final String RV_USER_STATUS_0 = "0";
	/**用户状态  1锁定*/
	public static final String RV_USER_STATUS_1 = "1";
	/**用户登录失败次数上限*/
	public static final int RV_USER_LOGIN_FAIL_ACCOUNT = 3;
}
