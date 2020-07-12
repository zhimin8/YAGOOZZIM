<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
.Swrapper {
	padding-left: 20%;
	padding-top: 5%;
	padding-bottom : 5%;
	display: inline-block;
}

.selectedSeat {
	float: left;
	border: 1px solid black;
	width: 300px;
	height: 200px;
}

.optionSelect {
	float: left;
	border: 1px solid black;
	width: 500px;
	height: 200px;
	text-align: center;
}

.seatImage {
	float: left;
	border: 1px solid black;
	width: 300px;
	height: 200px;
}

.seatNumber {
	float: left;
	border: 1px solid black;
	width: 500px;
	height: 200px;
}
</style>
</head>

<body>

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


		<!-- 여기에다가 이제  좌석 선택 div를 넣고 css를 꾸며야하는데-->

		<div class="seat-wrapper">
			<div class="container">
				<div class="Swrapper">

					<!-- 2. 시나리오 선택창 -->
					<div class="optionSelect">
						<form action="/semi/seat/seatselect.do">
							<select id="stadium" name="stadium">
								<option value="" selected>구장선택</option>
								<option value="jamsil">잠실</option>
								<option value="munhak">문학</option>
							</select>
							<select id="homeoraway" name="homeoraway">
								<option value="" selected>홈/어웨이</option>
								<option value="home">홈</option>
								<option value="away">어웨이</option>
							</select> 
							<select id="selectCondition" name="selectCondition">
								<option value="" selected>시야별 추천</option>
								<option value="cheer">응원석이 가까운 좌석</option>
								<option value="infield">내야가 잘 보이는 좌석</option>
								<option value="player">선수들과 가까운 좌석</option>
								<option value="table">테이블석</option>
								<option value="outfield">외야석</option>
								<option value="up4">4인 이상</option>
								<option value="down4">4인 이하</option>
							</select>

							<button type="submit">check</button>
						</form>
					</div>
				</div>
			</div>
		</div>



		<%@include file="../include/footer.jsp"%>
	</div>

</body>
</html>