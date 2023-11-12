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
	<%@ include file="../include/header.jsp"%>

	<div class="jumbotron">
		<div class="container">
			<h1 class="display-4">회원 수정</h1>
		</div>
	</div>

	<main role="main">
		<div class="container">

			<form action="/joongo/User/MemberModifyForm.do" method="post" class="form-horizontal" enctype="multipart/form-data">
				<c:set var="idMsg" value="아이디는 필수입력 항목입니다." />
				<c:set var="nmMsg" value="닉네임은 필수입력 항목입니다." />
				<c:set var="dayMsg" value="생년월일은 필수입력 항목입니다." />
				<c:set var="mailMsg" value="메일은 필수입력 항목입니다." />
				<c:set var="addrMsg" value="주소는 필수입력 항목입니다." />
				<c:set var="phoneMsg" value="번호는 필수입력 항목입니다." />



				<div class="form-group row">
					<label class="col-sm-2">아이디</label>
					<div class="col-sm-3">
						<input type="text" class="form-control" name="mid" id="mid" readonly="readonly" value="${userInfo.mid }">
					</div>
				</div>

				<div class="form-group row">
					<label class="col-sm-2">닉네임</label>
					<div class="col-sm-3">
						<input type="text" class="form-control" name="mname" id="mname" value="${userInfo.mname }" required="required" oninput="setCustomValidity('')"
							oninvalid="this.setCustomValidity('${nmMsg }')"
						>
					</div>
				</div>

				<div class="form-group row">
					<label class="col-sm-2">생년월일 (6글자)</label>
					<div class="col-sm-2">
						<input type="text" class="form-control" name="mbirth" id="mbirth" value="${userInfo.mbirth }" readonly="readonly" required="required" oninput="setCustomValidity('')"
							oninvalid="this.setCustomValidity('${dayMsg }')"
						>
					</div>
				</div>

				<div class="form-group">
					<div class="row">
						<label class="col-sm-2">이메일</label>
						<div class="col-sm-6">
							<div class="input-group">
								<input type="text" id="email1" class="form-control" required="required"  value="${userInfo.email.split('@')[0]}"
								oninput="setCustomValidity('')" oninvalid="this.setCustomValidity('${mailMsg }')">
								<div class="input-group-prepend">
									<span class="input-group-text">@</span>
								</div>
								<input type="text" id="email2" class="form-control"  value="${userInfo.email.split('@')[1]}">
								<select id="email3" class="form-control" onclick="setEmail()">
									<option value="직접입력">직접입력</option>
									<option value="naver.com">naver.com</option>
									<option value="gmail.com">gmail.com</option>
								</select>
							</div>
						</div>
						<div class="col-sm-2">
							<input type="hidden" id="email" name="email">
						</div>
					</div>
				</div>

				<div class="form-group row">
					<label class="col-sm-2">주소</label>
					<div class="col-sm-3">
						<input type="text" class="form-control" name="addr" id="addr" value="${userInfo.addr }" required="required" oninput="setCustomValidity('')"
							oninvalid="this.setCustomValidity('${addrMsg }')"
						>
					</div>
					<div class="col-sm-3">
						<input type="button" value="검색" class="btn btn-secondary" onclick="goPopup();">
					</div>
				</div>

				<div class="form-group row align-items-center">
					<label class="col-sm-2">상세주소</label>
					<div class="col-sm-4">
						<div class="row">
							<div class="col">
								<input type="text" class="form-control" name="addrdetail" id="addrdetail" value="${userInfo.addrdetail }">
							</div>
						</div>
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
						<input type="text" name="phone2" id="phone2" maxlength="4" class="form-control" value="${userInfo.mphone.split('-')[1]}"
						required="required" oninput="setCustomValidity('')"
							oninvalid="this.setCustomValidity('${phoneMsg }')"
						>
					</div>
					<div class="col-sm-2">
						<input type="text" name="phone3" id="phone3" maxlength="4" class="form-control" value="${userInfo.mphone.split('-')[2]}"
						required="required" oninput="setCustomValidity('')"
							oninvalid="this.setCustomValidity('${phoneMsg }')"
						>
					</div>
					<input type="hidden" id="mphone" name="mphone">
				</div>

				<div class="form-group row">
					<label class="col-sm-2">이미지</label>
					<div class="col-sm-5">
						<input type="file" class="form-control" name="mimage" id="mimage">
					</div>
				</div>

				<div class="form-group row">
					<div class="col-sm-offset-2 col-sm-10">
						<input type="submit" class="btn btn-info" onclick="pwEmailcheck()" value="등록"> 
						<input type="button" class="btn btn-warning" onclick="del()" value="회원 탈퇴"> <input
							type="button" class="btn btn-secondary" onclick="history.back()" value="취소"
						>
					</div>
				</div>

			</form>
		</div>
	</main>
	<%@ include file="../include/footer.jsp"%>

	<script>
		function goPopup() {
			var pop = window.open("../util/jusoPopup.jsp", "pop",
					"width=570,height=420, scrollbars=yes, resizable=yes");
		}
		function jusoCallBack(jibunAddr, addrDetail) {
			$('#addr').val(jibunAddr);
			$('#addrdetail').val(addrDetail);
		}
		function del() {
			if (confirm('정말로 삭제하시겠습니까?')) {
				location.href = './MemberRemove.do?mid=' + "${sessionScope.mid}";
			}
		}
	</script>
</body>
</html>