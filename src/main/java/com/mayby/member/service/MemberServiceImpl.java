package com.mayby.member.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mayby.member.domain.MemberMapper;
import com.mayby.member.vo.MemberVO;

@Service
public class MemberServiceImpl implements MemberService{
	private static final Logger LOGGER = LogManager.getLogger(MemberServiceImpl.class);
	
	@Autowired
	MemberVO memberVO;
	
	@Autowired
	MemberMapper memberMapper;
	

	@Override
	public void register(MemberVO vo) throws Exception {
		LOGGER.info(" M.S - register() 호출");
		memberMapper.registerMember(vo);
		LOGGER.info(" M.S - Mapper의 registerMember() 처리 완료");
	}


	@Override
	public int checkId(String m_id) throws Exception {
		LOGGER.info(" M.S - checkId() 호출");
		int result = memberMapper.checkId(m_id);
		LOGGER.info(" M.S - Mapper의 checkId() 처리완료");
		return result;
	}


	@Override
	public MemberVO memberLogin(MemberVO vo) throws Exception {
		LOGGER.info(" M.S - memberLogin() 호출");
		MemberVO member = memberMapper.memberLogin(vo);
		LOGGER.info(" M.S - memberLogin() 처리완료");
		return member;
	}
	
	// 회원 정보 가져오기
	@Override
	public MemberVO getMember(MemberVO vo) throws Exception {
		LOGGER.info(" M.S - getMember() 호출");
		MemberVO member = memberMapper.getMember(vo);
		LOGGER.info(" M.S - getMember() 처리완료");
		return member;
	}

	@Override
	// 회원 정보 수정
	public int memberUpdate(MemberVO vo) throws Exception {
		LOGGER.info(" M.S - memberUpdate() 호출");
		int result = memberMapper.memberUpdate(vo);
		LOGGER.info(" M.S - memberUpdate() 처리완료");
		return result;
	}

	// 회원 탈퇴 처리
	@Override
	public void memberDelete(MemberVO vo) throws Exception {
		LOGGER.info(" M.S - memberDelete() 호출");
		memberMapper.memberDelete(vo);
	}
	
	// 회원 아이디, 비번 확인
	@Override
	public String getCheck(MemberVO vo) throws Exception {
		LOGGER.info(" M.S - getCheck() 호출");
		String result = memberMapper.checkMember(vo);
		return result;
	}


	// 아이디로 회원 정보 조회
	@Override
	public MemberVO getMemberById(String m_id) throws Exception {
		LOGGER.info(" M.S - getMemberById() 호출");
		return memberMapper.getMemberById(m_id);
	}


}
