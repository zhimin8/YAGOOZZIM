package com.kh.baseball.board.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kh.baseball.board.model.dao.BoardDao;
import com.kh.baseball.board.model.vo.Board;
import com.kh.common.JDBCTemplate;
import com.kh.common.util.Paging;

public class BoardService {
	
	JDBCTemplate jdt = JDBCTemplate.getInstance();
	BoardDao bdao = new BoardDao();
	
	// 공지사항 리스트
	public Map<String, Object> noticeList(String orderby, int currentPage, int cntPerPage){
		Map<String, Object> res = new HashMap<>();
		Connection con = jdt.getConnection();
		Paging p =null;
		List<Board> blist = null;
		
		p = new Paging(bdao.getNext(),currentPage, cntPerPage);

		try {
			blist = bdao.noticeList(con, p, orderby);
			res.put("paging", p);
			res.put("blist", blist);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return res;
	}

	// 목록에서 공지사항 선택시 보여지는  자세한 내용
	public Board selectNotice(int noticeNo) throws SQLException {
				
		Board board = null;
		Connection con = jdt.getConnection();
				
		try {
			board = bdao.selectNotice(con, noticeNo);
					
		} finally {
			jdt.close(con);
		}
				
		return board;
	}
	
	// 공지사항 업로드
	public int noticeUpload(Board board) {
			
		int res = 0;
		Connection con = jdt.getConnection();
			
		try {
			res = bdao.noticeUpload(con, board);
				
			if(res >= 1 ) {
				//데이터가 제대로 업로드가 되었으면
				jdt.commit(con);
				System.out.println("커밋 완료");
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
			jdt.rollback(con);
			System.out.println("롤백ㅜ");
		} finally {
			jdt.close(con);
		}
			
		return res;
	}
	
	// 공지사항 업데이트
	public int noticeUpdate(Board board) {
		
		int res = 0;
		Connection con = jdt.getConnection();
		
		try {
			res = bdao.noticeUpdate(con, board);
			
			if(res >= 1 ) {
				//데이터가 제대로 업로드가 되었으면
				jdt.commit(con);
				System.out.println("커밋 완료");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			jdt.rollback(con);
			System.out.println("롤백ㅜ");
			
		} finally {
			jdt.close(con);
		}
		
		return res;
	}

	// 공지사항 삭제하기
	public int delete(int noticeNo) {
				
		int res = 0;
		Connection con = jdt.getConnection();
				
		try {
			res = bdao.delete(con, noticeNo);
					
			if(res >= 1 ) {
				//데이터가 제대로 업로드가 되었으면
				jdt.commit(con);
				System.out.println("커밋 완료");
			}
					
		} catch (SQLException e) {
			e.printStackTrace();
			jdt.rollback(con);
			System.out.println("롤백ㅜ");
					
		} finally {
			jdt.close(con);
		}
				
		return res;
	}
}
