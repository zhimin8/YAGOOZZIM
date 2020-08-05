package com.kh.baseball.board.model.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kh.baseball.board.model.dao.BoardDao;
import com.kh.baseball.board.model.vo.Board;

import common.util.Paging;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardDao bDao;

	@Override
	public Map<String, Object> noticeList(String orderby, int currentPage, int cntPerPage) {
		Map<String, Object> res = new HashMap<>();

		Paging page = new Paging(bDao.contentCnt(), currentPage, cntPerPage);
		List<Board> blist = bDao.noticeList(page, orderby);
		res.put("paging", page);
		res.put("blist", blist);

		return res;
	}

	@Override
	public Map<String, Object> selectNotice(int noticeNo) {
		Map<String, Object> res = new HashMap<>();
		Board board = bDao.selectNotice(noticeNo);
		List<Map<String, Object>> flist = bDao.selectFiles(noticeNo);

		res.put("board", board);
		res.put("flist", flist);

		return res;
	}

	@Override
	public int insertNotice(Board board, List<Map<String, Object>> fileData) {
		int temp = bDao.insertNotice(board);

		return temp;
	}

	@Override
	public int insertFile(List<Map<String, Object>> fileData) {
		int res = 0;
		for (Map<String, Object> file : fileData) {
			res = bDao.insertFile(file);
			MultipartFile mf = (MultipartFile) file.get("file");
			File f = new File((String) file.get("savePath"));

			try {
				mf.transferTo(f);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}
		return res;
	}

	@Override
	public int deleteNotice(int noticeNo) {
		deleteFiles(noticeNo);
		return bDao.deleteNotice(noticeNo);
	}

	@Override
	public void deleteFiles(int noticeNo) {
		List<Map<String, Object>> files = bDao.selectFiles(noticeNo);
		if(files.size() > 0) {
			for (Map<String, Object> filePath : files) {
				// 파일들의 파일 정보를 가져와서 씌워줌
				File file = new File((String) filePath.get("savePath"));
				file.delete();
			}
		}
	}
	
	
	
	
	
	
	
	
}
