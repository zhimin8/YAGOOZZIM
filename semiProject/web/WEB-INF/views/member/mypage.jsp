<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@include file="../include/top.jsp"%>
<%@include file="../include/header.jsp"%>

</head>
<!-- 필수양식 : end -->


<body>
	<div class="wrapper">
		<%@include file="../include/top.jsp"%>
		<%@include file="../include/header.jsp"%>


		<section class="section-sm">
			<div class="container">
				<div class="row">
					<div class="col-12 mb-4">
						<!-- course thumb -->
						<img src="../resources/images/courses/course-single.jpg"
							class="img-fluid w-100">
					</div>
				</div>
				<!-- course info -->
				<div class="row align-items-center mb-5">
					<div class="col-xl-3 order-1 col-sm-6 mb-4 mb-xl-0">
						<h2>MY PAGE</h2>
					</div>

					<!-- border -->
					<div class="col-12 mt-4 order-4">
						<div class="border-bottom border-primary"></div>
					</div>
				</div>
				<!-- course details -->
				<div class="row">
					<div class="col-12 mb-4"></div>
					<div class="col-12 mb-4">
						<h5 class="mb-3">ID</h5>
						<div class="col-12 px-0">
							<div class="row">
								<div class="col-md-6">
									<ul class="list-styled">
										<li>${sessionScope.loginInfo.m_id}</li>

									</ul>
								</div>


							</div>
						</div>
					</div>
					<hr>
					<form action="/semi/member/modifypw.do" method="post">
						<div class="col-12 mb-4">
							<h5 class="mb-3">CHANGE PASSWORD</h5>
							<div class="col-12 px-0">
								<input type="password" class="form-control mb-3" id="newPwd"
									name="newPwd" placeholder="PASSWORD">
								<button type="submit" class="btn btn-primary">CHANGE
									PASSWORD</button>
							</div>
						</div>
					</form>
					<hr>




					<div class="col-12 mb-4">
						<h5 class="mb-3">E-MAIL</h5>
						<div class="col-12 px-0">
							<div class="row">
								<div class="col-md-6">
									<ul class="list-styled">
										<li>${sessionScope.loginInfo.m_email}</li>

									</ul>
								</div>
							</div>
						</div>
					</div>
					<hr>
					<div class="col-12 mb-4">
						<h5 class="mb-3">PHONE NUMBER</h5>
						<div class="col-12 px-0">
							<div class="row">
								<div class="col-md-6">
									<ul class="list-styled">
										<li>${sessionScope.loginInfo.m_tell}</li>

									</ul>
								</div>
							</div>
						</div>
					</div>
					<hr>



					<form action="/semi/member/modifyteam.do" method="post">
						<div class="col-12 mb-4">
							<h5 class="mb-3">YOUR TEAM</h5>
							<div class="col-12 px-0">
								<div class="row">
									<div class="col-md-6">
										<ul class="list-styled">
											<li><c:choose>
													<c:when
														test="${sessionScope.loginInfo.teamname eq 'dusan'}"> 두산 베어스 </c:when>
													<c:when test="${sessionScope.loginInfo.teamname eq 'sk'}"> SK 와이번스 </c:when>
													<c:otherwise> LG 트윈스 </c:otherwise>
												</c:choose></li>

										</ul>
										<select name="newTeam" class="form-control mb-3">
											<option value="sk">SK 와이번스</option>
											<option value="lg">LG 트윈스</option>
											<option value="dusan">두산 베어스</option>
										</select>
										<button type="submit" class="btn btn-primary">CHANGE
											TEAM</button>
									</div>
								</div>
							</div>
						</div>
					</form>

				</div>
				
				<a onclick="popup()">회원탈퇴</a>
				

			</div>

		</section>
		<%@include file="../include/footer.jsp"%>
	</div>

	<script>
	function popup() {
		
		var url = "/semi/member/deletemember.do";
		var name = "plzdontgo"
		var option = "width = 600, height = 600,  left = 200, location = no";
		window.open(url, name, option);
		location.reload()
	}
	</script>

</body>
</html>
