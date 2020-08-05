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
								class="h2 text-primary font-secondary" href="notice.do">공지사항 / FAQ</a></li>
							<li
								class="list-inline-item text-white h3 font-secondary @@nasted"></li>
						</ul>
						<p class="text-lighten">공지사항 게시판</p>
					</div>
				</div>
			</div>
		</section>
		<!-- /page title -->
		
	<!-- 작성 form -->
	<div class = "container">
		<div class = "write-detail">
		
			<form action ="update.do" method="post" enctype="multipart/form-data">
			
				<table class="table table-striped">
					<tbody>
						<tr>
							<select name ="writeType" class="form-control mb-3">
								<option value="FAQ">FAQ</option>
								<option value="공지사항">공지사항</option>
							</select>
						</tr>
						
						<tr>
							<td><input type = "text" name="writeTitle" value="${data.board.n_title}"></td>
						</tr>
						
						<tr>
							<td> <span> 기존파일 : ${data.board.original_filepath} </span> 
								<br> 변경할 파일 : <input type="file" name="writeFile"> </td>
						</tr>
						
						<tr>
							<td><textarea cols="50" rows="10" name="writeContent">${data.board.n_cont}</textarea></td>
						</tr> 
				</tbody>
			</table>
			
			<input type="hidden" name="nNo" value="${data.board.n_no}"/>
		  	<button class="btn btn-primary btn-sm" id="write-btn" type="submit">수정완료</button>
		
			</form>
			<!-- /작성 form -->
			
			</div>
		</div>
	
	</div>
	
<%@include file="../include/footer.jsp"%>
</body>
</html>
