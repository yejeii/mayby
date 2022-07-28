/**
 * reply
 * made by jongjin
 */
 	getReplies();
	function getReplies(){
	
		let r_b_id = '${goodsInfo.p_idx}';
		
		$.getJSON("/reply/all/" +r_b_id, function(date){
			 console.log(date); 
				
			var str= "";
				
			$(date).each(function(){
				str += "<li data-r_no='" + this.r_no + "' class='replyLi'>"
		    	+   "<p class='r_m_id'>" + this.r_m_id + "</p>"
		    	+   "<p class='r_content'>" + this.r_content + "</p>"
			    	+   "<p class='r_regdate'>" + this.r_regdate+ "</p>"
	                +   "<button type='button' class='btn btn-xs btn-success' data-toggle='modal' data-target='#modifyModal'>댓글 수정</button>"
/* 		                +   "<button type='button' class='btn btn-xs btn-success'  data-target='#modifyModal'>댓글 삭제</button>"
*/				    	+ "</li>"
			    	+ "<hr/>";
			})

  		 $("#replies").html(str);
						
		})
	}
			
	$(".replyAddBtn").on("click",function(){
		var reply_text = $("#newReplyText");
		var reply_writer = $("#newReplyWriter");
		// var reply_r_b_id = $("#r_b_id").val();
		
		var reply_textVal = reply_text.val();
		var reply_writerrVal = reply_writer.val();
		var r_b_id =  $("#r_b_id").val();
		console.log(r_b_id);
		
		$.ajax({
			type:"post",
			url:"/reply",
			headers :{
				"Content-type" : "application/json",
				"X-HTTP-Method-Override" :"POST"
			},
			 dataType : "text",
		        data : JSON.stringify({
		            "r_b_id" : r_b_id,
		            "r_content" : reply_textVal,
		            "r_m_id" : reply_writerrVal
		        }),
		        success : function (result) {
		            if (result == "success") {
		                alert("댓글 등록 완료!");
		            }
		            getReplies(); // 댓글 목록 출력 함수 호출
		            reply_text.val(""); // 댓글 내용 초기화
		            reply_writer.val(""); // 댓글 작성자 초기화
		        }
		})
	})
	
$("#replies").on("click", ".replyLi button", function () {
    var reply = $(this).parent();

    var reply_no = reply.attr("data-r_no");
    var reply_text = reply.find(".r_content").text();
    var reply_writer = reply.find(".r_m_id").text();

    $("#r_no").val(reply_no);
    $("#r_content").val(reply_text);
    $("#r_m_id").val(reply_writer);
});

$(".modalDelBtn").on("click", function () {

	    // 댓글 번호
	    var reply_no = $(this).parent().parent().find("#r_no").val();

	    // AJAX통신 : DELETE
	    $.ajax({
	        type : "delete",
	        url : "/reply/" + reply_no,
	        headers : {
	            "Content-type" : "application/json",
	            "X-HTTP-Method-Override" : "DELETE"
	        },
	        dataType : "text",
	        success : function (result) {
	            console.log("result : " + result);
	            if (result == "success") {
	                alert("댓글 삭제 완료!");
	                $("#modifyModal").modal("hide"); // Modal 닫기
	                getReplies(); // 댓글 목록 갱신
	            }
	        }
	    });
	});  
	
	$(".modalModBtn").on("click", function () {
		alert("click")

	    // 댓글 선택자
	    var reply = $(this).parent().parent();
	    // 댓글번호
	    var reply_no = reply.find("#r_no").val();
	    // alert(reply_no)
	    
	    // 수정한 댓글내용
	    var reply_text = reply.find("#r_content").val();
	  
	    // AJAX통신 : PUT
	    $.ajax({
	        type : "put",
	        url : "/reply/" + reply_no,
	        headers : {
	            "Content-type" : "application/json",
	            "X-HTTP-Method-Override" : "PUT"
	        },
	        data : JSON.stringify(
	            {"r_content" : reply_text}
	        ),
	        dataType : "text",
	        success : function (result) {
	            console.log("result : " + result);
	            if (result == "success") {
	                alert("댓글 수정 완료!");
	                $("#modifyModal").modal("hide"); // Modal 닫기
	                getReplies(); // 댓글 목록 갱신
	            }
	        }
	    });

	});