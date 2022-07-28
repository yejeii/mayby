package com.mayby.admin.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mayby.admin.domain.AdminMapper;
import com.mayby.admin.vo.AdminVO;
import com.mayby.category.vo.CategoryVO;
import com.mayby.member.vo.MemberVO;
import com.mayby.model.vo.Criteria;
import com.mayby.model.vo.MMCriteria;
import com.mayby.product.vo.ProductVO;

@Service
public class AdminServiceImpl implements AdminService {
	
	private static final Logger LOGGER = LogManager.getLogger(AdminServiceImpl.class);

	@Autowired
	AdminMapper adminMapper;
	
	@Override
	public AdminVO loginPro(AdminVO vo) throws Exception {
		LOGGER.info(" A.S - loginPro() 호출");
		AdminVO admin = adminMapper.adminLogin(vo);
		LOGGER.info(" A.S - loginPro() 관리자 계정 GET");
		return admin;
	}

	// 카테고리 리스트
	@Override
	public List<CategoryVO> getCategoryList() throws Exception {
		LOGGER.info(" A.S - getCategoryList() 호출");
		return adminMapper.getcateList();
	}

	// 상품목록 리스트
	@Override
	public List<ProductVO> getProductList(Criteria cri) throws Exception {
		LOGGER.info(" A.S - getProductList() 호출");
		return adminMapper.getProductList(cri);
	}

	// 상품 총 갯수
	@Override
	public int getProductCnt(Criteria cri) throws Exception {
		LOGGER.info(" A.S - getProductCnt() 호출");
		return adminMapper.getGoodsCnt(cri);
	}
	
	// 상품 조회
	@Override
	public ProductVO getProductDetail(String p_id) throws Exception {
		LOGGER.info(" A.S - getProductDetail() 호출");
		return adminMapper.getProductDetail(p_id);
	}
	
	// 상품 수정
	@Override
	public int goodsModify(ProductVO vo) throws Exception {
		LOGGER.info(" A.S - goodsModify() 호출");
		return adminMapper.goodsModify(vo);
	}
	
	// 상품 삭제
	@Override
	public int goodsDelete(String p_id) throws Exception {
		LOGGER.info(" A.S - goodsDelete() 호출");
		return adminMapper.goodsDelete(p_id);
	}

	// 회원 목록 조회
	@Override
	public List<MemberVO> getMemberList(MMCriteria cri) throws Exception {
		LOGGER.info(" A.S - getMemberList() 호출");
		return adminMapper.getListPaging(cri);
	}

	// 회원 총 수
	@Override
	public int getMemberCnt(MMCriteria cri) throws Exception {
		LOGGER.info(" A.S - getMemberCnt() 호출");
		return adminMapper.getMemberCnt(cri);
	}

}
