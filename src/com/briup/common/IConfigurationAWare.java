package com.briup.common;

/**
 * 当某模块实现了该接口，那么配置模块在初始化该模块的同时会将自身的引用传递给该模块。
 * @author briup
 * @version 1.0 2010-9-14
 */
public interface IConfigurationAWare {
	/**
	 * @function 传递配置化模块
	 * @param configuration
	 */
	public void setConfiguration(IConfiguration configuration);
}
