package com.briup.server;

import java.util.Collection;

import com.briup.bean.BIDR;
import com.briup.bean.IWossModuleInit;

/**
 * @function 将服务器端的数据持久化
 * 
 * @author wangzh
 * @version 1.0
 */
public interface IDBStore extends IWossModuleInit {
	/**
	 * @function 将数据持久化
	 * @param collection  需要存储的数据
	 * @throws Exception
	 * @author wangzh
	 */
	public void save(Collection<BIDR> collection) throws Exception;
}
