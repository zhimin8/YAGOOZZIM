package com.kh.common.frontcontroller;

import java.util.HashMap;

import com.kh.baseball.board.controller.BoardController;
import com.kh.baseball.community.controller.CommunityController;
import com.kh.baseball.index.controller.IndexController;
import com.kh.baseball.member.controller.MemberController;
import com.kh.baseball.seat.controller.SeatController;
import com.kh.common.exception.Status404;

public class HandlerMapping {

	private HashMap<String, Controller> list = null;

	public HandlerMapping() {

		list = new HashMap<String, Controller>();
		list.put("index", new IndexController());
		list.put("member", new MemberController());
		list.put("seat", new SeatController());
		list.put("community", new CommunityController());
		list.put("board", new BoardController());
	}

	public Controller getController(String[] uriArr) throws Status404 {
		Controller res = list.get(uriArr[2]);

		if (res == null) {
			throw new Status404("uri를 다시 확인 해주세요.");
		}

		return res;

	}

	public String getMethod(String[] uriArr) throws Status404 {
		String methodName = "";

		switch (uriArr[2]) {

		case "index":
			switch (uriArr[3]) {
			case "index.do":
				methodName = "index";
				break;
			case "about.do":
				methodName = "aboutUs";
				break;
			case "insertdata.do":
				methodName = "insertData";
				break;
			default:
				throw new Status404("uri를 다시 확인 해주세요.");
			}
			break;

		case "member":
			switch (uriArr[3]) {
			case "join.do": methodName = "join"; 
					break;
			case "joinimple.do": methodName = "memberJoin";	
				break;
			case "idcheck.do": methodName = "idCheck";
				break;
			case "login.do": methodName = "logIn";
				break;
			case "loginimple.do": methodName = "memberLogin";
				break;
			case "logoutimple.do": methodName = "memberLogout";
				break;
			case "modifypw.do": methodName = "modifyPw";
				break;
			case "modifyteam.do": methodName = "modifyTeam";
				break;
			case "mypage.do": methodName = "myPage";
				break;
			case "findid.do": methodName = "findId";
				break;
			case "findidimple.do": methodName = "findidImple";
				break;
			case "findpwd.do": methodName = "findPwd";
				break;
			case "findpwdimple.do": methodName = "findPwdImple";
				break;
			case "deletemember.do": methodName = "deleteMember";
				break;
			case "deletememberimple.do": methodName = "deleteMemberImple";
				break;
			case "kakaoidcheck.do": methodName = "kakaoidCheck";
				break;
			case "kakaologin.do": methodName = "kakaoLogin";
				break;
			case "kakaojoinpage.do": methodName = "kakaoJoinPage";
				break;
			case "kakaojoin.do": methodName = "kakaoJoin";
				break;
			default:
				throw new Status404("uri를 다시 확인 해주세요.");
			}
			break;

		case "community":
			switch (uriArr[3]) {
			case "community.do" : methodName = "community";
				break;
			case "review.do" : methodName = "reviewList";
				break;
			case "review_detail.do": methodName = "selectReview";
				break;
			case "review_write.do" : methodName = "reviewWrite";
				break;
			case "review_upload.do" : methodName = "reviewUpload";
				break;
			case "review_update.do" : methodName = "update";
				break;
			case "update.do" : methodName = "reviewUpdate";
				break;
			case "delete.do" : methodName = "delete";
				break;
			case "tip.do" : methodName = "tipList";
				break;
			case "tip_detail.do" : methodName = "selectTip";
				break;
			case "tip_write.do" : methodName = "tipWrite";
				break;
			case "tip_upload.do" : methodName = "tipUpload";
				break;
			case "tip_update.do" : methodName = "tUpdate";
				break;
			case "tip_real_update.do" : methodName = "tipUpdate";
				break;
			case "tip_delete.do" : methodName = "tipDelete";
				break;
			default:
				throw new Status404("uriArr[2]가 community를 타고 있습니다. uri을 확인해주세요");
			}
			break;
			
		case "board":
			switch (uriArr[3]) {
			case "notice.do" : methodName = "noticeList";
				break;
			case "notice_detail.do": methodName = "selectNotice";
				break;
			case "notice_write.do" : methodName = "noticeWrite";
				break;
			case "notice_upload.do" : methodName = "noticeUpload";
				break;
			case "notice_update.do" : methodName = "update";
				break;
			case "update.do" : methodName = "noticeUpdate";
				break;
			case "delete.do" : methodName = "delete";
				break;
			default:
				throw new Status404("uriArr[2]가 board를 타고 있습니다. uri을 확인해주세요");
			}
			break;

		case "seat":
			switch (uriArr[3]) {
			case "seat.do":
				methodName = "seat";
				break;
			case "seatselect.do":
				methodName = "seatSelect";
				break;
			default:
				throw new Status404("uriArr[2]가 seat을 타고 있습니다. uri를 확인하세요.");
			}
			break;
			
		}
		return methodName;
	}

}