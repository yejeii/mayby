package com.mayby.cart.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mayby.cart.domain.CartMapper;
import com.mayby.cart.vo.CartVO;
import com.mayby.member.controller.MemberControllerImpl;
import com.mayby.model.vo.AttachImageVO;
import com.mayby.pimage.domain.AttachMapper;

@Service
public class CartServiceImpl implements CartService {

	private static final Logger LOGGER = LogManager.getLogger(MemberControllerImpl.class);

	@Autowired
	private CartMapper cm;
	
	@Autowired
	private AttachMapper am;
	
	/* 카트 추가 */
	@Override
	public int addCart(CartVO vo) throws Exception {
		LOGGER.info(" C.S - addCart() 호출");
		
		// 등록하고자 하는 데이터(같은 유저, 같은 상품)가 이미 DB에 존재하는지 확인
		// 존재 - 2 -> 장바구니 들어가서 수량 더 추가하던가 해라~~
		// 같은 유저 & 같은 상품 장바구니 넣지 않았다! -> 1 (새로 insert)
		// 예외 - 0
		CartVO checkCart = cm.checkCart(vo);
		LOGGER.info(" checkCart 리턴 전" + checkCart);
		if(checkCart != null) {		// 존재할 경우
			return 2;
		} 
		
		// 장바구니 등록 & 에러 시 반환
		try {
			LOGGER.info(" checkCart 리턴 전" + vo);
			return cm.addCart(vo);		// 1
		} catch(Exception e) {
			LOGGER.info(" Error : "+e.getMessage());
			return 0;
		}
	}

	/* 장바구니 정보 리스트 */
	@Override
	public List<CartVO> getCartList(String crt_m_id) throws Exception {
		LOGGER.info(" C.S - getCartList() 호출");
		
		/* 회원의 장바구니 정보 모두 호출 */
		List<CartVO> cart = cm.getCart(crt_m_id);
		
		// List 요소의 CartVO객체의 'salePrice', 'totalPrice', 'point', 'totalPoint' 속성 값 X
		for(CartVO vo : cart) {

			// 종합 정보 초기화
			vo.initSaleTotal();
			
			// 이미지 정보  get
			int p_idx = vo.getP_idx();
			List<AttachImageVO> imageList = am.getAttachList(p_idx);
			vo.setImageList(imageList);
		}
		return cart;
	}

	/* 카트 수량 수정 */
	@Override
	public int modifyCount(CartVO vo) throws Exception {
		LOGGER.info(" C.S - modifyCount() 호출");
		return cm.modifyCount(vo);
	}

	/* 카트 삭제 */
	@Override
	public int deleteCart(int crt_no) throws Exception {
		LOGGER.info(" C.S - deleteCart() 호출");
		LOGGER.info(" 삭제할 crt_no " + crt_no);
		return cm.deleteCart(crt_no);
	}
	

	
	
}
