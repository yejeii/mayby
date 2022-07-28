package com.mayby.product.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;

import com.mayby.model.vo.Criteria;

public interface ProductController {

	// 상품 상세 페이지
	public String productDetail(int p_idx, Model model, HttpServletRequest request);
	
	// 상품 이미지 가져오기
	public ResponseEntity<byte[]> getImage(String fileName) throws Exception;

	// 상품 검색
	public String searchGoods(Criteria cri, Model model) throws Exception;
	
	
}
