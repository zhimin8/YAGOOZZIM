package com.kh.baseball.community.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kh.baseball.community.model.service.CommunityService;

@Controller
public class CommunityController {
	
	@Autowired
	public CommunityService cService;
	
	@RequestMapping("community/community.do")
	public String community() {
		return "community/community";
	}
	
	@RequestMapping("community/review.do")
	public ModelAndView reviewList(@RequestParam Map<String, Object> commandMap) {
		ModelAndView mav = new ModelAndView();
		
		int currentPage = 1; // 현재 페이지 
		int cntPerPage = 10; // 한 창당 보일 게시글 수
		String orderby = "r_no"; // 정렬기준 : 게시글 번호
		
		if(commandMap.get("cPage") != null ) {
			currentPage = Integer.parseInt((String)commandMap.get("cPage"));
		}
		if(commandMap.get("cntPerPage") != null ) {
			cntPerPage = Integer.parseInt((String)commandMap.get("cntPerPage"));
		}
		
		Map<String, Object> res = cService.reviewList(orderby, currentPage, cntPerPage);
		
		mav.addObject("paging", res.get("paging"));
		mav.addObject("rdata", res.get("rlist"));
		mav.setViewName("community/review");
		
		return mav;
	}
	
	
	
	

}
