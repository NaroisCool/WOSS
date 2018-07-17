package com.briup.server.impl;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

import javax.sound.sampled.Port;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.briup.server.IServer;
import com.briup.server.ServerHandler;

public class ServerImpl implements IServer{
	private transient Logger logger=Logger.getLogger("serverLogger");
	private String port;
	@Override
	public void init(Properties properties) {
		port=properties.getProperty("port");
	}
	@Test
	@Override
	public void recive() {
		ServerSocket ss=null;
		Socket socket=null;
		try {
			ss=new ServerSocket(Integer.parseInt(port));
			while (true) {
				System.out.println("server waite");
				socket=ss.accept();
				new ServerHandler(socket).start();
			}
		} catch (IOException e) {
			logger.info(e.getMessage(),e);
			System.exit(0);
		}
	}
	@Override
	public void shutDown() throws Exception {

	}
}