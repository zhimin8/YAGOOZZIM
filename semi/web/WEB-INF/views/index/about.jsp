<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
</head>

<body>

	<div class="wrapper">
		<!-- top/header 부분 -->
		<%@include file="../include/top.jsp"%>
		<%@include file="../include/header.jsp"%>

		<!-- page title -->
		<section class="page-title-section overlay"
			data-background="../resources/images/backgrounds/page-title.jpg">
			<div class="container">
				<div class="row">
					<div class="col-md-8">
						<ul class="list-inline custom-breadcrumb">
							<li class="list-inline-item"> <a class="h2 text-primary font-secondary">ABOUT US</a></li>
							<br>
							<li class="list-inline-item text-white h3 font-secondary @@nasted"> 야구찜 : YAGOOZZIM</li>
						</ul>
					</div>
				</div>
			</div>
		</section>
		<!-- /page title -->

		<!-- teachers -->
		<section class="section">
			<div class="container">
				<div class="row justify-content-center">
					<div class="col-12">
						<img class="card-img-top rounded-0" id="about-img-size" src="../resources/images/about/logo.png">
					</div>
					<!-- teacher -->
					<div class="col-lg-4 col-sm-6 mb-5 mb-lg-0">
						<div class="card border-0 rounded-0 hover-shadow">
							<div class="card-body">
									<h3 class="card-title">하 지민</h3>
								<div class="d-flex justify-content-between" id="introduce-list">
									<div class="col-md-6">
										<ul class="list-styled">
											<li>커뮤니티 / 공지사항 게시판</li>
											<li>전체 디자인 수정 / 관리</li>
											<li>데이터 수집 및 가공 </li>
										</ul>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!-- teacher -->
					<div class="col-lg-4 col-sm-6 mb-5 mb-lg-0">
						<div class="card border-0 rounded-0 hover-shadow">
							<!--  <img class="card-img-top rounded-0" src="images/teachers/teacher-2.jpg" alt="teacher"> -->
							<div class="card-body">
									<h3 class="card-title">백 동주</h3>
								<div class="d-flex justify-content-between" id="introduce-list">
									<div class="col-md-6">
										<ul class="list-styled">
											<li>좌석ZZIM 페이지</li>
											<li>JSON 데이터 관리</li>
											<li>DB생성 및 관리</li>
										</ul>
									</div>
									
								</div>
							</div>
						</div>
					</div>
					<!-- teacher -->
					<div class="col-lg-4 col-sm-6 mb-5 mb-lg-0">
						<div class="card border-0 rounded-0 hover-shadow">
							<!--  <img class="card-img-top rounded-0" src="images/teachers/teacher-3.jpg" alt="teacher"> -->
							<div class="card-body">
									<h3 class="card-title">이 보라</h3>
								<div class="d-flex justify-content-between" id="introduce-list">
									<div class="col-md-6">
										<ul class="list-styled">
											<li>회원가입 / 로그인 (카카오)</li>
											<li>경기일정 데이터 크롤링</li>
											<li>DB 관리</li>
										</ul>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
		<!-- /teachers -->

	
		<%@include file="../include/footer.jsp"%>
	</div>


</body>
</html>
