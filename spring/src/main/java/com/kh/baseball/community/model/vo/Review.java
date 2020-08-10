package com.kh.baseball.community.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Review implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 454978962857888584L;

	private int r_no;
	private String r_writer;
	private String r_type;
	private String r_title;
	private String r_cont;
	private Date r_date;
	private String original_filepath;
	private String rename_filepath;
	private int r_available;

	
	public int getR_no() {
		return r_no;
	}

	public void setR_no(int r_no) {
		this.r_no = r_no;
	}


	public String getR_writer() {
		return r_writer;
	}


	public void setR_writer(String r_writer) {
		this.r_writer = r_writer;
	}


	public String getR_type() {
		return r_type;
	}


	public void setR_type(String r_type) {
		this.r_type = r_type;
	}


	public String getR_title() {
		return r_title;
	}


	public void setR_title(String r_title) {
		this.r_title = r_title;
	}


	public String getR_cont() {
		return r_cont;
	}


	public void setR_cont(String r_cont) {
		this.r_cont = r_cont;
	}


	public Date getR_date() {
		return r_date;
	}


	public void setR_date(Date r_date) {
		this.r_date = r_date;
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


	public int getR_available() {
		return r_available;
	}


	public void setR_available(int r_available) {
		this.r_available = r_available;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	
	

	

}
