<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
</head>

<body>

	<div class="wrapper">
		<!-- top/header 부분 -->
		<%@include file="../include/top.jsp"%>
		<%@include file="../include/header.jsp"%>

		<!-- hero slider -->
		<section class="hero-section overlay bg-cover" data-background="${pageContext.request.contextPath}/resources/images/backgrounds/background.jpg">
			<div class="container">
				<div class="hero-slider">
					<!-- slider item -->
					<div class="hero-slider-item">
						<div class="row">
							<div class="col-md-8">
								<h1 class="text-white" data-animation-out="fadeOutRight"
									data-delay-out="5" data-duration-in=".3"
									data-animation-in="fadeInLeft" data-delay-in=".1">All the SEAT you want</h1>
									
								<h3 class="text-muted mb-4" data-animation-out="fadeOutRight"
									data-delay-out="5" data-duration-in=".3"
									data-animation-in="fadeInLeft" data-delay-in=".4">: YAGOOZZIM</h3>
							</div>
						</div>
					</div>
				</div>
			</div>
			
			
		</section>
		<!-- /hero slider -->


		<!-- banner-feature -->
		<section class="bg-gray">
			<div class="container-fluid p-0">
				<div class="row no-gutters">
					<div class="col-xl-4 col-lg-5 align-self-end">
					
					</div>
					<div class="col-xl-8 col-lg-7">
						<div class="row feature-blocks bg-gray justify-content-between">
							<div
								class="col-sm-6 col-xl-5 mb-xl-5 mb-lg-3 mb-4 text-center text-sm-left">
								<i class="ti-book mb-xl-4 mb-lg-3 mb-4 feature-icon"></i>
								<h3 class="mb-xl-4 mb-lg-3 mb-4">좌석 ZZIM</h3>
								<p>당신이 원하는 모든 좌석의 뷰</p>
							</div>
							<div class="col-sm-6 col-xl-5 mb-xl-5 mb-lg-3 mb-4 text-center text-sm-left">
								<i class="ti-blackboard mb-xl-4 mb-lg-3 mb-4 feature-icon"></i>
								<h3 class="mb-xl-4 mb-lg-3 mb-4">ABOUT US</h3>
								<p>우리 열심히 했어요</p>
							</div>
							<div
								class="col-sm-6 col-xl-5 mb-xl-5 mb-lg-3 mb-4 text-center text-sm-left">
								<i class="ti-agenda mb-xl-4 mb-lg-3 mb-4 feature-icon"></i>
								<h3 class="mb-xl-4 mb-lg-3 mb-4">커뮤니티</h3>
								<p>경기 후기 또는 맛집 공유</p>
							</div>
							<div
								class="col-sm-6 col-xl-5 mb-xl-5 mb-lg-3 mb-4 text-center text-sm-left">
								<i class="ti-write mb-xl-4 mb-lg-3 mb-4 feature-icon"></i>
								<h3 class="mb-xl-4 mb-lg-3 mb-4">FnQ</h3>
								<p>자주 찾는 질문</p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
		<!-- /banner-feature -->

		<!-- events -->

		<section class="section bg-gray">
			<div class="container">
				<div class="row">
					<div class="col-12">

						<div
							class="d-flex align-items-center section-title justify-content-between">
							<h2 class="mb-0 text-nowrap mr-3">일 별 경기일정</h2>
							<div class="border-top w-100 border-primary d-none d-sm-block"></div>
					
						</div>
					</div>
				</div>
				<div class="row justify-content-center">
					<c:forEach items="${data.dataList}" var="list">
						<!-- event -->

						<div class="col-lg-4 col-sm-6 mb-5 mb-lg-0">
							<div class="card border-0 rounded-0 hover-shadow">
								<div class="card-img position-relative">
									<img class="card-img-top rounded-0"
										src="${pageContext.request.contextPath}/resources/images/events/black.png" alt="event thumb" />
									<div class="card-date">
										<span id="dataday">${list.p_day}</span>
									</div>
								</div>
								<div class="card-body">
									<%-- <h4 class="col-md-6">${list.p_time}</h4> --%>
									<h3 class="card-title">　${list.p_time}　　 ${list.p_play}</h3>
								</div>

							</div>
						</div>
					</c:forEach>
					<!-- event -->


				</div>

			</div>
		</section>
		<!-- /events -->

		<%@include file="../include/footer.jsp"%>

	</div>

</body>
</html>