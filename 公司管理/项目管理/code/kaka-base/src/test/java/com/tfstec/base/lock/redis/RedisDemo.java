package com.tfstec.base.lock.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RedisDemo {
	private static Logger log = LoggerFactory.getLogger(RedisDemo.class);

	public static void main(String[] args) {
		execute();
	}

	public static boolean execute() {
		int limitNum = 50;
		int lockTimeOut = 10000;
		String module = "xxxxxx";
		try {
			log.info("获取锁....");
			/*LockLimitRedisUtil.getLock(module, limitNum, lockTimeOut);*/
			log.info("继续执行....");
//			Thread.sleep(Integer.parseInt(StringUtils.getRandomNumber(1))*2000);
//			Thread.sleep(10000);
			log.info("执行完成....");
		} catch (Exception e) {
			log.debug("xxxxxx", e);
		} finally {
		}

		return Boolean.FALSE;
	}

}
