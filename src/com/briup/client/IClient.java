package com.briup.client;

import java.util.Collection;

import com.briup.bean.BIDR;
import com.briup.bean.IWossModuleInit;


/**
 * Client接口是采集系统网络模块客户端的规范。
 * Client的作用就是与服务器端进行通信，传递数据。
 * @author briup
 * @version 1.0 2010-9-14
 *
 */
public interface IClient extends IWossModuleInit {
	/**
	 * @function 与服务器通信，将数据发送给服务器端
	 * 
	 * @param collection
	 * 
	 * @author wangzh
	 */
	public void send(Collection<BIDR> collection);
	
}
