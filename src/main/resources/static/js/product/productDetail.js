/**
 * productDetail.jsp 
 * mady by yeji 
 */
 $(document).ready(function(){
	
	/* 상세 페이지에 이미지 출력 - 썸네일 말고 일반 이미지로 불러올 것*/
	const pobj = $(".image_wrap");
	
	if(pobj.data("productid")) {
		
		const uploadPath = pobj.data("path");
		const uuid = pobj.data("uuid");
		const fileName = pobj.data("filename");
		
		console.log(uploadPath)
		console.log(uuid)
		console.log(fileName)
		
		const fileCallPath = encodeURIComponent(uploadPath + "/" + uuid + "_" + fileName);
		pobj.find("img").attr("src", "/product/display?fileName="+fileCallPath);
	} else {
		pobj.find("img").attr("src", "${contextPath}/static/images/noImage.png");
	}
		
	/* 상품 수량 버튼 이벤트 */
	let quantity = $(".quantity_input").val();
	$(".plusBtn").on("click", function(){
		
		/* input 태그의 값이 변수 quantity에 저장한 값에 +1 해주는 코드 */
		$(".quantity_input").val(++quantity);
	});
	$(".minusBtn").on("click", function(){
		
		/* 0 이하가 될 수 없도록 */
		if(quantity > 1){
			$(".quantity_input").val(--quantity);
		}
	});
	
	/* 서버로 전송할 데이터 */
	const form = {
		crt_m_id : '${user.m_id}',
		crt_p_id : '${goodsInfo.p_id}',
		crt_count : ''
	}
	
	/* 장바구니 추가 버튼 */
	$(".addCartBtn").on("click", function(e){
		e.preventDefault()
		form.crt_count = $(".quantity_input").val();
		
		$.ajax({
			url: '/cart/add',
			type: "post",
			data: form,
			success: function(result) {
				//alert(result)
				cartAlert(result);
			}
		})
	});
	
	function cartAlert(result) {
		if(result == '0'){		// 서버 오류
			alert("장바구니에 추가 하지 못하였습니다.");
		} else if(result == '1'){	// DB OK
			alert("장바구니에 추가되었습니다.");
		} else if(result == '2'){	// DB에 이미 추가
			alert("장바구니에 이미 추가되어져 있습니다.");
		} else if(result == '5'){	// 로그인 X
			alert("로그인이 필요합니다.");	
		}
	}
	
	
	
	
});