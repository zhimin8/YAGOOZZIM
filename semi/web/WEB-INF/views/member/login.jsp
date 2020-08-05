<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
</head>
<body>
	<div class="wrapper">
		<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>

		<%@include file="../include/top.jsp"%>
		<%@include file="../include/header.jsp"%>

		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content rounded-0 border-0 p-4">
				<div class="modal-header border-0"></div>
				<div class="modal-body">
					<br> <br> <br> <br> <br> <br> <br>
					<br>
					<h3>Login</h3>
					<br> <br> <br>
					<form action="/semi/member/loginimple.do" method="post">

						<!-- 아이디 -->
						<div class="col-12">
							<input type="text" class="form-control mb-3" id="id" name="id"
								placeholder="INSERT YOUR ID">
						</div>

						<div class="col-12">
							<input type="password" class="form-control mb-3" id="userPwd"
								name="pwd" placeholder="INSERT YOUR PASSWORD">
						</div>



						<button type="submit"  id="btn1" class="checkMsg">LOGIN</button>
<!-- class="btn btn-primary" -->
					</form>


					
						<a onclick="kakaoLogin()" id="btn2" >KAKAO LOGIN </a>
					

				</div>
				<script>
					function kakaoLogin() {
						var id;
						var myJSKey = "8a56d13e8e03ddc1d4bee85ce528067a";
						Kakao.init(myJSKey);

						// 로그인 창을 띄웁니다.
						Kakao.Auth.login({
							success : function(authObj) {
								/* alert(JSON.stringify(authObj)); */
								// 로그인 성공시, API를 호출합니다.
								Kakao.API.request({
									url : '/v2/user/me',
									success : function(res) {
										//alert(JSON.stringify(res));
										id = res.id;
										$.ajax({
											url : "/semi/member/kakaoidcheck.do", // 요기에
											type : 'POST',
											data : {"id":id},
											success : function(data) {
												
												if(data == "") {
													location.href="/semi/member/kakaojoinpage.do?id="+id;
												}else {
													location.href="/semi/member/kakaologin.do?id="+id;
												}
											}, // success 

											fail : function(error) {
												alert(JSON.stringify(error));
											}
										}); // $.ajax */

									},
									fail : function(error) {
										alert(JSON.stringify(error));

									}
								});
							},
							fail : function(err) {
								alert(JSON.stringify(err));
							}
						});
						
					};
				</script>



				<div>
					<ul class="login-bottom">
						<li>아이디를 잊으셨나요?<a href="/semi/member/findid.do"> 아이디 찾기</a>
						<li>비밀번호를 잊으셨나요?<a href="/semi/member/findpwd.do"> 비밀번호
								찾기</a>
						<li>계정이 없으신가요?<a href="/semi/member/join.do"> 가입하러 가기</a>
					</ul>
				</div>
			</div>
		</div>


		<script>
			/*   function kakaoLogout() {
			     Kakao.Auth.logout(function(response) {
			        alert(response + 'logout');
			     });   */
		</script>
		<%@include file="../include/footer.jsp"%>
	</div>
</body>
</html>
