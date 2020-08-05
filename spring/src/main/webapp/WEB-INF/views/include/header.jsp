<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- header -->
<header class="fixed-top header">

	<div class="top-header py-2 bg-white">
		<div class="container">
			<div class="row no-gutters">

				<!-- 05/03 지민, 전화번호 및 sns 아이콘 삭제-->
				<div class="col-lg-4 text-center text-lg-left"></div>
				<!-- 05/03 지민, 전화번호 및 sns 아이콘 삭제 : end -->

				<div class="col-lg-8 text-center text-lg-right">
					<c:if test="${loginInfo == null}">
						<ul class="list-inline">
							<li class="list-inline-item"><a
								class="text-uppercase text-color p-sm-2 py-2 px-0 d-inline-block"
								href="${pageContext.request.contextPath}/member/login.do" data-toggle="modal"
								data-target="#loginModal">LOGIN</a></li>
							<li class="list-inline-item"><a
								class="text-uppercase text-color p-sm-2 py-2 px-0 d-inline-block"
								href="${pageContext.request.contextPath}/member/join.do" data-toggle="modal"
								data-target="#signupModal">REGISTER</a></li>
						</ul>
					</c:if>
					
					<c:if test="${loginInfo != null }">
						<ul class="list-inline">
							<li id="username">Hi ${sessionScope.loginInfo.m_id} Welcome!</li>
							<li class="list-inline-item"><a
								class="text-uppercase text-color p-sm-2 py-2 px-0 d-inline-block"
								href="${pageContext.request.contextPath}/member/logoutimple.do" data-toggle="modal"
								data-target="#loginModal">LOGOUT</a></li>
							<li class="list-inline-item"><a
								class="text-uppercase text-color p-sm-2 py-2 px-0 d-inline-block"
								href="${pageContext.request.contextPath}/member/mypage.do" data-toggle="modal"
								data-target="#signupModal">MYPAGE</a></li>
						</ul>
					</c:if>
				</div>
			</div>
		</div>
	</div>

	<!-- navbar -->
	<div class="navigation w-100">
		<div class="container">
			<nav class="navbar navbar-expand-lg navbar-light p-0">
				<!-- 보라가 임의로 수정 -->
				<a class="navbar-brand" href="${pageContext.request.contextPath}/index/index.do"> <img
					src="${pageContext.request.contextPath}/resources/img/mainPage/logo.png" alt="logo">
				</a>

				<div class="collapse navbar-collapse" id="navigation">
					<ul class="navbar-nav ml-auto text-center">
						<li class="nav-item active">
							<a class="nav-link" href="${pageContext.request.contextPath}/">Home</a>
						</li>
						<li class="nav-item @@about">
							<a class="nav-link" href="${pageContext.request.contextPath}/index/about.do">About Us</a>
						</li>
						<li class="nav-item @@courses">
							<a class="nav-link" href="${pageContext.request.contextPath}/seat/seat.do">좌석ZZIM</a>
						</li>
						<li class="nav-item @@events">
							<a class="nav-link" href="${pageContext.request.contextPath}/community/community.do">커뮤니티</a></li>
						<li class="nav-item @@events"><a class="nav-link" href="${pageContext.request.contextPath}/board/notice.do">공지사항</a></li>
					</ul>
				</div>
			</nav>
		</div>
	</div>
</header>

<!-- /header -->


