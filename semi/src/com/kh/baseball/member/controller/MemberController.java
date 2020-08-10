package com.kh.baseball.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.kh.baseball.member.model.service.MemberService;
import com.kh.baseball.member.model.vo.Member;
import com.kh.common.frontcontroller.Controller;
import com.kh.common.frontcontroller.ModelAndView;

public class MemberController implements Controller {

	public MemberService ms = new MemberService();

	public ModelAndView join(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.setView("member/memberJoin");
		return mav;
	}

	public ModelAndView kakaoLoginPage(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.setView("member/kakaoLogin");
		return mav;
	}

	public ModelAndView kakaoJoinPage(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();

		String id = request.getParameter("id");
		System.out.println("移댁뭅�삤 議곗씤�럹�씠吏�濡� �꽆�뼱�삩 �븘�씠�뵒泥댄겕 : " + id);

		request.setAttribute("id", id);
		mav.setView("member/kakaoJoin");
		System.out.println("�뿬源뚯쭊 媛��꽌 �뿉�윭�씤嫄닿�?");
		return mav;
	}

	public ModelAndView findId(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.setView("member/findId");
		return mav;
	}

	public ModelAndView deleteMember(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.setView("member/deleteMember");
		return mav;
	}

	public ModelAndView findPwd(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.setView("member/findPwd");
		return mav;
	}

	public ModelAndView memberJoin(HttpServletRequest request) {

		ModelAndView mav = new ModelAndView();

		Member m = new Member();
		m.setM_id(request.getParameter("userId"));
		m.setM_password(request.getParameter("userPwd"));
		m.setM_tell(request.getParameter("m_tell"));
		m.setM_email(request.getParameter("m_email"));
		m.setTeamname(request.getParameter("my_team"));

		int res = ms.memberJoin(m);
		if (res >= 1) {
			mav.addObject("alertMsg", "회원가입을 환영합니다");
			mav.addObject("url", "/semi/index/index.do");
			mav.setView("common/result");
		} else {
			mav.setView("member/memberJoin");
			mav.addObject("isSuccess", "false");
		}

		return mav;
	}

	public ModelAndView idCheck(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String res = ms.idCheck(request.getParameter("userId"));
		mav.setView("ajax");

		mav.addObject("userId", res);

		return mav;
	}

	public ModelAndView kakaoidCheck(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String id = request.getParameter("id");
		System.out.println("移댁뭅�삤 �븘�씠�뵒 泥댄겕 : " + id);
		String res = ms.idCheck(id);
		System.out.println("萸� �떞寃⑥솕�깘?" + res);
		mav.setView("ajax");
		mav.addObject("id", res);

		return mav;
	}

	public ModelAndView kakaoJoin(HttpServletRequest request) {

		ModelAndView mav = new ModelAndView();

		String id = request.getParameter("userId");
		System.out.println("移댁뭅�삤 議곗씤�쑝濡� �꽆�뼱�삩 �븘�씠�뵒泥댄겕 : " + id);

		Member m = new Member();
		m.setM_id(request.getParameter("userId"));
		m.setM_tell(request.getParameter("m_tell"));
		m.setM_email(request.getParameter("m_email"));
		m.setTeamname(request.getParameter("my_team"));

		int res = ms.memberJoin(m);
		if (res >= 1) {
			mav.addObject("alertMsg", "�쉶�썝媛��엯�쓣 �솚�쁺�빀�땲�떎");
			mav.addObject("url", "/semi/index/index.do");
			mav.setView("common/result");

		} else {
			mav.setView("member/kakaoJoin");
			mav.addObject("isSuccess", "false");
		}

		return mav;
	}

	public ModelAndView logIn(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.setView("member/login");
		return mav;
	}

	public ModelAndView memberLogin(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();

		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");

		Member res = ms.memberLogin(id, pwd);

		if (res != null) {
			if (res.getM_leaveyn().equals("y")) {
				mav.addObject("alertMsg", "�씠誘� �깉�눜�븳 �쉶�썝�엯�땲�떎.");
				mav.addObject("url", "/semi/member/login.do");
				mav.setView("common/result");
			} else {
				HttpSession session = request.getSession();
				session.setAttribute("loginInfo", res);
				mav.setView("index/index");
			}
		} else {
			mav.addObject("alertMsg", "�븘�씠�뵒�굹 鍮꾨�踰덊샇瑜� �솗�씤�빐二쇱꽭�슂.");
			mav.addObject("url", "/semi/member/login.do");
			mav.setView("common/result");
		}

		return mav;
	}

	public ModelAndView kakaoLogin(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();

		String id = request.getParameter("id");
		System.out.println("移댁뭅�삤 濡쒓렇�씤 硫붿꽌�뱶�뿉 �삩 �븘�씠�뵒 : " + id);

		Member res = ms.kakaoLogin(id);
		System.out.println("留덉�留� 而⑦듃濡ㅻ윭");

		if (res != null) {
			if (res.getM_leaveyn().equals("y")) {
				mav.addObject("alertMsg", "�씠誘� �깉�눜�븳 �쉶�썝�엯�땲�떎.");
				mav.addObject("url", "/semi/member/login.do");
				mav.setView("common/result");
			} else {
				HttpSession session = request.getSession();
				session.setAttribute("loginInfo", res);
				mav.setView("index/index");
			}

		} else {
			mav.addObject("alertMsg", "移댁뭅�삤 濡쒓렇�씤�뿉 �떎�뙣�븯���뒿�땲�떎. �떎�떆�떆�룄�븯�떆嫄곕굹 愿�由ъ옄�뿉寃� 臾몄쓽�븯�꽭�슂.");
			mav.addObject("url", "/semi/member/kakaologin.do");
			mav.setView("common/result");
		}

		return mav;
	}

	public ModelAndView myPage(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		Member member = null;
		HttpSession session = request.getSession();
		member = (Member) session.getAttribute("loginInfo");

		mav.addObject("loginInfomation", member);
		mav.setView("member/mypage");

		return mav;
	}

	public ModelAndView memberLogout(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();

		request.getSession().invalidate();
		mav.setView("index/index");

		return mav;
	}

	public ModelAndView modifyPw(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		Member member = (Member) session.getAttribute("loginInfo");

		String id = member.getM_id();
		String newpwd = request.getParameter("newPwd");

		int res = ms.modifyPw(id, newpwd);
		
		if (res >= 1) {
			mav.addObject("alertMsg", "鍮꾨�踰덊샇媛� �젙�긽�쟻�쑝濡� �닔�젙�릺�뿀�뒿�땲�떎!");
			mav.addObject("url", "/semi/member/mypage.do");
			mav.setView("common/result");
		} else {
			mav.addObject("alertMsg", "鍮꾨�踰덊샇 �닔�젙�쓣 �떎�떆�븳踰� �떆�룄�빐二쇱꽭�슂.");
			mav.addObject("url", "/semi/member/mypage.do");
			mav.setView("common/result");
		}

		return mav;
	}

	public ModelAndView modifyTeam(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		Member member = (Member) session.getAttribute("loginInfo");

		String id = member.getM_id();
		String newteam = request.getParameter("newTeam");

		int res = ms.modifyTeam(id, newteam);
		if (res >= 1) {
			mav.addObject("alertMsg", "�� 蹂�寃쎌씠 �젙�긽�쟻�쑝濡� �셿猷뚮릺�뿀�뒿�땲�떎.");
			mav.addObject("url", "/semi/index/index.do");
			mav.setView("common/result");
		} else {
			mav.addObject("alertMsg", "�� 蹂�寃쎌쓣 �떎�떆�븳踰� �떆�룄�빐二쇱꽭�슂.");
			mav.addObject("url", "/semi/member/mypage.do");
			mav.setView("common/result");
		}

		return mav;
	}

	public ModelAndView findidImple(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();

		String email = request.getParameter("email");
		String tell = request.getParameter("tell");

		ms.findidImple(email, tell);
		
		System.out.println("而⑦듃濡ㅻ윭�떒 �엯�땲�떎 :" + email);
		mav.addObject("alertMsg", "�벑濡앺븯�떊 �씠硫붿씪濡� ID媛� 諛쒖넚�릺�뿀�뒿�땲�떎.");
		mav.addObject("url", "/semi/index/index.do");
		mav.setView("common/result");

		return mav;
	}

	public ModelAndView findPwdImple(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();

		String id = request.getParameter("rememberid");
		String email = request.getParameter("rememberemail");
		System.out.println("�씠硫붿씪�씠�옉 �븘�씠�뵒 " + email + "///" + id);

		ms.findPwdImple(id, email);
		System.out.println("理쒖쥌�쑝濡� �꽆�뼱�삩 而⑦듃濡ㅻ윭�엯�땲�떎." + email);
		mav.addObject("alertMsg", "�벑濡앺븯�떊 �씠硫붿씪濡� �엫�떆鍮꾨�踰덊샇媛� 諛쒓툒�릺�뿀�뒿�땲�떎.");
		mav.addObject("url", "/semi/index/index.do");
		mav.setView("common/result");

		return mav;
	}

	public ModelAndView deleteMemberImple(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();

		String deleteid = request.getParameter("deleteid");
		String deletepwd = request.getParameter("deletepwd");

		int res = ms.deleteMemberImple(deleteid, deletepwd);
		System.out.println(res);
		if (res >= 1) {
			System.out.println("理쒖쥌而⑦듃濡ㅼ쑝濡� �솕�뼱�슂");
			request.getSession().invalidate();
			mav.addObject("url", "/semi/index/index.do");
			mav.setView("member/deleteResult");
		} else {
			mav.addObject("alertMsg", "�븘�씠�뵒�� 鍮꾨�踰덊샇瑜� �솗�씤�빐二쇱꽭�슂.");
			mav.addObject("url", "/semi/member/deletemember.do");
			mav.setView("common/result");
		}

		return mav;
	}

}
