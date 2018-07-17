package com.briup.client.impl;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Collection;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.briup.bean.BIDR;
import com.briup.client.IClient;

public class ClientImpl implements IClient {
	private transient Logger logger=Logger.getLogger("clientLogger");
	private String port;
	private String ip;
	@Override
	public void init(Properties properties) {
		port=properties.getProperty("port");
		ip=properties.getProperty("ip");
	}
	

	@Override
	public void send(Collection<BIDR> collection) {
		Socket socket=null;
		OutputStream os=null;
		ObjectOutputStream oos=null;
		try {
			System.out.println("开始发送数据");
			socket=new Socket(ip, Integer.parseInt(port));
			os=socket.getOutputStream();
			oos=new ObjectOutputStream(os);
			oos.writeObject(collection);
		} catch (IOException e) {
			logger.info(e.getMessage(),e);
		}
	}
}
