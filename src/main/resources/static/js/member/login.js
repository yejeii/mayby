$(document).ready(function() {

	$(".login_button").on("click", function(){
		// alert("버튼 클릭!");
		let inputEmail = $(".email_input").val();
		let inputPw = $(".pw_input").val();

		if(inputEmail == '' || inputPw == '') {
			alert('이메일 또는 비밀번호를 입력하세요!');
			$(".email_input").focus();
			return;
		}

		// 이메일 유효성 검사
		let emailExp = /^[0-9a-zA-Z]([-_\.]?[0-9|a-z|A-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;

		if(! emailExp.test(inputEmail)) {
			$('.final_mail_ck').css('display','block');
			$("#m_email").css('border-color', 'red');
		} else {
			$('.final_mail_ck').css('display', 'none');
			$("#m_email").css('border-color', 'green');
		}

		//alert("click")
		let url = "/member/loginPro";
		$("#loginForm").attr("action", url);
		$("#loginForm").submit();

	});

})
 