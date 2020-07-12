<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css"
	href="/semi/resources/slick/slick/slick.css" />
<link rel="stylesheet" type="text/css"
	href="/semi/resources/slick/slick/slick-theme.css" />

<style>
.seat-wrapper {
	width: 100%;
	height: 900px;
	text-align: center;
}

.all-wrapper {
	background: white;
	text-align: center;
	width: 100%;
	height: 95%;
	text-align: center;
}

.first-wrapper {
	background: white;
	text-align: center;
	display:inline-block;
	width: 80%;
	height: 40%;
}

.second-wrapper {
	display:inline-block;
	background: white;
	text-align: center;
	width: 100%;
	height: 60%;
}

.stadium-img {
	float:left;
	width: 40%;
	height: 100%;
	border: solid black 1px;
}

.seat-img {
	float:left;
	width: 40%;
	height: 100%;
	border: solid black 1px;
	text-align:center;
	background-repeat: no-repeat;
}

.seat-name {
	float:left;
	width: 20%;
	height: 100%;
	border: solid black 1px;
	line-height:300px;
}

.mainview{
	margin: 2% 2% 2% 20%;
	text-align:center;
}

.fade {
	display:inline-block;
	width: 80%;
	height: 80%;
}

</style>
</head>

<body onload="get();">

	<div class="wrapper">
		<!-- top/header 부분 -->
		<%@include file="../include/top.jsp"%>
		<%@include file="../include/header.jsp"%>

		<!-- page title -->
		<section class="page-title-section overlay"
			data-background="images/backgrounds/page-title.jpg">
			<div class="container">
				<div class="row">
					<div class="col-md-8">
						<ul class="list-inline custom-breadcrumb">
							<li class="list-inline-item"><a
								class="h2 text-primary font-secondary" href="courses.html">좌석ZZIM</a></li>
							<li class="list-inline-item text-white h3 font-secondary nasted"></li>
						</ul>
						<p class="text-lighten">사용자 선택에 따른 좌석추천</p>
					</div>
				</div>
			</div>
		</section>
		<!-- /page title -->

		<!-- 전체 흰색부분 -->
		
		<div class="seat-wrapper">
			<%
				int idx = 0;
				List<String> blockList = new ArrayList<>();
				List<String> seatList = new ArrayList<>();
			%>
			<div class="all-wrapper">
				<div class="first-wrapper">
					<div class="stadium-img">
						<img style="width: 100%; height: 100%"
							src="/semi/resources/seatImg/stadiumImg/${data.stadium }.jpg" />
					</div>
					<div class="seat-img">
					</div>
					<div class="seat-name"></div>
				</div>
				<div class="second-wrapper">
					<div class="mainview">
					<section class="fade">
						<c:forEach items="${data.fileList }" var="seat" varStatus="status">
							<%
								String str = ((String)((List)((Map)request.getAttribute("data")).get("fileList")).get(idx)).substring(2);
								String seatName = (String)((List)((Map)request.getAttribute("data")).get("fileList")).get(idx);
								int strIdx = (str.indexOf("b"))+1;
								str = str.substring(0, strIdx);
								blockList.add(str);
								seatList.add(seatName);
								idx++;
							%>

							<div class="seatView">
								<img style="width: 60%; height:100%; margin:0% 0% 0% 20%" id=${status.index} src="/semi/resources/seatImg/${seat }.jpg" />
							</div>
						</c:forEach>
						<%-- <%
							pageContext.setAttribute("blockList", blockList);
						%> --%>
					</section>
					</div>
				</div>
			</div>
		</div>

	</div>

	<%@include file="../include/footer.jsp"%>


	<script type="text/javascript"
		src="//code.jquery.com/jquery-1.11.0.min.js"></script>
	<script type="text/javascript"
		src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
	<script type="text/javascript"
		src="/semi/resources/slick/slick/slick.min.js"></script>

	<script type="text/javascript">
	
	var test = [];
	<%for (int i = 0; i < blockList.size(); i++) {
	String res = blockList.get(i);%>
	
	test[<%=i%>] ='<%=res%>';
	<%}%>
	
	var seatArr = [];
	<%for (int i = 0; i < seatList.size(); i++) {
	String sName = seatList.get(i);%>
	
	seatArr[<%=i%>] ='<%=sName%>';
	<%}%>
	
		$(document).ready(function() {
			$('.fade').slick({
				dots : true,
				infinite : true,
				speed : 500,
				fade : true,
				cssEase : 'linear'
			});
		});

		function get() {
			// 1. 아까 위에서 만든 blockList 가져오기
			// 2. blockList.get(idx)해서 파일명가져오기
			// 3. 만들어놓은 div에 bacgkround-image로 파일명 넣기
			document.querySelector('.seat-img').style.backgroundImage = "url(/semi/resources/seatImg/blockImg/"+ test[0] + ".jpg),url(/semi/resources/seatImg/blockImg/no-img.jpg)"
			document.querySelector('.seat-name').innerHTML=seatArr[0];
			img = new Image();
			
			var liArr = new Array();
			var ul = document.querySelector('.slick-dots');
			for (i = 0; i < ul.children.length; i++) {
				liArr.push(ul.children[i]);
			}
			liArr.forEach(function(el) {
				
					el.addEventListener('click',function() {
						
						var idx = (el.children[0].textContent) - 1;
						document.querySelector('.seat-img').style.backgroundImage = "url(/semi/resources/seatImg/blockImg/"+ test[idx] + ".jpg),url(/semi/resources/seatImg/blockImg/no-img.jpg)"
						document.querySelector('.seat-name').innerHTML=seatArr[idx]
						
					})
			})
			
			
		}
	</script>

</body>
</html>