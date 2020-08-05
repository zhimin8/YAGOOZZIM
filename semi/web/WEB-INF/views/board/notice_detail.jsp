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
							<li class="list-inline-item"><a class="h2 text-primary font-secondary" href="notice.do">공지사항 / FAQ</a></li>
						</ul>
						<p class="text-lighten">공지사항 게시판</p>
					</div>
				</div>
			</div>
		</section>
		<!-- /page title -->


		<!-- notice details -->
		<section class="section">
			<div class="container">
				<div class="row">
					<div class="col-12">
						<div class="d-flex">
							<div class="text-center mr-4">
								<div class="p-4 bg-primary text-white">
									<span class="h2 d-block">${data.board.n_type}</span>
								</div>
							</div>
							
							
					<!-- blog contect -->
					<div class="col-12 mb-5">
						<h3>${data.board.n_title}</h3>
						<hr>
						<div class="col-12">
							<ul class="list-inline">
								<li class="list-inline-item mr-4 mb-3 mb-md-0 text-light">
									<span class="font-weight-bold mr-2"> 작성자 : </span>${data.board.n_writer}</li>
								<li class="list-inline-item mr-4 mb-3 mb-md-0 text-light">작성일 : ${data.board.n_date}</li>
							</ul>
						</div>

						<!-- border -->
						<div class="col-12 mt-4">
							<div class="border-bottom border-primary"></div>
						</div>

						
						<c:if test= "${not empty data.board.rename_filepath}">
							<div class="col-12 mb-4" id="img-size">
								<br>
								<img src="../resources/upload/${data.board.rename_filepath}" alt="이미지가 안보여요." class="img-fluid w-100">
							</div>
						</c:if>
						
						<p>${data.board.n_cont}</p>
						
						</div>
					</div>
				</div>
				</div>
				</div>
					
				<c:if test="${sessionScope.loginInfo.m_id eq 'jimin'}">
					<!-- jimin대신 현재 접속한 아이디가 + 관리자가 떠야해 -->
					<form action="notice_update.do" method="post">
						<input type="hidden" name="nNo" value="${data.board.n_no}" /> 
						<input type="hidden" name="nType" value="${data.board.n_type}" /> 
						<input type="hidden" name="nTitle" value="${data.board.n_title}" /> 
						<input type="hidden" name="nCont" value="${data.board.n_cont}" /> 
						<input type="hidden" name="nOrigin" value="${data.board.original_filepath}" /> 
						<input type="hidden" name="nRename" value="${data.board.rename_filepath}" />
					 <button class="btn btn-primary btn-sm" id="write-btn-plus1" type="submit">수정</button>
					</form>

					<form action="delete.do" method="post">
						<input type="hidden" name="nNo" value="${data.board.n_no}" /> 
						<button class="btn btn-primary btn-sm" type="submit" id="write-btn-plus1" onclick="if(!confirm('삭제하시겠습니까?')){return false;}">삭제</button>
					</form>

				</c:if>
				<a href="notice.do" class="btn btn-primary btn-sm" id="write-btn-list">목록으로</a>
		
		
		</section>
		<!-- /notice details -->
	</div>

	
<%@include file="../include/footer.jsp"%>
</body>
</html>
