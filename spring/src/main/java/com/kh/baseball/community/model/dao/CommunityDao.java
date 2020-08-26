package com.kh.baseball.community.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.baseball.community.model.vo.Review;

import common.util.Paging;

@Repository
public class CommunityDao {
	
	@Autowired
	SqlSessionTemplate session;
	
	// 게시물 갯수 파악
	public int contentCnt() {
		return session.selectOne("Comm.contentCnt");
	}
	
	public List<Review> reviewList(Paging page, String orderby){
		Map<String, Object> data = new HashMap<>();
		data.put("page", page);
		data.put("orderby", orderby);
		return session.selectList("Comm.reviewList", data);
	}
	
	public Review selectReview(int reviewNo) {
		return session.selectOne("Comm.selectReview", reviewNo);
	}


}
