package com.briup.common.impl;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import com.briup.backup.IBackups;
import com.briup.bean.IWossModuleInit;
import com.briup.client.IClient;
import com.briup.client.IGather;
import com.briup.common.IConfiguration;
import com.briup.common.IConfigurationAWare;
import com.briup.logger.ILogger;
import com.briup.server.IDBStore;
import com.briup.server.IServer;

public class ConfigurationImpl implements IConfiguration {
	private IBackups backup;
	private IDBStore store;
	private IServer server;
	private IClient client;
	private IGather gather;

	private transient static Logger logger = Logger.getLogger("clientLogger");
	private static Element root = null;
	static {
		try {
			File file = new File("src/config.xml");
			SAXReader reader = new SAXReader();
			Document document = null;
			document = reader.read(file);
			root = document.getRootElement();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			//对异常进行处理
			System.exit(-1);
		}
	}
	public ConfigurationImpl(String name) {

	}

	public ConfigurationImpl() {
		Map<String, IWossModuleInit> map = new HashMap<>();
		// 获取根元素的子节点
		List<Element> elements = root.elements();
		
		IWossModuleInit iws = null;
		for (Element element : elements) {
			String className = element.attributeValue("class");
			// 通过标签获取的所有模块的全类名来创建实例
			try {
				iws = (IWossModuleInit) Class.forName(className).newInstance();
			} catch (Exception e) {
				e.printStackTrace();
			}
			map.put(element.getName(), iws);
			Properties properties = new Properties();
			List<Element> elements2 = element.elements();
			for (Element element2 : elements2) {
				properties.setProperty(element2.getName(), element2.getTextTrim());
			}
			iws.init(properties);
		}

		if(map.containsKey("backup")){
			backup = (IBackups) map.get("backup");
		}
		if(map.containsKey("dbStore")){
			store = (IDBStore) map.get("dbStore");
		}
	/*	if(map.containsKey("logger")){
			loggers = (ILogger) map.get("logger");
		}*/
		if(map.containsKey("server")){
			server = (IServer) map.get("server");
		}
		if(map.containsKey("client")){
			client = (IClient) map.get("client");
		}
		if(map.containsKey("gather")){
			gather = (IGather) map.get("gather");
		}
		// 依赖注入，作用：用来引用其他的模块，放在上面判断语句的后面
		Collection<IWossModuleInit> collection = map.values();
		for (IWossModuleInit iWossModuleInit : collection) {
			//判断是否需要依赖注入
			if (iWossModuleInit instanceof IConfigurationAWare) {
					((IConfigurationAWare)iWossModuleInit).setConfiguration(this);
			}
		}
	}

	@Override
	public IBackups getBackups() {
		
		return backup;
	}

	@Override
	public IDBStore getDBStore() {
		return store;
	}
	@Override
	public IServer getServer() {
		return server;
	}

	@Override
	public IClient getClient() {
		return client;
	}

	@Override
	public IGather getGather() {
		// 采集模块需要的配置信息。
		return gather;
	}


}
