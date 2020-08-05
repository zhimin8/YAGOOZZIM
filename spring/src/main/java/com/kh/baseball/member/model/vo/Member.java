package com.kh.baseball.member.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Member implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5727382092127915468L;
	
	private String m_id;
	private String m_password;
	private String m_tell;
	private String m_email;
	private String m_teamname;
	private Date m_enroll;
	private String m_leaveyn;
	
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	public String getM_password() {
		return m_password;
	}
	public void setM_password(String m_password) {
		this.m_password = m_password;
	}
	public String getM_tell() {
		return m_tell;
	}
	public void setM_tell(String m_tell) {
		this.m_tell = m_tell;
	}
	public String getM_email() {
		return m_email;
	}
	public void setM_email(String m_email) {
		this.m_email = m_email;
	}
	public String getM_teamname() {
		return m_teamname;
	}
	public void setM_teamname(String m_teamname) {
		this.m_teamname = m_teamname;
	}
	public Date getM_enroll() {
		return m_enroll;
	}
	public void setM_enroll(Date m_enroll) {
		this.m_enroll = m_enroll;
	}
	public String getM_leaveyn() {
		return m_leaveyn;
	}
	public void setM_leaveyn(String m_leaveyn) {
		this.m_leaveyn = m_leaveyn;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Member [m_id=" + m_id + ", m_password=" + m_password + ", m_tell=" + m_tell + ", m_email=" + m_email
				+ ", m_teamname=" + m_teamname + ", m_enroll=" + m_enroll + ", m_leaveyn=" + m_leaveyn + "]";
	}
	
	

	
	

}
