/**   
 * @Project: tfs-plm-web 
 * @Title: HttpHelper.java 
 * @Package com.tfstec.plm.util 
 * @Description: TODO 
 * @author A8509 
 * @date 2017年2月9日 下午2:10:24 
 * @Copyright: 2017 年 前海阿拉海钜科技. All rights reserved  
 * @version V1.0   
 */
package com.ecs.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ecs.pojo.RequestModule;
import com.ecs.pojo.ResponseModule;
import com.kaka.base.StringUtils;
import com.kaka.base.gson.GsonUtils;

/** 
 * @ClassName HttpHelper  
 * @Description TODO 
 * @author A8509 
 * @date 2017年2月9日  
 *   
 */
public class HttpHelper {
	private static Logger logger = LoggerFactory.getLogger(HttpHelper.class);
	/**
	 * @Title: getHttpRequestParamsByStream 
	 * @Description: 通过数据流，解析客户端数据
	 * @param request
	 * @return String    返回类型
	 */
	public static String getParamsByStream(HttpServletRequest request){
		StringBuilder sb = new StringBuilder();
        InputStream inputStream = null;
        BufferedReader reader = null;
        String str = "";
        try {
            inputStream = request.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            String line = "";
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            str = sb.toString();
        } catch (IOException e) {
        	logger.error("获取客户端数据发生错误",e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return str;
	}
	/**接收客户端数据*/
	public static RequestModule getRequestModule(String requestDatas){
		return GsonUtils.getJson(requestDatas, RequestModule.class);
	}
	/**服务器响应数据*/
	public static String responseDatas(ResponseModule responseModule){
		logger.debug("服务器响应数据：{}",GsonUtils.toJson(responseModule));
		if(StringUtils.isEmpty(responseModule.getData())){
			Map<String,Object> map = new HashMap<String,Object>();
			responseModule.setData(map);
		}
		return GsonUtils.toJson(responseModule);
	}
}
