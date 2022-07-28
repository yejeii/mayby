package com.mayby.product.service;

import java.util.List;

import com.mayby.category.vo.CategoryVO;
import com.mayby.model.vo.Criteria;
import com.mayby.product.vo.ProductVO;

public interface ProductService {
	
	// 상품 등록
	public void productEnroll(ProductVO vo) throws Exception;

	/* ----------SY---------------- */
	// 상품 아이디 중복 검사
	public int idCheck(String p_id) throws Exception;
	/* ----------SY---------------- */

	/* 상품 검색 */
	public List<ProductVO> getGoodsList(Criteria cri)  throws Exception;
	
	/* 상품 총 갯수*/
	public int goodsGetTotal(Criteria cri)  throws Exception;
	
	/* 여성 카테고리 리스트 */
	public List<CategoryVO> getCateCode1();
	
	/* 남성 카테고리 리스트 */
	public List<CategoryVO> getCateCode2();
	
	/* 상품 상세 정보 */
	public ProductVO getGoodsInfo(int p_idx);

}
