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
	
		
	  /* 회원수정 버튼 클릭 */
	$("#modifyBtn").on("click", function() {
			/* 유효성 검사 통과유무 변수 */
			var nameCheck = false;
			var emailCheck = false;
			var phoneCheck = false;
			var addressCheck = false;
			
			/* 변수 */
			let m_no = $("#m_no").val();
			let m_id = $("#m_id").val();
			let m_name = $("#m_name").val();
			let m_email = $("#m_email").val();
			let m_phone = $("#m_phone").val();
			let m_postcode = $("#m_postcode").val();
			let m_address = $("#m_address").val();
			let m_extraAddress = $("#m_extraAddress").val();
			let m_elseAddress = $("#m_elseAddress").val();
			// 정규식 표현
			var nameExp = /^[ㄱ-힣]{3,4}$/;
			var emailExp = /^[0-9a-zA-Z]([-_\.]?[0-9|a-z|A-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
			var phonExp = /^[0-9]{10,11}$/;
			
	  		// 이름 유효성 검사
	  		if(! nameExp.test(m_name)) {
				$('.final_name_ck').css('display','block');
				$("#m_name").css('border-color', 'red');
				$("#m_name").focus();
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
				$("#m_email").focus();
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
				$("#m_phone").focus();
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
				$("#m_postcode").focus();
	         addressCheck = false;	
			} else {
				$('.final_addr_ck').css('display','none');
				$("#m_postcode").css('border-color', 'green');
	            addressCheck = true;	
			}
			
			//console.log(nameCheck);
			//console.log(emailCheck);
			//console.log(phoneCheck);
			//console.log(addressCheck);
			
			// 최종 유효성 검사 & ajax 처리
			if(nameCheck&&emailCheck&&phoneCheck&&addressCheck) {
				// alert("true")
				
				var data = {
					"m_no":m_no, 
					"m_id":m_id,
					"m_name":m_name,
					"m_email":m_email,
					"m_phone":m_phone,
					"m_postcode":m_postcode,
					"m_address":m_address,
					"m_extraAddress":m_extraAddress,
					"m_elseAddress":m_elseAddress
				}
			//	alert(data)	// [object Object]
			//	console.log(JSON.stringify(data))	
				// {"m_no":"7","m_id":"user01","m_name":"김유저","m_email":"asdf@dac.com","m_phone":"01045761695","m_postcode":"17532","m_address":"경기 안성시 일죽면 화봉리 323-14","m_extraAddress":"","m_elseAddress":""}
		
			$.ajax({     
					type: 'post',
					async: false,
					data: JSON.stringify(data),            
					url:"/member/memberModifyPro",
					contentType: 'application/json',
					success : function(data) { 
						if(data == "success") {
							alert("수정이 완료되었습니다.");
							location.href="/member/myPage";  
						}
						else if(data == "fail") {
							alert("처리 도중 문제가 발생했습니다. 마이 페이지로 돌아갑니다.")
							location.href="/member/myPage";  
						}
		          		if(data == "not same user") {
				         	alert("접근할 수 없는 권한입니다 메인 페이지로 이동합니다");
							location.href="/";
						}
			       }  
				})
			}

			// 위의 if문에서 유효성 검사 변수들 중 단 한 개라도 false가 있을 경우 return false를 읽는다
			// 감싸고 있는 메서드(registerBtn click) 메서드 정상 종료되지 못할 경우를 대비
			return false;
		})
		
		
	
});
  
  