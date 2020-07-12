package com.kh.common.vo;

import com.oreilly.servlet.MultipartRequest;

public class UploadFile {
	
	private boolean isSuccess = false;
	private String originFileName;
	private String renameFilename;
	private String savePath;
	private MultipartRequest mrequest;
	
	public UploadFile() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UploadFile(boolean isSuccess, String originFileName, String renameFilename, String savePath,
			MultipartRequest mrequest) {
		super();
		this.isSuccess = isSuccess;
		this.originFileName = originFileName;
		this.renameFilename = renameFilename;
		this.savePath = savePath;
		this.mrequest = mrequest;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setisSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getOriginFileName() {
		return originFileName;
	}

	public void setOriginFileName(String originFileName) {
		this.originFileName = originFileName;
	}

	public String getRenameFilename() {
		return renameFilename;
	}

	public void setRenameFilename(String renameFilename) {
		this.renameFilename = renameFilename;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public MultipartRequest getMrequest() {
		return mrequest;
	}

	public void setMrequest(MultipartRequest mrequest) {
		this.mrequest = mrequest;
	}


}
