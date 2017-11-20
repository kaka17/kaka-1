package com.kaka.base.lock.zookeeper;

import com.kaka.base.config.ConfigLoader;

/**
 * 并发访问控制  帮助类-zk
 * @ClassName LockLimitZkHelper   
 * @author A10353 caowb 
 * @date 2017年7月5日  
 *   
 */
public class LockLimitZkHelper {

	/**
	 * zookeeper 轮询等待间隔时间
	 */
	public static final int WAIT_TIME = 1000;

	/**
	 *  zookeeper session 超时时间
	 */
	public static final int SESSION_TIMEOUT = 12000;

	/**
	 *  zookeeper connection超时时间
	 */
	public static final int CONNECTION_TIMEOUT = 10000;

	/**
	 * zookeeper 地址 [ 需在sysConfigure.properties配置中新增zookeeper.addr=172.16.201.8:2181,172.16.201.9:2181,172.16.201.10:2181 ]
	 */
	public static final String ZOOKEEPER_ADDR = ConfigLoader.getInstance("sysConfigure.properties")
			.getValue("zookeeper.addr");

}
