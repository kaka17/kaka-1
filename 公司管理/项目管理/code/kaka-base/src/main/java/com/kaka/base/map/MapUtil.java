/**   
 * @Project: tfs-base 
 * @Title: MapUtil.java 
 * @Package com.tfstec.base 
 * @Description: 获取MAP集合的数据 
 * @author daidg 
 * @date 2017年3月6日 下午2:18:45 
 * @Copyright: 2017 年 前海阿拉海钜科技. All rights reserved  
 * @version V1.0   
 */
package com.kaka.base.map;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;

import com.kaka.base.date.DateJodaUtil;

/** 
 * @ClassName MapUtil  
 * @Description 获取MAP集合的数据 
 * @author daidg 
 * @date 2017年3月6日  
 *   
 */
@SuppressWarnings("rawtypes")
public class MapUtil extends MapUtils {

	/** 
	 * @Title: getDate 
	 * @Description: 根据传入的map集合和key值获取时间 
	 * @param map 元数据集合
	 * @param key 获取的关键key值
	 * @return 参数说明
	 * @return Date    返回类型
	 */
	public static Date getDate(final Map map, final Object key) {
		if (map != null) {
			Object answer = map.get(key);
			if (answer != null) {
				if (answer instanceof Date) {
					return (Date)answer;
				}else if (answer instanceof Long) {
					return DateJodaUtil.getTimeToDate((Long) answer);
				} else if (answer instanceof Timestamp) {
					return DateJodaUtil.getTimestampToDate((Timestamp) answer);
				} else if (answer instanceof java.sql.Date) {
					return DateJodaUtil.getSqlDateToDate((java.sql.Date) answer);
				}
			}
		}
		return null;
	}

	/** 
	 * @Title: getDate 
	 * @Description: 根据传入的map集合和key值获取时间，不存在则给出默认值 
	 * @param map 元数据集合
	 * @param key 获取的关键key值
	 * @param defaultValue 时间默认值
	 * @return 参数说明
	 * @return Date    返回类型
	 */
	public static Date getDate(Map map, Object key, Date defaultValue) {
		Date date = getDate(map, key);
		if (date != null) {
			return date;
		}
		return defaultValue;
	}

	/** 
	 * @Title: getBigDecimal 
	 * @Description: 根据传入的map集合和key值获取浮点类型数据 
	 * @param map 元数据集合
	 * @param key 获取的关键key值
	 * @return 参数说明
	 * @return BigDecimal    返回类型
	 */
	public static BigDecimal getBigDecimal(final Map map, final Object key) {
		if (map != null) {
			Object answer = map.get(key);
			if (answer != null) {
				return (BigDecimal) answer;
			}
		}
		return null;
	}

	/** 
	 * @Title: getBigDecimal 
	 * @Description: 根据传入的map集合和key值获取浮点类型数据 ，不存在则给出默认值 
	 * @param map 元数据集合
	 * @param key 获取的关键key值
	 * @param defaultValue 默认值
	 * @return 参数说明
	 * @return BigDecimal    返回类型
	 */
	public static BigDecimal getBigDecimal(Map map, Object key, BigDecimal defaultValue) {
		BigDecimal bigDecimal = getBigDecimal(map, key);
		if (bigDecimal != null) {
			return bigDecimal;
		}
		return defaultValue;
	}

	/**
	 * 
	 * @Title: getObjectMap 
	 * @Description: 从MAP中获得MAP子集
	 * @param map  
	 * @param param  子集参数
	 * @return 参数说明
	 * @return Map<String,Object>    返回类型
	 */
	public static Map<String, Object> getObjectMap(Map<String, Object> map, String param) {
		@SuppressWarnings("unchecked")
		Map<String, Object> dataMap = (Map<String, Object>) map.get(param);
		return dataMap;

	}

	/**
	 * 
	 * @Title: getListObjectMap 
	 * @Description: 从MAP中获得List MAP子集
	 * @param map  
	 * @param param  子集参数
	 * @return 参数说明
	 * @return List<Map<String, Object>>    返回类型
	 */
	public static List<Map<String, Object>> getListObjectMap(Map<String, Object> map, String param) {
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> listDataMap = (List<Map<String, Object>>) map.get(param);
		return listDataMap;

	}

	/** 
	 * @Title: transBeanToMap 
	 * @Description: 将Object对象转化为Map集合
	 * @param obj 转换对象
	 * @return 参数说明
	 * @return Map<String,Object>    返回类型
	 */
	public static final Map<String, Object> transBeanToMap(Object obj) {
		if (obj == null) {
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();

				// 过滤class属性
				if (!key.equals("class")) {
					// 得到property对应的getter方法
					Method getter = property.getReadMethod();
					Object value = getter.invoke(obj);

					map.put(key, value);
				}
			}
		} catch (Exception e) {
			System.out.println("transBeanToMap Error " + e);
		}

		return map;
	}
	
	/** 
	 * @Title: convertObjMap 
	 * @Description: 将Object对象转化为Map集合
	 * @param obj 转换对象
	 * @return 参数说明
	 * @return Map<String,Object>    返回类型
	 */
	@SuppressWarnings("unchecked")
	public static final Map<String ,Object> convertObjMap(Object obj){
		return obj == null  ?null :(Map<String ,Object>)obj;
	}
	
	/** 
	 * @Title: convertObjMapOfDefaultEmpty 
	 * @Description: 将Object对象转化为Map集合
	 * @param obj 转换对象
	 * @return 参数说明
	 * @return Map<String,Object>    返回类型
	 */
	public static final Map<String ,Object> convertObjMapOfDefaultEmpty(Object obj){
		if (obj == null){
			return new HashMap<String ,Object>();
		}
		return convertObjMap(obj);
	}
}
