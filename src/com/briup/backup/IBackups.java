package com.briup.backup;

import com.briup.bean.IWossModuleInit;

/**
 * @function 备份模块
 * @author wangzh
 * @version 1.0
 *
 */
public interface IBackups extends IWossModuleInit {
	/**
	 * 备份数据时，在原来的文件上追加
	 */
	public static final boolean STORE_APPEND = true;
	
	/**
	 * 备份数据时，覆盖原文件 
	 */
	public static final boolean STORE_OVERRID = false;
	
	/**
	 * 读取备份数据时，删除备份文件
	 */
	public static final boolean LOAD_REMOVE = true;
	
	/**
	 * 读取备份数据时，保留备份文件
	 */
	public static final boolean LOAD_UNREMOVE = false;
	
	/**
	 * @function 通过key值去保存数据
	 * @param key 文件名
	 * @param object 要保存的数据
	 * @param flag 标志位
	 * @throws Exception
	 */
	public void store(String key,Object object,boolean flag) throws Exception;
	
	/**
	 * @function 通过key值去读取数据
	 * @param key
	 * @param flag
	 * @return
	 * @throws Exception
	 */
	public Object load(String key,boolean flag) throws Exception;
}
