package com.mayby.model.vo;


import lombok.Data;

@Data
public class AttachImageVO {

	// 경로
	private String pi_uploadPath;
	
	// uuid
	private String pi_uuid;
	
	// 파일명
	private String pi_fileName;
	
	// 상품 p_idx
	private int pi_p_id;
}
