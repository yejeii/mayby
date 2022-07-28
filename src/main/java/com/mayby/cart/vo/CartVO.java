package com.mayby.cart.vo;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mayby.model.vo.AttachImageVO;

import lombok.Data;

@Data
@Repository
public class CartVO {

	private int crt_no;	// 장바구니 no
	
	private String crt_m_id; 	// 회원 아이디
	
	private String crt_p_id;	// 상품 아이디
	
	private int crt_count;		// 장바구니에 들어간 상품 갯수
	
	// MyBatis FK 걸린 테이블 
	/* product */
	private int p_idx; 
	
	private String p_name;
	
	private int p_price;
	
	private int p_discount;	
	
	/* 추가 */
	private int salePrice;		// 적용할 상품 한 개의 판매 가격
	
	private int totalPrice;	// 판매 가격에 수량을 곱한 총 가격
	
	private int point;			// 상품 한 개에서 받을 수 있는 포인트
	
	private int totalPoint;		// 사용자가 구매하는 수량을 곱해 받을 수 있는 포인트

	/* 상품 이미지 */
	private List<AttachImageVO> imageList;
	
	// 장바구니 정보(테이블 한 줄) 초기화
	public void initSaleTotal() {
		this.salePrice = (int)(this.p_price * (1-this.p_discount));
		this.totalPrice = this.salePrice * this.crt_count;
		this.point = (int)(Math.floor(this.salePrice*0.05));
		this.totalPoint = this.point * this.crt_count;
	}
	
}
