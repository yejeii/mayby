package com.mayby.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterceptor implements HandlerInterceptor{

	private static final Logger LOGGER = LogManager.getLogger(LoginInterceptor.class);

	@Override
	// Controller 진입 전 작업
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		LOGGER.info(" L.I - preHandle 작동");
		// 세션 초기화, 삭제
		HttpSession session = request.getSession();
		session.invalidate();
		
		return true;
	}
	
	
}
