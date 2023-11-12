<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	import="joongo.vo.*"
	import="java.util.*"
	import="joongo.dao.*" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원 정보</title>

	<style>
		@import url("https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css");
	</style>

	<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>

</head>

<body>

	<%@ include file="../include/header.jsp"%>

	<div class="jumbotron">
		<div class="container">
			<h1 class="display-4">회원 정보</h1>
			<p class="lead">환영합니다. ${sessionScope.mid}님</p>
		</div>
	</div>

	<main role="main">
		<div class="container">

			<div class="text-right">
				<a href="/joongo/User/MemberModify.do?mid=${sessionScope.mid }" class="btn btn-sm btn-success">회원 수정</a> 
				<a href="" class="btn btn-sm btn-success">찜한 목록</a>
				<a href="" class="btn btn-sm btn-success">판매/구매 목록</a>
				<a href="" class="btn btn-sm btn-success">후기 목록</a>
				<a href="" class="btn btn-sm btn-success">위치</a>
			</div>

			<div class="row">
				<div class="col-md-3">
					<img src="/joongo/resource/images/${userInfo.mimage }"
						style="width: 100%">
				</div>
				<div class="col-md-8">
					<p></p>
					<p>
						아이디 : ${userInfo.mid }
					</p>
					<p>
						닉네임 : ${userInfo.mname }
					</p>
					<p>
						이메일 : ${userInfo.email }
					</p>
					<p>
						번호 : ${userInfo.mphone }
					</p>
					<p>
						가입 일자 : ${userInfo.mdate }
					</p>
					<p>
						매너 온도 : ${userInfo.manner }
					</p>
				</div>
			</div>

		</div>
	</main>

	<%@ include file="../include/footer.jsp"%>

</body>
</html>
