package com.kh.baseball.community.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.baseball.community.model.dao.CommunityDao;
import com.kh.baseball.community.model.vo.Review;

import common.util.Paging;


@Service
public class CommunityServiceImpl implements CommunityService {
	
	@Autowired
	public CommunityDao cDao;

	@Override
	public Map<String, Object> reviewList(String orderby, int currentPage, int cntPerPage) {
		Map<String, Object> res = new HashMap<>();
		
		Paging page = new Paging(cDao.contentCnt(), currentPage, cntPerPage);
		List<Review> rlist = cDao.reviewList(page, orderby);
		
		res.put("paging", page);
		res.put("rlist", rlist);
	
		return res;
	}

	@Override
	public Map<String, Object> selectReview(int noticeNo) {
		Map<String, Object> res = new HashMap<>();
		Review review = cDao.selectReview(noticeNo);
		List<Map<String,Object>> flist = null;
		
		return null;
	}
	
	
	
	
	
	

}
