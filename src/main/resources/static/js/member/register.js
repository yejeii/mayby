/**
 *  Daum 우편번호 API
 */
 
function execDaumPostcode() {
	new daum.Postcode({
	    oncomplete: function(data) {
	      // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

	    	// 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if(data.userSelectedType === 'R'){
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                }
                // 조합된 참고항목을 해당 필드에 넣는다.
                document.getElementById("m_extraAddress").value = extraAddr;
            
            } else {
                document.getElementById("m_extraAddress").value = '';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('m_postcode').value = data.zonecode;
            document.getElementById("m_address").value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("m_elseAddress").focus();
	     
	    }
	  }).open();
}
 
// JQuery
 $(document).ready(function(){
	
	/* 유효성 검사 통과유무 변수 */
	var idCheck = false; 	// 아이디
	var idckCheck = false; 	// 아이디 중복 검사
	var pwCheck = false;		// 비번
	var pwckCkeck = false;	// 비번 확인
	var pwckorCheck = false;		// 비번 확인 일치 확인
	var nameCheck = false;
	var emailCheck = false;
	var phoneCheck = false;
	var addressCheck = false;
	
	/**
	 * 아이디 중복검사 - ajax
	 *  input 태그(class="id_input") 실시간 감지
	 */
	 $('.id_input').on("propertychange change keyup paste input", function(){
		// console.log("keyup 테스트!");
		var id = $('.id_input').val();
		var idExp = /^[a-zA-Z0-9]{6,}$/;
		
		var data = {m_id: id};  // 컨트롤러에 넘길 데이터 이름:  데이터(.id_input에 입력된 값)

		$.ajax({
			type: 'post',
			async: false,
			url: '/member/memberIdCheck',
			data: data,  // 넘길 데이터
			success: function(result) {
				// console.log("성공여부: "+result);
				if(result == -1) {	// 입력값 X or 6자리 미만으로 입력
					$(".id_input"). css('border-color', 'red');
					$(".id_input_1").css('display', 'none');
					$(".final_id_ck").css('display', 'block');
					idckCheck = false;
				}
				else if(result == 1) {		// 중복 아이디인 경우
					$(".id_input"). css('border-color', 'red');
					$(".id_input_1").css('display', 'none');
					$('.id_input_2').css('display', 'inline-block');
					idckCheck = false;
				} else {
					$(".id_input"). css('border-color', 'green');
					$(".id_input_1").css('display', 'block');
					$('.id_input_2').css('display', 'none');
					$(".final_id_ck").css('display', 'none');
					idckCheck = true;
				}
			}
		})
	});

	/** 
	 * 비밀번호 확인 일치 유효성 검사
	 */
	$('.pwck_input').on("propertychange change input paste keyup", function(){
		var pw = $(".pw_input").val();
		var pwck = $(".pwck_input").val();
		$(".final_pwck_ck").css('display', 'none');
		
		if(pw == pwck) {
		    $('.pwck_input_re_1').css('display','block');
	        $('.pwck_input_re_2').css('display','none');
	        pwckcorCheck = true;
		} else {
			$('.pwck_input_re_1').css('display','none');
	        $('.pwck_input_re_2').css('display','block');
	        pwckcorCheck = false;
		}
		
	});
	
	 /**
	  * 유효성 검사 후 회원가입 처리
	  * form 태그의 속성 action에 url 경로가 추가되고
	  * form 태그가 서버에 전송된다
	  */
	$("#registerBtn").click(function(){
		// 입력값 변수
		var m_id = $("#m_id").val();
		var m_pw = $("#m_pw").val();
		var check_pw = $("#check_pw").val();
		var m_name = $("#m_name").val();
		var m_email = $("#m_email").val();
		var m_phone = $("#m_phone").val();
		var m_postcode = $("#m_postcode").val();
		var m_address = $("#m_address").val();
		var m_extraAddress = $("#m_extraAddress").val();
		var m_elseAddress = $("#m_elseAddress").val();
		
		// 정규식 표현
		var idExp = /^[a-zA-Z0-9]{6,}$/;
		var pwExp = /^[ㄱ-힣a-zA-Z0-9-_?!@^*]{8,16}$/;
		var nameExp = /^[ㄱ-힣]{3,4}$/;
		var emailExp = /^[0-9a-zA-Z]([-_\.]?[0-9|a-z|A-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
		var phonExp = /^[0-9]{10,11}$/;
		
		// 아이디 유효성 검사
		if(! idExp.test(m_id)) {		// 조건에 맞지 않은 경우
			$(".id_input_1").css('display', 'none');
			$(".final_id_ck").css('display', 'block');
			$(".id_input").css('border-color', 'red');
			idCheck = false;
		} else {
			$(".final_id_ck").css('display', 'none');
			$(".id_input").css('border-color', 'green');
			idCheck = true;
		}
		
		// 비밀번호 유효성 검사
		if(! pwExp.test(m_pw)) {
			$(".final_pw_ck").css('display', 'block');
			$(".pw_input").css('border-color', 'red');
			pwCheck = false;
		} else {
			$(".final_pw_ck").css('display', 'none');
			$(".pw_input").css('border-color', 'green');
			pwCheck = true;
		}
		
		// 비밀번호 확인 검사
		if(check_pw == ""){
			$('.final_pwck_ck').css('display','block');
			$(".pwck_input").css('border-color', 'red');
            pwckCheck = false;	
		}	else{
            $('.final_pwck_ck').css('display', 'none');
            $(".pwck_input").css('border-color', 'green');
            pwckCheck = true;
        }
  
  		// 이름 유효성 검사
  		if(! nameExp.test(m_name)) {
			$('.final_name_ck').css('display','block');
			$("#m_name").css('border-color', 'red');
            nameCheck = false;	
		} else {
			$('.final_name_ck').css('display', 'none');
			$("#m_name").css('border-color', 'green');
            nameCheck = true;
		}
		
		// 이메일 유효성 검사
		if(! emailExp.test(m_email)) {
			$('.final_mail_ck').css('display','block');
			$("#m_email").css('border-color', 'red');
            emailCheck = false;	
		} else {
			$('.final_mail_ck').css('display', 'none');
			$("#m_email").css('border-color', 'green');
            emailCheck = true;
		}
		
		// 연락저 유효성 검사
		if(! phonExp.test(m_phone)) {
			$('.final_phone_ck').css('display','block');
			$("#m_phone").css('border-color', 'red');
            phoneCheck = false;	
		} else {
			$('.final_phone_ck').css('display', 'none');
			$("#m_phone").css('border-color', 'green');
            phoneCheck = true;
		}
		
		// 주소 유효성 검사
		if(m_postcode == "" || m_address == "") {
			$('.final_addr_ck').css('display','block');
			$("#m_postcode").css('border-color', 'red');
            addressCheck = false;	
		} else {
			$('.final_addr_ck').css('display','none');
			$("#m_postcode").css('border-color', 'green');
            addressCheck = true;	
		}
		
		console.log(idCheck);
		console.log(idckCheck);
		console.log(pwCheck);
		console.log(pwckCheck);
		console.log(pwckcorCheck);
		console.log(nameCheck);
		console.log(emailCheck);
		console.log(phoneCheck);
		console.log(addressCheck);
		
		// 최종 유효성 검사
		if(idCheck&&idckCheck&&pwCheck&&pwckCheck&&pwckcorCheck&&nameCheck
				&&emailCheck&&phoneCheck&&addressCheck) {
			var url = "/member/registerPro";
			$("#joinForm").attr("action", url);
			$("#joinForm").submit();
		}
		
		// 위의 if문에서 유효성 검사 변수들 중 단 한 개라도 false가 있을 경우 return false를 읽는다
		// 감싸고 있는 메서드(registerBtn click) 메서드 정상 종료되지 못할 경우를 대비
		return false;
		
		
	});
	
	
	
})
  
  