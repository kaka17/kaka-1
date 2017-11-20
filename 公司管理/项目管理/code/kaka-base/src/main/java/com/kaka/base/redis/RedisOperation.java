/**   
 * @Project: tfscore 
 * @Title: RedisOperation.java 
 * @Package com.tfscore.redis 
 * @Description: Redis 操作类 
 * @author lx 
 * @date 2016年6月6日 上午9:01:05 
 * @Copyright: 2016 年 前海阿拉海钜科技. All rights reserved  
 * @version V1.0   
 *//*
package com.tfstec.base.redis;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yanxintec.nga.redis.core.RedisParam;
import com.yanxintec.nga.redis.core.support.JedisSentinelOperation;
import com.yanxintec.nga.redis.exception.RedisOperationException;
import com.yanxintec.nga.redis.serialize.support.KryoSerialization;

import redis.clients.jedis.Jedis;

*//** 
 * @ClassName RedisOperation  
 * @Description Redis 操作类 
 * @author lx 
 * @date 2016年6月6日  
 *   
 *//*
public class RedisOperation {
	private static Logger logger = LoggerFactory.getLogger(RedisOperation.class);
	private static JedisSentinelOperation jedisSentinelOperation;
	private static RedisOperation redisOperation;
	private RedisOperation() {}

	*//** 
	 * @Title: init 
	 * @Description: 初始化redis数据库连接
	 * @return void    返回类型
	 *//*
	private static void init() {
		jedisSentinelOperation = new JedisSentinelOperation();
		try {
			jedisSentinelOperation.setSerialization(new KryoSerialization());
		} catch (Exception e) {
			logger.error("#E 初始化redis发生异常，可能是连接redis失败." + e.getMessage());
		}
	}

	*//**
	 * 初始化
	 * @Title: getInstance 
	 * @Description: 初始化
	 * @return 参数说明
	 * @return JedisSentinelOperation    返回类型
	 *//*
	public static RedisOperation getInstance() {
		if (jedisSentinelOperation == null) {
			redisOperation = new RedisOperation();
			init();
		}
		return redisOperation;
	}

	*//**
	 * 新增
	 * @Title: insert 
	 * @param key
	 * @param param
	 * @return
	 * @throws RedisOperationException 参数说明
	 * @return boolean    返回类型
	 *//*
	public boolean insert(String key, RedisParam param) throws RedisOperationException {
		return jedisSentinelOperation.insert(key, param);
	}
	
	*//**
	 * 根据key查找
	 * @Title: find 
	 * @Description: 根据key查找
	 * @param key
	 * @return
	 * @throws RedisOperationException 参数说明
	 * @return Object    返回类型
	 *//*
	public Object find(String key) throws RedisOperationException{
		return jedisSentinelOperation.find(key);
	}
	
	*//**
	 * 删除key
	 * @Title: delete 
	 * @Description: 删除key 
	 * @param key
	 * @return
	 * @throws RedisOperationException 参数说明
	 * @return boolean    返回类型
	 *//*
	public boolean delete(String key) throws RedisOperationException{
		return jedisSentinelOperation.delete(key);
	}
	
	*//**
	 * 修改
	 * @Title: insert 
	 * @param key
	 * @param param
	 * @return
	 * @throws RedisOperationException 参数说明
	 * @return boolean    返回类型
	 *//*
	public boolean update(String key, RedisParam param) throws RedisOperationException {
		return jedisSentinelOperation.update(key, param);
	}
	

	*//**
	 * 不存在新增，存在修改
	 * @Title: insertOrUpdate 
	 * @param key
	 * @param param
	 * @return
	 * @throws RedisOperationException 参数说明
	 * @return boolean    返回类型
	 *//*
	public boolean insertOrUpdate(String key, RedisParam param) throws RedisOperationException {
		Object obj = jedisSentinelOperation.find(key);
		if(obj == null){
			return jedisSentinelOperation.insert(key, param);
		}else{
			return jedisSentinelOperation.update(key, param);
		}
	}
	*//** 
	 * @Title: exists 
	 * @Description: 判断一个key是否存在 
	 * @param key
	 * @throws RedisOperationException 参数说明
	 * @return boolean    返回类型
	 *//*
	public boolean exists(String key){
		Jedis jedis=null;
		try {
			jedis=jedisSentinelOperation.getResource();
			return jedis.exists(key);
		} catch (Exception e) {
			 throw new RuntimeException(e);
		}finally{
			jedisSentinelOperation.returnResource(jedis);
		}
	}
	*//** 
	 * @Title: set 
	 * @Description: 设置一个key的内容
	 * @param key
	 * @param value
	 * @return String    返回类型
	 *//*
	public String set(String key,String value){
		Jedis jedis=null;
		try {
			jedis=jedisSentinelOperation.getResource();
			return jedis.set(key, value);
		} catch (Exception e) {
			 throw new RuntimeException(e);
		}finally{
			jedisSentinelOperation.returnResource(jedis);
		}
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
	public Long zadd(String key,double score,String member){
		Jedis jedis=null;
		try {
			jedis=jedisSentinelOperation.getResource();
			return jedis.zadd(key, score, member);
		} catch (Exception e) {
			 throw new RuntimeException(e);
		}finally{
			jedisSentinelOperation.returnResource(jedis);
		}
	}
	*//**
	 * @Title: zrank 
	 * @Description: 获取key 中成员 member的倒序排名  0开始
	 * @param key
	 * @param score
	 * @param member
	 * @return 参数说明
	 * @return Long    返回类型
	 *//*
	public Long zrevrank(String key,String member){
		Jedis jedis=null;
		try {
			jedis=jedisSentinelOperation.getResource();
			return jedis.zrevrank(key, member);
		} catch (Exception e) {
			 throw new RuntimeException(e);
		}finally{
			jedisSentinelOperation.returnResource(jedis);
		}
	}
	*//**
	 * @Title: zcard 
	 * @Description: 获取key元素总量
	 * @param key
	 * @return 参数说明
	 * @return long    返回类型
	 *//*
	public Long zcard(String key){
		Jedis jedis=null;
		try {
			jedis=jedisSentinelOperation.getResource();
			return jedis.zcard(key);
		} catch (Exception e) {
			 throw new RuntimeException(e);
		}finally{
			jedisSentinelOperation.returnResource(jedis);
		}
	}
	*//** 
	 * @Title: get 
	 * @Description: 获取一个key的内容 
	 * @param key
	 * @return String    返回类型
	 *//*
	public String get(String key){
		Jedis jedis=null;
		try {
			jedis=jedisSentinelOperation.getResource();
			return jedis.get(key);
		} catch (Exception e) {
			 throw new RuntimeException(e);
		}finally{
			jedisSentinelOperation.returnResource(jedis);
		}
	}
	
	*//** 
	 * @Title: del 
	 * @Description: 删除一个key
	 * @param key
	 * @return 参数说明
	 * @return long    返回类型
	 *//*
	public long del(String key){
		Jedis jedis=null;
		try {
			jedis=jedisSentinelOperation.getResource();
			return jedis.del(key);
		} catch (Exception e) {
			 throw new RuntimeException(e);
		}finally{
			jedisSentinelOperation.returnResource(jedis);
		}
	}
	*//**
	 * 
	 * @Title: incr 
	 * @Description: 使key对应的value增加1
	 * @param key
	 * @return 参数说明
	 * @return long    返回类型
	 *//*
	public long incr(String key){
		Jedis jedis=null;
		try {
			jedis=jedisSentinelOperation.getResource();
			return jedis.incr(key);
		} catch (Exception e) {
			 throw new RuntimeException(e);
		}finally{
			jedisSentinelOperation.returnResource(jedis);
		}
	}

	*//**
	 * 
	 * @Title: expire 
	 * @Description: 设置Key的存活时间 
	 * @param key
	 * @param expireTime
	 * @return 参数说明
	 * @return long    返回类型
	 *//*
	
	public long expire(String key,int expireTime){
		Jedis jedis=null;
		try {
			jedis=jedisSentinelOperation.getResource();
			return jedis.expire(key, expireTime);
		} catch (Exception e) {
			 throw new RuntimeException(e);
		}finally{
			jedis.close();
		}
		
	}
	
	
	
	*//**
	 * byte[] 保存
	 * 
	 * @param key
	 * @param value
	 * @param expireTime
	 * @throws Exception
	 *//*
	public void insert(byte[] key, byte[] value, int expireTime){
		Jedis jedis=null;
		try {
			jedis=jedisSentinelOperation.getResource();
			jedis.set(key, value);
			if (expireTime > 0) {
				jedis.expire(key, expireTime);
			}
		} catch (Exception e) {
			 throw new RuntimeException(e);
		} finally {
			jedisSentinelOperation.returnResource(jedis);
		}
	}
	
	*//**
	 * Str 保存
	 * 
	 * @param key
	 * @param value
	 * @param expireTime
	 * @throws Exception
	 *//*
	public void insertStr(String key, String value, int expireTime){
		Jedis jedis=null;
		try {
			jedis=jedisSentinelOperation.getResource();
			jedis.set(key, value);
			if (expireTime > 0) {
				jedis.expire(key, expireTime);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			jedisSentinelOperation.returnResource(jedis);
		}
	}
	*//**
	 * byte[] key删除
	 * 
	 * @param key
	 * @throws Exception
	 *//*
	public long del(byte[] key){
		Jedis jedis=null;
		try {
			jedis=jedisSentinelOperation.getResource();
			return jedis.del(key);
		} catch (Exception e) {
			 throw new RuntimeException(e);
		} finally {
			jedisSentinelOperation.returnResource(jedis);
		}
	}
	
	*//**
	 * String byte[]
	 * 
	 * @param key
	 * @return
	 *//*
	public  byte[] get(byte[] key){
		Jedis jedis=null;
		try {
			jedis=jedisSentinelOperation.getResource();
			return jedis.get(key);
		} catch (Exception e) {
			 throw new RuntimeException(e);
		}finally{
			jedisSentinelOperation.returnResource(jedis);
		}
	}

	
	*//**
	 * 插入hset
	 * @param key
	 * @param field
	 * @param value
	 * @return
	 *//*
	public Long setHash(String key, String field, String value){
		Jedis jedis=null;
		try {
			jedis=jedisSentinelOperation.getResource();
			return jedis.hset(key, field, value);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally{
			jedisSentinelOperation.returnResource(jedis);
		}
	}
	
	*//**
	 * 获取hset里的value
	 * @param key
	 * @param field
	 * @return
	 *//*
	public String getHash(String key, String field) {
		Jedis jedis=null;
		try {
			jedis=jedisSentinelOperation.getResource();
			return jedis.hget(key, field);
		} catch (Exception e) {
			 throw new RuntimeException(e);
		}finally{
			jedisSentinelOperation.returnResource(jedis);
		}
	}
	
	*//**
	 * 删除hset
	 * @param key
	 * @param field
	 * @return
	 *//*
	public Long delHash(String key, String field) {
		Jedis jedis=null;
		try {
			jedis=jedisSentinelOperation.getResource();
			return jedis.hdel(key, field);
		} catch (Exception e) {
			 throw new RuntimeException(e);
		}finally{
			jedisSentinelOperation.returnResource(jedis);
		}
	}
	
	*//**
	 * 缓存脚本文件
	 * @Title: scriptLoad 
	 * @param script
	 * @return
	 *//*
	public String scriptLoad(String script) {
		Jedis jedis=null;
		try {
			jedis=jedisSentinelOperation.getResource();
			return jedis.scriptLoad(script);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally{
			jedisSentinelOperation.returnResource(jedis);
		}
	}
	
	*//**
	 * 执行对应脚本
	 * @Title: evalsha 
	 * @param sha1
	 * @param keys
	 * @param args
	 * @return
	 *//*
	public Object evalsha(String sha1,List<String> keys,List<String> args) {
		Jedis jedis=null;
		try {
			jedis=jedisSentinelOperation.getResource();
			return jedis.evalsha(sha1, keys, args);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally{
			jedisSentinelOperation.returnResource(jedis);
		}
	}

	
	
	public static void main(String[] args) throws RedisOperationException, Exception {
		String key = "user:score:key:";
		
		Jedis jedis=null;
		try {
			jedis=jedisSentinelOperation.getResource();
			Double test = jedis.zscore(key, "41234");
			System.out.println(test);
		} catch (Exception e) {
			 throw new RuntimeException(e);
		} finally {
			jedisSentinelOperation.returnResource(jedis);
		}
		
		
		
	}
}
*/