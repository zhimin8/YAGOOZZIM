package com.kh.baseball.member.model.dao;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
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
	
	public int modifyPw(Member member) {
		return session.selectOne("Member.modifyPw",  member);
	}
	
	
	

}
