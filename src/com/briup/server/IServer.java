package com.briup.server;

import com.briup.bean.IWossModuleInit;

/**
 * @function 采集系统网络模块服务器端规范
 * @description 类似于中央处理系统 ,与客户端交互，并且调用DBstrore进行入库操作，相当于调用入库模块
 * 
 * @author wangzh
 * @version 1.0
 * 
 */
public interface IServer extends IWossModuleInit {
	/**
	 * @function 接收3A服务器传过来的数据，调用入库模块进行数据持久化
	 *  
	 * @throws Exception
	 * 
	 */
	public void recive() throws Exception;
	/**
	 * @function 使server安全停止运行
	 * 
	 * @throws Exception
	 */
	public void shutDown() throws Exception;
}
