package com.briup.client;

import java.util.Collection;

import com.briup.bean.BIDR;
import com.briup.bean.IWossModuleInit;

/**
 * @function 采集模块的规范，负责采集数据
 * @description 当调用gather方法开始采集数据
 * 
 * @author wangzh
 *
 * @version 1.0
 */
public interface IGather extends IWossModuleInit {
	/**
	 * @function 采集3A服务器上的计费数据，将数据封装成集合返回
	 * @description 1.读取数据原文件    2.解释原文件   3. 封装到集合（完整对象/不完整对象）  4.返回
	 * 
	 * @return collection
	 * @throws Exception
	 * 
	 */
	public Collection<BIDR> gather() throws Exception;
}
