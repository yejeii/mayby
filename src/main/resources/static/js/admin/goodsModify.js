/**
 * goodsEnroll.jsp
 * made by yeji
 */
 $(document).ready(function(){	
	
	/* 위지윅 적용 */
	/* 상품 설명 */
	ClassicEditor
	.create(document.querySelector('#productContents_textarea'))
	.catch(error=>{
		console.error(error);
	});

	// 변수
	let modifyForm = $("#modifyForm");
	
	/* 할인율 인터페이스 출력 */
	let productPriceInput = $("input[name='p_price']");
	let discountInput = $("input[name='p_discount']");
	
	let productPrice = productPriceInput.val();
	let rawDiscountRate = discountInput.val();
	let discountRate = rawDiscountRate * 100;
	
	
	let discountPrice = productPrice * (1-rawDiscountRate);
	$(".span_discount").html(discountPrice);
	$("#discount_interface").val(discountRate);
			

	/* 할인율 input 설정 */
	$("#discount_interface").on("propertychange change keyup paste input", function() {
		
		// input 태그
		let userInput = $("#discount_interface");
		let discountInput = $("#p_discount");	// hidden 태그
		
		let discountRate = userInput.val();	// 사용자가 입력할 할인값  ex. 20
		let sendDiscountRate = discountRate / 100;  // 서버에 전송될 할인값  ex. 0.2
		
		let goodsPrice = $("#p_price").val();	// 원가  ex. 20000
		let discountPrice = goodsPrice * (1-sendDiscountRate);	 // 할인가격  ex. 16000
		
		if(!isNaN(discountRate)) {		// 숫자인 경우
			$(".span_discount").html(discountPrice);
			discountInput.val(sendDiscountRate);
		}
	})
	
	// input p_price 값이 변경된 경우 다시 넣어주기
	$("#p_price").on("change", function() {
		let userInput = $("#discount_interface");
		let discountInput = $("#p_discount");
		
		let discountRate = userInput.val();	// 사용자가 입력할 할인값
		let sendDiscountRate = discountRate / 100;  // 서버에 전송될 할인값
		
		let goodsPrice = $("#p_price").val();	// 원가
		let discountPrice = goodsPrice * (1-sendDiscountRate);	 // 할인가격
			
		if(!isNaN(discountRate)) {		// 숫자인 경우
				$(".span_discount").html(discountPrice);
		}
	})
	
	/* 취소 버튼 */
	$("#cancelBtn").click(function(e){
		e.preventDefault();
		$("#moveForm").submit();
	});
	
	/* 상품 수정 버튼 */
	$("#modifyBtn").click(function(e){
		e.preventDefault();
		
		
		// 유효성 검사
		// 유효성 변수
		let idCk = false;
		let nameCk = false;
		let catecodeCk = false;
		let sizeCk = false;
		let priceCk = false;
		let discountCk= false;
		let colorCk = false;
		let amountCk = false;
		//let imageCk = false;
		let fabricCk = false;
		let infoCk = false;
		
		// 체크대상 변수
		var productId = $("#p_id").val();
		var productName = $("#p_name").val();
		var productCatecode = $("select[name='p_catecode']").val();
		var productSizeCnt = $("input[name='p_size']:checkbox:checked").length;
		var productPrice = $("#p_price").val();
		var productDiscount = $("#discount_interface").val();
		var productColor = $("input[name='p_color']:checkbox:checked").length;
		var productAmount = $("#p_amount").val();
		//var productImage = $("#input_image").val();
		var productFabric = $("#p_fabric").val();
		var productInfo = $(".pInfo p").html(); 

		// 정규식 표현
		var idExp = /^[A-Z]{1,2}_[0-9]{4,}$/;
		var nameExp = /^[ㄱ-ㅎ-가-힣/]/;
		var priceExp = /^[0-9]+$/;
		var amountExp = /^[0-9]+$/;
		var fabricExp = /^[ㄱ-ㅎ-가-힣/]/;
		
		// 선태한 태그의 위치 반환
		var offset = $(".card-body").offset();
		
		// 유효성 체크
		if(! idExp.test(productId)) {
			$("#p_id_warn").css('display', 'block');
			$("#p_id").focus();
			idCk = false;
		} else {
			$("#p_id_warn").css('display', 'none');
			idCk = true;
		}
		
		if(! nameExp.test(productName)) {
			$("#p_name_warn").css('display', 'block');
			$("#p_name").focus();
			nameCk = false;
		} else {
			$("#p_name_warn").css('display', 'none');
			nameCk = true;
		}
		
		if(productCatecode === 'none') {
			$("#p_catecode_warn").css('display', 'block');
			catecodeCk = false;
		} else {
			$("#p_catecode_warn").css('display', 'none');
			catecodeCk = true;
		}
		
		if(productSizeCnt < 1) {
			$("#p_size_warn").css('display', 'block');
			$("#p_size_warn").html("한 개 이상 선택해야 합니다.");
			sizeCk = false;
		} else {
			$("#p_size_warn").html(productSizeCnt+"개가 선택되었습니다");
			$("#p_size_warn").css("color", "green");
			sizeCk = true;
		}
		
		if(! priceExp.test(productPrice)) {
			$("#p_price_warn").css('display', 'block');
			priceCk = false;
		} else {
			$("#p_price_warn").css('display', 'none');
			priceCk = true;
		}
		
		// isNan -> 문자인 경우 true
		if(!isNaN(productDiscount))  {	// 숫자인 경우
			$("#p_discount_warn").css('display', 'none');
			discountCk = true;
		} else {
			$(".span_discount").html('');
			$("#p_discount_warn").css('display', 'block');
			discountCk = false;
		}
		
		if(productColor < 1) {
			$("#p_color_warn").css('display', 'block');
			$("#p_color_warn").html("한 개 이상 선택해야 합니다.");
			colorCk = false;
		} else {
			$("#p_color_warn").html(productColor+"개의 색상이 선택되었습니다");
			$("#p_color_warn").css("color", "green");
			colorCk = true;
		}
		
		if(! amountExp.test(productAmount)) {
			$("#p_amount_warn").css('display', 'block');
			amountCk = false;
		} else {
			$("#p_amount_warn").css('display', 'none');
			amountCk = true;
		}
		
/*		if(productImage !== '') {
			$("#p_image_warn").css('display', 'none');
			imageCk = true;
		} else {
			$("#p_image_warn").css('display', 'block');
			imageCk = false;
		}
*/		
		if(! fabricExp.test(productFabric)) {
			$("#p_fabric_warn").css('display', 'block');
			fabricCk = false;
		} else {
			$("#p_fabric_warn").css('display', 'none');
			fabricCk = true;
		}
		
		if(productInfo != '<br data-cke-filler="true">') {
			$("#p_info_warn").css("display", "none");
			infoCk = true;
		} else {
			$("#p_info_warn").css("display", "block");
			infoCk = false;
		}
			
		// console.log(productId + productName + productCatecode + productSizeCnt + productPrice + productDiscount + productColor + productAmount + productImage + productFabric + infoCk)
		
		// 최종 확인
		if(idCk && nameCk && catecodeCk && sizeCk && priceCk && discountCk && colorCk && amountCk && fabricCk && infoCk) {
			modifyForm.submit();
		} else {
			$("html, body").animate({scrollTop : offset.top}, 400);
			return false;
		}
		
	});
})