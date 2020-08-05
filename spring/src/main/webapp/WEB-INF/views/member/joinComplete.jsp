<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 성공 페이지</title>
</head>
<body>
	<%  
		String id = request.getParameter("userId");
		String passwd = request.getParameter("userPwd");
		String tell = request.getParameter("m_tell"); 
	%>
	
	<!-- model에 담아서 보내기 실습해보기 -->
	<h1>회원 가입 성공!!</h1>
	<h3>--- 회원 정보 ---</h3>
	<p><%= id %>님 환영합니다!<br>
	<br><br>
	<hr>
	<a href="<%=request.getContextPath()%>/">메인으로 가기</a>
</body>
</html>






















