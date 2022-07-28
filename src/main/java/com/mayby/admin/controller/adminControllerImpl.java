package com.mayby.admin.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mayby.admin.service.AdminService;
import com.mayby.admin.vo.AdminVO;
import com.mayby.category.vo.CategoryVO;
import com.mayby.member.vo.MemberVO;
import com.mayby.model.vo.AttachImageVO;
import com.mayby.model.vo.Criteria;
import com.mayby.model.vo.MMCriteria;
import com.mayby.model.vo.MMPageVO;
import com.mayby.model.vo.PageVO;
import com.mayby.product.service.ProductService;
import com.mayby.product.vo.ProductVO;

import net.coobird.thumbnailator.Thumbnails;

@Controller
@RequestMapping("admin/*")
public class adminControllerImpl implements adminController{
	
	private static final Logger LOGGER = LogManager.getLogger(adminControllerImpl.class);
	private static final String SAVEFILEDIRECTORY = "C:\\spring\\mayby\\product\\reg_image";
	
	@Autowired
	AdminService adminService;
	
	@Autowired
	ProductService productService;
	
	@Value("${spring.servlet.multipart.location}")
	private String tempFileDirectory;
	
	@Override
	@PostMapping("loginPro")
	@ResponseBody
	public String loginPro(@RequestBody Map<String, String> param
									, HttpServletRequest request, Model model )throws Exception {
		LOGGER.info(" A.C - loginPro() 호출");
		LOGGER.info(" A.C - admin: "+ param.get("inputId") + ", "+ param.get("inputPw"));
		
		AdminVO vo = new AdminVO();
		vo.setA_id(param.get("inputId"));
		vo.setA_pw(param.get("inputPw"));
		String result = null;

		// DB와 비교
		AdminVO admin = adminService.loginPro(vo);
		if(admin == null) {
			LOGGER.info(" A.C - logingPro() 결과: 계정 X");
			result = "fail";
		}
		else {
			LOGGER.info(" A.C - logingPro() 결과: 계정 O");
			// 세션 초기화
			HttpSession  session = request.getSession();
			session.setAttribute("admin", admin.getA_id());
			result = "success";
		}
		
	return result;
	}

	@GetMapping("logout")
	public ResponseEntity logout(HttpSession session) {
		LOGGER.info(" A.C - logout() 호출");
		
		HttpHeaders header = new HttpHeaders();
		header.add("Content-Type", "text/html; charset=utf-8");
		String message = null;

		if(session.getAttribute("admin") != null) {
			session.invalidate();
			message += "<script>";
				message += "alert('정상적으로 로그아웃되었습니다!');";
				message += "location.href='/';";
			message += "</script>";

			return new ResponseEntity(message, header, HttpStatus.OK);
		} else {
			message += "<script>";
				message += "alert('접근할 수 권한입니다. ');";
				message += "history.back(-1);";
			message += "</script>";
		}
		return new ResponseEntity(message, header, HttpStatus.BAD_REQUEST);
	}
	
	@Override
	@GetMapping("main")
	public String adminpage(HttpServletRequest request) throws Exception {
		LOGGER.info(" A.C - adminpage() 호출");
		HttpSession session = request.getSession();
		session.getAttribute("admin");
		return "admin/main";
	}

	// 상품 등록 페이지 접속
	@Override
	@GetMapping("goodsEnroll")
	public String goodsEnroll(HttpServletRequest request, Model model) throws Exception {
		LOGGER.info(" A.C - goodsEnroll() 호출");
		
		HttpSession session = request.getSession();
		session.getAttribute("admin");
		
		// 카테고리 리스트 데이터를 담은 객체를 JSON 형식의 String 데이터로 변환해 뷰페이지에 전달할 예정
		// 1. pom.xml에 Jackson-datafind 라이브러리 추가
		// 2.  ObjectMapper클래스를 인스턴스화하여 사용
		ObjectMapper objMapper = new ObjectMapper();
		// 3. 카테고리 리스트(전달할 데이터) 가져오기
		List<CategoryVO> list = adminService.getCategoryList();
		// 4. writeValueAsString() - Java 객체를 String타입의  JSON형식 데이터로 변환
		String cateList = objMapper.writeValueAsString(list);
		// 5. 뷰로 데이터를 넘겨주기 위해 Model 객체의 addAttribute() 사용
		model.addAttribute("cateList", cateList);
		
		LOGGER.info(" A.C - 카테고리 리스트 변경 전 :  "+list);
		LOGGER.info(" A.C - 카테고리 리스트 변경 후 :  "+cateList);
		return "admin/goodsEnroll";
	}
	
	@Override
	@GetMapping("goodsEnrollInfo")
	public String goodsEnrollInfo(HttpServletRequest request) throws Exception {
		LOGGER.info(" A.C - goodsEnrollInfo() 호출");
		
		HttpSession session = request.getSession();
		session.getAttribute("admin");
		return "admin/goodsEnrollInfo";
	}

	@Override
	@PostMapping("goodsEnrollPro")
	public String goodsEnrollPro(HttpServletRequest request, RedirectAttributes rttr) throws Exception {
		LOGGER.info(" A.C - goodsEnrollPro() 호출");
		
		HttpSession session = request.getSession();
		session.getAttribute("admin");
		
		String p_size = "";
		String p_color = "";
		
		// vo의 필드 데이터타입에는 배열 형식 지정 불가 
		// -> @ModelAttribute("param") ProductVO vo 로 받을 수 없다(나의 정보력 한계....)
		// -> 하나씩 request로 받아서 처리
		String p_id = request.getParameter("p_id");
		String p_name = request.getParameter("p_name");
		String p_catecode = request.getParameter("p_catecode");
		String size[] = request.getParameterValues("p_size");
		String color[] = request.getParameterValues("p_color");
		int p_price = Integer.parseInt(request.getParameter("p_price"));
		double p_discount = Double.parseDouble(request.getParameter("p_discount"));
		int p_amount = Integer.parseInt(request.getParameter("p_amount"));
		String p_fabric = request.getParameter("p_fabric");
		String p_information = request.getParameter("p_information");
		String pi_fileName = request.getParameter("imageList[0].pi_fileName");
		String pi_uploadPath = request.getParameter("imageList[0].pi_uploadPath");
		String pi_uuid = request.getParameter("imageList[0].pi_uuid");
		
		
		
		
		AttachImageVO imageVO = new AttachImageVO();
		 imageVO.setPi_fileName(pi_fileName); 
		 imageVO.setPi_uploadPath(pi_uploadPath);
		 imageVO.setPi_uuid(pi_uuid);

		List<AttachImageVO> imageList = new ArrayList<>(); imageList.add( imageVO);
		LOGGER.info(" imageList : " + imageList.toString()); 
		
		
		for(String all_size: size) {
			p_size += all_size + ",";
		}
		for(String all_color: color) {
			p_color += all_color + ",";
		}

		// 마지막 콤마(,) 삭제 처리
		p_size = p_size.substring(0, p_size.length()-1);
		p_color = p_color.substring(0, p_color.length()-1);
		
		
		// ProductVO에 넣기
		ProductVO vo = new ProductVO();
		vo.setP_id(p_id);
		vo.setP_name(p_name);
		vo.setP_size(p_size);
		vo.setP_price(p_price);
		vo.setP_discount(p_discount);
		vo.setP_color(p_color);
		vo.setP_amount(p_amount);
		vo.setP_catecode(p_catecode);
		vo.setP_fabric(p_fabric);
		vo.setP_information(p_information);
		vo.setImageList(imageList);

		LOGGER.info("저장될 ProductVO : "+vo.toString());

		//LOGGER.info(" A.C - DB에 저장될 상품 정보: "+vo.toString());
		productService.productEnroll(vo);

		String enrolled_id = vo.getP_id();	
		// DirectAttribute의 addAttribute 메서드
		// -> addFlashAttribute와 다르게 URL뒤에 데이터가 붙어서 전달됨(GET 방식)
	    rttr.addFlashAttribute("p_id", enrolled_id);	
	    
	    // 세션에 저장 - 과연 필요한지.. 나중에 다시 확인!
		/*
		 * HttpSession session = request.getSession(); session.setAttribute("enrolled",
		 * p_id);
		 */
	    
	    return "redirect:goodsManage";
	}
	
	// 상품 관리 페이지
	@Override
	@GetMapping("goodsManage")
	public void goodsManage(HttpServletRequest request, Criteria cri, Model model) throws Exception {
		LOGGER.info(" A.C - goodsManage() 호출");
		
		HttpSession session = request.getSession();
		session.getAttribute("admin");
		
		/* 상품 리스트 데이터 */
		List list = adminService.getProductList(cri);
		LOGGER.info(" list : "+list);
		
		if(! list.isEmpty()) {	// 비어있지 않다면
			model.addAttribute("list", list);
		} else {
			model.addAttribute("listCheck", "empty");
			return;
		}
		
		/* 페이지 이동 인터페이스 데이터 */
		int total = adminService.getProductCnt(cri);
		LOGGER.info(" total page : "+total);
		PageVO pagemaker = new PageVO(cri, total);
		model.addAttribute("pageMaker", pagemaker);
		
		LOGGER.info(" A.C - 상품 List, 페이징 처리 뷰로 전송");
	}

	// 상품 조회, 수정 페이지
	@Override
	@GetMapping({"goodsDetail", "goodsModify"})
	public void goodsDetail(HttpServletRequest request, String p_id, Criteria cri, Model model) throws Exception, JsonProcessingException {
		LOGGER.info(" A.C - goodsDetail() 호출");
		
		HttpSession session = request.getSession();
		session.getAttribute("admin");
		
		ObjectMapper objMapper = new ObjectMapper();
		
		/* 카테고리 리스트 데이터 */
		model.addAttribute("cateList", objMapper.writeValueAsString(adminService.getCategoryList()));
		
		/* 목록 페이지 조건 정보 */
		model.addAttribute("cri", cri);
		
		/* 조회 페이지 정보 */
		model.addAttribute("goodsInfo", adminService.getProductDetail(p_id));
		
	}
	
	// 상품 수정 처리
	@Override
	@PostMapping("goodsModifyPro")
	public String goodsModifyPro(HttpServletRequest request, ProductVO vo, RedirectAttributes rttr) throws Exception {
		LOGGER.info(" A.C - goodsModifyPro() 호출");
		
		HttpSession session = request.getSession();
		session.getAttribute("admin");
		
		int result = adminService.goodsModify(vo);
		rttr.addFlashAttribute("modify_result", result);
		
		return "redirect:/admin/goodsManage";		
	}
	
	// 상품 삭제 처리
	@PostMapping("goodsDelete")
	@Override
	public String goodsDeletePro(HttpServletRequest request, String p_id, RedirectAttributes rttr) throws Exception {
		LOGGER.info(" A.C - goodsDeletePro() 호출");
		
		HttpSession session = request.getSession();
		session.getAttribute("admin");
		
		int result = adminService.goodsDelete(p_id);
		rttr.addFlashAttribute("delete_result", result);
		return "redirect:/admin/goodsManage";
	}
	
	// 상품 이미지 처리
	// 경로에 잇을 한글 처리 깨짐 방지를 위해 서버에서 뷰로 돌아올 때 인코딩 처리 produces = MediaType.APPLICATION_JSON_VALUE
	@Override
	@PostMapping(value="uploadFilePro", produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<List<AttachImageVO>> uploadFilePro(HttpServletRequest request, MultipartFile[] uploadFile) throws Exception {
		LOGGER.info(" A.C - uploadFilePro() 호출");
		
		HttpSession session = request.getSession();
		session.getAttribute("admin");
		
		/* 들어온 파일 MIME TYPE 체크(최후 유효성 검사) */
		for(MultipartFile multipartFile: uploadFile) {
			File checkfile = new File(multipartFile.getOriginalFilename());		
			String type = null;
			/*  java.nio.file.Files 클래스의 probeContentType() 메서드
			 *   파라미터로 전달받은 파일의 MIME TYPE을 문자열(Stirng)로 반환해주는 메서드
			 *   파라미터로는 Path 객체를 전달받아야. -> File 클래스의 toPath() 사용
			 */
			type = Files.probeContentType(checkfile.toPath());
			LOGGER.info("MIME TYPE : " + type);
			
			// MIME TYPE이 이미지일 경우 MIME TYPE 값
			// image/gif, image/png, image/jpeg, image/bmp, image/webp
			if(! type.startsWith("image")) {	// 이미지가 아니라면
				List<AttachImageVO> list = null;
				return new ResponseEntity<>(list, HttpStatus.BAD_REQUEST);
			}
		} // for
		
		/**
		 * 이미지 파일 서버에 저장 순서
		 * 1. 이미지 파일 저장
		 *    1. 저장 경로 지정
		 *    2. 저장될 폴더 생성
		 *    3. 파일 저장
		 * 2, 썸네일 이미지 파일 생성 및 저장
		 * 	   1. 저장 경로 지정
		 *    2. 저장될 폴더 생성
		 *    3. 파일 저장
		 * 3. 각 이미지 정보 List 객체에 저장
		 * 4. 상태 코드가 200 & http Body에 이미지 정보가 담긴 List 객체 전송
		*/
		// 1-1. SAVEFILEDIRECTORY
		String regDate = new SimpleDateFormat("yy.MM.dd").format(new Date());
		String datePath = regDate.replace("-", File.separator);
		
		// 1-2. 
		File uploadPath = new File(SAVEFILEDIRECTORY, datePath);
			// 디렉터리(경로 파일) 생성
			if(!uploadPath.exists()) {
				uploadPath.mkdirs();
			}	// 이까지 클라에서 이미지 선택하면 지정 경로가 서버에 생성됨!
			
		/* 이미저 정보 담는 객체 */
		List<AttachImageVO> list = new ArrayList();	
			
		// 1-3.
		for(MultipartFile multipartFile : uploadFile) {
			LOGGER.info("-----------------------------------------------");
			LOGGER.info("파일 이름 : " + multipartFile.getOriginalFilename());
			LOGGER.info("파일 타입 : " + multipartFile.getContentType());
			LOGGER.info("파일 크기 : " + multipartFile.getSize());			

			/* 이미지 정보 객체 */
			AttachImageVO vo = new AttachImageVO();
		
			/* 파일 이름 */
			String uploadFileName = multipartFile.getOriginalFilename();			
			vo.setPi_fileName(uploadFileName);
			vo.setPi_uploadPath(datePath);	// 22.07.14
			
			/* uuid 적용 파일 이름 */
			// 업무에서 사용하는 리눅스, UNIX는 한글지원이 안 되는 운영체제 
			// 파일업로드시 파일명은 ASCII코드로 저장되므로, 한글명으로 저장 필요
			// UUID클래스 - (특수문자를 포함한)문자를 랜덤으로 생성 
			String uuid = UUID.randomUUID().toString();
			uploadFileName = uuid + "_" + uploadFileName;
			vo.setPi_uuid(uuid);
			
			/* 파일 위치, 파일 이름을 합친 File 객체 */
			File saveFile = new File(uploadPath, uploadFileName);	
			
			/* 파일 저장 */
			try {
				multipartFile.transferTo(saveFile);
				
				/* 2. 썸네일 저장 */
				File thumbFilePath = new File(uploadPath+"\\thumbnail");
				if(! thumbFilePath.exists()) {
					thumbFilePath.mkdirs();
				}
				// 파일 이름의 객체
				File thumbnailFile = new File(thumbFilePath, "TN_" + uploadFileName);				
				
				// 이미지 읽어오기
				// ImageIO 클래스 - 이미지를 읽어오거나 생성(작성?) 할 수 있도록 도와주는 메서드 소유
				// BufferedImage - 이미지 데이터를 처리하거나 조작에 필요한 값과 메서드를 제공
				BufferedImage bo_image = ImageIO.read(saveFile);
				
				int newWidth = 650;
				int newHeight = 850;
				
				// 이미지 가로,세로 측정
				int originWidth = bo_image.getWidth();
				int origintHeight = bo_image.getHeight();
				
				if(origintHeight > newHeight) {
					// 높이 기준으로 이미지 비율 축소
					double ratio = (double)newHeight/(double)origintHeight;
					int chagedWidth = (int)(originWidth * ratio);
					int chagedHeight = (int)(origintHeight * ratio);
				
					Thumbnails.of(saveFile)
			        .size(chagedWidth, chagedHeight)
			        .toFile(thumbnailFile);
				} else {
					Thumbnails.of(saveFile)
			        .size(originWidth, origintHeight)
			        .toFile(thumbnailFile);
				}
				/*
				 * //비율 double 
				 * ratio = 3; 
				 * //넓이 높이 
				 * int width = (int) (bo_image.getWidth() / ratio); 
				 * int height = (int) (bo_image.getHeight() / ratio);
				 * Thumbnails.of(saveFile) .size(width, height) .toFile(thumbnailFile);
				 */
				
			} catch (Exception e) {
				e.printStackTrace();
			} 
			
			// 3.
			/* AttachImageVO객체를 List의 요소로 추가 */
			list.add(vo);
		}	// for
		
		// 4.
		ResponseEntity<List<AttachImageVO>> result = new ResponseEntity<List<AttachImageVO>>(list, HttpStatus.OK);
		return result;	
	}

	// 상품 이미지 파일 삭제
	@Override
	@PostMapping("deleteFile")
	public ResponseEntity<String> deleteFile(HttpServletRequest request, String fileName) throws Exception {
		LOGGER.info(" A.C - deleteFile() 호출");
		
		HttpSession session = request.getSession();
		session.getAttribute("admin");
		
		// fileName : 22.07.14%5Cthumbnail%2.....jpg
		File file = null;
		
		try {
			// 1. UTF-8으로 인코딩된 경로를 디코딩 처리
			// 구분자(한글 이름 포함)가 "%5", "%2F" 따위의 문자로 변경되어 있음
			// ->  URL Decoder 클래스의 decode() 메서드로 다시 / or \ 구분자로 디코딩 해준다
			file = new File(SAVEFILEDIRECTORY + "\\"+ URLDecoder.decode(fileName, "UTF-8"));
			//LOGGER.info("삭제할 파일: "+file.toString());
			/* 파일 삭제 */
			file.delete();
			
			/* 원본 파일 삭제 */
			// getAbsolutePath() :  대상 File 객체의 경로를 문자열(String) 타입의 데이터로 반환
			String thumbnailFileName = file.getAbsolutePath();
			//LOGGER.info( "썸네일 파일: "+thumbnailFileName);
			String originFileName = thumbnailFileName.replace("\\thumbnail\\TN_", "\\");
			// String originFileName = file.getAbsolutePath().replace("TN_", "");
			LOGGER.info("originFileName : " + originFileName);
			file = new File(originFileName);
			file.delete();
			
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("fail", HttpStatus.NOT_IMPLEMENTED);
		}
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}

	/* 회원 목록 조회 */
	@Override
	@GetMapping("memberManage")
	public void memberManage(HttpServletRequest request, MMCriteria cri, Model model) throws Exception {
		LOGGER.info(" A.C - memberManage() 호출");
		
		HttpSession session = request.getSession();
		session.getAttribute("admin");
		
		List<MemberVO> list =  adminService.getMemberList(cri);
		LOGGER.info(" memberList : "+list.toString());
		if(! list.isEmpty()) {	// 비어있지 않다면
			model.addAttribute("memberList", list);
		} else {
			model.addAttribute("listCheck", "empty");
			return;
		}
		
		/* 페이지 이동 인터페이스 데이터 */
		int total = adminService.getMemberCnt(cri);	// 회원 수
		MMPageVO pagemaker = new MMPageVO(cri, total);
		model.addAttribute("pageMaker", pagemaker);
		
		LOGGER.info(" A.C - 회원 List, 페이징 처리 뷰로 전송");
		
	}

	
}
