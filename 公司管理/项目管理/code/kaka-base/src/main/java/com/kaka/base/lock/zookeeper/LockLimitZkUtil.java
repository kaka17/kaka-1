package com.kaka.base.lock.zookeeper;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.I0Itec.zkclient.ZkClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.kaka.base.StringUtils;

/**
 * 并发访问控制类-zk
 * @ClassName LockLimitZkUtil   
 * @author A10353 caowb 
 * @date 2017年7月5日  
 *   
 */
public class LockLimitZkUtil {
	private static Logger log = LoggerFactory.getLogger(LockLimitZkUtil.class);

	private ZkClient zkClient = null;

	private String baseNode = null;// 根节点（以项目模块命名）
	private String node = null;// 具体子节点
	private Integer limitNum = null;// 控制并发数
	private Integer lockTimeOut = null;// 控制超时时间（超时此设置时间将会获取锁）

	/**
	 * 实列化类
	 * @param baseNode 根节点（module 项目模块命名）
	 * @param limitNum 控制并发数
	 * @param lockTimeOut 控制超时时间（超时此设置时间将会获取锁）
	 */
	public LockLimitZkUtil(String baseNode, Integer limitNum, Integer lockTimeOut) {
		zkClient = new ZkClient(LockLimitZkHelper.ZOOKEEPER_ADDR, LockLimitZkHelper.SESSION_TIMEOUT,
				LockLimitZkHelper.CONNECTION_TIMEOUT);
		this.baseNode = "/"+baseNode;
		this.limitNum = limitNum;
		this.lockTimeOut = lockTimeOut;

	}

	/**
	 * 创建根节点
	 * @Title: createBaseNode
	 */
	private void createBaseNode() {
		try {
			if (!zkClient.exists(baseNode)) {
				zkClient.createPersistent(baseNode);
			}
		} catch (Exception e) {
		}
	}

	/**
	 * 创建子节点
	 * @Title: createNode
	 */
	private void createNode() {
		try {
			zkClient.createEphemeralSequential(buildNodeFullPath(), null);
			return;
		} catch (Exception e) {
			synchronized (LockLimitZkHelper.class) {
				createBaseNode();
			}
		}
		zkClient.createEphemeralSequential(buildNodeFullPath(), null);
	}

	/**
	 * 删除子节点
	 * @Title: delNode
	 */
	private void delNode() {
		zkClient.delete(buildNodeFullPath());
	}

	/**
	 * 关闭connection
	 * @Title: closeConnection
	 */
	private void closeConnection() {
		zkClient.close();
	}

	/**
	 * 构建子节点全路径 
	 * @Title: buildNodeFullPath 
	 * @return
	 */
	private String buildNodeFullPath() {
		return baseNode + "/" + node;
	}

	/**
	 * 获取根节点下所有子节点并排序
	 * @Title: getNodeList 
	 * @return
	 */
	private List<String> getNodeList() {
		List<String> nodeList = zkClient.getChildren(baseNode);
		if (nodeList == null) {
			return Lists.newArrayList();
		}
		getSortList(nodeList);
		return nodeList;
	}

	/**
	 * 对子节点排序
	 * @Title: getSortList 
	 * @param nodeList
	 */
	private void getSortList(List<String> nodeList) {
		Collections.sort(nodeList, new Comparator<String>() {
			public int compare(String strOne, String strTwo) {
				int len = node.length();
				return strOne.substring(len).compareTo(strTwo.substring(len));
			}
		});
	}

	/**
	 * 获取节点在所有子节点中位置
	 * @Title: getNodeIndex 
	 * @param nodeList
	 * @return
	 */
	private Integer getNodeIndex(List<String> nodeList) {
		int index = -1;
		for (int i = 0, j = nodeList.size(); i < j; i++) {
			String str = nodeList.get(i);
			if (str.substring(0, node.length()).equals(node)) {
				index = i;
				break;
			}
		}
		return index;
	}

	/**
	 * 获取锁
	 * @Title: getLock
	 */
	public void getLock() {
		this.node = StringUtils.getUuidNm();
		createNode();
		List<String> nodeList = getNodeList();
		int index = getNodeIndex(nodeList);
		if (index < limitNum) {
			return;
		}
		int nodeSize = nodeList.size();
		try {
			int time = 0;
			while (time < lockTimeOut) {
				log.debug("{}-未获得锁-需等待>>>当前节点共:{},位置:{}", baseNode, nodeSize, index+1);
				Thread.sleep(LockLimitZkHelper.WAIT_TIME);
				nodeList = getNodeList();
				index = getNodeIndex(nodeList);
				if (index < limitNum) {
					return;
				}
				nodeSize = nodeList.size();
				time += LockLimitZkHelper.WAIT_TIME;
			}
			log.debug("{}-未获得锁-等待获取超时>>>当前节点共:{},位置:{}", baseNode, nodeSize, index+1);

		} catch (Exception e) {
			log.error("{}-获取锁失败>>>当前节点共:{},位置:{}", baseNode, nodeSize, index+1, e);
		}
	}

	/**
	 * 删除与回收资源
	 * @Title: release
	 */
	public void release() {
		delNode();
		closeConnection();
	}

}
