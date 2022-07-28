package com.mayby.category.vo;

import org.springframework.stereotype.Repository;

import lombok.Data;

@Repository
@Data
public class CategoryVO {

	// 카테고리 등급
	private int pc_tier;
	
	// 카테고리 이름
	private String pc_name;
	
	// 카테고리 넘버
	private String pc_code;
	
	// 상위 카테고리
	private String pc_parent;
}
