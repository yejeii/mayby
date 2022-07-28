/**
 * goodsDetail.jsp
 * made by yeji
 */
 $(document).ready(function(){
	
	/* 상품 상세설명 ckeditor */
	ClassicEditor
		.create(document.querySelector('#productContents_textarea'))
		.then(editor => {
			console.log(editor);
			editor.enableReadOnlyMode( '#productContents_textarea' );
		})
		.catch(error=>{
			console.error(error);
		});

	/* 목록 이동 버튼 */
	$("#cancelBtn").on("click", function(e){
		e.preventDefault();
		$("#moveForm").submit();
	})
})