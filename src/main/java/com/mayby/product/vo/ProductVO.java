package com.mayby.product.vo;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mayby.category.vo.CategoryVO;
import com.mayby.model.vo.AttachImageVO;

import lombok.Data;

@Repository
@Data
public class ProductVO {

	private int p_idx;
	private String p_id;
	private String p_name;
	private String p_catecode; 	// 카테고리 코드
	private String p_size;
	private int p_price;
	private double p_discount;
	private String p_color;
	private int p_amount;
	private Timestamp p_regdate;
	private Timestamp p_updatedate;
	private String p_fabric;
	private String p_information;
	
	private String pc_name;		// 카테고리 이름
	
	// 이미지 정보
	private List<AttachImageVO> imageList;
	
}
