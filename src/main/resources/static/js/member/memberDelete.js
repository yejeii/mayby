
// JQuery
 $(document).ready(function(){
	
	/* 유효성 검사 통과유무 변수 */
	var idckCheck = false; 	// 아이디 중복 검사
	var pwckCkeck = false;	// 비번 확인

	$('.idck_input').on("propertychange change input paste keyup", function(){
		// 입력값 변수
		var m_id = $("#m_id").val();

		// 아이디 입력여부 검사
		if(m_id == ""){
			$('.delete_id_ck_1').css('display','block');
			$('.delete_id_ck_2').css('display','none');
            idckCheck = false;	
		}	else{
			$('.delete_id_ck_1').css('display','none');
			$('.delete_id_ck_2').css('display','block');
            idckCheck = true;
        }
		console.log(idckCheck);
		
	});
			
	$('.pwck_input').on("propertychange change input paste keyup", function(){
		// 입력값 변수
		var m_pw = $("#m_pw").val();
		
		// 비밀번호 입력여부 검사
		if(m_pw == ""){
			$('.delete_pw_ck_1').css('display','block');
			$('.delete_pw_ck_2').css('display','none');
            pwckCheck = false;	
		}	else{
			$('.delete_pw_ck_1').css('display','none');
			$('.delete_pw_ck_2').css('display','block');
            pwckCheck = true;
        }

		console.log(pwckCheck);
		
	});
})
  
  