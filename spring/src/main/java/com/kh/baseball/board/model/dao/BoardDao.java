package com.kh.baseball.board.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.baseball.board.model.vo.Board;

import common.util.Paging;

@Repository
public class BoardDao {
	
	@Autowired
	private SqlSessionTemplate session;
	
	// 게시물 갯수 파악
	public int contentCnt() {
		return session.selectOne("Board.contentCnt");
	}
	
	public List<Board> noticeList(Paging page, String orderby){
		Map<String, Object> data = new HashMap<>();
		data.put("page", page);
		data.put("orderby", orderby);
		return session.selectList("Board.noticeList", data);
	}
	
	public Board selectNotice(int noticeNo) {
		return session.selectOne("Board.selectNotice", noticeNo);
	}
	
	public List<Map<String,Object>> selectFiles(int noticeNo){
		return session.selectList("Board.selectFiles", noticeNo);
	}
	
	public int insertNotice(Board board) {
		return session.insert("Board.insertNotice", board);
	}
	
	public int insertFile(Map<String, Object> file) {
		return session.insert("Board.insertFile", file);
	}
	
	public int deleteNotice(int noticeNo) {
		return session.delete("Board.deleteNotice", noticeNo);
	}
	
	public int deleteFile(int noticeNo) {
		return session.delete("Board.deleteFile", noticeNo);
	}
	
	
	
	
	
	
	

}
