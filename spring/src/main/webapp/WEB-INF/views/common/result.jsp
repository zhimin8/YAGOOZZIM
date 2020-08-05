<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<script>
		init = function() {

			<c:if test="${data.alertMsg != null}">
			alert("<c:out value="${data.alertMsg}"/>");
			// 메세지
			</c:if>
			<c:if test="${data.back != null}">
				history.back();
				// 뒤로가기
			</c:if>
			
			
			<c:if test="${data.url != null}">
			location.href = "<c:out value="${data.url}"/>";
			// url이동
			</c:if>
			
		
		};

		init();
	</script>




</body>
</html>