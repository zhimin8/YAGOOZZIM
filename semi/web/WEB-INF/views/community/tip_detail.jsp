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
	<section class="page-title-section overlay"data-background="../resources/images/backgrounds/page-title.jpg">
		<div class="container">
			<div class="row">
				<div class="col-md-8">
					<ul class="list-inline custom-breadcrumb">
						<li class="list-inline-item"><a class="h2 text-primary font-secondary" href="community.do">COMMUNITY : 꿀TIP 공유</a></li>
					</ul>
					<p class="text-lighten">여러분이 알고있는 경기장 꿀팁을 공유해보세요!</p>
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
		<h3>${data.tip.t_title}</h3>
		<hr>
		<div class="col-12">
			<ul class="list-inline">
				<li class="list-inline-item mr-4 mb-3 mb-md-0 text-light"> <span class="font-weight-bold mr-2"> 작성자 : </span>${data.tip.t_writer}</li>
				<li class="list-inline-item mr-4 mb-3 mb-md-0 text-light">작성일 : ${data.tip.t_date}</li>
			</ul>
		</div>
		      
		<!-- border -->
		<div class="col-12 mt-4">
			<div class="border-bottom border-primary"></div>
		</div>
		
		<c:if test= "${not empty data.tip.rename_filepath}">
			<div class="col-12 mb-4" id="img-size">
				<br>
				<img src= "../resources/upload/${data.tip.rename_filepath}" alt="이미지가 안나와요ㅜ" class="img-fluid w-100">
			</div>
		</c:if>
			<p>${data.tip.t_cont}</p>
	</div>
	
	    </div>
	  </div>
	  
	  <br><br>
	  
	<c:if test = "${data.tip.t_writer == sessionScope.loginInfo.m_id}"> <!-- jimin대신 현재 접속한 아이디가 + 관리자가 떠야해 -->
	  
	  	<form action="tip_update.do" method="post">
	  		<input type="hidden" name="tNo" value="${data.tip.t_no}"/>
	  		<input type="hidden" name="tType" value="${data.tip.t_type}"/>
	  		<input type="hidden" name="tTitle" value="${data.tip.t_title}"/>
	  		<input type="hidden" name="tCont" value="${data.tip.t_cont}"/>
	  		<input type="hidden" name="tOrigin" value="${data.tip.original_filepath}"/>
	  		<input type="hidden" name="tRename" value="${data.tip.rename_filepath}"/>
	  		<button class="btn btn-primary btn-sm" id="write-btn-plus1" type="submit">수정</button>
	  	</form>
		
		<form action="tip_delete.do" method="post">
			<input type="hidden" name="tNo" value="${data.tip.t_no}"/>
			<button class="btn btn-primary btn-sm" type="submit" id="write-btn-plus1" onclick="if(!confirm('삭제하시겠습니까?')){return false;}">삭제</button>
	  	</form>
	  	
		</c:if>
		
	<a href="tip.do" class="btn btn-primary btn-sm" id="write-btn-list">목록으로</a>
	
	</section>
	<!-- /blog details -->

	</div>
	
<%@include file="../include/footer.jsp"%>
</body>
</html>
