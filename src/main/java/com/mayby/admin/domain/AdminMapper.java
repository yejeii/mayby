package com.mayby.admin.domain;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mayby.admin.vo.AdminVO;
import com.mayby.category.vo.CategoryVO;
import com.mayby.member.vo.MemberVO;
import com.mayby.model.vo.AttachImageVO;
import com.mayby.model.vo.Criteria;
import com.mayby.model.vo.MMCriteria;
import com.mayby.product.vo.ProductVO;

@Mapper
public interface AdminMapper {

	// 로그인 확인
	public AdminVO adminLogin(AdminVO vo) throws Exception;

	// 카테고리 리스트
	public List<CategoryVO> getcateList() throws Exception;
	
	// 상품 리스트
	public List<ProductVO> getProductList(Criteria cri) throws Exception;
	
	// 상품 총 갯수
	public int getGoodsCnt(Criteria cri) throws Exception;

	// 상품 조회
	public ProductVO getProductDetail(String p_id) throws Exception;

	// 상품 수정
	public int goodsModify(ProductVO vo) throws Exception;

	// 상품 삭제
	public int goodsDelete(String p_id) throws Exception;
	
	// 이미지 등록
	public void imageEnroll(AttachImageVO vo) throws Exception;
	
	// 회원 정보 조회(페이징 처리)
	public List<MemberVO> getListPaging(MMCriteria cri) throws Exception;
	
	// 회원 총 수
	public int getMemberCnt(MMCriteria cri) throws Exception;
}
