/**
 * boardDetail.jsp
 * made by yeji
 */
 $(document).ready(function(){
	
	let form = $("#moveForm");
	let mForm = $("#modifyForm");
	 
	/* 위지윅 적용 */
	/* 게시글 내용*/
	ClassicEditor
		.create(document.querySelector('#fb_content'))
		.then(editor => {
			console.log(editor);
	})
	
	/* 취소 버튼 */
	$("#cancelBtn").on("click", function(e){
		e.preventDefault();
		form.find("#fb_no").remove();
      form.attr("action", "/fboard/boardList");
      form.submit();
	});
	
	/* 수정 버튼 */
	$("#modifyBtn").on("click", function(e){
		e.preventDefault();
        mForm.submit();
    });
    
	
	
});