/**
 * goodsManage.jsp
 * made by yeji
 */
 // 페이지가 로드될 때 반드시 실행되는 익명함수
 $(document).ready(function(){
	
	let searchForm = $('#searchForm');
	let moveForm = $('#moveForm');

	/* 상품 아이디 검색 버튼 동작 
	  *
	*/
	$(".searchBtn").on("click", function(e){
		e.preventDefault();
		
		/* 검색 키워드 유효성 검사 */
		if(! searchForm.find("input[name='keyword']").val()) {		// 키워드 미입력
			alert("입력된 키워드가 없습니다.");
			return false;
		}
		
		searchForm.find("input[name='pageNm']").val("1");
		searchForm.submit();
	});
	
	/* 페이지 이동 버튼
	  *  숫자 버튼을 누르면 <a> 태그의 동작이 멈추고, <a> 태그에 저장된 href 속성 값을
	  *  <form id="moveForm"> 태그의 내부에 있는 name=pageNm  <input> 값으로 저장시킨 후
	  *  <form> 태그 속성에 설정되어 있는 url 경로와 method 방식으로 form을 서버로 전송
	  * $(this) -> 함수가 시행되게 한 주체, <a> 태그 자신을 가리킴
	  */
	$('.pageMaker_btn a').on("click", function(e){
		e.preventDefault();
		// alert("click")
		moveForm.find("input[name='pageNm']").val($(this).attr("href"));
		moveForm.submit();
	});

	/** 
	 * 상품 조회 페이지 이동
	 * 상품 p_id의 <a> 태그 클릭시 선택된 상품의 상세 조회 페이지로 이동
	 * moveForm 태그 안에 input 태그 추가 -> value값에 a 태그의 href 속성 값(p_id)을 저장한 후
	 * moveForm의 action 속성 값을 다시 지정하여 전송
	 * $(this) -> 함수가 시행되게 한 주체, class="move"인 <a> 태그 자신을 가리킴
	 */
	$(".move").on("click", function(e) {
		e.preventDefault();
		moveForm.append("<input type='hidden' name='p_id' value='"+$(this).attr("href")+"'>");
		moveForm.attr("action", "/admin/goodsDetail");
		// alert("성공");
		// return;
		moveForm.submit();
	}); 
	

	
});