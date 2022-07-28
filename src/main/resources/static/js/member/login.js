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

		//alert("click")
		let url = "/member/loginPro";
		$("#loginForm").attr("action", url);
		$("#loginForm").submit();

	});

})
 