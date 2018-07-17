package com.briup.server;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Collection;

import org.apache.log4j.Logger;

import com.briup.bean.BIDR;
import com.briup.server.impl.DBStoreImpl;

public class ServerHandler extends Thread{
	private transient Logger logger=Logger.getLogger("serverLogger");
	private Socket socket;
	private ObjectInputStream ois;
	public ServerHandler(Socket socket) {
		super();
		this.socket = socket;
	}
	@SuppressWarnings("unchecked")
	@Override
	public void run() {
		try {
			InputStream iStream=socket.getInputStream();
			ois=new ObjectInputStream(iStream);
			Collection<BIDR> bidrs=(Collection<BIDR>) ois.readObject();
			new DBStoreImpl().save(bidrs);
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
	}
}
