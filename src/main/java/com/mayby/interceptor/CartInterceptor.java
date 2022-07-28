package com.mayby.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;

import com.mayby.member.vo.MemberVO;

public class CartInterceptor implements  HandlerInterceptor{

	private static final Logger LOGGER = LogManager.getLogger(LoginInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		LOGGER.info(" C.I - CartInterceptor 실행");
		
		/* 로그인 시 session "user" 키로 로그인 사용자 정보 데이터 저장해둔 상태 
		  * 세션의 user를 불러들여 데이터 유무 확인으로 로그인 여부 판단!
		 **/
		HttpSession  session = request.getSession();
		MemberVO vo = (MemberVO)session.getAttribute("user");
		
		if(vo == null) {
			response.sendRedirect("/member/login");
			return false;
		} else {
			return true;
		}
	}

	
}
