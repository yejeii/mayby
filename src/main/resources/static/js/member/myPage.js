/**
 * myPage.jsp
 */
 $(document).ready(function(){
	
	/* 수정 버튼 클릭 이벤트 */
	$("#modifyBtn").on("click", function(e){
		e.preventDefault();
		$("#myForm").attr("action", "/member/memberModify");
		$("#myForm").submit();
	});
	
	/* 홈 이동 클릭 이벤트 */
	$("#cancelBtn").on("click", function(e){
		e.preventDefault();
		// alert("치")
		$(location).attr("href", "/");
	});
	
	
});