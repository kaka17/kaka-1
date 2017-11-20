package com.ecs.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kaka.base.StringUtils;

public class KakaStringUtils {
	
	private static Logger logger = LoggerFactory.getLogger(KakaStringUtils.class);
	/**
	 * 生成用户编号
	 * 规则：00000000 位递增数字  
	 * @return
	 */
	public static String getAccountId(String maxAccountId){
		NumberFormat f=new DecimalFormat("00000000");
		String newAccountId = "";
		if(StringUtils.isEmpty(maxAccountId)){
			newAccountId = f.format(0);
			return newAccountId;
		}
		int oldAccountId = Integer.valueOf(maxAccountId);
		if(oldAccountId<99999999){
			oldAccountId = oldAccountId+1;
			newAccountId = f.format(oldAccountId);
		}else{
			logger.error("生成编号已经超出范围!");
		}
		
		return newAccountId;
	}
}
