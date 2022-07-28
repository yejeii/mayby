package com.mayby.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;

import com.mayby.member.vo.MemberVO;

public interface MemberController {
	
	// 로그인 
	public String login();
	
	// 회원등록
	public String register();
	
	// 회원페이지 조회
	// public void myPage();

	// 회원 정보 수정 처리
	public String memberModifyPro(MemberVO vo, HttpServletRequest request, HttpSession session) throws Exception;

	// 회원 탈퇴 페이지
	public void memberDelete(HttpSession session, MemberVO vo) throws Exception;
	
	// 회원 탈퇴 처리
	public ResponseEntity memberDeletePro(HttpSession session, HttpServletRequest request, MemberVO vo) throws Exception;
}
