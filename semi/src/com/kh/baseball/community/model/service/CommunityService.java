package com.kh.baseball.community.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kh.baseball.community.model.dao.CommunityDao;
import com.kh.baseball.community.model.vo.Review;
import com.kh.baseball.community.model.vo.Tip;
import com.kh.common.JDBCTemplate;
import com.kh.common.util.Paging;

public class CommunityService {
	
	JDBCTemplate jdt = JDBCTemplate.getInstance();
	CommunityDao cdao = new CommunityDao();
	
	// 리뷰 리스트
	public Map<String, Object> reviewList(String orderby, int currentPage, int cntPerPage){
		
		Map<String, Object> res = new HashMap<>();
		Connection con = jdt.getConnection();
		Paging p =null;
			
		List<Review> rlist = null; 
			
		try {
			p = new Paging(cdao.getNext(),currentPage, cntPerPage);
			System.out.println(p); // p에 데이터 담겨있는지 확인
				
			rlist = cdao.reviewList(con, p, orderby);
			res.put("paging", p);
			res.put("rlist", rlist);
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdt.close(con);
		}
		
		return res;
	}
	
	// 목록에서 리뷰 선택시 보여지는  자세한 내용
	public Review selectReview(int ReviewNo) {
			
		Review review = null;
		Connection con = jdt.getConnection();
			
		try {
			review = cdao.selectReview(con, ReviewNo);
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdt.close(con);
		}
			
		return review;
	}
	
	// 리뷰 업로드
	public int reviewUpload(Review review) {
		
		int res = 0;
		Connection con = jdt.getConnection();
		
		try {
			res = cdao.reviewUpload(con, review);
			
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

	// 리뷰 업로드
	public int reviewUpdate(Review review) {
			
			int res = 0;
			Connection con = jdt.getConnection();
			
			try {
				res = cdao.reviewUpdate(con, review);
				
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

	// 리뷰 삭제하기
	public int delete(int reviewNo) {
			
			int res = 0;
			Connection con = jdt.getConnection();
			
			try {
				res = cdao.delete(con, reviewNo);
				
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

		
//-----------------------------꿀팁부분--------------------------------	
	
	// 리뷰 리스트
	public Map<String, Object> tipList(String orderby, int currentPage, int cntPerPage){
		
		Map<String, Object> res = new HashMap<>();
		Connection con = jdt.getConnection();
		Paging p =null;
			
		List<Tip> tlist = null; 
			
		try {
			p = new Paging(cdao.getTipNext(),currentPage, cntPerPage);
			
			System.out.println(p); // p에 데이터 담겨있는지 확인
				
			tlist = cdao.tipList(con, p, orderby);
				
			res.put("paging", p);
			res.put("tlist", tlist);
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdt.close(con);
		}
		
		return res;
	}

	// 목록에서 팁 선택시 보여지는  자세한 내용
	public Tip selectTip(int tipNo) {
			
		Tip tip = null;
		Connection con = jdt.getConnection();
			
		try {
			tip = cdao.selectTip(con, tipNo);
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdt.close(con);
		}
			
		return tip;
	}
	
	// 팁 업로드
	public int tipUpload(Tip tip) {
		
		int res = 0;
		Connection con = jdt.getConnection();
		
		try {
			res = cdao.tipUpload(con, tip);
			
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
	
	// 리뷰 업로드
	public int tipUpdate(Tip tip) {
				
		int res = 0;
		Connection con = jdt.getConnection();
			
		try {
			res = cdao.tipUpdate(con, tip);
					
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
	
	// 리뷰 업로드
	public int tipDelete(int tipNo) {
				
		int res = 0;
		Connection con = jdt.getConnection();
				
		try {
			res = cdao.tipDelete(con, tipNo);
					
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
