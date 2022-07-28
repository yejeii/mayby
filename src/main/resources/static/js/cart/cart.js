/**
 * cart.jsp
 */
 
 $(document).ready(function(){
	
	setTotalInfo();
	
	/* 상품 이미지 삽입 */
	// <img> 태그를 감싸고 있는 image_wrap <div> 태그 모든 객체에 접근하여 순차적으로 코드를 실행하도록 Jquery의 each() 메서드를 활용
	// i: 객체 순서 == 배열의 index
	// obj : i번째에에서 접근하는 객체
	$(".image_wrap").each(function(i, obj){
		const pobj = $(obj);
	
		if(pobj.data("productid")){
			const uploadPath = pobj.data("path");
			const uuid = pobj.data("uuid");
			const fileName = pobj.data("filename");
			
			const fileCallPath = encodeURIComponent(uploadPath + "/thumbnail/TN_" + uuid + "_" + fileName);
			console.log(fileCallPath);
			
			$(this).find("img").attr('src', '/product/display?fileName=' + fileCallPath);
		} else {
			$(this).find("img").attr('src', '${contextPath}/static/images/noImage.png');
		}

	});
	
	$(".continueBtn").on("click", function(e){
		e.preventDefault();
		location.replace("/product/productList")
	})
});
	
	/* 체크여부에따른 종합 정보 변화(다시 세팅) */
	$(".individual_cart_checkbox").on("change", function(){
		/* 총 주문 정보 세팅(배송비, 총 가격, 마일리지, 물품 수, 종류) */
		setTotalInfo($(".cart_info_div"));
		
	});
	
	/* 체크박스 전체 선택 */
	// prop() - 인자가 하나일 경우 호출한 객체가 인자로 부여한 속성의 값 프로퍼티로 가져옴
	// attr() - 인자가 두 개인 경우 호출하는 객체를 대상으로 첫 번째 인자로 부여한 이름을 가진 속성의 값을 두 번째 인자 값으로 변경
	$(".all_check_input").on("click", function(){
	
		/* 체크박스 체크/해제 */
		if($(".all_check_input").is(":checked")) {
			$("input[name=indi_check]").prop("checked", true);
		} else {
			$("input[name=indi_check]").prop("checked", false);
		}
		
		/* 총 주문 정보 세팅(배송비, 총 가격, 마일리지, 물품 수, 종류) */
		setTotalInfo($(".cart_info_div"));	
	});
	
	$("input[name=indi_check]").on("click", function(){
		let total = $("input[name=indi_check]").length;
		let checked = $("input[name=indi_check]:checked").length;
		
		if(total != checked) {
			$(".all_check_input").prop("checked", false);
		} else {
			$(".all_check_input").prop("checked", true);
		}
		
		/* 총 주문 정보 세팅(배송비, 총 가격, 마일리지, 물품 수, 종류) */
		setTotalInfo($(".cart_info_div"));	
	})
		
	/* 총 주문 정보 세팅(배송비, 총 가격, 마일리지, 물품 수, 종류) */
	function setTotalInfo() {
		// 만들어야 할 데이터 초기화
		let totalPrice = 0;				// 총 가격
		let totalCount = 0;				// 총 갯수
		let totalKind = 0;				// 총 종류
		let totalPoint = 0;				// 총 마일리지
		let deliveryPrice = 0;			// 배송비
		let finalTotalPrice = 0; 		// 최종 가격(총 가격 + 배송비)
		
		// 각 상품 정보를 순회하며 더하면서 값 구한다
		// cart_info_div 식별자를 통해 div에 접근,
		// find()  메서드로 <input> 태그에 접근, get
		// cart_info_div는 상품 마다 존재 -> jquery의 each(index, element) 메서드로 객체 수만큼  순회
		// index - 순회하는 객체의 인덱스, == 배열의 인덱스
		// element - 순회 대상인 각 객체
		// ※parseInt() 메서드
		//  -> <input> 태그의 값을 얻어오면 그 값은 'string' 타입으로 인식!  이를 int 타입으로 변경
		$(".cart_info_div").each(function(index, element){
			
			/* 체크 유무에 따라 실행 */
			//  element(div 객체)에 있는 체크 박스가 checked인 상태일 때 true이며 if문 실행
			// is() 메서드 - 호출하는 객체가 is() 메서드의 인자로 지정한 선택자를 가지고 있으면 true를 반환
			if($(element).find(".individual_cart_checkbox").is(":checked") === true ){
				// 총 가격
				totalPrice += parseInt($(element).find(".individual_totalPrice_input").val());
				// 총 갯수
				totalCount += parseInt($(element).find(".individual_productCount_input").val());
				// 총 종류
				totalKind += 1;
				// 총 마일리지
				totalPoint += parseInt($(element).find(".individual_totalPoint_input").val());
				
				console.log("상품 총 가격 : " + totalPrice + " 상품 총 갯수 : " + totalCount + "상품 총 종류 : "+ totalKind + "총 마일리지" + totalPoint);
			}
		});
		
		/* 배송비 결정 */
		if(totalPrice >= 50000){
			deliveryPrice = 0;
		} else if(totalPrice == 0){
			deliveryPrice = 0;
		} else {
			deliveryPrice = 3000;	
		}
		
		/* 최종 가격 */
		finalTotalPrice = totalPrice + deliveryPrice;
		// alert("최종 가격 : " + finalTotalPrice + "  배송비 : "+ deliveryPrice);
		
		/* 값 삽입 */
		// 총 가격
		$(".totalPriceDiv").text(totalPrice.toLocaleString());
		// 총 갯수
		$(".totalCountSpan").text(totalCount);
		// 총 종류
		$(".totalKindDiv").text(totalKind);
		// 총 마일리지
		$(".totalPointDiv").text(totalPoint.toLocaleString());
		// 배송비
		$(".deliveryPriceDiv").text(deliveryPrice.toLocaleString());	
		// 최종 가격(총 가격 + 배송비)
		$(".finalTotalPriceDiv").text(finalTotalPrice.toLocaleString());
	}
	
	/* 수량버튼 */
	$(".plusBtn").on("click", function(){
		// alert("click")
		let quantity = $(this).parent("div").parent("div").find("input").val();
		$(this).parent("div").parent("div").find("input").val(++quantity);
	});
	$(".minusBtn").on("click", function(){
		// alert("click")
		let quantity = $(this).parent("div").parent("div").find("input").val();
		if(quantity > 1){
			$(this).parent("div").parent("div").find("input").val(--quantity);		
		}
	});

	/* 수량 변경 이벤트 */
	$(".quantity_modify_btn").on("click", function(){
		let crt_no = $(this).data("cartno");		// HTML5에선 data 속성에 작성한 대문자를 소문자로 바꾼다고 함!
		let crt_count = $(this).parent("div").parent("div").find("input").val();
		// alert(crt_no)
		// alert(crt_count)
		$(".update_crt_no").val(crt_no);
		$(".update_crt_count").val(crt_count);
		$(".quantity_update_form").submit();
		
	});
	
	/* 장바구니 삭제 버튼 */
	$(".deleteBtn").on("click", function(e){
		e.preventDefault();
		const crt_no = $(this).data("cartno");
		// alert(crt_no)
		$(".delete_crt_no").val(crt_no);
		$(".quantity_delete_form").submit();
	});
	