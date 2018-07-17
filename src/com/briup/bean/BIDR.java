package com.briup.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @function 信息采集bean类
 * @author wangzh
 * @version 1.0.1
 */
public class BIDR implements Serializable {

	private static final long serialVersionUID = 6649560524670625968L;
	private String AAA_login_name; //3A服务器登录名字
	private Date login_date;  //登入时间
	private Date logout_date; //登出时间
	private String NAS_ip;		   //NAS_ip
	private String login_ip;	   //登入ip
	private Integer time_duration; //上网持续时间

	public BIDR() {
	}

	public BIDR(String aAA_login_name, Date login_date,
			Date logout_date, String nAS_ip, String login_ip,
			Integer time_duration) {
		this.AAA_login_name = aAA_login_name;
		this.login_date = login_date;
		this.logout_date = logout_date;
		this.NAS_ip = nAS_ip;
		this.login_ip = login_ip;
		this.time_duration = time_duration;
	}

	public String getAAA_login_name() {
		return AAA_login_name;
	}

	public void setAAA_login_name(String aAA_login_name) {
		AAA_login_name = aAA_login_name;
	}

	public Date getLogin_date() {
		return login_date;
	}

	public void setLogin_date(Date login_date) {
		this.login_date = login_date;
	}

	public Date getLogout_date() {
		return logout_date;
	}

	public void setLogout_date(Date logout_date) {
		this.logout_date = logout_date;
	}

	public String getNAS_ip() {
		return NAS_ip;
	}

	public void setNAS_ip(String nAS_ip) {
		NAS_ip = nAS_ip;
	}

	public String getLogin_ip() {
		return login_ip;
	}

	public void setLogin_ip(String login_ip) {
		this.login_ip = login_ip;
	}

	public Integer getTime_duration() {
		return time_duration;
	}

	public void setTime_duration(Integer time_duration) {
		this.time_duration = time_duration;
	}

	@Override
	public String toString() {
		return "BIDR [AAA_login_name=" + AAA_login_name + ", login_date="
				+ login_date + ", logout_date=" + logout_date + ", NAS_ip="
				+ NAS_ip + ", login_ip=" + login_ip + ", time_duration="
				+ time_duration + "]";
	}

}
