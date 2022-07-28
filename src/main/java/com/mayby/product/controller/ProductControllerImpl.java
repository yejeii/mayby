package com.mayby.product.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mayby.model.vo.AttachImageVO;
import com.mayby.model.vo.Criteria;
import com.mayby.model.vo.PageVO;
import com.mayby.pimage.service.AttachService;
import com.mayby.product.service.ProductService;
import com.mayby.product.vo.ProductVO;

@Controller
@RequestMapping("/product/*")
public class ProductControllerImpl implements ProductController {

	private static final Logger LOGGER = LogManager.getLogger(ProductControllerImpl.class);
	private static final String SAVEFILEDIRECTORY = "C:\\spring\\mayby\\product\\reg_image";

	@Autowired
	private AttachService attachService;

	@Autowired
	private ProductService productService;

	// 상품 상세 페이지
	@Override
	@GetMapping("productDetail/{p_idx}")
	public String productDetail(@PathVariable("p_idx") int p_idx, Model model, HttpServletRequest request) {
		LOGGER.info(" P.C - productDetail() 호출");

		HttpSession session = request.getSession();
		session.getAttribute("user");
		// model 객체로 뷰 리턴
		model.addAttribute("goodsInfo", productService.getGoodsInfo(p_idx));

		return "product/productDetail";
	}

	// 상품 이미지 불러오기
	/**
	 * 이미지 출력 구현 과정 
	 * 1. 파라미터로 전달받은 '파일 경로'와 '파일 이름'을 활용하여 대상 이미지 파일을 File 객체로 생성 
	 * 2. 해당 File 객체를 활용하여 MIME TYPE에 대한 정보를 GET 
	 * 3. ResponseEntity에 대상 이미지 데이터를 복사하여 body에 추가 
	 * 4. header의 'Content Type'에 앞서 얻어낸 MIME TYPE으로 수정 
	 * 5. ResponseEntity 객체를 호출한 뷰(view)로 전송
	 */
	@Override
	@GetMapping("display")
	public ResponseEntity<byte[]> getImage(String fileName) throws Exception {
		LOGGER.info(" P.C - getImage() 호출");

		// 1. 파일 객체 생성
		File file = new File(SAVEFILEDIRECTORY + "\\" + fileName);
		LOGGER.info("파일경로: " + file.toString());
		// SAVEFILEDIRECTORY/thumbnail/TN_+파일이름

		// 2. 'Content Type' 명시 & 데이터 파일 반환
		// nio.Files클래스의 probeContentType() : 파라미터로 전달받은 파일의 MIME TYPE을 문자열(Stirng)로 반환
		// FileCopyUtils클래스 : 파일과 stream 복사에 사용할 수 있는 클래스
		// copyToByteArray(file) 메서드 : 대상 파일을 복사하여 Byte 배열로 반환
		ResponseEntity<byte[]> result = null;

		try {
			HttpHeaders responseHeader = new HttpHeaders();

			// 4.. content-type(뷰 body에 보낼 데이터 타입)에 이미지 데이터 타입 명시
			responseHeader.add("Content-type", Files.probeContentType(file.toPath()));

			/**
			 * ResponseEntity(인자1, 인자2, 인자3) 인자1 - 출력시킬 대상 이미지 파일 FilesCopyUtils 클래스 : 파일과
			 * stream 복사에 사용할 수 있는 메서드를 제공 copyToByteArray() 메서드 : 파라미터로 부여하는 File 객체 즉, 대상
			 * 파일을 복사하여 Byte 배열로 반환 인자2 - 'Content-Type' 속성을 지정 해준 HttpHeader 객체(변수 header
			 * 인자3 - status코드
			 */
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), responseHeader, HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}

	  @PostMapping("productIdCheck") 
	  public ResponseEntity IdCheck(String p_id) throws Exception { 
		  LOGGER.info(" M.C - IdCheck() ?몄텧");
	  
		  int result = 0;
		  
		  if(p_id == null || p_id.length() < 7) { 
			  result = -1; 
			  return new ResponseEntity(result, HttpStatus.OK); 
		  }
		  result = productService.idCheck(p_id);

		  LOGGER.info(" result :  "+ result);
		  ResponseEntity entity = null; 
		  entity = new ResponseEntity(result,	HttpStatus.OK); 
		  
		  return entity;
	 }

	/* 이미지 정보 반환 */
	@GetMapping(value = "getAttachList", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AttachImageVO>> getAttachList(int pi_p_id) throws Exception {
		LOGGER.info(" P.C - getAttachList() 호출");
		return new ResponseEntity<List<AttachImageVO>>(attachService.getAttachList(pi_p_id), HttpStatus.OK);
	}

	/* 상품 검색(리스트) */
	@Override
	@GetMapping("productList")
	public String searchGoods(Criteria cri, Model model) throws Exception {
		LOGGER.info(" P.C - searchGoods() 호출");
		LOGGER.info(" cri:  " + cri);

		List<ProductVO> list = productService.getGoodsList(cri);
		LOGGER.info(" Product List : " + list);

		if (!list.isEmpty()) { // list 내용이 있을 때
			model.addAttribute("list", list);
			LOGGER.info(" list : " + list);
		} else {
			model.addAttribute("listcheck", "empty");
			return "product/productList";
		}
		model.addAttribute("pageMaker", new PageVO(cri, productService.goodsGetTotal(cri)));
		return "product/productList";
	}

}
