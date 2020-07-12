package com.kh.baseball.board.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kh.baseball.board.model.vo.Board;
import com.kh.baseball.community.model.vo.Review;
import com.kh.common.JDBCTemplate;
import com.kh.common.util.Paging;

public class BoardDao {
	
	JDBCTemplate jdt = JDBCTemplate.getInstance();
	
	// 공지사항 리스트
	public List<Board> noticeList(Connection con, Paging p, String orderby) throws SQLException{
		List<Board> blist = new ArrayList<Board>();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		String sql = 
		"SELECT *FROM (SELECT rownum nnum, n1.*FROM (SELECT *FROM BOARD_NOTICE WHERE N_AVAILABLE = 1 ORDER BY " + orderby + " DESC)N1) WHERE NNUM BETWEEN ? AND ?";
		
		pstm = con.prepareStatement(sql);
		pstm.setInt(1, p.getStart());
		pstm.setInt(2, p.getEnd());
	
		rs = pstm.executeQuery();
		
		while(rs.next()) {
			Board board = new Board();
			board.setN_no(rs.getInt(2));
			board.setN_writer(rs.getString(3));
			board.setN_title(rs.getString(4));
			board.setN_cont(rs.getString(5));
			board.setN_date(rs.getDate(6));
			board.setOriginal_filepath(rs.getString(7));
			board.setRename_filepath(rs.getString(8));
			board.setN_type(rs.getString(9));
			board.setN_available(rs.getInt(10));
			
			blist.add(board);
			
		}
		
		return blist;
	}
	
	// 글 번호 관련 함수 - 2
	public int getNext() {
		PreparedStatement pstm = null;
		Connection con = jdt.getConnection();
		ResultSet rs = null;
		
		String sql = "SELECT N_NO FROM BOARD_NOTICE ORDER BY N_NO DESC";
		
		try {
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();
			if(rs.next()) {
				// 이미 있으면 그거에 +1
				return rs.getInt(1) +1;
			}
			// 만약 첫번째 게시물이면, 1 반환
			return 1; 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 위의 모든 상황에서 오류가 나면 게시글 번호로 쓸 수 없는 -1 반환
		return -1;
	}

	// 공지사항에서 글 선택 시
	public Board selectNotice(Connection con, int noticeNo) {
		Board board = null;
		PreparedStatement pstm = null;
		
		String sql = " SELECT * FROM BOARD_NOTICE WHERE N_NO = ? ";
		ResultSet rs = null;
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, noticeNo);
			rs = pstm.executeQuery();
			
		while(rs.next()) {	
			board = new Board();
			board.setN_no(rs.getInt(1));
			board.setN_writer(rs.getString(2));
			board.setN_title(rs.getString(3));
			board.setN_cont(rs.getString(4));
			board.setN_date(rs.getDate(5));
			board.setOriginal_filepath(rs.getString(6));
			board.setRename_filepath(rs.getString(7));
			board.setN_available(1);
			board.setN_type(rs.getString(9));
		}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return board;
	}

	public int noticeUpload(Connection con, Board board) throws SQLException {
		int res = 0;
		PreparedStatement pstm = null;
		String sql = "INSERT INTO board_notice values(?, ?, ?, ?, sysdate, ?, ?, ?, ?)";
		
		try {
		pstm = con.prepareStatement(sql);
		pstm.setInt(1, getNext()); //여기에 글번호 들어감.
		pstm.setString(2, board.getN_writer());
		pstm.setString(3, board.getN_title());
		pstm.setString(4, board.getN_cont());
		pstm.setString(5, board.getOriginal_filepath());
		pstm.setString(6, board.getRename_filepath());
		pstm.setString(7, board.getN_type());
		pstm.setInt(8, 1);
		
		
		
		res = pstm.executeUpdate();
		
		} finally {
			jdt.close(pstm);
		}
		
		return res;
	}
	
	// 글 수정하기
	public int noticeUpdate(Connection con, Board board) throws SQLException {
			
		int res = 0;
		PreparedStatement pstm = null;
			
		String sql = "UPDATE BOARD_NOTICE SET N_TITLE = ?, N_CONT = ?, ORIGINAL_FILEPATH = ?, RENAME_FILEPATH = ? WHERE N_NO = ?";
			
			try {
				pstm = con.prepareStatement(sql);
				
				pstm.setString(1, board.getN_title());
				pstm.setString(2, board.getN_cont());
				pstm.setString(3, board.getOriginal_filepath());
				pstm.setString(4, board.getRename_filepath());
				pstm.setInt(5, board.getN_no());
			
				res = pstm.executeUpdate();
			
			} finally {
				jdt.close(pstm);
			}
			
			return res;
		}
		
	// 글 삭제하기
	public int delete(Connection con, int noticeNo) throws SQLException {
		
		int res = 0;
		PreparedStatement pstm = null;
			
		String sql = "UPDATE BOARD_NOTICE SET N_AVAILABLE = 0 WHERE N_NO = ?";
			
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, noticeNo);
				
			res = pstm.executeUpdate();
			
		} finally {
			jdt.close(con);
		}
			
		return res;
	}
	
}
