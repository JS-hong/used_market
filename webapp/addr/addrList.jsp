<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
import="joongo.vo.*" import="java.util.*" import="joongo.dao.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
@import
	url("https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css")
	;
</style>
<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
</head>
<body>
	<%@ include file="../include/header.jsp"%>

	<div class="jumbotron">
	<div class="container">
		<h1 class="display-4">위치 정보</h1>
	</div>
</div>

<main role="main">
	<div class="container">
	
		<div class="text-right">
			<a href="javascript:void(0)" onclick="openPopup('${pageContext.request.contextPath}/addr/addrModify.jsp?mid=${sessionScope.mid}')"
    class="btn btn-sm btn-success">현재위치 수정</a>
		</div>
		
		<div class="row">
			<%
 			String mid = request.getParameter("mid"); // 회원 ID 파라미터 가져오기
			AddrDAO adao = new AddrDAO();
			AddrVO avo = adao.select(mid); // 해당 회원 ID로 조회된 주소 정보 가져오기

			if (avo != null) { // 조회된 주소 정보가 있으면 화면에 표시
			%>
			<div class="col-md-8">
			<p></p>
				<p>
					아이디 :	<%=avo.getMid()%>
					</p>
				<p>
					닉네임 :	<%=avo.getAddr()%>
					</p>
				<p>
					이메일 :	<%=avo.getAddr2()%>
					
				</p>
			</div>
			<% } else { %>
			<div class="col-md-8">
				<p>조회된 데이터가 없습니다.</p>
			</div>
			<% } %>
		</div>
		
	</div>
</main>

<%@ include file="../include/footer.jsp"%>
<script>
function openPopup(url) {
    window.open(url, "popup", "width=500,height=500");
}
</script>
</body>
</html>
