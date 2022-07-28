/**
 * boardList.jsp
 * made by yeji
 */

 $(document).ready(function(){
	let moveForm =  $("#moveForm");
	
	/* 게시글 등록 버튼 이벤트 */
	$("#registerBtn").on("click", function(e){
		e.preventDefault();
		// alert("click")
		location.href="/fboard/boardEnroll";
	});
	
	/* 검색 버튼 클릭 */
	$(".search_area button").on("click", function(e){
		e.preventDefault();
			
		let type = $(".search_area select").val();
      let keyword = $(".search_area input[name='keywordInput']").val();
        
      if(! type){
          alert("검색 종류를 선택하세요.");
          return false;
      }
        
      if(!keyword){
          alert("키워드를 입력하세요.");
          return false;
      }        	
		
		// alert(keyword)
		moveForm.find("input[name='type']").val(type);		// 던져질 파라미터의 value값에 T/W/C..을 넣는 것
		moveForm.find("input[name='keyword']").val(keyword);
		moveForm.find("input[name='pageNm']").val(1);
		moveForm.submit();
	})
	
	/* 상세 게시글 a 이벤트 */
	 $(".move").on("click", function(e){
        e.preventDefault();
        
        moveForm.append("<input type='hidden' name='fb_no' value='"+ $(this).attr("href")+ "'>");
        moveForm.attr("action", "/fboard/boardDetail");
        moveForm.submit();
    });
    
    /* 페이징 번호 클릭 시 이동 */
     $(".pageMaker_btn a").on("click", function(e){
		e.preventDefault();
		// alert("click")
        moveForm.find("input[name='pageNm']").val($(this).attr("href"));
        moveForm.submit();
    });
 

	
});