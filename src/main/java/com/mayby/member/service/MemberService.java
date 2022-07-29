package com.mayby.member.service;

import com.mayby.member.vo.MemberVO;

public interface MemberService {
	
	// 회원가입
	public void register(MemberVO vo) throws Exception;
	
	// 아이디 체크
	public int checkId(String m_id) throws Exception;
	
	// 로그인
	public MemberVO memberLogin(MemberVO vo) throws Exception;
	
	// 회원정보 수정
	public int memberUpdate(MemberVO vo) throws Exception;

	// 회원 정보 조회
	public MemberVO getMember(MemberVO vo) throws Exception;
	
	// 아이디로 회원 정보 조회 
	public MemberVO getMemberById(String m_id) throws Exception;
	
	// 회원 아이디, 비밀번호 체크
	public String getCheck(MemberVO vo) throws Exception;
	
	// 회원 탈퇴 처리
	public void memberDelete(MemberVO vo) throws Exception;
	
	//회원 프로필 수정
	public void modifyproflie(String m_id, String m_proflie) throws Exception;
}
