package com.mayby.product.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mayby.admin.domain.AdminMapper;
import com.mayby.category.vo.CategoryVO;
import com.mayby.model.vo.AttachImageVO;
import com.mayby.model.vo.Criteria;
import com.mayby.pimage.domain.AttachMapper;
import com.mayby.product.domain.ProductMapper;
import com.mayby.product.vo.ProductVO;

@Service
public class ProductServiceImpl implements ProductService{

	private static final Logger LOGGER = LogManager.getLogger(ProductServiceImpl.class);
	
	@Autowired
	ProductMapper productMapper;
	
	@Autowired 
	AdminMapper adminMapper;
	
	@Autowired
	AttachMapper attachMapper;
	
	/* 상품 등록 */
	@Transactional
	@Override
	public void productEnroll(ProductVO vo) throws Exception {
		LOGGER.info(" P.S - productEnroll() 호출");
		productMapper.registerProduct(vo);
		
		if(vo.getImageList() == null || vo.getImageList().size() <= 0) {
			return;
		}
		
		vo.getImageList().forEach(attach ->{
			attach.setPi_p_id(vo.getP_idx());
			try {
				adminMapper.imageEnroll(attach);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		LOGGER.info(" P.S - 상품 정상 등록 완료");
	}

	/* 상품 아이디 중복 검사 */
	@Override
	public int idCheck(String p_id) throws Exception {
		LOGGER.info(" P.S - idCheck() 호출");
		int result = productMapper.idCheck(p_id);
		LOGGER.info(" result : "+result); 
		return result;
	}

	/* 상품 목록 */
	@Override
	public List<ProductVO> getGoodsList(Criteria cri) throws Exception{
		LOGGER.info(" P.S - getGoodsList() 호출");
		
		List<ProductVO> list = productMapper.getGoodsList(cri);
		LOGGER.info(" list : "+list.toString());
		// 배열 list에는 상품에 정보를 담고 있는 ProdcutVO객체가 List 요소로 담겨있음
		
		/** 
		 * list 각 요소로 있는 ProductVO 객체 하나하나에 이미지 정보 추가 
		 * List객체에 담긴 모든 요소(ProductVO 객체)를 순회하면서 구현부에 작성된 코드를 실행
		 * 자바로 FK 키 조인해서 imageList 반환
		 */
		list.forEach(product -> {
			// 이미지 정보를 DB로부터 호출하여 그 값을 ProductVO객체의 imageList에 저장
			int pi_p_id = product.getP_idx();
			List<AttachImageVO> imageList = attachMapper.getAttachList(pi_p_id);
			product.setImageList(imageList);
		});
		return list;
	}

	/* 상품 총 갯수 */
	@Override
	public int goodsGetTotal(Criteria cri) throws Exception {
		LOGGER.info(" P.S - goodsGetTotal() 호출");
		return productMapper.goodsGetTotal(cri);
	}

	/* 여성 카테고리 리스트 */
	@Override
	public List<CategoryVO> getCateCode1() {
		LOGGER.info(" P.S - getCateCode1() 호출");
		return productMapper.getCateCode1();
	}
	
	/* 남성 카테고리 리스트 */
	@Override
	public List<CategoryVO> getCateCode2() {
		LOGGER.info(" P.S - getCateCode2() 호출");
		return productMapper.getCateCode2();
	}

	/* 상품 상세 정보 */
	@Override
	public ProductVO getGoodsInfo(int p_idx) {
		LOGGER.info(" P.S - getGoodsInfo() 호출");
		ProductVO vo = productMapper.getGoodsInfo(p_idx);
		
		// ProductVO 객체에 이미지 정보(AttachImageVO) 추가
		vo.setImageList(attachMapper.getAttachList(p_idx));
		return vo;
	}


}
