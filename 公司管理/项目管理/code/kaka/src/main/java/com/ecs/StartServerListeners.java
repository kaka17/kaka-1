package com.ecs;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StartServerListeners implements ServletContextListener{
	private static Logger logger = LoggerFactory.getLogger(StartServerListeners.class);
	
	//容器启动时加载服务
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		logger.info("项目开始启动。。。");
	}

	//容器停止时关闭服务
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		logger.info("项目结束关闭。。。");
	}

}