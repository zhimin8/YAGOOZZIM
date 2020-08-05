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
					<h3>FIND YOUR ID</h3>
					<br> <br> <br>

					<form action="/semi/member/findidimple.do" method="post">

						<!-- 아이디 -->
						<div class="col-12">
							<input type="text" class="form-control mb-3" id="eamil"
								name="email" placeholder="찾으시는 ID의 EMAIL주소를 입력하세요.">
						</div>

						<div class="col-12">
							<input type="text" class="form-control mb-3" id="tell"
								name="tell" placeholder="등록하신 핸드폰 번호를 입력하세요.">
						</div>



						<button type="submit" class="btn btn-primary">아이디 찾기</button>



					</form>


				</div>
			</div>
		</div>


		<%@include file="../include/footer.jsp"%>
	</div>
</body>
</html>