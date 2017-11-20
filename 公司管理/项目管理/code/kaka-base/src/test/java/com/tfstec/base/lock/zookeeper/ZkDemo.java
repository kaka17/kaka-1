package com.tfstec.base.lock.zookeeper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kaka.base.lock.zookeeper.LockLimitZkUtil;

public class ZkDemo {
	private static Logger log = LoggerFactory.getLogger(ZkDemo.class);

	public static void main(String[] args) {
		execute();
	}

	public static boolean execute() {
		int limitNum = 1;
		int lockTimeOut = 2000000;
		String baseNode = "moduleName";

		LockLimitZkUtil zkClientUtil = new LockLimitZkUtil(baseNode, limitNum, lockTimeOut);
		try {
			log.info("获取锁....");
			zkClientUtil.getLock();
			log.info("继续执行....");
//			Thread.sleep(Integer.parseInt(StringUtils.getRandomNumber(1))*2000);
//			Thread.sleep(10000);
			log.info("执行完成....");
		} catch (Exception e) {
			log.debug("xxxxxx", e);
		} finally {
			zkClientUtil.release();
		}

		return Boolean.FALSE;
	}
}
