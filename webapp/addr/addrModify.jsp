<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="joongo.vo.*" import="java.util.*" import="joongo.dao.*"%>
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

	<div class="jumbotron">
		<div class="container">
			<h1 class="display-4">위치 수정</h1>
		</div>
	</div>

	<main role="main">
		<div class="container">
			<form action="addrModifyProc.jsp" method="post" class="form-horizontal">
				<%
				String mid = request.getParameter("mid"); // 회원 ID 파라미터 가져오기
				AddrDAO adao = new AddrDAO();
				AddrVO avo = adao.select(mid); // 해당 회원 ID로 조회된 주소 정보 가져오기

				if (avo != null) { // 조회된 주소 정보가 있으면 화면에 표시
				%>
				<div class="form-group row">
					<label class="col-sm-2">현재 위치</label>
					<div class="col-sm-5 row">
						<input type="text" class="form-control col-sm-9" name="addr2" id="addr2" required="required" value="<%=avo.getAddr2()%>"> <input type="button" value="검색"
							class="btn btn-secondary col-sm-3" onclick="goPopup();"
						>
					</div>
				</div>


				<div class="form-group row">
					<div class="col-sm-offset-2 col-sm-10">
						<input type="submit" class="btn btn-info" value="수정"> <input type="button" id="cancelBtn" class="btn btn-secondary" value="취소">

					</div>
				</div>
				<%
				}
				%>
			</form>
		</div>
	</main>
	<%@ include file="../include/footer.jsp"%>

	<script>
		function goPopup() {
			var pop = window.open("../util/jusoPopup.jsp", "pop",
					"width=520,height=420, scrollbars=yes, resizable=yes");
		}
		function jusoCallBack(jibunAddr, addrDetail) {
			$('#addr2').val(jibunAddr);
		}

		document.querySelector('#cancelBtn').addEventListener('click',
				function() {
					window.close();
				});
	</script>
</body>
</html>