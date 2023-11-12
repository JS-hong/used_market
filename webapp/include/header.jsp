<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
<script src="/joongo/resources/js/script.js"></script>
<nav class="navbar navbar-expand navbar-dark bg-dark">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="/joongo">Home</a>
		</div>
		<div>
			<ul class="navbar-nav mr-auto">
				<c:choose>
					<c:when test="${not empty sessionScope.mid}">
						<li class="nav-item"><a class="nav-link" href="/joongo/Product/List.do?pageNum=1&category=0&location=&keyword=">상품 목록</a>
						<li class="nav-item"><a class="nav-link" href="/joongo/User/MemberView.do?mid=${sessionScope.mid }">마이 페이지</a>
						<li class="nav-item"><a class="nav-link" href="">고객 지원</a>
						<li class="nav-item"><a class="nav-link" href="/joongo/Chat/ChatList.do?mid=${sessionScope.mid}&pageNum=1">채팅</a>
						<li class="nav-item"><a class="nav-link" href="/joongo/user/logout.jsp">로그아웃</a>
					</c:when>
					<c:otherwise>
						<li class="nav-item"><a class="nav-link" href="">고객 지원</a>
						<li class="nav-item"><a class="nav-link" href="/joongo/user/join.jsp">회원가입</a>
						<li class="nav-item"><a class="nav-link" href="/joongo/User/Login.do">로그인</a>
						<li class="nav-item"><a class="nav-link" href="/joongo/User/FindId.do">아이디 찾기</a>
						<li class="nav-item"><a class="nav-link" href="/joongo/User/FindPw.do">비밀번호 찾기</a>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
	</div>
</nav>

