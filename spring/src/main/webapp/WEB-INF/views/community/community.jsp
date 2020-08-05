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
							<li class="list-inline-item"><a
								class="h2 text-primary font-secondary" href="community.do">COMMUNITY : 커뮤니티</a></li>
							<li
								class="list-inline-item text-white h3 font-secondary @@nasted"></li>
						</ul>
						<p class="text-lighten"> 내가 다녀온 구장  / 좌석 / 경기에 대한 후기공유 </p>
					</div>
				</div>
			</div>
		</section>
		<!-- /page title -->

		<section class="section">
			<div class="container">
				<div class="row">
					<div class="col-12">
					
						<ul class="list-unstyled">

							<!-- notice item -->
							<li class="d-md-table mb-4 w-100 border-bottom hover-shadow">
							
								<!-- 업로드한 날짜 띄우기 -->
								<div
									class="d-md-table-cell text-center p-4 bg-primary text-white mb-4 mb-md-0">
									<span class="h2 d-block">후기 공유</span>
								</div>
								
								<!-- 후기공유 관련 -->
								<div
									class="d-md-table-cell px-4 vertical-align-middle mb-4 mb-md-0">
									<a href="review.do" class="h4 mb-3 d-block">좌석 및 경기 후기</a>
									<p class="mb-0"> 여러분이 다녀온 경기에 대해 서로 공유해보세요!</p>
								</div>
								
								<div class="d-md-table-cell text-right pr-0 pr-md-4">
									<a href="review.do" class="btn btn-primary">후기 보러가기</a>
								</div>
							</li>
							
							
							<!-- notice item -->
							<li class="d-md-table mb-4 w-100 border-bottom hover-shadow">
							
								<!-- 업로드한 날짜 띄우기 -->
								<div
									class="d-md-table-cell text-center p-4 bg-primary text-white mb-4 mb-md-0">
									<span class="h2 d-block">구장 꿀팁</span>
								</div>
								
								<!-- 후기공유 관련 -->
								<div
									class="d-md-table-cell px-4 vertical-align-middle mb-4 mb-md-0">
									<a href="tip.do" class="h4 mb-3 d-block">경기장 꿀팁 </a>
									<p class="mb-0"> 여러분이 알고 있는 경기장 꿀팁을 공유해보세요!</p>
								</div>
								
								<div class="d-md-table-cell text-right pr-0 pr-md-4">
									<a href="tip.do" class="btn btn-primary">꿀팁 보러가기</a>
								</div>
							</li>
							
						</ul>
						
					</div>
				</div>
			</div>
		</section>

	</div>


<%@include file="../include/footer.jsp"%>
</body>
</html>
