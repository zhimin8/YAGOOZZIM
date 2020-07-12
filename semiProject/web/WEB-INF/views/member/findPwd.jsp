<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
</head>
<body>
	<div class="wrapper">
		<%@include file="../include/top.jsp"%>
		<%@include file="../include/header.jsp"%>


		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content rounded-0 border-0 p-4">
				<div class="modal-header border-0"></div>
				<div class="modal-body">
					<br> <br> <br> <br> <br> <br> <br>
					<br>
					<h3>FIND YOUR PASSWORD</h3>
					<br> <br> <br>

					<form action="/semi/member/findpwdimple.do" method="post">

						<!-- 아이디 -->
						<div class="col-12">
							<input type="text" class="form-control mb-3" id="rememberid"
								name="rememberid" placeholder="ID를 입력하세요.">
						</div>

						<div class="col-12">
							<input type="text" class="form-control mb-3" id="rememberemail"
								name="rememberemail" placeholder="등록하신 이메일주소를 입력하세요.">
						</div>
						<button type="submit" class="btn btn-primary">비밀번호 찾기</button>
					</form>
				</div>
			</div>
		</div>






		<%@include file="../include/footer.jsp"%>
	</div>
</body>
</html>