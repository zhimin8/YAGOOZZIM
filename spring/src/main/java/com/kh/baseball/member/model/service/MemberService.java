package com.kh.baseball.member.model.service;

import java.sql.SQLException;
import java.util.Map;

import com.kh.baseball.member.model.vo.Member;

public interface MemberService {
	
	public int memberJoin(Map<String, Object>commandMap) throws SQLException;
	public String idCheck(String userId) throws SQLException;
	
	public Member memberLogin(Member member) throws SQLException;
	
	public int modifyPw(String id, String newPwd);
	public int modifyTeam(String id, String newTeam);
	
	
	

}
