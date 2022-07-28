/**
 * 상품 목록 페이징 처리
 */
 $(document).ready(function(){

	/* 페이지 이동 버튼 */
    const moveForm = $('#moveForm');
    
    // 페이지 이동 div 클릭시 페이지 이동 
    // $( 'div' ).children( 'p.bl' ).css( 'color', 'blue' );
    // div 요소의 자식 요소 중 클래스 값으로 bl을 가진 p 요소의 색을 파란색으로 만들어라
	$(".pageMaker_btn a").on("click", function(e){
		e.preventDefault();
		// alert("click")
		moveForm.find("input[name='pageNm']").val( $(this).attr("href"));
		moveForm.submit();
	});
	
 })