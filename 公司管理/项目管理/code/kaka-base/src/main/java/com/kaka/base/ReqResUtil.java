package com.kaka.base;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.dubbo.common.URL;
import com.kaka.base.gson.GsonUtils;

/**
 * 请求request 和 响应response
 */
public class ReqResUtil {

	protected final static Logger log = LoggerFactory.getLogger(ReqResUtil.class);
	static String phoneReg = "\\b(ip(hone|od)|android|opera m(ob|in)i"
			+ "|windows (phone|ce)|blackberry"
			+ "|s(ymbian|eries60|amsung)|p(laybook|alm|rofile/midp"
			+ "|laystation portable)|nokia|fennec|htc[-_]"
			+ "|mobile|up.browser|[1-4][0-9]{2}x[1-4][0-9]{2})\\b";
	static String tableReg = "\\b(ipad|tablet|(Nexus 7)|up.browser"
			+ "|[1-4][0-9]{2}x[1-4][0-9]{2})\\b";

	// 移动设备正则匹配：手机端、平板
	static Pattern phonePat = Pattern.compile(phoneReg, Pattern.CASE_INSENSITIVE);
	static Pattern tablePat = Pattern.compile(tableReg, Pattern.CASE_INSENSITIVE);

	/**
	 * 检测是否是移动设备访问 
	 * @Title: check 
	 * @param userAgent
	 * @return
	 */
	public static boolean checkMobile(HttpServletRequest request) {
		String userAgent = getUserAgent(request);
		if (null == userAgent) {
			userAgent = "";
		}
		Matcher matcherPhone = phonePat.matcher(userAgent);
		Matcher matcherTable = tablePat.matcher(userAgent);
		if (matcherPhone.find() || matcherTable.find()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 获取user-agent
	 * 
	 * @param request
	 * @return
	 */
	public static String getUserAgent(HttpServletRequest request) {
		String str = request.getHeader("User-Agent");
		if (str != null) {
			str = str.toLowerCase();
		}
		return str;
	}


	public static Map<String, Object> getMapByAttribute(HttpServletRequest request, String params) {

		@SuppressWarnings("unchecked")
		Map<String, Object> paramsMap = (Map<String, Object>) request.getAttribute(params);

		return paramsMap;
	}

}
