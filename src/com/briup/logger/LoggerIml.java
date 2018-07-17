package com.briup.logger;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LoggerIml implements ILogger{

	@Override
	public void init(Properties properties) {
		// TODO Auto-generated method stub
		
	}
	private Logger logger;
	public LoggerIml() {
		// TODO Auto-generated constructor stub
		
		PropertyConfigurator.configure("src/log4j.properties");
		Logger rootLogger = logger.getRootLogger();
		
	}
	@Override
	public void debug(String msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void info(String msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void warn(String msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void error(String msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fatal(String msg) {
		// TODO Auto-generated method stub
		
	}

}
