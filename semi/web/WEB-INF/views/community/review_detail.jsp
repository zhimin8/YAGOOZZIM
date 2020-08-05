<%@page import="java.io.PrintWriter"%>
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
		<section class="page-title-section overlay" data-background="../resources/images/backgrounds/page-title.jpg">
			<div class="container">
				<div class="row">
					<div class="col-md-8">
						<ul class="list-inline custom-breadcrumb">
							<li class="list-inline-item"> <a class="h2 text-primary font-secondary" href="community.do">COMMUNITY : 후기공유</a></li>
						</ul>
						<p class="text-lighten">여러분이 다녀온 경기에 대해 서로 공유해보세요!</p>
					</div>
				</div>
			</div>
		</section>
		<!-- /page title -->


		<!-- blog details -->
		<section class="section-sm bg-gray">
			<div class="container">
				<div class="row">

		<!-- blog contect -->
		<div class="col-12 mb-5">
			<h3>${data.review.r_title}</h3>
			<hr>
			<div class="col-12">
				<ul class="list-inline">
					<li class="list-inline-item mr-4 mb-3 mb-md-0 text-light"> <span class="font-weight-bold mr-2"> 작성자 : </span>${data.review.r_writer}</li>
					<li class="list-inline-item mr-4 mb-3 mb-md-0 text-light">작성일 : ${data.review.r_date}</li>
				</ul>
			</div>

			<!-- border -->
			<div class="col-12 mt-4">
				<div class="border-bottom border-primary"></div>
			</div>
			
			<c:if test= "${not empty data.review.rename_filepath}">
				<div class="col-12 mb-4" id="img-size" >
					<br>
					<img src="../resources/upload/${data.review.rename_filepath}" alt="이미지가 왜 안나오지!" class="img-fluid w-100">
				</div>
			</c:if>
			
				<p>${data.review.r_cont}</p>
		</div>

				</div>
			</div>

			<br><br>
			
		<c:if test="${data.review.r_writer == sessionScope.loginInfo.m_id}"> <!-- jimin대신 현재 접속한 아이디가 + 관리자가 떠야해 -->
					
			<form action="review_update.do" method="post">
				<input type="hidden" name="rNo" value="${data.review.r_no}"/> 
				<input type="hidden" name="rType" value="${data.review.r_type}"/>
				<input type="hidden" name="rTitle" value="${data.review.r_title}"/> 
				<input type="hidden" name="rCont" value="${data.review.r_cont}"/>
				<input type="hidden" name="rOrigin" value="${data.review.original_filepath}"/>
				<input type="hidden" name="rRename" value="${data.review.rename_filepath}"/>
				<button class="btn btn-primary btn-sm" id="write-btn-plus1" type="submit">수정</button>
			</form>

			<form action="delete.do" method="post">
				<input type="hidden" name="rNo" value="${data.review.r_no}" />
				<button class="btn btn-primary btn-sm" type="submit" id="write-btn-plus1" onclick="if(!confirm('삭제하시겠습니까?')){return false;}">삭제</button>
			</form>

		</c:if>

		<a href="review.do" class="btn btn-primary btn-sm" id="write-btn-list">목록으로</a>
		
		</section>
		<!-- /blog details -->

	</div>
	
	<%@include file="../include/footer.jsp"%>
</body>
</html>
