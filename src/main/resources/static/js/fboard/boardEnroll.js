/**
 * 
 */
 $(document).ready(function(){
	
	/* 위지윅 적용 */
	/* 상품 설명 */
	ClassicEditor
	.create(document.querySelector('#fb_content'))
	.catch(error=>{
		console.error(error);
	});
	
	let enrollForm = $("#enrollForm");
	
	$("#enrollBtn").on("click", function(e){
		e.preventDefault();	
		
		let titleCk = false;
		let contentsCk = false;
		
		let fb_title = $("#fb_subject").val();
		let fb_content = $(".fb_content p").html();
		
		if(fb_title == '') {
			$("#q_title_warn").css("display", "block");
			titleCk = false;
		} else {
			$("#q_title_warn").css("display", "none");
			titleCk = true;
		}
		
		if(fb_content != '<br data-cke-filler="true">') {
			$("#q_contents_warn").css("display", "none");
			contentsCk = true;
		} else {
			$("#q_contents_warn").css("display", "block");
			contentsCk = false;
		}
		
		// 최종 검사
		if(titleCk&&contentsCk) {
			// alert("true");
			// alert(fb_title+fb_content);
			enrollForm.submit();
		} else {
			return false;
		}
		
	});
})