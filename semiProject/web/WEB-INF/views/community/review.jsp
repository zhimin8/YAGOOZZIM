<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta charset="UTF-8">
 <script src="https://kit.fontawesome.com/039b4a229f.js" crossorigin="anonymous"></script>
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
							<li class="list-inline-item"><a class="h2 text-primary font-secondary" href="community.do">COMMUNITY: 후기공유</a></li>
						</ul>
						<p class="text-lighten">여러분이 다녀온 경기에 대해 서로 공유해보세요!</p>
					</div>
				</div>
			</div>
		</section>
		<!-- /page title -->



		<!-- blogs -->
		<section class="section" id="notice-section">
			<div class="container">
				
			<c:if test="${not empty sessionScope.loginInfo.m_id }">
				<a href="review_write.do" class="btn btn-primary btn-sm" id="write-btn">작성하기</a>
			</c:if>

		<!-- blog post -->
			<article class="col-lg-4 col-sm-6 mb-5">
				<div class="table-size">
					<table class="card rounded-0 border-bottom border-primary border-top-0 border-left-0 border-right-0 hover-shadow">
						<thead class="list-inline mb-3">
							<th class="list-inline-item mr-3 ml-0" id="table-type"><span>분류</span></th>
							<th class="list-inline-item mr-3 ml-0" id="table-title"><span>제목</span></th>
							<th class="list-inline-item mr-3 ml-0" id="thead-writer"><span>작성자</span></th>
							<th class="list-inline-item mr-3 ml-0" id="thead-date"><span>작성일</span></th>
							<th class="list-inline-item mr-3 ml-0" id="thead-btn"><span>자세히보기</span></th>
						</thead>
								
						<tbody>
							<c:forEach items="${data.rdata}" var="review">
								
								<tr class="list-inline mb-3" id ="tbody-size">
									
									<td class="list-inline-item mr-3 ml-0" id="table-type">${review.r_type}</td>
									<td class="list-inline-item mr-3 ml-0" id="table-title">
										<a href="review_detail.do?r_no=${review.r_no}"><h4 class="card-title">${review.r_title}</h4></a>
									</td>
									
									<td class="list-inline-item mr-3 ml-0" id="table-writer"> ${review.r_writer}</td>
						
									<td class="list-inline-item mr-3 ml-0" id="table-date">${review.r_date}</td>
									
									<td class="list-inline-item mr-3 ml-0" id="table-btn">
										<div class ="tbody-btn">
											<a href="review_detail.do?r_no=${review.r_no}" class="btn btn-primary btn-sm">읽어 보기</a>
										</div>
									</td>
										
								</tr>
									
							</c:forEach>
						</tbody>
					</table>
							
				</div> 
			</article>
		</div>
		<!-- /blogs -->
			
			<!-- 페이징 처리 -->
			<div class="paging">
				
				<!-- section pagination -->
				<a href="review.do?cPage=1"><i class="fas fa-angle-double-left"></i></a>
				<a href="review.do?cPage=${data.paging.blockStart-1}"><i class="fas fa-angle-left"></i></a>
						
				<c:forEach begin="${data.paging.blockStart}" end="${data.paging.blockEnd}" var="page">
					<a href="review.do?cPage=${page}"><span>${page}</span></a>
				</c:forEach>
	
				<a href="review.do?cPage=${data.paging.blockEnd+1}"><i class="fas fa-angle-right"></i></a> 
				<a href="review.do?cPage=${data.paging.lastPage}"><i class="fas fa-angle-double-right"></i></a>
	
			</div>
		</section>
	</div>
	
<%@include file="../include/footer.jsp"%>
</body>
</html>
