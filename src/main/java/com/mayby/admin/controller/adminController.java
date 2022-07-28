package com.mayby.admin.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mayby.model.vo.AttachImageVO;
import com.mayby.model.vo.Criteria;
import com.mayby.model.vo.MMCriteria;
import com.mayby.product.vo.ProductVO;


public interface adminController {
	
	// 관리자 로그인
	public String loginPro(Map<String, String> param, HttpServletRequest request,  Model model ) throws Exception;
	
	// 관리자 페이지
	public String adminpage(HttpServletRequest request) throws Exception;
	
	// 상품 등록 페이지
	public String goodsEnroll(HttpServletRequest request, Model model) throws Exception;
	
	// 상품 상세 설명 등록 페이지
	public String goodsEnrollInfo(HttpServletRequest request) throws Exception;
	
	// 상품 등록
	public String goodsEnrollPro(HttpServletRequest request, RedirectAttributes rttr) throws Exception;
	
	// 상품 이미지 등록 처리
	public ResponseEntity<List<AttachImageVO>> uploadFilePro(HttpServletRequest request, MultipartFile[] uploadFile) throws Exception;
	
	// 상품 이미지 파일 삭제
	public ResponseEntity<String> deleteFile(HttpServletRequest request, String fileName) throws Exception;
	
	// 상품 관리 페이지
	public void goodsManage(HttpServletRequest request, Criteria cri, Model model) throws Exception;
	
	// 상품 조회, 수정 페이지
	public void goodsDetail(HttpServletRequest request, String p_id, Criteria cri, Model model) throws Exception;

	// 상품 수정 처리
	public String goodsModifyPro(HttpServletRequest request, ProductVO vo, RedirectAttributes rttr) throws Exception;
	
	// 상품 삭제 처리
	public String goodsDeletePro(HttpServletRequest request, String p_id, RedirectAttributes rttr) throws Exception;
	
	// 회원 관리 페이지
	public void memberManage(HttpServletRequest request,  MMCriteria cri, Model model) throws Exception;
}
