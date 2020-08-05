package com.kh.baseball.board.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kh.baseball.board.model.service.BoardService;
import com.kh.baseball.board.model.vo.Board;

@Controller
public class BoardController {
	
	@Autowired
	BoardService bService;
	
	// 공지사항 리스트 항목
	@RequestMapping("/board/notice.do")
	public ModelAndView noticeList(@RequestParam Map<String, Object> commandMap) {
		ModelAndView mav = new ModelAndView();
		
		int currentPage = 1;
		int cntPerPage = 10;
		String orderby = "n_no";
		
		if(commandMap.get("cPage") != null ) {
			currentPage = Integer.parseInt((String) commandMap.get("cPage"));
		}
		if(commandMap.get("cntPerPage") != null) {
			cntPerPage = Integer.parseInt((String) commandMap.get("cntPerPage"));
		}
		
		Map<String, Object> res = bService.noticeList(orderby, currentPage, cntPerPage);
	
		mav.addObject("noticeData", res);
		mav.setViewName("board/notice");
		
		return mav;
	}
	
	@RequestMapping("/board/notice_detail.do")
	public ModelAndView selectNotice(int noticeNo) {
		ModelAndView mav = new ModelAndView();
		Map<String, Object> boardMap = bService.selectNotice(noticeNo);
		
		if(boardMap.get("board") != null ) {
			mav.addObject("data", boardMap);
			mav.setViewName("/board/notice_detail");
		} else {
			mav.addObject("alertMsg", "존재하지 않는 게시물 입니다.");
			mav.addObject("back", "back");
			mav.setViewName("common/result");
		}
		return mav;
	}
	
	// 공지사항 작성하기
	@RequestMapping("/board/notice_write.do")
	public String noticeWrite() {
		// ModelAndView mav = new ModelAndView();
		// 관리자만 접근 가능
		/* Member m = (Member)request.getSession().getAttribute("loginInfo"); */
			
		/*
		 * if(m != null){ mav.setViewName("board/notice_write"); }
		 * 
		 * if(m == null){ mav.addObject("alertMsg", "관리자만 작성 가능합니다.");
		 * mav.addObject("back", "back"); mav.setViewName("common/result"); } return
		 * mav;
		 */
		return "board/notice_write";
	}
	
	@RequestMapping("/board/notice_upload.do")
	public ModelAndView noticeUpload(Board board, @RequestParam List<MultipartFile> files, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		
		String root = request.getSession().getServletContext().getRealPath("/");
		List<Map<String, Object>> fileData = new ArrayList<>();
		HttpSession session = request.getSession();
		
		// 회원이 아니면 못보고 못써
		// Member member = (Member) session.getAttribute("logInInfo"); // loginInfo 확인해보기
		// notice.setNoticeWriter(member.getM_id());
		
		int i = 0;
		for(MultipartFile mf : files) {
			String savePath = root + "resources/upload";
			String originFileName = mf.getOriginalFilename();
			HashMap<String, Object> data = new HashMap<>();
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			String renameFileName = sdf.format(new Date()) + i + "." + originFileName.substring(originFileName.lastIndexOf(".")+1);
			savePath += renameFileName;
			
			data.put("originFileName", originFileName);
			data.put("renameFileName", renameFileName);
			data.put("savePath", savePath);
			data.put("file", mf);
			
			fileData.add(data);
			i++;
		}
		
		int res = bService.insertNotice(board, fileData);
		mav.setViewName("redirect:notice.do");
		return mav;
	}
	
	@RequestMapping("/board/delete.do")
	public ModelAndView noticeDelete(int noticeNo) {
		ModelAndView mav = new ModelAndView();
		int res = bService.deleteNotice(noticeNo);
		
		if(res > 0) {
			mav.addObject("alertMsg", "삭제가 완료되었습니다.");
			mav.addObject("url", "/baseball/board/notice.do");
			mav.setViewName("common/result");
		} else {
			mav.addObject("alertMsg", "존재하지 않는 게시물 입니다.");
			mav.addObject("url", "/baseball/board/notice.do");
			mav.setViewName("common/result");
		}
		return mav;
	}
	
	
	
	

}
