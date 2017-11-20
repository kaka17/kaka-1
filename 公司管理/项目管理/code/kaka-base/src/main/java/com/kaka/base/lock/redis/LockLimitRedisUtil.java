/*package com.tfstec.base.lock.redis;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.tfstec.base.redis.RedisHandleUtil;

*//**
 * 并发访问控制类-同一秒内允许访问数-redis
 * @ClassName LockLimitRedisUtil   
 * @author A10353 caowb 
 * @date 2017年7月5日  
 *   
 *//*
public class LockLimitRedisUtil {
	private static Logger log = LoggerFactory.getLogger(LockLimitRedisUtil.class);

	*//**
	 * 获取锁
	 * @Title: getLock  
	 * @param module 模块名
	 * @param limitNum 控制并发数
	 * @param lockTimeOut 控制超时时间（超时此设置时间将会获取锁）
	 *//*
	public static void getLock(String module, Integer limitNum, Integer lockTimeOut) {
		List<String> keys = Lists.newArrayList(LockLimitRedisHelper.REDIS_KEY_PREFIX+module);
		List<String> args = Lists.newArrayList(String.valueOf(limitNum));
		try {
			int time = 0;
			while (time < lockTimeOut) {
				Long evalsha = (Long) RedisHandleUtil.evalsha(LockLimitRedisHelper.REDIS_SCRIPT_SHA,
						keys, args);
				if (evalsha == LockLimitRedisHelper.LOCK_ACQUIRE) {
					return;
				}
				if (time > lockTimeOut - LockLimitRedisHelper.CRISIS_TIME) {
					time += LockLimitRedisHelper.CRISIS_WAIT_TIME;
					Thread.sleep(LockLimitRedisHelper.CRISIS_WAIT_TIME);
				} else {
					time += LockLimitRedisHelper.WAIT_TIME;
					Thread.sleep(LockLimitRedisHelper.WAIT_TIME);
				}
				log.debug("{}-未获得锁-需等待>>>已等待:{}", module,time);
			}
			log.debug("{}-未获得锁-等待获取超时", module);
		} catch (Exception e) {
			log.error("{}-获取锁失败", module, e);
		}

	}

}
*/