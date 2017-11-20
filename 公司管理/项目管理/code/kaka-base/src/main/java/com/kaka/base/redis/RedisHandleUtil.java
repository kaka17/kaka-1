/**   
 * @Project: tfscore 
 * @Title: RedisHandleUtil.java 
 * @Package com.tfscore.redis 
 * @Description: 分布式变量存储操作类 
 * @author lx 
 * @date 2016年6月6日 上午9:02:27 
 * @Copyright: 2016 年 前海阿拉海钜科技. All rights reserved  
 * @version V1.0   
 *//*
package com.tfstec.base.redis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yanxintec.nga.redis.core.ExpireTime;
import com.yanxintec.nga.redis.core.ExpireTime.NXXX;
import com.yanxintec.nga.redis.core.RedisParam;
import com.yanxintec.nga.redis.exception.RedisOperationException;

*//** 
 * @ClassName RedisHandleUtil  
 * @Description 分布式变量存储操作类 
 * @author lx 
 * @date 2016年6月6日  
 *   
 *//*
public class RedisHandleUtil {
	private static RedisOperation redis = RedisOperation.getInstance();

	*//**
	 * @Title: find 
	 * @param key 外部键
	 * @throws RedisOperationException 
	 *//*
	@SuppressWarnings("unchecked")
	public static Object find(String key) throws RedisOperationException {
		Map<String, Object> paramMap = (Map<String, Object>) redis.find(key);
		if (paramMap == null) {
			paramMap = new HashMap<String, Object>();
		}
		return paramMap;
	}

	*//**
	 * @Title: find 
	 * @Description: 得到内部变量值
	 * @param key 外部键
	 * @param innerkey 内部键
	 * @throws RedisOperationException 
	 *//*
	@SuppressWarnings("unchecked")
	public static Object find(String key, String innerkey) throws RedisOperationException {
		Map<String, Object> paramMap = (Map<String, Object>) find(key);
		if (paramMap != null) {
			return paramMap.get(innerkey);
		}
		return "";
	}

	*//**
	 * @Title: insertOrUpdate 
	 * @Description:  添加修改变量
	 * @param key 外部键
	 * @param paramMap 内部变量
	 * 6*60*60*24*30= 15552000(保存半年)
	 * @throws RedisOperationException 
	 *//*
	public static void insertOrUpdate(String key, Map<String, Object> paramMap)
			throws RedisOperationException {
		redis.insertOrUpdate(key, new RedisParam(paramMap, new ExpireTime(NXXX.NX, 15552000)));
	}

	*//**
	 * 
	 * @Title: update 
	 * @Description: 避免调上文中的修改函数 更新key的存活时间 
	 * @param key 外部键
	 * @param paramMap 内部变量
	 * @throws RedisOperationException 参数说明
	 * @return void    返回类型
	 *//*
	public static void update(String key, Map<String, Object> paramMap,long expireTime)
			throws RedisOperationException {
		redis.insertOrUpdate(key,new RedisParam(paramMap, new ExpireTime(NXXX.NX, expireTime)));
	}
	
	*//**
	 * 
	 * @Title: insert 
	 * @Description: 添加修改变量 
	 * @param key
	 * @param paramMap
	 * @param expireTime  过期时间
	 * @throws RedisOperationException 参数说明
	 * @return void    返回类型
	 *//*
	public static void insert(String key, Map<String, Object> paramMap,long expireTime)
			throws RedisOperationException {
		redis.insertOrUpdate(key, new RedisParam(paramMap, new ExpireTime(NXXX.NX, expireTime)));
	}
	
	
	public static void insert(String key, Object paramMap,long expireTime)
			throws RedisOperationException {
		redis.insert(key, new RedisParam(paramMap, new ExpireTime(NXXX.NX, expireTime)));
	}
	
	*//**
	 * byte[] 保存
	 * @param key
	 * @param value
	 * @param expireTime
	 * @throws RedisOperationException
	 *//*
	public static void insert(byte[] key, byte[] value, int expireTime)
			throws RedisOperationException {
		redis.insert(key,value,expireTime);
	}
	*//**
	 * byte[] 保存
	 * @param key
	 * @param value
	 * @param expireTime
	 * @throws RedisOperationException
	 *//*
	public static void insertStr(String key, String value, int expireTime)
			throws RedisOperationException {
		redis.insertStr(key,value,expireTime);
	}
	
	*//**
	 * @Title: delete 
	 * @Description: 删除变量 
	 * @param key 外部键
	 * @throws RedisOperationException 
	 *//*
	public static void delete(String key) throws RedisOperationException {
		redis.delete(key);
	}

	*//** 
	 * @Title: exists 
	 * @Description: 判断一个key是否存在 
	 * @param key
	 * @throws RedisOperationException 参数说明
	 * @return boolean    返回类型
	 *//*
	public static boolean exists(String key) {
		return redis.exists(key);
	}

	*//** 
	 * @Title: set 
	 * @Description: 设置一个key的内容
	 * @param key
	 * @param value
	 * @return 参数说明
	 * @return String    返回类型
	 *//*
	public static String set(String key, String value) {
		return redis.set(key, value);
	}
	*//**
	 * @Title: zadd 
	 * @Description: 设置一个key 分数score 成员member
	 * @param key
	 * @param score
	 * @param member
	 * @return 参数说明
	 * @return Long    返回类型
	 *//*
	public static Long zadd(String key,double score,String member)
			throws RedisOperationException {
		return redis.zadd(key, score, member);
	}
	*//**
	 * @Title: zrank 
	 * @Description: 获取key 中成员 member的顺序排名  0开始
	 * @param key
	 * @param score
	 * @param member
	 * @return 参数说明
	 * @return Long    返回类型
	 *//*
	public static Long zrevrank(String key,String member)
			throws RedisOperationException {
		return redis.zrevrank(key, member);
	}
	*//**
	 * @Title: zcard 
	 * @Description: 获取key元素总量
	 * @param key
	 * @return 参数说明
	 * @return long    返回类型
	 *//*
	public static Long zcard(String key)
			throws RedisOperationException {
		return redis.zcard(key);
	}
	*//**
	 * 
	 * @Title: set 
	 * @Description: 创建一个key 值为value 存活时间为 expireTime
	 * @param key
	 * @param expireTime
	 * @param value
	 * @return 参数说明
	 * @return long    返回类型
	 *//*
	public static long set(String key ,int expireTime,String value){
		
		redis.set(key, value);
		return redis.expire(key, expireTime);
	}
	
	*//**
	 * 
	 * @Title: expire 
	 * @Description: 将key的存活时间设为 expireTime
	 * @param key
	 * @param expireTime
	 * @return 参数说明
	 * @return long    返回类型
	 *//*
	public static long expire(String key ,int expireTime){
		return redis.expire(key, expireTime);
	}

	*//** 
	 * @Title: get 
	 * @Description: 获取一个key的内容 
	 * @param key
	 * @return String    返回类型
	 *//*
	public static String get(String key) {
		return redis.get(key);
	}
	
	*//** 
	 * @Title: get 
	 * @Description: 获取一个key的内容 
	 * @param key
	 * @return String    返回类型
	 *//*
	public static byte[] get(byte[] key) {
		return redis.get(key);
	}
	
	

	*//** 
	 * @Title: del 
	 * @Description: 删除一个key
	 * @param key
	 * @return 参数说明
	 * @return long    返回类型
	 *//*
	public static long del(String key) {
		return redis.del(key);
	}
	
	*//**
	 * del byte[]
	 * @param key
	 *//*
	public static long del(byte[] key){
		return redis.del(key);
	}
	
	*//**
	 * @Title: delete 
	 * @Description: 删除变量 
	 * @param key 外部键
	 * @throws RedisOperationException 
	 *//*
	@SuppressWarnings("unchecked")
	public static void delete(String key, String innerkey) throws RedisOperationException {
		Object obj = find(key);
		if (obj != null) {
			Map<String, Object> paramMap = (Map<String, Object>) obj;
			if (paramMap != null) {
				paramMap.remove(innerkey);
				insertOrUpdate(key, paramMap);
			}
		}
	}
	*//**
	 * @Title: incr 
	 * @Description: 给指定的 key 的值加一 并返回
	 * @param key
	 * @return 参数说明
	 * @return String    返回类型
	 *//*
	public static String incr(String key){
		return String.valueOf(redis.incr(key));
	}
	
	
	*//**
	 * @Title: saveHash 
	 * @param key
	 * @param field
	 * @param value
	 * @return
	 *//*
	public static Long setHash(String key, String field, String value){
		return redis.setHash(key, field, value);
	}
	
	*//**
	 * @Title: getHash 
	 * @param key
	 * @param field
	 * @return
	 *//*
	public static String getHash(String key, String field){
		return redis.getHash(key, field);
	}
	
	*//**
	 * @Title: delHash 
	 * @param key
	 * @param field
	 * @return
	 *//*
	public static Long delHash(String key, String field){
		return redis.delHash(key, field);
	}
	

	*//**
	 * 缓存脚本文件
	 * @Title: scriptLoad 
	 * @param script
	 * @return
	 *//*
	public static String scriptLoad(String script) {
		return redis.scriptLoad(script);
	}

	*//**
	 * 执行对应脚本
	 * @Title: evalsha 
	 * @param sha1
	 * @param keys
	 * @param args
	 * @return
	 *//*
	public static Object evalsha(String sha1, List<String> keys, List<String> args) {
		return redis.evalsha(sha1, keys, args);
	}
}
*/