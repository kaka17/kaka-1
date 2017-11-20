/**   
 * @Project: tfscore 
 * @Title: GsonUtils.java 
 * @Package com.tfscore.gson 
 * @Description: JSON转换器 
 * @author lx 
 * @date 2016年6月6日 上午12:04:39 
 * @Copyright: 2016 年 前海阿拉海钜科技. All rights reserved  
 * @version V1.0   
 */
package com.kaka.base.gson;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.kaka.base.BaseGlobal;
import com.kaka.base.pojo.BaseResult;
import com.kaka.base.pojo.Param;

import net.sf.json.xml.XMLSerializer;


/** 
 * @ClassName GsonUtils  
 * @Description JSON转换器 
 * @author lx 
 * @date 2016年6月6日  
 *   
 */
public class GsonUtils {
//	private static Logger logger = LoggerFactory.getLogger(GsonUtils.class);
	private static Gson gson = new GsonBuilder().disableHtmlEscaping().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
	/**
	 * 
	 * @Title: retJson 
	 * @Description: 将对象转换为JSON字符串 
	 * @param code 返回代码
	 * @param msg  描述返回信息
	 * @return 参数说明
	 * @return String    返回类型
	 * @throws
	 */
	public static String retJson(String code, String msg) {
		return GsonUtils.retJson(code,msg,null);
	}

	/**
	 * 
	 * @Title: retJson 
	 * @Description: 将对象转换为JSON字符串
	 * @param code 返回代码
	 * @param msg 描述返回信息
	 * @param data 数据集
	 * @return 参数说明
	 * @return String    返回类型
	 * @throws
	 */
	public static String retJson(String code,Object msg,Object data) {
		return GsonUtils.retJson(code,msg,data,null);
	}

	/**
	 * 
	 * @Title: retJson 
	 * @Description: 将对象转换为JSON字符串
	 * @param code 返回代码
	 * @param desc 描述返回信息
	 * @param data 数据集
	 * @param retain 保留字段
	 * @return 参数说明
	 * @return String    返回类型
	 * @throws
	 */
	public static String retJson(String code,Object desc, Object data, 
			Object retain) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(BaseGlobal.CODE, code);
		map.put(BaseGlobal.MSG, desc);
		map.put(BaseGlobal.DATA, data);
		map.put(BaseGlobal.RETAIN, retain);
		//logger.info(GsonUtils.toJson(map));
		return GsonUtils.toJson(map);

	}
	
	/**
	 * 
	 * @Title: toJson 
	 * @Description: 将对象转换为JSON字符串
	 * @param obj
	 * @return 参数说明
	 * @return String    返回类型
	 * @throws
	 */
	public static String toJson(Object obj) {
		return gson.toJson(obj);
	}

	/**
	 * 
	 * @Title: toJsonForXml 
	 * @Description: 将xml转换为JSON字符串
	 * @throws
	 */
	public static String toJsonForXml(String xml) {
		XMLSerializer xmlSerializer = new XMLSerializer();
		String result = xmlSerializer.read(xml).toString();
		return result;
	}
	
	/**
	 * @Title: getJson 
	 * @Description: 将JSON字符串转换为对象 
	 * @param json JSON字符串
	 * @param classOfT 转换对象
	 * @return 参数说明
	 * @return T    返回类型
	 * @throws
	 */
	public static <T> T getJson(String json, Class<T> classOfT) {
		return gson.fromJson(json, classOfT);
	}
	
	/** 
	 * @Title: getJson 
	 * @Description: 将JSON字符串转换为集合对象 
	 * @param json JSON字符串
	 * @param type 集合类别
	 * @return 参数说明
	 * @return List<T>    返回类型
	 */
	public static <T> List<T> getJson(String json, Type type) {
		// 用法
//		 List<Bean> beans = GsonUtils.getJson(json,new TypeToken<List<Bean>>() {}.getType());
		return gson.fromJson(json, type);
	}

	/** 
	 * @Title: retJson 
	 * @Description: 将基本结果信息数据转换为json串 
	 * @param baseResult 基本结果信息数据
	 * @return 参数说明
	 * @return String    返回类型
	 * @throws 
	 */
	public static String retJson(BaseResult baseResult) {
		return retJson(baseResult.getCode(),baseResult.getMsg(),baseResult.getData(),baseResult.getRetain());
	}
	
	/**
	 * @Title: getJson 
	 * @Description: 将JSON字符串转换为集合
	 * @param json JSON字符串
	 */
	public static <T> List<Param> getJsonToParam(String json) {
		// 用法
		// List<Param> beans = GsonUtils.getJson(json,new TypeToken<List<Param>>() {}.getType());
		return GsonUtils.getJson(json, new TypeToken<List<Param>>() {
		}.getType());
	}
	/**
	 * @Title: getJsonToMap 
	 * @Description: 将JSON字符串转换为Map
	 * @param json JSON字符串
	 */
	public static Map<String,String> getJsonToMap(String json) {
		return gson.fromJson(json, new TypeToken<Map<String, String>>() { }.getType());
	}
	/**
	 * @Title: getJsonToMap 
	 * @Description: 将JSON字符串转换为Map
	 * @param json JSON字符串
	 */
	public static Map<String,Object> getJsonToObjMap(String json) {
		return gson.fromJson(json, new TypeToken<Map<String, Object>>() { }.getType());
	}
	/**
	 * @Title: getJsonToListMap 
	 * @Description: 将JSON字符串转换为List<Map<String,String>>
	 * @param json JSON字符串
	 */
	public static List<Map<String,String>> getJsonToListMap(String json) {
		Type type = new TypeToken<List<Map<String,String>>>() {}.getType();  
		return gson.fromJson(json, type);
	}
	/**
	 * @Title: getJsonToListObjectMap 
	 * @Description: 将JSON字符串转换为List<Map<String,Object>>
	 * @param json JSON字符串
	 */
	public static List<Map<String,Object>> getJsonToListObjectMap(String json) {
		Type type = new TypeToken<List<Map<String,Object>>>() {}.getType();  
		return gson.fromJson(json, type);
	}
	/**
	 * @Title: getJsonToMap 
	 * @Description: 将JSON字符串转换为Map<String,Object>
	 * @param json JSON字符串
	 */
	public static Map<String,Object> getJsonToObjectMap(String json) {

		//此处不适用Gson，原因是Gson自动将int 和long类型转成double
		Map<String,Object> map = JSONObject.parseObject(json);
		return map;

	}
}
