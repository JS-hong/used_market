$(function() {
	$('#mid').on('input', function() {
		var val = $(this).val();
		$.ajax({
			url: 'checkId.jsp', // 중복 검사를 수행하는 JSP 페이지
			data: { mid: val },
			success: function(data) {
				if (data.isDuplicate) {
					$('#mid-message').html('<span style="color:red;">이미 사용 중인 아이디입니다.</span>');
				} else {
					$('#mid-message').html('<span style="color:green;">사용 하지않는 아이디입니다.</span>');
				}
			}
		});
	});
});

$(function() {
	$('#mpw, #pwcheck').on('keyup', function () {
		var password = $('#mpw').val();
		var confirmPassword = $('#pwcheck').val();
		if(password === confirmPassword){
			$('#pw-message').html('<span style="color:green;">비밀번호가 일치합니다.</span>');
		} else{
			$('#pw-message').html('<span style="color:red;">비밀번호가 일치하지 않습니다.</span>');
		}
	});
});

  $(document).ready(function(){
    $(".form-group").on("change", "#email1, #email2, #email3", function(){
      var email = $("#email1").val() + "@" + $("#email2").val();
      if ($("#email3").val() !== '직접입력') {
        email += $("#email3").val();
        $("#email").val(email);
      }
    });
  });


function setEmail() {
	var email1 = document.querySelector('#email1');
	var email2 = document.querySelector('#email2');
	var email3 = document.querySelector('#email3');

	if (email3.value === '직접입력') {
		email2.value = '';
		email2.removeAttribute('readonly');
	} else {
		email2.value = email3.value;
		email2.setAttribute('readonly', true);
	}
}


function checkJoin() {

	var midPtn = /^[a-zA-Z0-9]{6,8}$/;
	var mpwPtn = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[~!@#$%^&*()_\-+=\{\}\[\]\\|\:;"'<>,\.\?]).{8,12}$/;
	var mnamePtn = /^[A-Za-z0-9가-힣-]{4,8}$/;
	var mbirthPtn = /^\d{8}$/;

	if (!midPtn.test($('#mid').val())) {
		alert('[아이디]' + '\n영어와 숫자를 조합하여 6~8자로 입력해주세요.')
		$('#mid').focus();
	} else if (!mpwPtn.test($('#mpw').val())) {
		alert('[비밀번호]' + '\비밀번호는 영어, 숫자, 특수문자만 사용가능합니다, 8~12자로 입력해주세요.')
		$('#mpw').focus();
	} else if (!mnamePtn.test($('#mname').val())) {
		alert('[닉네임]' + '\4~6자로 입력해주세요.')
		$('#mname').focus();
	} else if (!mbirthPtn.test($('#mbirth').val())) {
		alert('[생년월일]' + '\8자로 입력해주세요.')
		$('#mbirth').focus();
	} else {
		$('form').submit();
	}
}