package com.kh.baseball.board.controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.kh.baseball.board.model.service.BoardService;
import com.kh.baseball.board.model.vo.Board;
import com.kh.baseball.community.model.vo.Review;
import com.kh.baseball.member.model.vo.Member;
import com.kh.common.frontcontroller.Controller;
import com.kh.common.frontcontroller.ModelAndView;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class BoardController implements Controller{
	BoardService bservice = new BoardService();
	
	// navBar 에 공지사항 선택 시 출력됨
	public ModelAndView noticeList(HttpServletRequest request) {
				
		ModelAndView mav = new ModelAndView();
				
		int currentPage = 1; // 현재 페이지 
		int cntPerPage = 10; // 한 창당 보일 게시글 수
		String orderby = "n_no"; // 정렬기준 : 게시글 번호
				
		if(request.getParameter("cPage") != null ) {
			currentPage = Integer.parseInt(request.getParameter("cPage"));
		}
		if(request.getParameter("cntPerPage") != null ) {
			cntPerPage = Integer.parseInt(request.getParameter("cntPerPage"));
		}
				
		// nservice로 이동해서 처리해보자.
		Map<String, Object> res = bservice.noticeList(orderby, currentPage, cntPerPage);
				
		mav.addObject("paging", res.get("paging"));
		mav.addObject("bdata", res.get("blist"));
		mav.setView("board/notice");
				
		return mav;
	}
	
	// 공지사항 세부선택
	public ModelAndView selectNotice(HttpServletRequest request) throws SQLException {
		
		ModelAndView mav = new ModelAndView();
		
		int noticeNo = Integer.parseInt(request.getParameter("n_no"));
		
		System.out.println("notice 번호 (n_no) : " + noticeNo);
		Board board = bservice.selectNotice(noticeNo);
		
		mav.addObject("board", board);
		mav.setView("board/notice_detail");
	
		return mav;
	}

	// review에서 글 작성하기를 눌렀을 때 실행될 메소드.
	public ModelAndView noticeWrite(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		// 관리자만 접근 가능
		Member m = (Member)request.getSession().getAttribute("loginInfo");
			
			if(m != null){
				mav.setView("board/notice_write");
			}
		
			if( m == null){
				mav.addObject("alertMsg", "관리자만 작성 가능합니다.");
				mav.addObject("back", "back");
				mav.setView("common/result");
			}
			return mav;
		}

	// 공지사항 업로드
	public ModelAndView noticeUpload(HttpServletRequest request) throws IOException {
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
			
			Board board = new Board();
			board.setN_title(title);
			board.setN_cont(content);
			board.setN_writer(writer);
			board.setOriginal_filepath(originFileName);
			board.setRename_filepath(renameFileName);
			board.setN_type(type);
			
			int res = bservice.noticeUpload(board);
			
			if(res >= 1) {
				mav.addObject("alertMsg", "게시물 등록이 성공되었습니다.");
				mav.addObject("url", "/semi/board/notice.do");
				mav.setView("common/result");
			} else {
				mav.addObject("alertMsg", "게시물 등록이 실패하였습니다.");
				mav.addObject("back", "back");
				mav.setView("common/result");
			}
		
		return mav;
		
	}
	
	// 수정 클릭 시, 제일먼저 실행되는 메소드 (service, dao 필요없음)
	public ModelAndView update(HttpServletRequest request) throws IOException {
		
		ModelAndView mav = new ModelAndView();
		request.setCharacterEncoding("utf-8");
			
		int noticeNo = Integer.parseInt(request.getParameter("nNo"));
		String title = request.getParameter("nTitle");
		String type = request.getParameter("nType");
		String content = request.getParameter("nCont");
		String originFileName = request.getParameter("nOrigin");
		String renameFileName = request.getParameter("nRename");
		 	
		Board board = new Board();
		board.setN_no(noticeNo);
		board.setN_title(title);
		board.setN_type(type);
		board.setN_cont(content);
		board.setOriginal_filepath(originFileName);
		board.setRename_filepath(renameFileName);
		 	
		mav.addObject("board", board);
		mav.setView("board/notice_update");
			
		return mav;
	}
	
	// 업데이트 양식 / 데이터 전송 메소드
	public ModelAndView noticeUpdate(HttpServletRequest request) throws IOException {
		
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
			// originFileName = mrequest.getParameter("writeFile");
			
			if(originFileName != null) {
							
				String fileName = String.valueOf(new Date().getTime());
				renameFileName = fileName + originFileName.substring(originFileName.lastIndexOf("."));
							
				File originFile = new File(savePath + "\\" + originFileName);
				File renameFile = new File(savePath + "\\" + renameFileName);
				originFile.renameTo(renameFile);
				
			}
		 
		 		int noticeNo = Integer.parseInt(mrequest.getParameter("nNo"));
			 	String title = mrequest.getParameter("writeTitle");
			 	String type = mrequest.getParameter("nType");
			 	String content = mrequest.getParameter("writeContent");
			 	
			 	Board board = new Board();
			 	board.setN_no(noticeNo);
				board.setN_title(title);
				board.setN_cont(content);
				board.setOriginal_filepath(originFileName);
				board.setRename_filepath(renameFileName);
				board.setN_type(type);
			 	
			 	int res = bservice.noticeUpdate(board);
			 	
			 	if(res >= 1) {
					mav.addObject("alertMsg", "게시물 변경이 성공되었습니다.");
					mav.addObject("url", "/semi/board/notice.do");
					mav.setView("common/result");
				} else {
					mav.addObject("alertMsg", "게시물 변경에 실패하였습니다.");
					mav.addObject("back", "back");
					mav.setView("common/result");
				}
			 	
				return mav;
			}

	// 공지사항 삭제
	public ModelAndView delete(HttpServletRequest request) throws IOException {
		ModelAndView mav = new ModelAndView();
		int noticeNo = Integer.parseInt(request.getParameter("nNo"));
			 
		int res = bservice.delete(noticeNo);
			 
		if(res >= 1) {
			mav.addObject("alertMsg", "게시물이 삭제 되었습니다.");
			mav.addObject("url", "/semi/board/notice.do");
			mav.setView("common/result");
		} else {
			mav.addObject("alertMsg", "게시물 삭제가 실패하였습니다.");
			mav.addObject("back", "back");
			mav.setView("common/result");
		}
		return mav;
	}

}
