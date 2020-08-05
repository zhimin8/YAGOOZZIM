<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@include file="../include/top.jsp" %>
<%@include file="../include/header.jsp" %>

</head>
<!-- 필수양식 : end -->

<body>

		
	<!-- 팀분석 wrapper -->
	<div class = "team-wrapper">
		<div class="container">
		<!-- 최근 3연전 결과 -->
			<div id ="three-game-result"> 최근 3연전 1 경기 결과 </div>
			<div id ="three-game-result"> 최근 3연전 2 경기 결과 </div>
			<div id ="three-game-result"> 최근 3연전 3 경기 결과 </div>
			
		<!-- 팀 순위 -->
			<div id = "team-rank"> 전체 순위</div>
		</div>
	</div>
	

<!-- 필수양식 :footer-->
<%@include file="../include/footer.jsp" %>
<!-- 필수양식 :footer : end-->

</body>
</html>
