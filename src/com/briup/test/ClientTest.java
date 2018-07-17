package com.briup.test;

import com.briup.client.IClient;
import com.briup.client.IGather;
import com.briup.common.IConfiguration;
import com.briup.common.impl.ConfigurationImpl;

public class ClientTest {
	public static void main(String[] args){
		IConfiguration configurationImpl = new ConfigurationImpl();
		IClient client=configurationImpl.getClient();
		IGather gather = configurationImpl.getGather();
		try {
			client.send(gather.gather());
		} catch (Exception e) {
			System.exit(0);
		}
	}
}
