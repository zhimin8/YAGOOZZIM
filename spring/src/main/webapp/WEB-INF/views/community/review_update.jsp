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
			<div class="container" >
				<div class="row">
					<div class="col-md-8">
						<ul class="list-inline custom-breadcrumb">
							<li class="list-inline-item"><a class="h2 text-primary font-secondary" href="community.do"> COMMUNITY : 커뮤니티</a></li>
						</ul>
						<p class="text-lighten">여러분이 다녀온 경기에 대해 서로 공유해보세요!</p>
					</div>
				</div>
			</div>
		</section>
		<!-- /page title -->
		
	<!-- 작성 form -->
	<div class ="write-cover">
	<div class = "container">
		<div class = "write-detail">
			<form action ="update.do" method="post" enctype="multipart/form-data" >
				<table class="table table-striped">
					<tbody>
						<tr>
							<select name ="writeType" class="form-control mb-3">
								<option value="경기">경기 관련</option>
								<option value="좌석">좌석 관련</option>
							</select>
						</tr>
						<tr>
							<td><input type = "text" name="writeTitle" value="${data.review.r_title}"></td>
						</tr>
						<tr>
							<td><span> 기존파일 : ${data.review.original_filepath}</span> <br>변경할 파일 : <input type="file" name="writeFile"></td>
						</tr>
						<tr>
							<td><textarea cols="50" rows="10" name="writeContent">${data.review.r_cont}</textarea></td>
						</tr> 
				</tbody>
			</table>
			
		  	<input type="hidden" name="rNo" value="${data.review.r_no}">
		  		<button class="btn btn-primary btn-sm" id="write-complete" type="submit">수정완료</button>
			</form>
			<!-- /작성 form -->
			
			</div>
		</div>
	</div>
	</div>
	
</body>
</html>
