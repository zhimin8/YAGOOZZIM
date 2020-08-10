package com.kh.baseball.community.model.service;

import java.util.Map;

public interface CommunityService {
	
	public Map<String, Object> reviewList(String orderby, int currentPage, int cntPerPage);
	public Map<String, Object> selectReview (int noticeNo);
	

}
