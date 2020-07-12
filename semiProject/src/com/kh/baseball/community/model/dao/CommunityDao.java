package com.kh.baseball.community.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.kh.baseball.community.model.vo.Review;
import com.kh.baseball.community.model.vo.Tip;
import com.kh.common.JDBCTemplate;
import com.kh.common.util.Paging;

public class CommunityDao {
	
	JDBCTemplate jdt = JDBCTemplate.getInstance();
	
	public List<Review> reviewList(Connection con, Paging p, String orderby) throws SQLException{
		List<Review> rlist = new ArrayList<Review>();
		
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		String sql = 
		"SELECT *FROM (SELECT rownum rnum, n1.*FROM (SELECT *FROM BOARD_REVIEW WHERE R_AVAILABLE = 1 ORDER BY " + orderby + " DESC)N1) WHERE RNUM BETWEEN ? AND ?";		
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, p.getStart());
			pstm.setInt(2, p.getEnd());
		
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				
				Review review = new Review();
				review.setR_no(rs.getInt(2));
				review.setR_writer(rs.getString(3));
				review.setR_type(rs.getString(4));
				review.setR_title(rs.getString(5));
				review.setR_cont(rs.getString(6));
				review.setR_date(rs.getDate(7));
				review.setOriginal_filepath(rs.getString(8));
				review.setRename_filepath(rs.getString(9));
				review.setR_available(rs.getInt(10));
				
				rlist.add(review);
			}
			
		} finally {
			jdt.close(rs, pstm);
		}
		return rlist;
	}
	
	// 리뷰선택시 나오는 리스트-
	public Review selectReview(Connection con, int ReviewNo) throws SQLException{
		
		Review review = null;
		PreparedStatement pstm = null;
		
		String sql = " SELECT * FROM BOARD_REVIEW WHERE R_NO = ? ";
		ResultSet rs = null;
		
		try {
		pstm = con.prepareStatement(sql);
		pstm.setInt(1, ReviewNo);

		rs = pstm.executeQuery();
		
		while(rs.next()) {
			review = new Review();
			review.setR_no(rs.getInt(1));
			review.setR_writer(rs.getString(2));
			review.setR_type(rs.getString(3));
			review.setR_title(rs.getString(4));
			review.setR_cont(rs.getString(5));
			review.setR_date(rs.getDate(6));
			review.setOriginal_filepath(rs.getString(7));
			review.setRename_filepath(rs.getString(8));
			review.setR_available(1);
		}
		
		} finally {
			jdt.close(rs, pstm);
		}
		
		return review;
	}
	
	// 글 번호 관련 함수
	public int contentCnt(Connection con) throws SQLException {
		int res = 0;
		String sql = "SELECT COUNT(*) FROM BOARD_REVIEW";
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				res = rs.getInt(1)  ;
			}
			
		} finally {
			jdt.close(rs,stmt);
		}
		return res;
	}
	
	// 글 번호 관련 함수 - 2
	public int getNext() {
		PreparedStatement pstm = null;
		Connection con = jdt.getConnection();
		ResultSet rs = null;
		
		String sql = "SELECT R_NO FROM BOARD_REVIEW ORDER BY R_NO DESC";
		
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
	
	// 리뷰 업로드
	public int reviewUpload(Connection con, Review review) throws SQLException {
		int res = 0;
		// sql에서 데이터를 받아오는 것이 아니기 때문에 여부만 알면됨. -> ResultSet 필요없음
		PreparedStatement pstm = null;
		// 원래 sql문 : INSERT INTO BOARD_REVIEW VALUES(R_NO.NEXTVAL, 'jimin',  '타입', '제목', '내용', sysdate, '원래파일경로', '바뀐파일경로');
		String sql = "INSERT INTO board_review values(?, ?, ?, ?, ?, sysdate, ?, ?, ?)";
		
		try {
		// sql문이 실행 준비된 상태
		pstm = con.prepareStatement(sql);
			pstm.setInt(1, getNext()); //여기에 글번호 들어감.
			pstm.setString(2, review.getR_writer());
			pstm.setString(3, review.getR_type());
			pstm.setString(4, review.getR_title());
			pstm.setString(5, review.getR_cont());
			pstm.setString(6, review.getOriginal_filepath());
			pstm.setString(7, review.getRename_filepath());
			pstm.setInt(8, 1);
		
			// 제대로 input이 되면 1이 반환이 될것.
			res = pstm.executeUpdate();
			
		} finally {
			jdt.close(pstm);
		}
		
		return res;
	}

	// 글 수정하기
	public int reviewUpdate(Connection con, Review review) throws SQLException {
		
		int res = 0;
		PreparedStatement pstm = null;
		
		String sql = "UPDATE BOARD_REVIEW SET R_TITLE = ?, R_CONT = ?, ORIGINAL_FILEPATH= ?, RENAME_FILEPATH = ? WHERE R_NO = ?";
		
		try {
			pstm = con.prepareStatement(sql);
			
			pstm.setString(1, review.getR_title());
			pstm.setString(2, review.getR_cont());
			pstm.setString(3, review.getOriginal_filepath());
			pstm.setString(4, review.getRename_filepath());
			pstm.setInt(5, review.getR_no());
		
			res = pstm.executeUpdate();
		
		} finally {
			jdt.close(pstm);
		}
		
		return res;
	}
	
	// 글 삭제하기
	public int delete(Connection con, int reviewNo) throws SQLException {
		int res = 0;
		PreparedStatement pstm = null;
		
		String sql = "UPDATE BOARD_REVIEW SET R_AVAILABLE = 0 WHERE R_NO = ?";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, reviewNo);
			
			res = pstm.executeUpdate();
		
		} finally {
			jdt.close(con);
		}
		
		return res;
	}
	
	
//-----------------------------꿀팁부분--------------------------------
	
	// 꿀팁 보러가기 선택시 나오는 창
	public List<Tip> tipList(Connection con, Paging p, String orderby) throws SQLException{
		
		List<Tip> tlist = new ArrayList<Tip>();
		
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		String sql = 
		"SELECT *FROM (SELECT rownum tnum, n1.*FROM (SELECT *FROM BOARD_TIP WHERE T_AVAILABLE = 1 ORDER BY " + orderby + " DESC)N1) WHERE TNUM BETWEEN ? AND ?";		
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, p.getStart());
			pstm.setInt(2, p.getEnd());
		
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				
				Tip tip = new Tip();
				
				tip.setT_no(rs.getInt(2));
				tip.setT_writer(rs.getString(3));
				tip.setT_type(rs.getString(4));
				tip.setT_title(rs.getString(5));
				tip.setT_cont(rs.getString(6));
				tip.setT_date(rs.getDate(7));
				tip.setOriginal_filepath(rs.getString(8));
				tip.setRename_filepath(rs.getString(9));
				tip.setT_available(rs.getInt(10));
				
				tlist.add(tip);
			}
			
		} finally {
			jdt.close(rs, pstm);
		}
		return tlist;
	}

	// 꿀팁 목록 선택 시 나오는 것
	public Tip selectTip(Connection con, int tipNo) throws SQLException{
			
		PreparedStatement pstm = null;
		String sql = " SELECT * FROM BOARD_TIP WHERE T_NO = ? ";
		ResultSet rs = null;
		Tip tip = null;
			
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, tipNo);

			rs = pstm.executeQuery();
			
			while(rs.next()) {
				tip = new Tip();
				tip.setT_no(rs.getInt(1));
				tip.setT_writer(rs.getString(2));
				tip.setT_type(rs.getString(3));
				tip.setT_title(rs.getString(4));
				tip.setT_cont(rs.getString(5));
				tip.setT_date(rs.getDate(6));
				tip.setOriginal_filepath(rs.getString(7));
				tip.setRename_filepath(rs.getString(8));
				tip.setT_available(1);
			}
			
			} finally {
				jdt.close(rs, pstm);
			}
			
			return tip;
		}

	// 글 번호 관련 함수 - 2
	public int getTipNext() {
		PreparedStatement pstm = null;
		Connection con = jdt.getConnection();
		ResultSet rs = null;
		
		String sql = "SELECT T_NO FROM BOARD_TIP ORDER BY T_NO DESC";
		
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
	
	// 리뷰 업로드
	public int tipUpload(Connection con, Tip tip) throws SQLException {
		int res = 0;
		// sql에서 데이터를 받아오는 것이 아니기 때문에 여부만 알면됨. -> ResultSet 필요없음
		PreparedStatement pstm = null;
		String sql = "INSERT INTO BOARD_TIP VALUES(?, ?, ?, ?, ?, SYSDATE, ?, ?, ?)";
			
		try {
			// sql문이 실행 준비된 상태
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, getTipNext()); //여기에 글번호 들어감.
			pstm.setString(2, tip.getT_writer());
			pstm.setString(3, tip.getT_type());
			pstm.setString(4, tip.getT_title());
			pstm.setString(5, tip.getT_cont());
			pstm.setString(6, tip.getOriginal_filepath());
			pstm.setString(7, tip.getRename_filepath());
			pstm.setInt(8, 1);
			
			// 제대로 input이 되면 1이 반환이 될것.
			res = pstm.executeUpdate();
				
		} finally {
			jdt.close(pstm);
		}
			
		return res;
	}

	// 글 수정하기
	public int tipUpdate(Connection con, Tip tip) throws SQLException {
			
		int res = 0;
		PreparedStatement pstm = null;
			
		String sql = "UPDATE BOARD_TIP SET T_TITLE = ?, T_CONT = ?, ORIGINAL_FILEPATH= ?, RENAME_FILEPATH = ? WHERE T_NO = ?";
			
		try {
			pstm = con.prepareStatement(sql);
				
			pstm.setString(1, tip.getT_title());
			pstm.setString(2, tip.getT_cont());
			pstm.setString(3, tip.getOriginal_filepath());
			pstm.setString(4, tip.getRename_filepath());
			pstm.setInt(5, tip.getT_no());
			
			res = pstm.executeUpdate();
			
		} finally {
			jdt.close(pstm);
		}
			
		return res;
	}
	
	// 글 삭제하기
	public int tipDelete(Connection con, int tipNo) throws SQLException {
		int res = 0;
		PreparedStatement pstm = null;
			
		String sql = "UPDATE BOARD_TIP SET T_AVAILABLE = 0 WHERE T_NO = ?";
			
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1,tipNo);
				
			res = pstm.executeUpdate();
			
		} finally {
			jdt.close(con);
		}
		
		return res;
	}
	
}
