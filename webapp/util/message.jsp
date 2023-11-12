<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
import="joongo.vo.*" import="java.util.*" import="joongo.dao.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
td:not(:first-child) {
	text-align: center;
}
</style>
</head>
<body>
	<%@ include file="../include/header.jsp"%>

	<div class="jumbotron">
		<div class="container">
			<h1 class="display-4">message</h1>
		</div>
	</div>

	<main role="main">
		<div class="container">
			<c:if test="${param.msg =='out' }">
				<div class="row">
					<div class="col" align="center">
						<p class="alert alert-danger p-5">
							로그아웃이 완료 되었습니다.<br> 이용해 주셔서 감사합니다.
						</p>
			</c:if>
		</div>
		</div>
		</div>
	</main>
	
	<main role="main">
		<div class="container">
			<c:if test="${param.msg =='join' }">
				<div class="row">
					<div class="col" align="center">
						<p class="alert alert-danger p-5">
							회원가입이 완료되었습니다.<br> 환영합니다.
						</p>
			</c:if>
		</div>
		</div>
		</div>
	</main>
	
	<main role="main">
		<div class="container">
			<c:if test="${param.msg =='joinfail' }">
				<div class="row">
					<div class="col" align="center">
						<p class="alert alert-danger p-5">
							회원가입에 실패하였습니다.<br> 이용해 주셔서 감사합니다.
						</p>
			</c:if>
		</div>
		</div>
		</div>
	</main>
	
	<main role="main">
		<div class="container">
			<c:if test="${param.msg =='login' }">
				<div class="row">
					<div class="col" align="center">
						<p class="alert alert-danger p-5">
							<%=session.getAttribute("mid")%>님 환영합니다.<br>
						</p>
			</c:if>
		</div>
		</div>
		</div>
	</main>
	
	<main role="main">
		<div class="container">
			<c:if test="${param.msg =='findid' }">
				<div class="row">
					<div class="col" align="center">
						<p class="alert alert-danger p-5">
							회원님의 아이디는 ${param.id } 입니다.<br>
						</p>
			</c:if>
		</div>
		</div>
		</div>
	</main>

	<%@ include file="../include/footer.jsp"%>
</body>
</html>