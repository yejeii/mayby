package com.mayby.cart.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mayby.cart.service.CartService;
import com.mayby.cart.vo.CartVO;
import com.mayby.member.controller.MemberControllerImpl;
import com.mayby.member.vo.MemberVO;

@Controller
@RequestMapping("/cart/*")
public class CartControllerImpl implements CartController{

	private static final Logger LOGGER = LogManager.getLogger(MemberControllerImpl.class);

	@Autowired
	private CartService cs;
	
	@GetMapping("cartpage")
	public String mycart() {
		LOGGER.info("mycart() 호출");
		return "cart/cartpage";
	}
	
	/* 카트 추가 
	  * addCartPro(CartVO cart, HttpServletRequest request) 
	  *  -> 등록할 데이터를 전달받아야 하기 때문에 CartVO 타입의 파라미터 변수를 선언, 로그인 여부를 위한  session 객체를 받기 위해 HttpServletRequest
	 **/
	@PostMapping("add")
	@ResponseBody 
	public String addCartPro(@RequestBody CartVO cart, HttpServletRequest request) throws Exception {
		LOGGER.info(" C.C - addCartPro() 호출");
		
		// 로그인 체크
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO)session.getAttribute("user");
		LOGGER.info("mvo : "+mvo);
		if(mvo == null) {
			return "5";
		}
		
		// 카트 등록
		// cart.setCrt_m_id(mvo.getM_id());
		LOGGER.info(" 받은 cart : "+ cart.toString());
		int result = cs.addCart(cart);
		return result + "";
	}
	
	/*
	 * 장바구니 뷰 페이지 이동
	 */
	@GetMapping("{id}")
	public String cartPage(@PathVariable("id") String crt_m_id, Model model) throws Exception {
		LOGGER.info(" C.C - cartPage() 호출");
		// 장바구니 데이터 전송
		List<CartVO> cartList = cs.getCartList(crt_m_id);
		LOGGER.info(crt_m_id + "님의 장바구니 List : "+ cartList);
		model.addAttribute("cartInfo", cartList);
		
		return "/cart/cart";
	}
	
	/*
	 * 카트 수량 수정
	 */
	@PostMapping("update")
	public String updateCartPro(CartVO cart) throws Exception {
		LOGGER.info(" C.C - updateCartPro() 호출");
		LOGGER.info(" 들고온 장바구니 : "+cart);

		cs.modifyCount(cart);
		return "redirect:/cart/" + cart.getCrt_m_id();
	}
	
	/*
	 * 카트 삭제
	 */
	@PostMapping("/cart/delete")
	public String deleteCartPro(CartVO cart) throws Exception {
		LOGGER.info(" C.C - deleteCartPro() 호출");
		LOGGER.info(" 삭제할 장바구니 : "+cart);
		
		int result = cs.deleteCart(cart.getCrt_no());
		LOGGER.info(" 삭제 결과 : "+ result);
		// 장바구니 페이지로 리다이렉트 
		// 이동 URL : /cart/사용자아이디
		return "redirect:/cart/" + cart.getCrt_m_id();
		
	}
}
