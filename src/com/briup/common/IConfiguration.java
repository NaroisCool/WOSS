package com.briup.common;

import com.briup.backup.IBackups;
import com.briup.client.IClient;
import com.briup.client.IGather;
import com.briup.logger.ILogger;
import com.briup.server.IDBStore;
import com.briup.server.IServer;

/**
 * Configuration接口提供了配置模块的规范。
 * 配置模块通过某种配置方式将Logger、BackUP、Gather、Client、Server、DBStore等模块的实现类进行实例化，
 * 并且将其所需要配置信息予以传递。通过配置模块可以获得各个模块的实例。
 * 
 * @author briup
 * @version 1.0 2010-9-14
 */
public interface IConfiguration {
	/**
	 * @function 获取备份模块的实例
	 * @return
	 * @throws Exception
	 */
	public IBackups getBackups();

	/**
	 * @function 获取入库模块实例
	 * @return
	 * @throws Exception
	 */
	public IDBStore getDBStore();

	/**
	 * @function 获取日志模块的实例
	 * @return
	 * @throws Exception
	 */
//	public ILogger getLogger();

	/**
	 * @function 获取服务器端的实例
	 * @return
	 * @throws Exception
	 */
	public IServer getServer();

	/**
	 * @function 获取客户端的实例
	 * @return
	 * @throws Exception
	 */
	public IClient getClient();

	/**
	 * @function 获取采集模块的实例
	 * @return
	 * @throws Exception
	 */
	public IGather getGather();
}
