package com.briup.client.impl;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.briup.Utils.ClearLogfile;
import com.briup.backup.IBackups;
import com.briup.bean.BIDR;
import com.briup.client.IGather;
import com.briup.common.IConfiguration;
import com.briup.common.IConfigurationAWare;

public class GatherImpl implements IGather, IConfigurationAWare {
	private Logger logger = Logger.getLogger("clientLogger");
	private IBackups backups;
	private String path;
	private String gatherFileName;
	private String countFileName;
	private String pathName = "radwtmpBU";

	@Override
	public void init(Properties properties) {
		path = properties.getProperty("path");
		gatherFileName = properties.getProperty("gatherFileName");
		countFileName = properties.getProperty("countFileName");
	}

	@Test
	public void test() {

		try {
			// 将收集的数据发送给客户端。
			ClientImpl clientImp = new ClientImpl();
			clientImp.send(this.gather());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public Collection<BIDR> gather() {
		// 运行前清空log日志
		ClearLogfile clearLogfile = new ClearLogfile();
		clearLogfile.clear("src/com/briup/data/client.log");
		clearLogfile.clear("src/com/briup/data/server.log");
		clearLogfile.clear("src/com/briup/data/woss.log");
		try {
			// 设置备份的路径，创建备份实现类的对象。
			// 日志路径
			File file = new File(path);

			Object object = null;
			try {
				logger.info("加载备份文件");
				object = backups.load(pathName, IBackups.LOAD_UNREMOVE);
			} catch (Exception e) {
				logger.error("备份文件为空");
			}
			RandomAccessFile raf = new RandomAccessFile(file, "rw");
			// 存放完整数据
			Collection<BIDR> bidrs = new ArrayList<>();
			// 存放不完整数据
			@SuppressWarnings("unchecked")
			Map<String, Object> map = (object == null) ? new HashMap<String, Object>() : ((Map<String, Object>) object);
			String line = "";
			// 跳到上次读取的位置
			logger.info("进入采集模块");
			Object p = map.get("point");
			Long point = null;
			if (p == null) {
				logger.info("从0开始采集");
				point = 0L;
			} else {
				point = Long.parseLong(p.toString());
				logger.info("从" + point + "开始采集");
			}
			raf.seek(point);
			logger.info("开始采集数据");
			while ((line = raf.readLine()) != null) {
				// 每读取一行数据进行一次分割。
				String[] strs = line.split("[|]");
				BIDR bidr = (BIDR) map.get(strs[4]);
				// 判断日志文件数据的ip是否是备份文件里的存在的ip
				if (map.containsKey(strs[4])) {
					if (strs[2].equals("8")) {
						Date login_date = bidr.getLogin_date();
						Date loginout_date = new Date(Long.parseLong(strs[3]) * 1000);
						// 根据登录时间获取下一天的凌晨时间
						Calendar calendar = Calendar.getInstance();
						Date nextDay;
						// 跨了n天
						int n = loginout_date.getDate() - login_date.getDate();
						Date lastlogin_date = null;
						// 判断是否垮了时段
						if (n > 0) {
							for (int i = 0; i <= n ; i++) {
								calendar.setTime(login_date);
								calendar.add(Calendar.DAY_OF_MONTH, i+1);
								nextDay = calendar.getTime();
								nextDay.setHours(0);
								nextDay.setMinutes(0);
								nextDay.setSeconds(0);
								// 第一天的时间段
								if (i == 0) {
									bidr.setLogout_date(nextDay);
									bidr.setTime_duration(
											(int) ((bidr.getLogout_date().getTime() - bidr.getLogin_date().getTime())/1000));
									bidrs.add(bidr);
									lastlogin_date = bidr.getLogout_date();
								} else if (i < n && i > 0) {

									BIDR bidr3 = new BIDR();
									bidr3.setLogout_date(nextDay);
									bidr3.setLogin_date(lastlogin_date);
									bidr3.setAAA_login_name(bidr.getAAA_login_name());
									bidr3.setLogin_ip(bidr.getLogin_ip());
									bidr3.setNAS_ip(bidr.getNAS_ip());
									bidr3.setTime_duration((int) ((bidr3.getLogout_date().getTime()- bidr3.getLogin_date().getTime())/1000));
									bidrs.add(bidr3);
									lastlogin_date = bidr3.getLogout_date();
								} else if (i == n ) {
									// 最后一天的时段
									BIDR bidr2 = new BIDR();
									bidr2.setLogin_date(lastlogin_date);
									bidr2.setLogout_date(loginout_date);
									bidr2.setAAA_login_name(bidr.getAAA_login_name());
									bidr2.setLogin_ip(bidr.getLogin_ip());
									bidr2.setNAS_ip(bidr.getNAS_ip());
									bidr2.setTime_duration((int) ((bidr2.getLogout_date().getTime() - bidr2.getLogin_date().getTime())/1000));
									bidrs.add(bidr2);
									map.remove(strs[4]);
								}

							}

							// 第二天到第n天使完整的时间段

						} else {
							// 未跨时段，正常设置。
							bidr.setLogout_date(loginout_date);
							bidr.setTime_duration((int) ((bidr.getLogout_date().getTime() - bidr.getLogin_date().getTime())/1000));
							bidrs.add(bidr);
							map.remove(strs[4]);
						}
					} else {
						logger.info(bidr.getLogin_ip() + "出现多次上线！！");
					}
				} else if (strs[2].equals("7")) {
					// 如果读取的数据不存在map里，而且是上线的，那么这是一条新的上线数据
					BIDR bidr1 = new BIDR();
					Date date = new Date(Long.parseLong(strs[3]) * 1000);
					bidr1.setAAA_login_name(strs[0]);
					bidr1.setNAS_ip(strs[1]);
					bidr1.setLogin_date(date);
					bidr1.setLogin_ip(strs[4]);
					map.put(strs[4], bidr1);
				} else {
					// 未上线就下线，报错。
					logger.error(bidr.getLogin_ip() + "未上线就下线了！！");
				}
			}
			Collection<Object> values = map.values();
			long currentTimeMillis = System.currentTimeMillis();
			Date date = new Date(currentTimeMillis);
			for (Object object2 : values) {
				BIDR bidr = new BIDR();
				if (object2 instanceof BIDR) {
					bidr = (BIDR) object2;
				} else {
					logger.info("");
				}
				bidr.setLogout_date(date);
				bidr.setTime_duration((int) ((bidr.getLogout_date().getTime() - bidr.getLogin_date().getTime())/1000));
				bidrs.add(bidr);
			}
			// 读完后，记录指针位置
			point = raf.getFilePointer();
			logger.info("完整的数据：" + bidrs.size());
			logger.info("不完整数据：" + map.size());
			logger.info("采集结束，本次结束位置：" + point);
			// 将
			map.put("point", point);
			// 备份不完整的数据,并覆盖
			logger.info("进入备份模块");
			backups.store(pathName, map, IBackups.STORE_OVERRID);
			if (raf != null)
				raf.close();
			return bidrs;
		} catch (Exception e) {

			logger.info(e.getMessage(), e);
			return null;
		}
	}

	@Override
	public void setConfiguration(IConfiguration configuration) {
		backups = configuration.getBackups();
	}
}
