package com.kh.baseball.community.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Tip implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5284096267109121128L;
	
	private int t_no;
	private String t_writer;
	private String t_type;
	private String t_title;
	private String t_cont;
	private Date t_date;
	private String original_filepath;
	private String rename_filepath;
	private int t_available;

	public int getT_no() {
		return t_no;
	}

	public void setT_no(int t_no) {
		this.t_no = t_no;
	}

	public String getT_writer() {
		return t_writer;
	}

	public void setT_writer(String t_writer) {
		this.t_writer = t_writer;
	}

	public String getT_type() {
		return t_type;
	}

	public void setT_type(String t_type) {
		this.t_type = t_type;
	}

	public String getT_title() {
		return t_title;
	}

	public void setT_title(String t_title) {
		this.t_title = t_title;
	}

	public String getT_cont() {
		return t_cont;
	}

	public void setT_cont(String t_cont) {
		this.t_cont = t_cont;
	}

	public Date getT_date() {
		return t_date;
	}

	public void setT_date(Date t_date) {
		this.t_date = t_date;
	}

	public String getOriginal_filepath() {
		return original_filepath;
	}

	public void setOriginal_filepath(String original_filepath) {
		this.original_filepath = original_filepath;
	}

	public String getRename_filepath() {
		return rename_filepath;
	}

	public void setRename_filepath(String rename_filepath) {
		this.rename_filepath = rename_filepath;
	}

	public int getT_available() {
		return t_available;
	}

	public void setT_available(int t_available) {
		this.t_available = t_available;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	

}
