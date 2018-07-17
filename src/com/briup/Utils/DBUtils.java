package com.briup.Utils;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import oracle.jdbc.driver.OracleDriver;

public class DBUtils {
	private static String driver;
	private static String url;
	private static String user;
	private static String password;
	private static Connection conn = null;
	private static Statement st = null;
	private static PreparedStatement pst = null;
	
	static {
		Properties pro = new Properties();
		try {
			pro.load(new FileInputStream("src/com/briup/Utils/db.properties"));
			driver = pro.getProperty("driver");
			url = pro.getProperty("url");
			user = pro.getProperty("user");
			password = pro.getProperty("password");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static Connection getConnection() {
		try {
			
			Class.forName(driver);
			// 第三种注册Driver
			 //Driver driver=new OracleDriver();
			// DriverManager.registerDriver(driver);
			conn = DriverManager.getConnection(url, user, password);
			st = conn.createStatement();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

	public static void main(String[] args) {
		DBUtils dbUtils = new DBUtils();
		System.err.println(dbUtils.getConnection());
	}

	public static void close() {
		try {
			if (st != null)
				st.close();
			if (pst != null)
				pst.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Statement getSt() {
		return st;
	}

	public static void setSt(Statement st) {
		DBUtils.st = st;
	}
}