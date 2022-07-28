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
	let enrollForm = $("#enrollForm");
	let idckCheck = false; 	// 아이디 중복 검사

	/* 할인율 input 설정 */
	$("#discount_interface").on("propertychange change keyup paste input", function() {
		
		// input 태그
		let userInput = $("#discount_interface");
		let discountInput = $("#p_discount");
		
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
	
	/** 
	 * 다중 이미지 업로드 처리 방식
	 * '상품 등록 페이지' 에서 사용자가 이미지를 선택 시 이미지가 업로드(컴퓨터에 저장) 되고, 
	 *  최종적으로 다른 상품 정보들이 작성된 뒤 '등록 버튼'을 눌렀을 때 '업로드 된 이미지'의 정보가 DB에 저장
	*/ 
	$("input[type='file']").on("change", function(e){
		// alert("동작");
		
		/* 이미지가 등록될 때 파일이 이미 존재할 시 삭제 후 서버에 업로드*/
		if($(".imgDeleteBtn").length > 0){
			deleteFile();
		}
		
		// 1. 화면의 이동 없이 첨부파일을 서버로 전송하기 위한 FormData 객체 사용
		let formData = new FormData();
		let fileInput = $('input[name="uploadFile"]');
		let fileList = fileInput[0].files;
		let fileObj = fileList[0];
		
/*    console.log("fileList : " + fileList); 	// [object FileList]
		console.log("fileObj : " + fileObj); 	// [object File]
		console.log("fileName : " + fileObj.name);	// JN_7381.jpg
		console.log("fileSize : " + fileObj.size);	// 2008105
		console.log("fileType(MimeType) : " + fileObj.type);	// image/jpeg
*/		

		// 2. 유효성 검사
		if(!fileCheck(fileObj.name, fileObj.size)){
			return false;
		}
		alert("등록 가능한 파일입니다!");
		
		// 3.  선택한 파일을 FormData에 "uploadFile"이란 이름(key)으로 추가
		for(let i = 0; i < fileList.length; i++){
			formData.append("uploadFile", fileList[i]);
		}
		
		// 4. 첨부파일 서버로 전송
		/** processData : 서버로 전송할 데이터를 queryStirng 형태로 변환할지 여부
		  *	   contentType : 서버로 전송되는 데이터의 content-type
 		  *   dataType : 서버로부터 반환받을 데이터 타입
 		*/
		$.ajax({
			url: '/admin/uploadFilePro',
	    	processData : false,
	    	contentType : false,
	    	data : formData,
	    	type : 'POST',
	    	dataType : 'json',
	    	success: function(result) {	// Status 200
				//console.log(result);
				showUploadImage(result);
			},
			error: function(result) {	// Status 400
				alert("이미지 파일이 아닙니다.");
			}
		});	
		
	});
	
	/* 이미지 파일 유효성 검사 */
	let regex = new RegExp("(.*?)\.(jpg|png|jpeg)$");
	let maxSize = 1024 * 1024 * 1; 	// 1MB
	
	function fileCheck(fileName, fileSize){
		if(fileSize >= maxSize){
			alert("파일 사이즈가 초과했습니다.");
			return false;
		}
		if(!regex.test(fileName)){
			alert("png / jpg/ jpeg 의 파일만 업로드 가능합니다.");
			return false;
		}
		return true;		
	}
	
	/**
	 * 미리 보기 이미지 출력(썸네일 이미지)
	 * 서버로부터 전달받은 배열 데이터(이미지)의 첫 번째 요소만 출력 
	 */
	function showUploadImage(uploadResultArr) {
		console.log(uploadResultArr)
		
		/* 전달받은 데이터 검증 */
		if(!uploadResultArr || uploadResultArr.length == 0) {return}
		
		// 변수 설정
		let uploadResult = $("#uploadResult");
		let obj = uploadResultArr[0];
		//alert(obj)
		let objLength = uploadResultArr.length;
		let str = '';
		let estr = '';
		let thumbnailPath = obj.pi_uploadPath + "\\thumbnail";
		// console.log(thumbnailPath)	// 22.07.14\thumbnail
		
		/* 경로 부분 증 파일 구분자('\')를 /로 수정 */
		//  replace(/\\/g, '/') : String 문자열 중 모든 '\'을 '/'로 변경해준다는 의미
		//  UTF-8로 인코딩을 자동으로 해주지 않는 웹브라우저에 대한 처리
		//  encodeURIComponent() 메서드 
		//     - 문자 A-Z a-z 0-9 - _ . ! ~ * ' ( )을 제외한 모든 문자를 UTF-8로 인코딩하여 이스케이프 문자로 변환
		// 	 -  '/'와 '\'문자 또한 인코딩 -> replace() 필요 X 
		// thumbnailPath = encodeURIComponent(thumbnailPath.replace(/\\/g, '/') + "/TN_" + obj.uuid + "_" + obj.fileName);
		thumbnailPath = encodeURIComponent(thumbnailPath + "/TN_" + obj.pi_uuid + "_" + obj.pi_fileName);
		
		str += "<div id='result_card'>";
		str += 	"<img src='/product/display?fileName=" + thumbnailPath +"'>";
		str += 	"<div class='imgDeleteBtn' data-file='" + thumbnailPath + "'>x</div>";
		// fileName : 22.07.14%5Cthumbnail%2.....jpg
		str += 	"<input type='hidden' name='imageList[0].pi_fileName' value='"+ obj.pi_fileName +"'>";
		str += 	"<input type='hidden' name='imageList[0].pi_uuid' value='"+ obj.pi_uuid +"'>";
		str += 	"<input type='hidden' name='imageList[0].pi_uploadPath' value='"+ obj.pi_uploadPath +"'>";	
		str += "</div>";
		// <div> 태그 하위에 붙이기
		uploadResult.append(str);   
		
		for(var i=0; i<objLength; i++) {
			let imageObj = uploadResultArr[i];
			// alert(imageObj)
			let imagePath = imageObj.pi_uploadPath + "\\thumbnail";
			imagePath =  encodeURIComponent(imagePath + "/TN_" + imageObj.pi_uuid + "_" + imageObj.pi_fileName);
			
			estr += "<div id='else_cart'>";
			estr += 	"<input type='hidden' name='imageList["+i+"].pi_fileName' value='"+ imageObj.pi_fileName +"'>";
			estr += 	"<input type='hidden' name='imageList["+i+"].pi_uuid' value='"+ imageObj.pi_uuid +"'>";
			estr += 	"<input type='hidden' name='imageList["+i+"].pi_uploadPath' value='"+ imageObj.pi_uploadPath +"'>";	
			estr += "</div>";
		}
		uploadResult.append(estr);
	}
	
	/* 파일 삭제 */
	function deleteFile(){
		let targetFile = $(".imgDeleteBtn").data("file");
		//console.log("삭제할 파일 :"+ targetFile)
		let targetDiv = $("#result_card");
		$.ajax({
			url: '/admin/deleteFile',
			data : {fileName : targetFile},
			dataType : 'text',
			type : 'POST',
			success : function(result){
				//console.log(result);
				targetDiv.remove();
				// alert("파일을 삭제했습니다.")
				$("input[type='file']").val("");
			},
			error : function(result){
				//console.log(result);
				alert("파일을 삭제하지 못하였습니다.")
			}
		});
	}
	
	/* 이미지 삭제 버튼 */
	// 'x'가 출력되어 있는 <div> 태그는 웹 페이지가 완전히 렌더링 된 이후 Javascript 코드를 통해 새롭게 출력된(동적으로 출력된) 태그
	// -> 그냥 click() 사용불가!  on() 사용할 것!
	// #uploadReulst <div> 태그를 식별자로 하여 그 내부에 있는 'imgDeleteBtn' <div> 태그를 클릭(click) 하였을 때 콜백 함수가 호출
	$("#uploadResult").on("click", ".imgDeleteBtn", function(e){
		deleteFile();
	});
	
	/* 취소 버튼 */
	$("#cancelBtn").click(function(){
		alert("상품 관리 페이지로 이동합니다");
		location.href='/admin/goodsManage';
	});
	
	/**
	 * 아이디 중복검사 - ajax
	 *  input 태그(class="id_input") 실시간 감지
	 */
	 $('.id_input').on("propertychange change keyup paste input", function(){
		// console.log("keyup 테스트!");
		var id = $('.id_input').val();
		
		var data = {p_id: id};  // 컨트롤러에 넘길 데이터 이름:  데이터(.id_input에 입력된 값)
	
		$.ajax({
			type: 'post',
			async: false,
			url: '/product/productIdCheck',
			data: data,  // 넘길 데이터
			success: function(result) {
				// console.log("성공여부: "+result);
				if(result == -1) {	// 입력값 X or 7자리 미만으로 입력
					$(".id_input"). css('border-color', 'red');
					$(".p_id_input_1").css('display', 'none');
					$("#p_id_warn").css('display', 'block');
					idckCheck = false;
				}
				else if(result == 1) {		// 중복 아이디인 경우
					$(".id_input"). css('border-color', 'red');
					$(".p_id_input_1").css('display', 'none');
					$('.p_id_input_2').css('display', 'inline-block');
					idckCheck = false;
				}
				else {		// 사용 가능한 아이디
					$(".id_input"). css('border-color', 'green');
					$(".p_id_input_1").css('display', 'block');
					$('.p_id_input_2').css('display', 'none');
					$("#p_id_warn").css('display', 'none');
					idckCheck = true;
				}
			}
		})
	});
	
	/* 상품 등록 버튼 */
	$("#enrollBtn").click(function(e){
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
		let imageCk = false;
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
		var productImage = $("#input_image").val();
		var productFabric = $("#p_fabric").val();
		var productInfo = $(".pInfo p").html(); 

		// 정규식 표현
		var idExp = /^[A-Z]{1,2}_[0-9]{4,}$/;
		var nameExp = /^[ㄱ-ㅎ-가-힣0-9a-zA-Z/]/;
		var priceExp = /^[0-9]+$/;
		var amountExp = /^[0-9]+$/;
		var fabricExp = /^[ㄱ-ㅎ-가-힣/]/;
		
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
			$("select[name='p_catecode']").focus();
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
			$("#p_price").focus();
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
			$("#p_amount").focus()
			amountCk = false;
		} else {
			$("#p_amount_warn").css('display', 'none');
			amountCk = true;
		}
		
		if(productImage !== '') {
			$("#p_image_warn").css('display', 'none');
			imageCk = true;
		} else {
			$("#p_image_warn").css('display', 'block');
			imageCk = false;
		}
		
		if(! fabricExp.test(productFabric)) {
			$("#p_fabric_warn").css('display', 'block');
			$("#p_fabric").focus()
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
		// alert(idckCheck);
		
		// 최종 확인
		if(idCk && idckCheck && nameCk && catecodeCk && sizeCk && priceCk && discountCk && colorCk && amountCk && imageCk && fabricCk && infoCk) {
			//alert("와우!")
			enrollForm.submit();
		} else {
			return false;
		}
		
	});
})