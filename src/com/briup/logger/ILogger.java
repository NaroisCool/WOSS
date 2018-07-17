package com.briup.logger;

import com.briup.bean.IWossModuleInit;

/**
 * @function 该接口提供了日志模块的规范
 * @description 日志模块将日志信息划分为五种级别，具体的日志级别，格式，记录方式有实现类决定
 * 
 * @author wangzh
 * @version 1.0
 * 
 */
public interface ILogger extends IWossModuleInit {

	/**
	 * @description 记录Debug级别的日志
	 * @param msg 需要记录的日志信息
	 */
	public void debug(String msg);

	/**
	 * @description 记录info级别的日志
	 * @param msg 需要记录的日志信息
	 */
	public void info(String msg);

	/**
	 * @description 记录warn级别的日志
	 * @param msg 需要记录的日志信息
	 */
	public void warn(String msg);

	/**
	 * @description 记录error级别的日志
	 * @param msg 需要记录的日志信息
	 */
	public void error(String msg);

	/**
	 * @description 记录Debug级别的日志
	 * @param msg 需要记录的日志信息
	 */
	public void fatal(String msg);
}
