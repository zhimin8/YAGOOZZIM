package com.kh.common.exception;

import javax.servlet.ServletException;

public class Status404 extends ServletException{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Status404(String errMsg) {
		super(errMsg);
	}
	
	
	
}
