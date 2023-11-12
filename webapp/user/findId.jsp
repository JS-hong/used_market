<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
			<h1 class="display-4">아이디 찾기</h1>
		</div>
	</div>

	<main role="main">
		<div class="container">

			<form action="/joongo/User/MemberFindId.do" method="post" class="form-horizontal">

				<div class="form-group row">
					<label class="col-sm-2">닉네임</label>
					<div class="col-sm-3">
						<input type="text" class="form-control" name="mname" id="mname" required="required">
					</div>
				</div>

				<div class="form-group row">
					<label class="col-sm-2">생년월일 (6글자)</label>
					<div class="col-sm-2">
						<input type="text" class="form-control" name="mbirth" id="mbirth" required="required">
					</div>
				</div>

				<div class="form-group row">
					<label class="col-sm-2">연락처</label>
					<div class="col-sm-2">
						<select class="form-control" name="phone1" id="phone1">
							<option>010
							<option>02
							<option>033
						</select>
					</div>
					<div class="col-sm-2">
						<input type="text" name="phone2" id="phone2" maxlength="4" class="form-control" value="1234" required="required">
					</div>
					<div class="col-sm-2">
						<input type="text" name="phone3" id="phone3" maxlength="4" class="form-control" value="5678" required="required">
					</div>
					<input type="hidden" id="mphone" name="mphone">
				</div>

				<div class="form-group row">
					<div class="col-sm-offset-2 col-sm-10">
						<input type="submit" class="btn btn-info" onclick="findID()" value="확인">
						<input type="button" class="btn btn-secondary" onclick="history.back()" value="취소">
					</div>
				</div>
			</form>
		</div>
	</main>
	<%@ include file="../include/footer.jsp"%>
</body>
<script>function findID() {
    var phone1 = document.getElementById("phone1").value;
    var phone2 = document.getElementById("phone2").value;
    var phone3 = document.getElementById("phone3").value;
    var mphone = phone1 + "-" + phone2 + "-" + phone3;
    document.getElementById("mphone").value = mphone; // 이 부분을 추가하세요.
}</script>
</html>