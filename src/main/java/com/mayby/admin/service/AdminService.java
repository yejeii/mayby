package com.mayby.admin.service;

import java.util.List;

import com.mayby.admin.vo.AdminVO;
import com.mayby.category.vo.CategoryVO;
import com.mayby.member.vo.MemberVO;
import com.mayby.model.vo.Criteria;
import com.mayby.model.vo.MMCriteria;
import com.mayby.product.vo.ProductVO;

public interface AdminService {

	// 로그인 확인
	public AdminVO loginPro(AdminVO vo) throws Exception;
	
	// 카테고리 리스트
	public List<CategoryVO> getCategoryList() throws Exception;
	
	// 상품 목록
	public List<ProductVO> getProductList(Criteria cri) throws Exception;
	
	// 상품 총 갯수
	public int getProductCnt(Criteria cri) throws Exception;

	// 상품 조회
	public ProductVO getProductDetail(String p_id) throws Exception;
	
	// 상품 수정
	public int goodsModify(ProductVO vo) throws Exception;

	// 상품 삭제
	public int goodsDelete(String p_id) throws Exception;
	
	// 회원 리스트(페이징 적용) 
	public List<MemberVO> getMemberList(MMCriteria cri) throws Exception;
	
	// 회원 총 수
	public int getMemberCnt(MMCriteria cri) throws Exception;

}
