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
							<li class="list-inline-item">
								<a class="h2 text-primary font-secondary" href="notice.do">공지사항 및 FAQ</a>
							</li>
							<li class="list-inline-item text-white h3 font-secondary @@nasted"></li>
						</ul>
						<p class="text-lighten">공지사항 및 자주 찾는 질문</p>
					</div>
				</div>
			</div>
		</section>
		<!-- /page title -->

<section class="section">
	<c:if test="${sessionScope.loginInfo.m_id eq 'test080301'}">
		<a href="notice_write.do" class="btn btn-primary btn-sm" id="write-btn-form">작성하기</a>
	</c:if>
		<div class="container">
			<c:forEach items="${data.bdata}" var="board">
				
				<div class="row">
				<div class="col-12">
			
				<ul class="list-unstyled">
					<!-- notice item -->
				<li class="d-md-table mb-4 w-100 border-bottom hover-shadow">
					
					<div class="d-md-table-cell text-center p-4 bg-primary text-white mb-4 mb-md-0" id="faq-box">
					<span class="h3 d-block" id="faq-box">${board.n_type}</span></div>
					<!-- 공지사항 관련 -->
					<div class="d-md-table-cell px-4 vertical-align-middle mb-4 mb-md-0">
						<a href="notice_detail.do?n_no=${board.n_no}" class="h4 mb-3 d-block">${board.n_title}</a>
						<p class="mb-0">${board.n_date}</p>
					</div>
					<!-- 자세히 보기 클릭  -->
					<div class="d-md-table-cell text-right pr-0 pr-md-4">
						<a href="notice_detail.do?n_no=${board.n_no}" class="btn btn-primary">자세히 보기</a>
					</div>
				
				</li>
				</ul>
				
				</div>
				</div>
				
			</c:forEach>
		</div>
			
	<div class="paging">
			
			<!-- section pagination -->
				<a href="notice.do?cPage=1">
					<i class="fas fa-angle-double-left"></i>
				</a> 
				<a href="notice.do?cPage=${data.paging.blockStart-1}">
					<i class="fas fa-angle-left"></i>
				</a>
					
				<c:forEach begin="${data.paging.blockStart}" end="${data.paging.blockEnd}" var="page">
					<a href="notice.do?cPage=${page}">
					<span>${page}</span>
					</a>
				</c:forEach>

				<a href="notice.do?cPage=${data.paging.blockEnd+1}">
					<i class="fas fa-angle-right"></i>
				</a> 
				<a href="notice.do?cPage=${data.paging.lastPage}">
					<i class="fas fa-angle-double-right"></i>
				</a>

			</div>
			<!-- // section pagination -->
		</section>

	</div>
<%@include file="../include/footer.jsp"%>
</body>
</html>
