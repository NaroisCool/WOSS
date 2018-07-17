package com.briup.Utils;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

public class DateUtils {
	//	判断是否跨时段，并进行处理
	@Test
	public void test(){
		Date login_date = new Date(94,11,19,12,00,00);
		System.out.println(login_date);
		login_date.setDate(login_date.getDate()+1);;
		login_date.setHours(0);
		login_date.setMinutes(0);
		login_date.setSeconds(0);
		System.out.println(login_date);
	
	}
	public void IsLongitudinally (Date current,Date login_date){
		//定义一个计算上线的时间，单位是s.
//		long during;
//		during=((current.getTime()-login_date.getTime()))/1000;
//		System.out.println(during);

	}
}
