package com.kh.baseball.member.model.dao;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.baseball.member.model.vo.Member;

@Repository
public class MemberDao {
	
	@Autowired
	SqlSessionTemplate session;
	
	
	public int memberJoin(Map<String, Object> commandMap) {
		return session.insert("Member.commandMap", commandMap);
	}
	
	public String idCheck(String userId) {
		return session.selectOne("Member.selectId", userId);
	}
	
	public Member memberLogIn(Member member) {
		return session.selectOne("Member.memberLogin", member);
	}
	
	public int modifyPw(String id, String newPwd) {
		
		Member modifyPw = new Member();
		modifyPw.setM_id(id);
		modifyPw.setM_password(newPwd);
		
		return session.update("Member.modifyPw", modifyPw);
	}
	
	public int modifyTeam(String id, String newTeam) {
		
		System.out.println("Dao, modifyTeam newTeam : " + newTeam );
		
		Member modifyTeam = new Member();
		modifyTeam.setM_id(id);
		modifyTeam.setM_teamname(newTeam);
		
		return session.update("Member.modifyTeam", modifyTeam);
	}
	
	public Member findIdImple(String email, String tell) {
		
		System.out.println("dao에서 아이디 찾기 : " + email);
		System.out.println("dao에서 아이디 찾기 : " + tell);
		
		Member findId = new Member();
		findId.setM_email(email);
		findId.setM_tell(tell);
		
		return session.selectOne("Member.findId", findId);
	}
	
	
	
	
	

}
