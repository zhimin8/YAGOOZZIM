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
<section class="page-title-section overlay" data-background="images/backgrounds/page-title.jpg" style="background-image: url(&quot;images/backgrounds/page-title.jpg&quot;);">
  <div class="container">
    <div class="row">
      <div class="col-md-8">
        <ul class="list-inline custom-breadcrumb">
          <li class="list-inline-item"><a class="h2 text-primary font-secondary" href="courses.html">Our Courses</a></li>
          <li class="list-inline-item text-white h3 font-secondary "></li>
        </ul>
        <p class="text-lighten">Our courses offer a good compromise between the continuous assessment favoured by some universities and the emphasis placed on final exams by others.</p>
      </div>
    </div>
  </div>
</section>
<!-- /page title -->
		
		
<!-- courses -->
<section class="section">
  <div class="container">
    <!-- course list -->
<div class="row justify-content-center">
  <!-- course item -->
  <div class="col-lg-4 col-sm-6 mb-5">
    <div class="card p-0 border-primary rounded-0 hover-shadow">
      <img class="card-img-top rounded-0" src="images/courses/course-1.jpg" alt="course thumb">
      <div class="card-body">
        <ul class="list-inline mb-2">
        </ul>
        <a href="course-single.html">
          <h4 class="card-title"> SK와이번스 </h4>
        </a>
        <p class="card-text mb-4"> 장소 : 인천SK행복드림구장 </p>
        <a href="course-single.html" class="btn btn-primary btn-sm">좌석ZZIM</a>
      </div>
    </div>
  </div>
  <!-- course item -->
  <div class="col-lg-4 col-sm-6 mb-5">
    <div class="card p-0 border-primary rounded-0 hover-shadow">
      <img class="card-img-top rounded-0" src="images/courses/course-2.jpg" alt="course thumb">
      <div class="card-body">
        <ul class="list-inline mb-2">
        </ul>
        <a href="course-single.html">
          <h4 class="card-title"> 두산베어스 </h4>
        </a>
        <p class="card-text mb-4"> 장소 : 잠실야구장 </p>
        <a href="course-single.html" class="btn btn-primary btn-sm">좌석ZZIM</a>
      </div>
    </div>
  </div>
  <!-- course item -->
  <div class="col-lg-4 col-sm-6 mb-5">
    <div class="card p-0 border-primary rounded-0 hover-shadow">
      <img class="card-img-top rounded-0" src="images/courses/course-3.jpg" alt="course thumb">
      <div class="card-body">
        <ul class="list-inline mb-2">
        </ul>
        <a href="course-single.html">
          <h4 class="card-title"> LG트윈스 </h4>
        </a>
        <p class="card-text mb-4"> 장소 : 잠실야구장 </p>
        <a href="course-single.html" class="btn btn-primary btn-sm">좌석ZZIM</a>
      </div>
    </div>
  </div>

</div>
<!-- /course list -->

  </div>
</section>
<!-- /courses -->

		<%@include file="../include/footer.jsp"%>

	</div>

</body>
</html>