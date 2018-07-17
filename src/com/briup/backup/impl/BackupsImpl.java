package com.briup.backup.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.briup.backup.IBackups;

public class BackupsImpl implements IBackups{
	private transient Logger logger=Logger.getLogger(BackupsImpl.class);
	private String path;
	@Override
	public void init(Properties properties) {
		// TODO Auto-generated method stub
		path = properties.getProperty("path");
	}
	/**
	 * @function 通过key值去保存数据
	 * @param key 文件名
	 * @param object 要保存的数据
	 * @param flag 标志位
	 * @throws Exception
	 */
	@Override
	public void store(String key, Object object, boolean flag) throws Exception {
		File file=new File(path+key);
		if(!file.exists()){
			file.createNewFile();
		}
		ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(path+key,flag));
		logger.info("开始备份不完整数据");
		oos.writeObject(object);
		logger.info("备份完成");
	}

	@Override
	public Object load(String key, boolean flag) throws Exception {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path+key));
		Object object = ois.readObject();
		return object;
	}
	// 获取备份的数据
			
}
