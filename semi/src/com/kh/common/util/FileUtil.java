package com.kh.common.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.common.frontcontroller.ModelAndView;
import com.kh.common.vo.UploadFile;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class FileUtil {

	public UploadFile fileUpload(String uploadFolder, HttpServletRequest request) {

		// 업로드할 파일의 용량 제한 : 10mb
		UploadFile uploadFile = new UploadFile();
		int maxSize = 1024 * 1024 * 10;
		String originFileName = "";
		String renameFileName = "";
		// 해당파일의 경로룰 불러와서 루트의 절대 경로를 뽑아오자
		String root = request.getServletContext().getRealPath("/");
		String savePath = root + uploadFolder;

		// request를 MultipartRequest 객체로 변환
		// MultipartRequest객체가 생성됨과 동시에 파일 업로드가 이루어 진다.
		// 파일명 중첩을 방지하려고, 임의로 넣는 것
		MultipartRequest mrequest;

		try {
			mrequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new DefaultFileRenamePolicy());
			originFileName = mrequest.getFilesystemName("noticeFile"); // 사용자가 올렸던 파일 이름이 뜨게된다

			if (originFileName != null) {

				String fileName = String.valueOf(new Date().getTime());
				renameFileName = fileName + originFileName.substring(originFileName.lastIndexOf("."));

				File originFile = new File(savePath + "\\" + originFileName);
				File renameFile = new File(savePath + "\\" + renameFileName);
				originFile.renameTo(renameFile);

				uploadFile.setisSuccess(true);
				uploadFile.setOriginFileName(originFileName);
				uploadFile.setSavePath(savePath);
				uploadFile.setMrequest(mrequest);
				uploadFile.setRenameFilename(renameFileName);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			uploadFile.setisSuccess(false);
			uploadFile.setOriginFileName(originFileName);
		}

		return uploadFile;

	}

	public boolean fileDownload(ModelAndView mav, HttpServletResponse response) {

		boolean res = false;
		File downFile = new File((String) mav.getData().get("path"));
		String ofname = (String) mav.getData().get("ofname");
		ServletOutputStream downOutput;

		try {
			response.setHeader("Content-Disposition", "attachment; filename =" + URLEncoder.encode(ofname, "utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			downOutput = response.getOutputStream();
			BufferedInputStream bin = new BufferedInputStream(new FileInputStream(downFile));

			int read = 0;
			while ((read = bin.read()) != -1) {
				downOutput.write(read);
				downOutput.flush();
			}

			downOutput.close();
			bin.close();
			res = true;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

}
