<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
</head>
<body>
	<div class="wrapper">
		<%@include file="../include/top.jsp"%>
	
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content rounded-0 border-0 p-4">
				<div class="modal-header border-0"></div>
				<div class="modal-body">
					<br> <br>
					<br>
					<h2> 정말 탈퇴를 하실건가요? </h2>
					<h4>탈퇴를 하시면 회원님의 모든 정보가 삭제됩니다.</h4>
					<br> <br> <br>

					<form action="/semi/member/deletememberimple.do" method="post">

						<h6>확인을 위해 아이디와 비밀번호를 다시한번 입력해주세요.</h6>
						<div class="col-12">
							<input type="text" class="form-control mb-3" id="deleteid"
								name="deleteid" placeholder="ID를 입력하세요.">
						</div>

						<div class="col-12">
							<input type="text" class="form-control mb-3" id="deletepwd"
								name="deletepwd" placeholder="비밀번호를 입력해주세요.">
						</div>

						<button type="submit" class="btn btn-primary">NEXT</button>



					</form>


				</div>
			</div>
		</div>

	</div>
</body>
</html>