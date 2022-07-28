package com.mayby.reply.vo;

import java.util.Date;

import org.springframework.stereotype.Repository;

import lombok.Data;

@Data
@Repository
public class ReplyVO {

	private Integer r_no; //댓글 번호
	
	private Integer r_b_id; // 상품번호
	
	private String r_m_id; // 작성자 아이디
	
	private String r_content; //댓글 내용
	
	private Date r_regdate; // 작성일자
	
	private Date r_updatedate; // 수정일자
	
	private Double r_rating;
}
