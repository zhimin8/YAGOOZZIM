package com.kh.baseball.member.controller;

import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kh.baseball.member.model.service.MemberService;
import com.kh.baseball.member.model.vo.Member;

@Controller
public class MemberController {
	
	@Autowired
	public MemberService mService;
	
	@RequestMapping("/member/join.do")
	public ModelAndView join() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("member/memberJoin");
		return mav;
	}
	
	@RequestMapping("/member/joinimple.do")
	public ModelAndView memberJoin(@RequestParam Map<String, Object> commandMap) throws SQLException {
		ModelAndView mav = new ModelAndView();
		int res = mService.memberJoin(commandMap);
		
		if(res >= 1) {
			mav.addObject("alertMsg", "회원가입을 환영합니다");
			mav.setViewName("member/joinComplete");
		} else {
			mav.setViewName("common/result");
			mav.addObject("alertMsg", "회원가입에 실패했습니다.");
			mav.addObject("isSuccess", "false");
			mav.addObject("back", "back");
		}
		return mav;
	}
	
	@RequestMapping(value = "/member/idcheck.do", produces = "application/text; charset=utf-8")
	@ResponseBody
	public String idCheck(String userId) throws SQLException {
		return mService.idCheck(userId);
	}
	
	@RequestMapping("/member/login.do")
	public String login() {
		return "member/login";
	}
	
	@RequestMapping("/member/loginimple.do")
	public ModelAndView memberLogin(@RequestParam String id, @RequestParam String pwd, HttpSession session) throws SQLException {
		ModelAndView mav = new ModelAndView();
		
		Member member = new Member();
		member.setM_id(id);
		member.setM_password(pwd);
		
		Member res = mService.memberLogin(member);
		
		if (res != null) {
			session.setAttribute("loginInfo", res);
			mav.setViewName("index/index");
		} else {
			mav.addObject("alertMsg", "아이디 혹은 비밀번호가 틀렸습니다.");
			mav.setViewName("redirect:login.do");
		}
		
		System.out.println("로그인의 결과값 : " + res );
		return mav;
	}

	@RequestMapping("/member/logoutimple.do")
	public ModelAndView memberLogout(HttpSession session) {
		ModelAndView mav = new ModelAndView();
			
		if(session != null) {
			session.removeAttribute("loginInfo");
		}
		mav.setViewName("index/index");
		return mav;
	}
	
	@RequestMapping("/member/mypage.do")
	public ModelAndView myPage(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		Member member = (Member) session.getAttribute("loginInfo");
		
		mav.addObject("loginInfomation", member);
		mav.setViewName("member/mypage");

		return mav;
	}
	
	@RequestMapping("/member/modifypw.do")
	public ModelAndView modifyPw(String newPwd, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		Member member = (Member) session.getAttribute("loginInfo");
		
		String id = member.getM_id();
		
		int res = mService.modifyPw(id, newPwd);
		if(res >= 1) {
			mav.addObject("alertMsg","비밀번호 변경에 성공했습니다.");
			mav.setViewName("/member/login");
		} else {
			mav.addObject("alertMsg","비밀번호 변경에 실패했습니다.");
			mav.setViewName("redirect:mypage.do");
		}
		return mav;
	}
	
	@RequestMapping("/member/modifyteam.do")
	public int modifyTeam(String newTeam,HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		Member member = (Member) session.getAttribute("loginInfo");
		String id = member.getM_id();
		
		int res = mService.modifyTeam(id, newTeam);
		
		if(res >= 1) {
			mav.addObject("alertMsg","팀 변경에 성공했습니다.");
			mav.setViewName("/member/mypage");
		} else {
			mav.addObject("alertMsg","팀 변경에 실패했습니다.");
			mav.setViewName("redirect:mypage.do");
		}
		
		return res;
	}
	
	
	
	
	
	
	

	
	 
	
	
	
	
	
	
	
	

}
