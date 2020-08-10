package com.kh.baseball.community.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import com.kh.baseball.community.model.service.CommunityService;
import com.kh.baseball.community.model.vo.Review;
import com.kh.baseball.community.model.vo.Tip;
import com.kh.baseball.member.model.vo.Member;
import com.kh.common.frontcontroller.Controller;
import com.kh.common.frontcontroller.ModelAndView;


public class CommunityController implements Controller{
	
	CommunityService cservice = new CommunityService();
	
	// 커뮤니티 클릭 시 -> community/community나오기
	public ModelAndView community(HttpServletRequest request) throws IOException {
		
		ModelAndView mav = new ModelAndView();
		mav.setView("community/community");
		
		return mav;
	}
	
//===============공통사항==================
	
	// 후기공유 -> board/reviewList
	public ModelAndView reviewList(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
			
		int currentPage = 1; // 현재 페이지 
		int cntPerPage = 10; // 한 창당 보일 게시글 수
		String orderby = "r_no"; // 정렬기준 : 게시글 번호
			
		if(request.getParameter("cPage") != null ) {
			currentPage = Integer.parseInt(request.getParameter("cPage"));
		}
		if(request.getParameter("cntPerPage") != null ) {
			cntPerPage = Integer.parseInt(request.getParameter("cntPerPage"));
		}
			
		// nservice로 이동해서 처리해보자.
		Map<String, Object> res = cservice.reviewList(orderby, currentPage, cntPerPage);
			
		mav.addObject("paging", res.get("paging"));
		mav.addObject("rdata", res.get("rlist"));
		mav.setView("community/review");
			
		return mav;
	}
		
	// reviewList에서 글를 눌렀을 때 실행될 메소드.
	public ModelAndView selectReview(HttpServletRequest request) {
		
		ModelAndView mav = new ModelAndView();
		//회원만 작성 할 수 있도록 회원 세션을 받아온다.
		Member m = (Member)request.getSession().getAttribute("loginInfo");
		
		if(m != null){
			
			int reviewNo = Integer.parseInt(request.getParameter("r_no"));
			
			System.out.println("review 번호 (r_no) : " + reviewNo);
			Review review = cservice.selectReview(reviewNo);
			
			mav.addObject("review", review);
			mav.setView("community/review_detail");
		}
		
		if( m == null){
			mav.addObject("alertMsg", "회원만 조회 가능합니다.");
			mav.addObject("url", "/semi/member/login.do");
			mav.setView("common/result");
		}
		
		return mav;
	}
	
	// review에서 글 작성하기를 눌렀을 때 실행될 메소드.
	public ModelAndView reviewWrite(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		//회원만 작성 할 수 있도록 회원 세션을 받아온다.
		Member m = (Member)request.getSession().getAttribute("loginInfo");
		
		if(m != null){
			mav.setView("community/review_write");
		}
	
		if( m == null){
			mav.addObject("alertMsg", "회원만 작성 가능합니다.");
			mav.addObject("url", "/semi/member/login.do");
			mav.setView("common/result");
		}
		return mav;
	}

	// review에서 글 작성하기를 눌렀을 때 실행될 메소드.
	public ModelAndView reviewUpload(HttpServletRequest request) throws IOException {
		 
		 ModelAndView mav = new ModelAndView();
		
		 //회원만 작성 할 수 있도록 회원 세션을 받아온다.
		 Member m = (Member)request.getSession().getAttribute("loginInfo");
		 System.out.println("로그인 확인 : " + m); //로그인 받아오는지 확인용
		
			// 업로드할 파일의 용량 제한 : 10mb
			int maxSize = 1024 * 1024 * 30;
			// 루트 절대 경로를 뽑아오자
			String root = request.getSession().getServletContext().getRealPath("/");
			// 최종적인 파일 저장 경로
			String savePath = root + "resources/upload";
			System.out.println("savePath : " + savePath);
			String originFileName = "";
			String renameFileName = "";
			 
			// request를 MultipartRequest 객체로 변환
			// MultipartRequest객체가 생성됨과 동시에 파일 업로드가 이루어 진다.
			// 파일명 중첩을 방지하려고, 임의로 넣는 것
			MultipartRequest mrequest = new MultipartRequest(request, savePath, maxSize,"UTF-8", new DefaultFileRenamePolicy());
			
			originFileName = mrequest.getFilesystemName("writeFile"); //사용자가 올렸던 파일 이름이 뜨게된다
			
			if(originFileName != null) {
				
				String fileName = String.valueOf(new Date().getTime());
				renameFileName = fileName + originFileName.substring(originFileName.lastIndexOf("."));
				
				File originFile = new File(savePath + "\\" + originFileName);
				File renameFile = new File(savePath + "\\" + renameFileName);
				originFile.renameTo(renameFile);
				
				System.out.println("변경되서 저장되는 파일명 :" + renameFile);
			}
			
			// 값들은 넣기쉽게 변수에 넣자.
			String title = mrequest.getParameter("writeTitle");
			String content = mrequest.getParameter("writeContent");
			String writer = m.getM_id();
			String type = mrequest.getParameter("writeType");
			
			// 위에서 설정한 값들을 넣어줄겨
			Review review = new Review();
			review.setR_title(title);
			review.setR_cont(content);
			review.setR_writer(writer);
			review.setR_type(type);
			review.setOriginal_filepath(originFileName);
			review.setRename_filepath(renameFileName);
			
			int res = cservice.reviewUpload(review);
			
			if(res >= 1) {
				mav.addObject("alertMsg", "게시물 등록이 성공되었습니다.");
				mav.addObject("url", "/semi/community/review.do");
				mav.setView("common/result");
			} else {
				mav.addObject("alertMsg", "게시물 등록이 실패하였습니다.");
				mav.addObject("back", "back");
				mav.setView("common/result");
			}
		
		return mav;
	 }
	 
	// 수정 버튼 클릭 시, 전달 및 출력되는 메소드
	public ModelAndView update(HttpServletRequest request) throws IOException {
			
		ModelAndView mav = new ModelAndView();
		request.setCharacterEncoding("utf-8");
			
		int reviewNo = Integer.parseInt(request.getParameter("rNo"));
		String title = request.getParameter("rTitle");
		String type = request.getParameter("rType");
		String content = request.getParameter("rCont");
		String originFileName = request.getParameter("rOrigin");
		String renameFileName = request.getParameter("rRename");
		 	
		Review review = new Review();
		review.setR_no(reviewNo);
		review.setR_title(title);
		review.setR_type(type);
		review.setR_cont(content);
		review.setOriginal_filepath(originFileName);
		review.setRename_filepath(renameFileName);
		 	
		mav.addObject("review", review);
		mav.setView("community/review_update");
			
		return mav;
	}
		
	// review에서 글 작성하기를 눌렀을 때 실행될 메소드.
	public ModelAndView reviewUpdate(HttpServletRequest request) throws IOException {
		
		 ModelAndView mav = new ModelAndView();
		 request.setCharacterEncoding("utf-8");
		 
		// 업로드할 파일의 용량 제한 : 20mb
			int maxSize = 1024 * 1024 * 20;
			// 루트 절대 경로를 뽑아오자
			String root = request.getSession().getServletContext().getRealPath("/");
			// 최종적인 파일 저장 경로
			String savePath = root + "resources/upload";
			System.out.println("savePath : " + savePath);
			
			String originFileName = "";
			String renameFileName = "";
			
			MultipartRequest mrequest 
			= new MultipartRequest(request, savePath, maxSize,"UTF-8", new DefaultFileRenamePolicy());
			
			originFileName = mrequest.getFilesystemName("writeFile"); //사용자가 올렸던 파일 이름이 뜨게된다
			System.out.println("오리지날 파일 제발 : " + originFileName);
			
			if(originFileName != null) {
							
				String fileName = String.valueOf(new Date().getTime());
				renameFileName = fileName + originFileName.substring(originFileName.lastIndexOf("."));
							
				File originFile = new File(savePath + "\\" + originFileName);
				File renameFile = new File(savePath + "\\" + renameFileName);
				originFile.renameTo(renameFile);
				
			}
		 
		 		int reviewNo = Integer.parseInt(request.getParameter("rNo"));
			 	String title = mrequest.getParameter("writeTitle");
			 	String type = mrequest.getParameter("rType");
			 	String content = mrequest.getParameter("writeContent");
			 	
			 	System.out.println("review 번호 (r_no) 를 받아오고 있나요 ?: " + reviewNo);
			 	
			 	Review review = new Review();
			 	review.setR_no(reviewNo);
			 	review.setR_title(title);
			 	review.setR_type(type);
			 	review.setR_cont(content);
			 	review.setOriginal_filepath(originFileName);
			 	review.setRename_filepath(renameFileName);
			 	
			 	int res = cservice.reviewUpdate(review);
			 	
			 	if(res >= 1) {
					mav.addObject("alertMsg", "게시물 변경이 성공되었습니다.");
					mav.addObject("url", "/semi/community/review.do");
					mav.setView("common/result");
				} else {
					mav.addObject("alertMsg", "게시물 변경에 실패하였습니다.");
					mav.addObject("back", "back");
					mav.setView("common/result");
				}
			 	
				return mav;
			}
	
	// 리뷰 삭제
	public ModelAndView delete(HttpServletRequest request) throws IOException {
		 ModelAndView mav = new ModelAndView();
		 int reviewNo = Integer.parseInt(request.getParameter("rNo"));
		 
		 int res = cservice.delete(reviewNo);
		 
		 if(res >= 1) {
				mav.addObject("alertMsg", "게시물이 삭제 되었습니다.");
				mav.addObject("url", "/semi/community/review.do");
				mav.setView("common/result");
			} else {
				mav.addObject("alertMsg", "게시물 삭제가 실패하였습니다.");
				mav.addObject("back", "back");
				mav.setView("common/result");
			}
		 
		 return mav;
	 }

//-----------------------------꿀팁부분--------------------------------

	//후기공유 -> board/reviewList
	public ModelAndView tipList(HttpServletRequest request) {
				
		ModelAndView mav = new ModelAndView();
				
		int currentPage = 1; // 현재 페이지 
		int cntPerPage = 10; // 한 창당 보일 게시글 수
		String orderby = "t_no"; // 정렬기준 : 게시글 번호
				
		if(request.getParameter("cPage") != null ) {
			currentPage = Integer.parseInt(request.getParameter("cPage"));
		}
		if(request.getParameter("cntPerPage") != null ) {
			cntPerPage = Integer.parseInt(request.getParameter("cntPerPage"));
		}
				
		// nservice로 이동해서 처리해보자.
		Map<String, Object> res = cservice.tipList(orderby, currentPage, cntPerPage);
				
		mav.addObject("paging", res.get("paging"));
		mav.addObject("tdata", res.get("tlist"));
		mav.setView("community/tip");
				
		return mav;
	}
		
	// reviewList에서 글를 눌렀을 때 실행될 메소드.
	public ModelAndView selectTip(HttpServletRequest request) {
		
		ModelAndView mav = new ModelAndView();
		//회원만 작성 할 수 있도록 회원 세션을 받아온다.
		Member m = (Member)request.getSession().getAttribute("loginInfo");
		
		if(m != null){
			
			int tipNo = Integer.parseInt(request.getParameter("t_no"));
			
			System.out.println("review 번호 (r_no) : " + tipNo);
			Tip tip = cservice.selectTip(tipNo);
			
			mav.addObject("tip", tip);
			mav.setView("community/tip_detail");
		}
		
		if( m == null){
			mav.addObject("alertMsg", "회원만 조회 가능합니다.");
			mav.addObject("url", "/semi/member/login.do");
			mav.setView("common/result");
		}
		
		return mav;
	}
	
	// review에서 글 작성하기를 눌렀을 때 실행될 메소드.
	public ModelAndView tipWrite(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		//회원만 작성 할 수 있도록 회원 세션을 받아온다.
		Member m = (Member)request.getSession().getAttribute("loginInfo");
		
		if( m != null){
			mav.setView("community/tip_write");
		}
	
		if( m == null){
			mav.addObject("alertMsg", "회원만 작성 가능합니다.");
			mav.addObject("url", "/semi/member/login.do");
			mav.setView("common/result");
		}
		return mav;
	}

	// review에서 글 작성하기를 눌렀을 때 실행될 메소드.
	public ModelAndView tipUpload(HttpServletRequest request) throws IOException {
			 
		ModelAndView mav = new ModelAndView();
			
		//회원만 작성 할 수 있도록 회원 세션을 받아온다.
		Member m = (Member)request.getSession().getAttribute("loginInfo");
		System.out.println("로그인 확인 : " + m); //로그인 받아오는지 확인용
			
		// 업로드할 파일의 용량 제한 : 10mb
		int maxSize = 1024 * 1024 * 20;
		// 루트 절대 경로를 뽑아오자
		String root = request.getSession().getServletContext().getRealPath("/");
		// 최종적인 파일 저장 경로
		String savePath = root + "resources/upload";
		System.out.println("savePath : " + savePath);
		String originFileName = "";
		String renameFileName = "";
				 
		// request를 MultipartRequest 객체로 변환
		// MultipartRequest객체가 생성됨과 동시에 파일 업로드가 이루어 진다.
		// 파일명 중첩을 방지하려고, 임의로 넣는 것
		MultipartRequest mrequest = new MultipartRequest(request, savePath, maxSize,"UTF-8", new DefaultFileRenamePolicy());
				
		originFileName = mrequest.getFilesystemName("writeFile"); //사용자가 올렸던 파일 이름이 뜨게된다
				
			if(originFileName != null) {
					
				String fileName = String.valueOf(new Date().getTime());
				renameFileName = fileName + originFileName.substring(originFileName.lastIndexOf("."));
					
				File originFile = new File(savePath + "\\" + originFileName);
				File renameFile = new File(savePath + "\\" + renameFileName);
				originFile.renameTo(renameFile);
					
				System.out.println("변경되서 저장되는 파일명 :" + renameFile);
			}
				
			// 값들은 넣기쉽게 변수에 넣자.
			String title = mrequest.getParameter("writeTitle");
			String content = mrequest.getParameter("writeContent");
			String writer = m.getM_id();
			String type = mrequest.getParameter("writeType");
				
			// 위에서 설정한 값들을 넣어줄겨
			Tip tip= new Tip();
				tip.setT_title(title);
				tip.setT_cont(content);
				tip.setT_writer(writer);
				tip.setT_type(type);
				tip.setOriginal_filepath(originFileName);
				tip.setRename_filepath(renameFileName);
				
				int res = cservice.tipUpload(tip);
				
				if(res >= 1) {
					mav.addObject("alertMsg", "게시물 등록이 성공되었습니다.");
					mav.addObject("url", "/semi/community/tip.do");
					mav.setView("common/result");
				} else {
					mav.addObject("alertMsg", "게시물 등록이 실패하였습니다.");
					mav.addObject("back", "back");
					mav.setView("common/result");
				}
			
			return mav;
		 }
	
	// 수정 버튼 클릭 시, 전달 및 출력되는 메소드
	public ModelAndView tUpdate(HttpServletRequest request) throws IOException {
				
		ModelAndView mav = new ModelAndView();
		request.setCharacterEncoding("utf-8");
				
		int tipNo = Integer.parseInt(request.getParameter("tNo"));
		String title = request.getParameter("tTitle");
		String type = request.getParameter("tType");
		String content = request.getParameter("tCont");
		String originFileName = request.getParameter("tOrigin");
		String renameFileName = request.getParameter("tRename");
			 	
		Tip tip = new Tip();
		tip.setT_no(tipNo);
		tip.setT_title(title);
		tip.setT_type(type);
		tip.setT_cont(content);
		tip.setOriginal_filepath(originFileName);
		tip.setRename_filepath(renameFileName);
			 	
		mav.addObject("tip", tip);
		mav.setView("community/tip_update");
				
		return mav;
	}
	
	// tip에서 글 작성하기를 눌렀을 때 실행될 메소드.
	public ModelAndView tipUpdate(HttpServletRequest request) throws IOException {
			
		 ModelAndView mav = new ModelAndView();
		 request.setCharacterEncoding("utf-8");
			 
			// 업로드할 파일의 용량 제한 : 10mb
				int maxSize = 1024 * 1024 * 30;
				// 루트 절대 경로를 뽑아오자
				String root = request.getSession().getServletContext().getRealPath("/");
				// 최종적인 파일 저장 경로
				String savePath = root + "resources/upload";
				System.out.println("savePath : " + savePath);
				
				String originFileName = "";
				String renameFileName = "";
				
				MultipartRequest mrequest 
				= new MultipartRequest(request, savePath, maxSize,"UTF-8", new DefaultFileRenamePolicy());
				
				originFileName = mrequest.getFilesystemName("writeFile"); //사용자가 올렸던 파일 이름이 뜨게된다
				// originFileName = mrequest.getParameter("writeFile");
				System.out.println("오리지날 파일 제발 : " + originFileName);
				
				if(originFileName != null) {
								
					String fileName = String.valueOf(new Date().getTime());
					renameFileName = fileName + originFileName.substring(originFileName.lastIndexOf("."));
								
					File originFile = new File(savePath + "\\" + originFileName);
					File renameFile = new File(savePath + "\\" + renameFileName);
					originFile.renameTo(renameFile);
					
				}
			 
			 	int tipNo = Integer.parseInt(mrequest.getParameter("tNo"));
				String title = mrequest.getParameter("writeTitle");
				String type = mrequest.getParameter("tType");
				String content = mrequest.getParameter("writeContent");
				 	
				 	
				Tip tip = new Tip();
				tip.setT_no(tipNo);
				tip.setT_title(title);
				tip.setT_type(type);
				tip.setT_cont(content);
				tip.setOriginal_filepath(originFileName);
				tip.setRename_filepath(renameFileName);
				 	
				int res = cservice.tipUpdate(tip);
				 	
				 	if(res >= 1) {
						mav.addObject("alertMsg", "게시물 변경이 성공되었습니다.");
						mav.addObject("url", "/semi/community/tip.do");
						mav.setView("common/result");
					} else {
						mav.addObject("alertMsg", "게시물 변경에 실패하였습니다.");
						mav.addObject("back", "back");
						mav.setView("common/result");
					}
				 	
				return mav;
		}
		
	// tip 삭제
	public ModelAndView tipDelete(HttpServletRequest request) throws IOException {
		ModelAndView mav = new ModelAndView();
		int tipNo = Integer.parseInt(request.getParameter("tNo"));
			 
		int res = cservice.delete(tipNo);
			 
			if(res >= 1) {
				mav.addObject("alertMsg", "게시물이 삭제 되었습니다.");
				mav.addObject("url", "/semi/community/tip.do");
				mav.setView("common/result");
			} else {
				mav.addObject("alertMsg", "게시물 삭제가 실패하였습니다.");
				mav.addObject("back", "back");
				mav.setView("common/result");
			}
				 
		return mav;
	}
	
	}
		