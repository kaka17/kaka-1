/**   
 * @Project: tfs-base 
 * @Title: CommonException.java 
 * @Package com.tfstec.base.exception 
 * @Description: 异常处理工具类 
 * @author Administrator 
 * @date 2017年9月11日 上午9:34:00 
 * @Copyright: 2017 年 前海阿拉海钜科技. All rights reserved  
 * @version V1.0   
 */
package com.kaka.base.exception;

/** 
 * @ClassName CommonException  
 * @Description 异常处理工具类 
 * @author Administrator 
 * @date 2017年9月11日  
 *   
 */
public class CheckException extends RuntimeException{

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;
	
	public CheckException(){
		
	}
	
	public CheckException(String message){
		super(message);
	}
	
	public CheckException(Throwable cause){
		super(cause);
	}

	public CheckException(String message,Throwable cause){
		super(message,cause);
	}
	
	public CheckException(String message,Throwable cause,boolean enableSuppression,boolean writableStackTrace){
		super(message,cause,enableSuppression,writableStackTrace);
	}
}
