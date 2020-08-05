package com.kh.baseball.board.model.service;

import java.util.List;
import java.util.Map;

import com.kh.baseball.board.model.vo.Board;

public interface BoardService {
	
	public Map<String, Object> noticeList (String orderby, int currentPage, int cntPerPage);
	public Map<String, Object> selectNotice (int noticeNo);
	
	public int insertNotice (Board board, List<Map<String, Object>> fileData);
	public int insertFile (List<Map<String, Object>> fileData);
	
	public int deleteNotice(int noticeNo);
	public void deleteFiles(int noticeNo);
	
	
	
	
	
	

}
