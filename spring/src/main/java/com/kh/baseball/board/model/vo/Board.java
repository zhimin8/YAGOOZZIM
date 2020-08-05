package com.kh.baseball.board.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Board implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6199283818280263744L;
	
	private int n_no;
	private String n_writer;
	private String n_title;
	private String n_cont;
	private String n_type;
	private Date n_date;
	private String original_filepath;
	private String rename_filepath;
	private int n_available;
	public int getN_no() {
		return n_no;
	}
	public void setN_no(int n_no) {
		this.n_no = n_no;
	}
	public String getN_writer() {
		return n_writer;
	}
	public void setN_writer(String n_writer) {
		this.n_writer = n_writer;
	}
	public String getN_title() {
		return n_title;
	}
	public void setN_title(String n_title) {
		this.n_title = n_title;
	}
	public String getN_cont() {
		return n_cont;
	}
	public void setN_cont(String n_cont) {
		this.n_cont = n_cont;
	}
	public String getN_type() {
		return n_type;
	}
	public void setN_type(String n_type) {
		this.n_type = n_type;
	}
	public Date getN_date() {
		return n_date;
	}
	public void setN_date(Date n_date) {
		this.n_date = n_date;
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
	public int getN_available() {
		return n_available;
	}
	public void setN_available(int n_available) {
		this.n_available = n_available;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Board [n_no=" + n_no + ", n_writer=" + n_writer + ", n_title=" + n_title + ", n_cont=" + n_cont
				+ ", n_type=" + n_type + ", n_date=" + n_date + ", original_filepath=" + original_filepath
				+ ", rename_filepath=" + rename_filepath + ", n_available=" + n_available + "]";
	}
	
	

}
