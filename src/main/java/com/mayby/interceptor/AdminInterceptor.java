package com.mayby.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;

public class AdminInterceptor implements  HandlerInterceptor{

	private static final Logger LOGGER = LogManager.getLogger(AdminInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		LOGGER.info(" A.I - AdminInterceptor 실행");
		
		/* 로그인 시 session "admin" 키로 로그인 사용자 정보 데이터 저장해둔 상태 
		  * 세션의 admin를 불러들여 데이터 유무 확인으로 로그인 여부 판단!
		 **/
		HttpSession  session = request.getSession();
		String admin = (String) session.getAttribute("admin");
		
		if(admin == null) {
			response.sendRedirect("/member/login");
			return false;
		} else {
			return true;
		}
	}
}
