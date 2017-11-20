/*package com.tfstec.base.lock.redis;

import com.tfstec.base.StringUtils;
import com.tfstec.base.redis.RedisHandleUtil;

*//**
 * 并发访问控制  帮助类-redis
 * @ClassName LockLimitRedisHelper   
 * @author A10353 caowb 
 * @date 2017年7月5日  
 *   
 *//*
public class LockLimitRedisHelper {

	*//**
	 * redis 轮询等待间隔时间
	 *//*
	public static final int WAIT_TIME = 1000;

	*//**
	 * redis 临近超时时间点
	 *//*
	public static final int CRISIS_TIME = 2000;

	*//**
	 * redis 临近超时时间点 轮询递减时间
	 *//*
	public static final int CRISIS_WAIT_TIME = 200;

	*//**
	 * 获取到锁
	 *//*
	public static final Long LOCK_ACQUIRE = 1L;

	*//**
	 * redis key 前缀
	 *//*
	public static final String REDIS_KEY_PREFIX = "lock:limit:";
	*//**
	 * redis script sha key
	 *//*
	public static final String REDIS_KEY_SCRIPT_SHA = "lock:sha";
	*//**
	 * redis script sha 
	 *//*
	public static final String REDIS_SCRIPT_SHA;

	static {
		synchronized (LockLimitRedisHelper.class) {
			String sha = RedisHandleUtil.get(REDIS_KEY_SCRIPT_SHA);
			if (StringUtils.isNotBlank(sha)) {
				REDIS_SCRIPT_SHA = sha;
			} else {
				StringBuilder script = new StringBuilder();
				script.append("local key = KEYS[1] \n");
				script.append("local limit = tonumber(ARGV[1]) \n");
				script.append("local current = tonumber(redis.call('get', key) or \"0\") \n");
				script.append("if current + 1 > limit then \n");
				script.append("    return 0 \n");
				script.append("else \n");
				script.append("    redis.call(\"INCRBY\", key,\"1\") \n");
				script.append("    redis.call(\"expire\", key,\"1\") \n");
				script.append("    return 1 \n");
				script.append("end");
				REDIS_SCRIPT_SHA = RedisHandleUtil.scriptLoad(script.toString());
				RedisHandleUtil.set(REDIS_KEY_SCRIPT_SHA, REDIS_SCRIPT_SHA);
			}
		}

	}

}
*/