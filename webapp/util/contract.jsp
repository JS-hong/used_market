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
	<%@ include file="../include/header.jsp"%>

	<div class="jumbotron">
		<div class="container">
			<h1 class="display-4">이용약관</h1>
		</div>
	</div>

	<main role="main">
		<div class="container">

			<form action="../user/join.jsp" method="post" class="form-horizontal" id="myForm">

				<div class="form-group col">
					<input type="checkbox" id="agree1"> <label class="col-sm-4" for="agree1">이용약관 동의 (필수)</label>
					<div class="col-sm-7">
						<textarea class="form-control" cols="10" rows="4" name="content" id="content"> 여러분을 환영합니다. JOONGO 서비스 및 제품(이하 ‘서비스’)을 이용해 주셔서 감사합니다. 본 약관은 다양한 JOONGO
                서비스의 이용과 관련하여 JOONGO 서비스를 제공하는 JOONGO 주식회사(이하 ‘JOONGO’)와 이를 이용하는 JOONGO 서비스
                회원(이하 ‘회원’) 또는 비회원과의 관계를 설명하며, 아울러 여러분의 JOONGO 서비스 이용에 도움이 될 수 있는
                유익한 정보를 포함하고 있습니다. JOONGO 서비스를 이용하시거나 JOONGO 서비스 회원으로 가입하실 경우 여러분은 본
                약관 및 관련 운영 정책을 확인하거나 동의하게 되므로, 잠시 시간을 내시어 주의 깊게 살펴봐 주시기
                바랍니다.</textarea>
					</div>
				</div>

				<div class="form-group col">
					<input type="checkbox" id="agree2"> <label class="col-sm-4" for="agree2">개인정보 수집 및 이용 동의 (필수)</label>
					<div class="col-sm-7">
						<textarea class="form-control" cols="10" rows="4" name="content" id="content">개인정보보호법에 따라 JOONGO에 회원가입 신청하시는 분께 수집하는 개인정보의 항목, 개인정보의 수집 및
                이용목적, 개인정보의 보유 및 이용기간, 동의 거부권 및 동의 거부 시 불이익에 관한 사항을 안내 드리오니
                자세히 읽은 후 동의하여 주시기 바랍니다.1. 수집하는 개인정보 이용자는 회원가입을 하지 않아도 정보 검색,
                뉴스 보기 등 대부분의 네이버 서비스를 회원과 동일하게 이용할 수 있습니다. 이용자가 메일, 캘린더, 카페,
                블로그 등과 같이 개인화 혹은 회원제 서비스를 이용하기 위해 회원가입을 할 경우, 네이버는 서비스 이용을
                위해 필요한 최소한의 개인정보를 수집합니다.</textarea>
					</div>
				</div>

				<div class="form-group col">
					<input type="checkbox" id="agree3"> <label class="col-sm-4" for="agree3">프로모션 정보 수신 동의 (선택)</label>
					<div class="col-sm-7">
						<textarea class="form-control" cols="10" rows="4" name="content" id="content"> JOONGO에서 제공하는 이벤트/혜택 등 다양한 정보를 휴대전화(JOONGO앱 알림 또는 문자), 이메일로 받아보실 수
                있습니다. 일부 서비스(별도 회원 체계로 운영하거나 JOONGO 가입 이후 추가 가입하여 이용하는 서비스 등)의
                경우, 개별 서비스에 대해 별도 수신 동의를 받을 수 있으며, 이때에도 수신 동의에 대해 별도로 안내하고
                동의를 받습니다.</textarea>
					</div>
				</div>
				<br>
				<div class="form-group row">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="button" class="btn btn-info" id="submit-button">확인</button>
						<input type="button" class="btn btn-secondary" onclick="history.back()" value="취소">
					</div>
				</div>
			</form>
		</div>
	</main>

	<%@ include file="../include/footer.jsp"%>
	<script type="text/javascript">
$(document).ready(function() {
    // 확인 버튼 클릭 시 약관 동의 여부 확인
    $('#submit-button').click(function() {
        if (!$('#agree1').is(':checked') || !$('#agree2').is(':checked')) {
            alert('필수 약관에 동의해주셔야 회원가입이 가능합니다.');
            return false; // 폼 제출 막기
        }
        $('#myForm').submit(); // 폼 제출
    });
});
</script>
</body>
</html>