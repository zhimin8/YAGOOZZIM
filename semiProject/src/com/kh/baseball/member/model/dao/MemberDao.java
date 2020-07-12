package com.kh.baseball.member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.spi.DirStateFactory.Result;

import com.kh.baseball.member.model.vo.Member;
import com.kh.common.JDBCTemplate;

public class MemberDao {

	JDBCTemplate jdt = JDBCTemplate.getInstance();

	public int memberJoin(Connection conn, Member m) throws SQLException {

		int res = 0;
		String sql = "insert into b_member values(M_NO.nextval,?,?,?,?,?,sysdate,'n')";
		PreparedStatement pstm = null;

		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, m.getM_id());
			pstm.setString(2, m.getM_password());
			pstm.setString(3, m.getM_tell());
			pstm.setString(4, m.getM_email());
			pstm.setString(5, m.getTeamname());
			res = pstm.executeUpdate();
		} finally {
			jdt.close(pstm);
		}
		return res;
	}

	public int kakaoJoin(Connection conn, Member m) throws SQLException {

		int res = 0;
		String sql = "insert into b_member values(M_NO.nextval,?,'n',?,?,?,sysdate,'n')";
		PreparedStatement pstm = null;

		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, m.getM_id());
			pstm.setString(2, m.getM_tell());
			pstm.setString(3, m.getM_email());
			pstm.setString(4, m.getTeamname());
			res = pstm.executeUpdate();

		} finally {
			jdt.close(pstm);
		}
		return res;
	}

	public String idCheck(Connection conn, String id) throws SQLException {

		String res = "";
		String sql = "select m_id from b_member where m_id = '" + id + "'";
		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				res = rs.getString(1);
			}

		} finally {
			jdt.close(rs, stmt);
		}

		return res;
	}

	public Member memberLogin(Connection conn, String id, String pwd) throws SQLException {
		Member res = null;
		String sql = "select m_id, m_password, m_tell, m_email, m_teamname, m_leaveyn from b_member where m_id = ? and m_password = ?";
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, id);
			pstm.setString(2, pwd);

			rs = pstm.executeQuery();
			while (rs.next()) {
				res = new Member();
				res.setM_id(rs.getString(1));
				res.setM_password(rs.getString(2));
				res.setM_tell(rs.getString(3));
				res.setM_email(rs.getString(4));
				res.setTeamname(rs.getString(5));
				res.setM_leaveyn(rs.getString(6));
				
			}
			System.out.println(res);

		} finally {
			jdt.close(rs, pstm);
		}

		return res;
	}

	public Member kakaoLogin(Connection conn, String id) throws SQLException {
		Member res = null;
		String sql = "select m_id, m_tell, m_email, m_teamname, m_leaveyn from b_member where m_id = ?";
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, id);

			rs = pstm.executeQuery();
			while (rs.next()) {
				res = new Member();
				res.setM_id(rs.getString(1));
				res.setM_tell(rs.getString(2));
				res.setM_email(rs.getString(3));
				res.setTeamname(rs.getString(4));
				res.setM_leaveyn(rs.getString(5));
			}

		} finally {
			jdt.close(rs, pstm);
		}

		return res;
	}

	public int modifyPw(Connection con, String id, String newpwd) throws SQLException {
		int res = 0;
		PreparedStatement pstm = null;
		String sql = "update b_member set m_password = ? where m_id=?";

		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, newpwd);
			pstm.setString(2, id);
			res = pstm.executeUpdate();

		} finally {

			jdt.close(pstm);

		}

		return res;

	}

	public int modifyTeam(Connection con, String id, String newteam) throws SQLException {
		int res = 0;
		PreparedStatement pstm = null;
		String sql = "update b_member set m_teamname = ? where m_id=?";

		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, newteam);
			pstm.setString(2, id);
			res = pstm.executeUpdate();

		} finally {

			jdt.close(pstm);

		}

		return res;

	}

	public static String getRamdomPassword(int len) {
		char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
				'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

		int idx = 0;
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < len; i++) {

			idx = (int) (charSet.length * Math.random()); // 36 * �깮�꽦�맂 �궃�닔瑜� Int濡� 異붿텧 (�냼�닽�젏�젣嫄�)
			sb.append(charSet[idx]);
		}

		return sb.toString();
	}

	public Member findidImple(Connection conn, String email, String tell) throws SQLException {
		Member m = null;
		String sql = "select m_id from b_member where m_email = ? and m_tell = ?";
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, email);
			pstm.setString(2, tell);
			System.out.println("�떎�삤�떎 : " + email);
			System.out.println("�떎�삤�떎 : " + tell);
			rs = pstm.executeQuery();

			while (rs.next()) {
				m = new Member();
				m.setM_id(rs.getString(1));
			}
			System.out.println("�뼱�뵒�꽌 �삤瑜섎굹�뒓嫄대뜲 媛쒕묄移섎꽕");

		} finally {
			jdt.close(rs, pstm);
		}

		return m;
	}

	public String findPwdImple(Connection conn, String id, String email) throws SQLException {

		String res = null;
		int update = 0;
		String pwd = getRamdomPassword(5);
		PreparedStatement pstm = null;
		String sql = "update b_member set m_password = ? where m_id=? and m_email=?";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, pwd);
			pstm.setString(2, id);
			pstm.setString(3, email);
			System.out.println("�떎�삤�떎 : " + email);
			System.out.println("�떎�삤�떎 : " + id);
			System.out.println("�떎�삤�떎 : " + pwd);
			update = pstm.executeUpdate();
			System.out.println("�씡�뒪�걧�듃�뾽�뜲�씠�듃�셿猷�");

			if (update != 0) {

				res = pwd;
				jdt.commit(conn);
			} else {
				System.out.println("諛붾�먭쾶�뾾�뒗�뵒�슂");
				jdt.rollback(conn);
			}

			System.out.println("pwd�뿉 �뀑�똿�셿猷�");

		} finally {
			jdt.close(pstm);
		}

		return res;
	}

	public int deleteMemberImple(Connection conn, String id, String pwd) throws SQLException {
		int res = 0;
		String sql = "update b_member set m_leaveyn = 'y' where m_id=? and m_password=?";
		PreparedStatement pstm = null;
		try {
			System.out.println(id + " // " + pwd + "媛�吏�怨� �궘�젣�븯�윭 �떎�삤�솕�떎");
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, id);
			pstm.setString(2, pwd);
			res = pstm.executeUpdate();
			System.out.println("�떎�삤�뿉�꽌 �궘�젣�셿猷�");
		} finally {
			jdt.close(conn);
		}

		return res;
	}

}
