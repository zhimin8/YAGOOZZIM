package com.kh.baseball.member.model.service;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.baseball.member.model.dao.MemberDao;
import com.kh.baseball.member.model.vo.Member;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	MemberDao mDao;

	@Override
	public int memberJoin(Map<String, Object> commandMap) throws SQLException {
		return mDao.memberJoin(commandMap);
	}

	@Override
	public String idCheck(String userId) throws SQLException {
		return mDao.idCheck(userId);
	}

	@Override
	public Member memberLogin(Member member) throws SQLException {
		return mDao.memberLogIn(member);
	}

	@Override
	public int modifyPw(String id, String newPwd){ 
		return mDao.modifyPw(id, newPwd);
	}

	@Override
	public int modifyTeam(String id, String newTeam) {
		return mDao.modifyTeam(id, newTeam);
	}

	@Override
	public Member findIdImple(String email, String tell) {
		return mDao.findIdImple(email, tell);
	}
	
	
	
	

}
