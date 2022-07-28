package com.mayby.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mayby.member.service.MemberService;
import com.mayby.member.vo.MemberVO;

@Controller
@RequestMapping("/member/*")
public class MemberControllerImpl implements MemberController{

	private static final Logger LOGGER = LogManager.getLogger(MemberControllerImpl.class);

	@Autowired
	MemberService memberService;
	
	@Override
	@GetMapping("login")
	public String login() {
		LOGGER.info(" M.C - login() 호출, 로그인 페이지 이동");
		return "member/login";
	}

	@PostMapping("loginPro")
	public String loginPro(@ModelAttribute("member") MemberVO vo
			, HttpServletRequest request, HttpServletResponse response, RedirectAttributes rttr) throws Exception{
		LOGGER.info(" M.C - loginPro() 호출, 로그인 처리");
		// LOGGER.info(vo);

		try {
			// dB 데이터와 비교
			MemberVO member = memberService.memberLogin(vo);
			if(member == null) {
				LOGGER.info(" M.C - 회원정보 X");
				rttr.addFlashAttribute("login_result", "failed");
				return "redirect:login";
			}
			else {
				LOGGER.info(" M.C - 회원정보 O");
				HttpSession session = request.getSession();
				session.setAttribute("user", member);
				rttr.addFlashAttribute("login_result", "success");
				return "redirect:/index";
			}
		} catch(Exception e) {
			LOGGER.info(" M.C - 처리 중 ERROR");
			LOGGER.error("Error occured at Memeber Register", e);
			rttr.addFlashAttribute("login_result", "server error");
		}
		return "redirect:/member/login";
	}
	
	@Override
	@GetMapping("register")
	public String register() {
		LOGGER.info(" M.C - register() 호출, 회원가입 페이지 이동");
		return "member/register";
	}

	@PostMapping("memberIdCheck")
	public ResponseEntity IdCheck(String m_id) throws Exception {
		LOGGER.info(" M.C - IdCheck() 호출");
		
		int result = 0;
		
		// 유효성 검사
		if(m_id == null || m_id.length()<6) {
			result = -1;
			return new ResponseEntity(result, HttpStatus.OK);
		}
		
		result = memberService.checkId(m_id);
		LOGGER.info(" M.C - IdCheck() 결과값 = "+ result);
		
		ResponseEntity entity = null;
		entity = new ResponseEntity(result, HttpStatus.OK);
		return entity;
	}
	
	@PostMapping("registerPro")
	public ResponseEntity registerPro(@ModelAttribute("memberVO") MemberVO vo,
															HttpServletRequest request, HttpServletResponse response) {
		
		
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		String message = null;
		ResponseEntity entity = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset-utf-8");
		
		try {
			memberService.register(vo);
			
			message = "<script>";
				message += "alert('회원가입이 정상적으로 처리되었습니다. 로그인창으로 이동합니다.');";
				message += "location.href='"+request.getContextPath()+"/member/login';";		
			message += "</script>";
		} catch (Exception e) {
			message  = "<script>";
			    message +=" alert('회원가입 처리 중 오류가 발생했습니다. 메인 페이지로 돌아갑니다');";
			    message += " location.href='"+request.getContextPath()+"/';";
		    message += " </script>";
			LOGGER.error("Error occured at Memeber Register", e);
		}
		entity = new ResponseEntity(message, responseHeaders, HttpStatus.OK);
		return entity;
	}

	// @Override
	@GetMapping("myPage")
	public void myPage(@SessionAttribute("user") MemberVO vo, Model model) throws Exception {
		LOGGER.info(" M.C - myPage() 호출");
		MemberVO member = memberService.getMember(vo);
		model.addAttribute("member", member);
		
	}

	@GetMapping("logout")
	public ResponseEntity logout(HttpSession session) {
		LOGGER.info(" M.C - logout() 호출");
		
		HttpHeaders header = new HttpHeaders();
		header.add("Content-Type", "text/html; charset=utf-8");
		String message = null;

		try {
		session.invalidate();
		message += "<script>";
			message += "alert('정상적으로 로그아웃되었습니다!');";
			message += "location.href='/';";
		message += "</script>";

		} catch(Exception e) {
			message += "<script>";
				message += "alert('접근할 수 권한입니다. ');";
				message += "history.back(-1);";
			message += "</script>";
			LOGGER.error("Error occured at Memeber Register", e);
		}
		return new ResponseEntity(message, header, HttpStatus.OK);
	}

	// 회원 정보 수정 페이지
	// Override
	@GetMapping("memberModify")
	public void memberModify(HttpSession session, HttpServletRequest request, MemberVO vo, Model model) throws Exception {
		LOGGER.info(" M.C - memberUpdate() 호출");
		session = request.getSession();
		vo = (MemberVO) session.getAttribute("user");
		MemberVO member = memberService.getMemberById(vo.getM_id());
		model.addAttribute("member", member);
	}

	/* 회원 정보 수정 처리 */
	@Override
	@PostMapping("memberModifyPro")
	@ResponseBody
	public String memberModifyPro(@RequestBody MemberVO vo, HttpServletRequest request, HttpSession session) throws Exception {
		LOGGER.info(" M.C - memberModifyPro() 호출");
		LOGGER.info(" vo : "+vo.toString());
		String data = null;
		MemberVO member = (MemberVO)session.getAttribute("user");
		LOGGER.info(" user : "+member.toString());
		if(member.getM_id().equals(vo.getM_id())) {		// 동일한 유저 세션
			int result = memberService.memberUpdate(vo);
			LOGGER.info("result : "+result);
			if(result == 1) {		// 변경 성공
				data = "success"; 
			} else { 
				data = "fail";		// 디비 처리 실패
			}
		} else {	// 동일한 유저 x
			data = "not same user";
		}
		return data; 
	}
	
	/* 회원 탈퇴 페이지 */
	@Override
	@GetMapping("memberDelete")
	public void memberDelete(HttpSession session, MemberVO vo) throws Exception {
		LOGGER.info(" M.C - memberDelete() 호출");
		MemberVO VO = (MemberVO) session.getAttribute("user");
	}

	/* 회원 탈퇴 처리 */
	@Override
	@PostMapping("memberDelete")
	public ResponseEntity memberDeletePro(HttpSession session, HttpServletRequest request, MemberVO vo) throws Exception {
		LOGGER.info(" M.C - memberDeletePro() 호출");

		HttpHeaders header = new HttpHeaders();
		header.add("Content-Type", "text/html; charset=utf-8");
		String message = null;

		LOGGER.info("vo :" + vo.toString());        
		String result = memberService.getCheck(vo);
		LOGGER.info("result :" + result );
		try {
			if (result != null) { 		// 아이디, 비번 O
				memberService.memberDelete(vo);
				request.getSession();
				session.invalidate();
				message += "<script>";
				message += "alert('정상적으로 탈퇴 처리되었습니다!');";                  
				message += "location.href='/';";
				message += "</script>";
				
			} else if(result == null) { 		// 아이디 or 비번 wrong
				message += "<script>";
				message += "alert('아이디 또는 비밀번호가 다릅니다');";                  
				message += "history.back(-1);";
				message += "</script>";
				}
			
		} catch (Exception e) { 	// 서버 오류
			message += "alert('처리 도중 문제가 발생했습니다. ');";
			message += "history.back(-1);";
			message += "</script>";
			LOGGER.error("Error occured at Memeber Register", e);

		}
		return new ResponseEntity(message, header, HttpStatus.OK);
		
	}

	
}
