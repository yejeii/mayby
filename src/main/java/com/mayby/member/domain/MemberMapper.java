package com.mayby.member.domain;

import org.apache.ibatis.annotations.Mapper;

import com.mayby.member.vo.MemberVO;

@Mapper
public interface MemberMapper {

	// 회원 등록
	public void registerMember(MemberVO vo) throws Exception;
	
	// 중복 아이디 확인
	public int checkId(String m_id) throws Exception;
	
	// 로그인 처리
	public MemberVO memberLogin(MemberVO member) throws Exception;
	
	// 회원 정보 조회
	public MemberVO getMember(MemberVO vo) throws Exception;
	
	// 회원 정보 수정
	public int memberUpdate(MemberVO member) throws Exception;
	
	// 회원 탈퇴
	public void memberDelete(MemberVO member) throws Exception;
	
	// 회원 아이디, 비번 확인
	public String checkMember(MemberVO vo) throws Exception;
	
	// 아이디로 회원 정보 조회
	public MemberVO getMemberById(String m_id) throws Exception;

	//회원 프로필 사진 수정
	public void updateproflie(String m_id,String m_proflie);
}
