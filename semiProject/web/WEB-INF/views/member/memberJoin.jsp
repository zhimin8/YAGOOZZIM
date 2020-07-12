<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<style>
</style>
</head>
<body>
	<div class="wrapper">
		<%@include file="../include/top.jsp"%>
		<%@include file="../include/header.jsp"%>

		<section>
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content rounded-0 border-0 p-4">
					<div class="modal-header border-0"></div>
					<div class="modal-body">
						<div class="login">
							<br> <br> <br> <br> <br> <br> <br>
							<br>
							<h3>Resister</h3>
							<a href="/semi/member/kakaojoin.do">Register</a> <br> <br>
							<br>
							<form action="/semi/member/joinimple.do" method="post" onsubmit="return validate();">

								<div class="col-12">
									<input type="text" class="form-controlid" id="userId"name="userId" placeholder="ID">
									<button type="button" onclick="idCheck()" class="btn btn-check">CHECK</button>
									<br> <br> <span id="idCheckMsg"></span> <br> <br>

								</div>

								<br>
								<div class="col-12">
									<input type="password" class="form-control mb-3" id="pwd1" name="userPwd" placeholder="PASSWORD">
								</div>

								<div class="col-12">
									<input type="password" class="form-control mb-3" id="pwd2" name="reuserPwd" placeholder="CONFIRM PASSWORD">

								</div>
								<div class="col-12" id="pwdChk"></div>
								<br> <br>

								<div class="col-12">
									<input type="text" class="form-control mb-3" id="email"
										name="m_email" placeholder="E-MAIL">
								</div>



								<div class="col-12">
									<input type="text" class="form-control mb-3" id="tell"
										name="m_tell" placeholder="PHONE NUMBER">
								</div>


<br>


								<div class="col-12">
									<h4>SELECT YOUR TEAM</h4>
									<select name="my_team" class="form-control mb-3">
										<option value="sk">SK 와이번스</option>
										<option value="lg">LG 트윈스</option>
										<option value="dusan">두산 베어스</option>
									</select>

								</div>


								<br> 

								<div class="col-12">
									<button type="submit" class="btn btn-primary">SIGN UP</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>


		</section>

		<script src="https://code.jquery.com/jquery-3.5.0.js"
			integrity="sha256-r/AaFHrszJtwpe+tHyNi/XCfMxYpbsRg2Uqn0x3s2zc="
			crossorigin="anonymous">
			
		</script>


		<script>
			var idCheckFlag = false;

			<c:if test="${data.isSuccess == 'false'}">
			alert("회원가입에 실패하였습니다. 다시한번 확인해주세요.")
			</c:if>

			function idCheck() 
				$.ajax({
					url : '/semi/member/idcheck.do',
					type : 'get',
					data : $('#userId').serialize(),
					success : function(data) {
							if (data != '') {
								document.querySelector('#idCheckMsg').textContent = '';
								document.querySelector('#idCheckMsg').textContent = '는 이미 존재하는 아이디 입니다.';
								idCheckFlag = false;
							} else {
								document.querySelector('#idCheckMsg').textContent = '';
								document.querySelector('#idCheckMsg').textContent = data + '는 사용 가능한 아이디입니다.';
								idCheckFlag = true;
							}
						}
					})

			}

			function validate() {
				var Pwd = document.getElementById('userPwd');
				var regExpPwd = /(?=.*\d{1,50})(?=.*[~!@#$%\^&*()_+=]{1,50})(?=.*[a-zA-Z]{2,50}).{8,50} /;
				function chk(re, e, msg) {
					function chk(re, e, msg) {
						if (re.test(e.value)) {
							return true;
						} else {
							alert(msg);
							e.value = "";
							e.focus();
							return false;
						}

					}

					if (!idCheckFlag) {
						alert("아이디 중복 검사를 해주세요");
						return false;
					}

					if (chk(regExpPw, pass,
							'암호는 영문자 숫자 기호문자의 조합으로 8글자 이상 작성해 주세요.')) {
						return false;
					}

					return true;
				}
			

			$(function() {
				$("input").keyup(function() {
					var pwd1 = $("#pwd1").val();
					var pwd2 = $("#pwd2").val();
					if (pwd1 != "" || pwd2 != "") {
						if (pwd1 == pwd2) {
							$("#pwdChk").text('비밀번호가 일치합니다.');
						} else {
							$("#pwdChk").text('비밀번호를 확인하세요.');
						}
					}
				});
			});
		</script>
		<%@include file="../include/footer.jsp"%>
	</div>
</body>
</html>