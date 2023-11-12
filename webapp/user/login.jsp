 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="joongo.vo.MemberVO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login.jsp</title>
</head>
<body>

	<%@ include file="../include/header.jsp"%>

	<div class="jumbotron">
		<div class="container">
			<h1 class="display-4">로그인</h1>
		</div>
	</div>

	<main role="main">
		<div class="container" align="center">
			<div class="col-md-4 col-md-offset-4">
				<h3 class="form-signin-heading">Please sign in</h3>

				<main role="main">
					<div class="container">
						<c:if test="${param.msg =='findid' }">
							<div class="row">
								<div class="col" align="center">
									<p class="alert alert-danger p-5">
										회원님의 아이디는 <br> ${param.id } 입니다.<br>
									</p>
						</c:if>
					</div>
				</main>

				<!-- msg 값에 fail이면 보이게 -->
				<c:if test="${param.msg == 'fail'}">
					<div class="alert alert-danger">
						아이디 또는 비밀번호가 <br>일치하지 않습니다.
					</div>
				</c:if>

				<form action="/joongo/User/MemberLoginForm.do" method="post" class="form-signin">
					<div class="form-group">
						<label class="sr-only">UserName</label> 
						<input type="text" name="mid" class="form-control" placeholder="ID" required autofocus>
					</div>

					<div class="form-group">
						<label class="sr-only">Password</label>
						<input type="password" name="mpw" class="form-control" placeholder="Password" required autofocus>
					</div>

					<input type="submit" class="btn btn-lg btn-block btn-warning" value="로그인">
				</form>
			</div>
		</div>
	</main>

	<%@ include file="../include/footer.jsp"%>
</body>
</html>








