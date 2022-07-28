/**
 * MemberManage.jsp
 * made by yeji
 */
 
 $(document).ready(function(){
	
	let moveForm = $('#moveForm');
	
	/**
	 * 페이지 이동 버튼
	 */
	 $('.pageMaker_btn a').on("click", function(e){
		e.preventDefault();
		// alert("click")
		moveForm.find("input[name='pageNm']").val($(this).attr("href"));
		moveForm.submit();
	});
	
})