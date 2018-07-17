package com.briup.server.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Properties;
import org.apache.log4j.Logger;
import com.briup.Utils.DBUtils;
import com.briup.backup.IBackups;
import com.briup.bean.BIDR;
import com.briup.common.IConfiguration;
import com.briup.common.IConfigurationAWare;
import com.briup.server.IDBStore;

public class DBStoreImpl implements IDBStore, IConfigurationAWare {
	private transient Logger logger = Logger.getLogger(DBStoreImpl.class);
	private IBackups backup;
	private String bakPath;
	private String fileName;

	@Override
	public void init(Properties properties) {
		bakPath = properties.getProperty("path");
	}

	@SuppressWarnings("deprecation")
	@Override
	public void save(Collection<BIDR> collection) {
		// 备份文件名
		fileName = "bakup";
		// 连接数据库
		Connection conn = DBUtils.getConnection();
		PreparedStatement pst = null;
		try {
			int batch = 0;
			// 执行语句成功的条数
			int length = 0;
			// 预处理的天数
			int day = 0;
			logger.info("进入入库模块");
			logger.info("开始入库");
			logger.info("数据包大小：" + collection.size());
			for (BIDR bidr : collection) {

				batch++;
				// 判断当前对象的登录时间和预处理的时间是否一致，同一天的用同一个preparestatement
				if (day != bidr.getLogin_date().getDate()) {
					// 获取每个对象的数据对应表的天数。
					day = bidr.getLogin_date().getDate();
					if (pst != null) {
						length += pst.executeBatch().length;
						pst.close();
					}
					String sql = "insert into t_detail_" + day + " values(?,?,?,?,?,?)";
					pst = conn.prepareStatement(sql);
					logger.info("插入到第" + day + "天的表数据中");
				}
				pst.setString(1, bidr.getAAA_login_name());
				pst.setString(2, bidr.getLogin_ip());
				pst.setTimestamp(3, new Timestamp(bidr.getLogin_date().getTime()));
				pst.setTimestamp(4, new Timestamp(bidr.getLogout_date().getTime()));
				pst.setString(5, bidr.getNAS_ip());
				pst.setInt(6, bidr.getTime_duration());
				pst.addBatch();
				if (batch % 400 == 0) {
					length += pst.executeBatch().length;
				}
			}
			conn.commit();
			logger.info("入库完成");
			length += pst.executeBatch().length;
			logger.info("插入数据：" + length);
		} catch (SQLException e) {
			logger.info("插入数据异常,即将调用备份模块", e);
			try {
				backup.store(fileName, collection, IBackups.STORE_OVERRID);
			} catch (Exception e1) {
				logger.info(e1.getMessage(), e);
			}
			logger.info("备份完成，请查看文件" + bakPath);
		}
		DBUtils.close();

	}

	@Override
	public void setConfiguration(IConfiguration configuration) {
		backup = configuration.getBackups();
	}

}
