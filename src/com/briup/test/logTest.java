package com.briup.test;

import org.apache.log4j.Logger;
import org.junit.Test;

public class logTest {

	//在每一个需要log的地方都初始化一个私有静态的变量

	private transient Logger logger=Logger.getLogger("serverLogger");
	@Test
	public void test(){
		try {
			int a=100/0;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//参数e代表异常的位置
			logger.info(e.getMessage(),e);;
		}
	}
}
