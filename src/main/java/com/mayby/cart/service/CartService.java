package com.mayby.cart.service;

import java.util.List;

import com.mayby.cart.vo.CartVO;

public interface CartService {

	/* 카트 추가 */
	public int addCart(CartVO vo) throws Exception;
	
	/* 카트 정보 리스트 */
	public List<CartVO> getCartList(String crt_m_id) throws Exception;

	/* 카트 수량 수정 */
	public int modifyCount(CartVO vo) throws Exception;
	
	/* 카트 삭제 */
	public int deleteCart(int crt_no) throws Exception;
}
