/**
 * boardDetail.jsp
 * made by yeji
 */
 $(document).ready(function(){
	
	let form = $("#infoForm");
	
	/* 위지윅 적용 */
	/* 게시글 내용*/
	ClassicEditor
		.create(document.querySelector('#fb_content'))
		.then(editor => {
			console.log(editor);
			editor.enableReadOnlyMode( '#fb_content' );
		})
		.catch(error=>{
			console.error(error);
		});
	
	/* 취소 버튼 */
	$("#cancelBtn").on("click", function(e){
		e.preventDefault();
		form.find("#fb_no").remove();
		form.attr("action", "/fboard/boardList");
		form.submit();
	});
	
	/* 게시글 수정 버튼 */
	$("#modifyBtn").on("click", function(e){
		e.preventDefault();
		form.attr("action", "/fboard/boardModify");
		form.submit();
	});
	
	/* 게시글 삭제 버튼 */
    $("#deleteBtn").on("click", function(e) {
		e.preventDefault();
		form.attr("action", "/fboard/boardDelete");
        form.attr("method", "post");
        form.submit();
	});
	
	
});