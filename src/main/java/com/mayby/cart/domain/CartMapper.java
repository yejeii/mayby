package com.mayby.cart.domain;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mayby.cart.vo.CartVO;

@Mapper
public interface CartMapper {

	/* 카트 추가(테이블의 row 한 줄) */
	public int addCart(CartVO cart) throws Exception;
	
	/* 카트 삭제(테이블의 row 한 줄) */
	public int deleteCart(int crt_no) throws Exception;
	
	/* 카트 수량 수정(row의 수량 변경) */
	public int modifyCount(CartVO cart) throws Exception;
	
	/* 카트 목록(해당 CartVO에서 지정한 회원의 모든 row의 값 get) */
	public List<CartVO> getCart(String ctr_m_id) throws Exception;
	
	/* 카트 확인(회원정보아 상품 정보를 넘겨 해당 row 있는지  확인) */
	public CartVO checkCart(CartVO cart) throws Exception;
}
