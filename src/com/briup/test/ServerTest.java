package com.briup.test;

import com.briup.common.IConfiguration;
import com.briup.common.impl.ConfigurationImpl;
import com.briup.server.IServer;

public class ServerTest {
	public static void main(String[] args) throws Exception {
		IConfiguration configurationImpl = new ConfigurationImpl();
		IServer server = configurationImpl.getServer();
		server.recive();
	}
}
