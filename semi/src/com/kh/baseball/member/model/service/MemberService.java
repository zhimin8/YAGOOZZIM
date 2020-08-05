package com.kh.baseball.member.model.service;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.kh.baseball.member.model.dao.MemberDao;
import com.kh.baseball.member.model.vo.Member;
import com.kh.common.JDBCTemplate;

public class MemberService {

	JDBCTemplate jdt = JDBCTemplate.getInstance();
	public MemberDao mDao = new MemberDao();

	public int memberJoin(Member m) {

		int res = 0;
		Connection conn = jdt.getConnection();

		try {
			res = mDao.memberJoin(conn, m);
			jdt.commit(conn);
		} catch (SQLException e) {
			jdt.rollback(conn);
			e.printStackTrace();
		} finally {
			jdt.close(conn);
		}

		return res;
	}

	public String idCheck(String id) {
		String res = "";
		Connection conn = jdt.getConnection();
		try {
			res = mDao.idCheck(conn, id);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdt.close(conn);
		}

		return res;
	}

	public Member memberLogin(String id, String pwd) {
		Member res = null;

		Connection conn = jdt.getConnection();
		try {
			res = mDao.memberLogin(conn, id, pwd);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdt.close(conn);
		}

		return res;
	}
	public Member kakaoLogin(String id) {
		Member res = null;

		Connection conn = jdt.getConnection();
		try {
			res = mDao.kakaoLogin(conn, id);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdt.close(conn);
		}

		return res;
	}
	public int kakaorJoin(Member m) {

		int res = 0;
		Connection conn = jdt.getConnection();

		try {
			res = mDao.kakaoJoin(conn, m);
			jdt.commit(conn);
		} catch (SQLException e) {
			jdt.rollback(conn);
			e.printStackTrace();
		} finally {
			jdt.close(conn);
		}

		return res;
	}

	public int modifyPw(String id, String newpwd) {
		int res = 0;
		Connection con = jdt.getConnection();
		try {
			res = mDao.modifyPw(con, id, newpwd);

			if (res >= 1) {
				jdt.commit(con);
			} else {
				jdt.rollback(con);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			jdt.close(con);
		}

		return res;
	}

	public int modifyTeam(String id, String newpwd) {
		int res = 0;
		Connection con = jdt.getConnection();
		try {
			res = mDao.modifyTeam(con, id, newpwd);

			if (res >= 1) {
				jdt.commit(con);
			} else {
				jdt.rollback(con);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			jdt.close(con);
		}

		return res;
	}

	public void findidImple(String email, String tell) {
		Member m = new Member();
		Connection conn = jdt.getConnection();

		try {
			m = mDao.findidImple(conn, email, tell);
			// 연결해서 아이디 받아왔어..
			System.out.println("다오랑 연결해서 받아온 상태의 서비스단이다 이자샤");

			PasswordAuthentication pa = new PasswordAuthentication("boradory92@gmail.com", "Rnrmfqhfk92!");
			// 구글 해킹하면 죽는다
			Properties prop = System.getProperties();
			prop.put("mail.smtp.starttls.enable", "true");
			prop.put("mail.smtp.host", "smtp.gmail.com");
			prop.put("mail.smtp.auth", "true");
			prop.put("mail.smtp.port", "587");

			Session session = Session.getDefaultInstance(prop, new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {

					return pa;
				}
			});
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress("boradory92@gmail.com", "YAGOOZZIM")); // 보내는 계정
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(email, "회원님"));// 받는 계정

			msg.setSubject("[YAGOOZZIM : 당신이 원하는 모든 좌석] 찾으신 아이디가 도착했습니다!");

			String htmlBody = "<form action ='http://localhost:8356/semi/index/index.do' method='post'>"
					+ "<h4>안녕하세요!<br> 회원님이 찾으신 아이디가 도착했습니다!<br>" + "회원님의 아이디는 " + m.getM_id() + "입니다!</h4><br>"
					+ "<button type='submit'>YAGOOZZIM으로 이동하기</button></form>";

			Multipart mp = new MimeMultipart();
			MimeBodyPart htmlPart = new MimeBodyPart();
			htmlPart.setContent(htmlBody, "text/html; charset=utf-8");
			mp.addBodyPart(htmlPart);
			msg.setContent(mp);

			Transport.send(msg);

			System.out.println("서비스 단입니다 : " + m.getM_id() + "// " + email);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void findPwdImple(String id, String email) {
		String password = null;
		Connection conn = jdt.getConnection();
		System.out.println("-------다오한테 넘기는중=");
		try {
			password = mDao.findPwdImple(conn, id, email);
			// 연결해서 아이디 받아왔어..
			System.out.println("넘어와어 서비스로");
			PasswordAuthentication pa = new PasswordAuthentication("boradory92@gmail.com", "Rnrmfqhfk92!");
			Properties prop = System.getProperties();
			prop.put("mail.smtp.starttls.enable", "true");
			prop.put("mail.smtp.host", "smtp.gmail.com");
			prop.put("mail.smtp.auth", "true");
			prop.put("mail.smtp.port", "587");

			Session session = Session.getDefaultInstance(prop, new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {

					return pa;
				}
			});
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress("boradory92@gmail.com", "YAGOOZZIM")); // 보내는 계정
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(email, "회원님"));// 받는 계정

			msg.setSubject("[YAGOOZZIM : 당신이 원하는 모든 좌석] 임시 비밀번호가 도착했습니다!");

			String htmlBody = "<form action ='http://localhost:8356/semi/index/index.do' method='post'>"
					+ "<h4>안녕하세요!<br>" + id + "회원님이 찾으신 비밀번호가 도착했습니다!<br>" + "발급된 임시비밀번호는  "
					+ password + "입니다!<br>" + "임시 비밀번호로 로그인 후 반드시 비밀번호를 변경하시길 바랍니다.</h4><br>"
					+ "<button type='submit'>YAGOOZZIM으로 이동하기</button></form>";

			Multipart mp = new MimeMultipart();
			MimeBodyPart htmlPart = new MimeBodyPart();
			htmlPart.setContent(htmlBody, "text/html; charset=utf-8");
			mp.addBodyPart(htmlPart);
			msg.setContent(mp);

			Transport.send(msg);

			System.out.println("서비스 단입니다 : " + password + "// " + email);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public int deleteMemberImple(String id, String pwd) {
		int res = 0;
		Connection conn = jdt.getConnection();
		try {
			System.out.println("서비스에서 다오로 간다잉");
			res = mDao.deleteMemberImple(conn, id, pwd);
			System.out.println("서비스단에 다오들렸다가왔다");

			if (res >= 1) {
				jdt.commit(conn);
				System.out.println("서비스에서 삭제완료");
			} else {
				jdt.rollback(conn);
				System.out.println("서비스에서 롤백됨");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			jdt.close(conn);
		}

		return res;
	}

}
