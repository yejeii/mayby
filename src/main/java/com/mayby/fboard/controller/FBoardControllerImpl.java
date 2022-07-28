package com.mayby.fboard.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mayby.fboard.service.FBoardService;
import com.mayby.fboard.service.FBoardServiceImpl;
import com.mayby.fboard.vo.FBoardVO;
import com.mayby.member.vo.MemberVO;
import com.mayby.model.vo.Criteria;
import com.mayby.model.vo.PageVO;

@Controller
@RequestMapping("/fboard/*")
public class FBoardControllerImpl implements FBoardController {

	private static final Logger LOGGER = LogManager.getLogger(FBoardServiceImpl.class);
	private static final String FBOARDSAVEDIRECTORY = "C:\\spring\\mayby\\fboard\\reg_image";

	@Autowired
	FBoardService fs;

	/* 게시판 목록 페이지 접속(페이징 적용) */
    @GetMapping("boardList")
    public void boardList(HttpServletRequest request, HttpSession session, Model model, Criteria cri) {
		LOGGER.info(" F.C - boardList() 호출");
		LOGGER.info(" cri : "+cri);
		request.getSession();
		MemberVO vo = (MemberVO) session.getAttribute("user");
        model.addAttribute("list", fs.getListPaging(cri));
        
        /* 전체 보드 게시글에 따른 페이징 적용 */
        int total = fs.getTotal(cri);
        PageVO pageMaker = new PageVO(cri, total);
        LOGGER.info("pageMaker : "+pageMaker);
        model.addAttribute("pageMaker", pageMaker);
    }
    
	/* 게시판 글쓰기 페이지 */
	@GetMapping("boardEnroll")
	public void getEnrollBoard(HttpServletRequest request, RedirectAttributes rttr, Model model)
			throws Exception {
		LOGGER.info(" F.C - getEnrollBoard() 호출");

		try {
			HttpSession session = request.getSession();
			MemberVO vo = (MemberVO) session.getAttribute("user");
			if (vo == null) {
				rttr.addAttribute("enroll_login", "denied");
			} else {
				model.addAttribute("userID", vo.getM_id());
				rttr.addAttribute("enroll_login", "access");
			}
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			rttr.addAttribute("enroll_login", "server error");
		}
		
	}

	/* 게시판 글 등록 처리 */
	@PostMapping("boardEnrollPro")
	public String getEnrollBoardPro(FBoardVO fvo, HttpServletRequest request,HttpSession session, RedirectAttributes rttr) throws Exception {
		LOGGER.info(" F.C - getEnrollBoardPro() 호출");
		LOGGER.info(" fvo : "+fvo);
		
		try {
			request.getSession();
			/*MemberVO vo = (MemberVO) session.getAttribute("user");
			if (vo == null) {
				rttr.addFlashAttribute("result", "access denied");
			} else {*/
				fs.enrollBoard(fvo);
				rttr.addFlashAttribute("result", "enroll success");
			/*}*/
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			rttr.addFlashAttribute("result", "server error");
		}
		// redirect - '등록', '수정', '삭제' 작업이 처리 된 후 "새로고침"을 통해 동일한 내용을 계속 서버에 등록할 수 없게 하기 위함
		return "redirect:/fboard/boardList";
	}

	/* 상세 페이지  - 건네준 파라미터 저장 */
	@GetMapping("boardDetail")
	public void getBoardDetail(@RequestParam("fb_no") int fb_no, Criteria cri, HttpServletRequest request, HttpSession session,
			Model model) throws Exception {
		LOGGER.info(" F.C - getBoardDetail() 호출");
		request.getSession();
		session.getAttribute("user"); // 클라에 세션 던져줌
		FBoardVO vo = fs.getBoardDetail(fb_no);
		model.addAttribute("board", vo);
		model.addAttribute("cri", cri);
	}

	/* 수정 페이지 */
	@GetMapping("boardModify")
	public void boardModify(int fb_no, Criteria cri, Model model) throws Exception {
		LOGGER.info(" F.C - boardModify() 호출");
		model.addAttribute("board", fs.getBoardDetail(fb_no));
		model.addAttribute("cri", cri);
	}
	
	/* 게시판 수정 처리 */
	@PostMapping("boardModify")
    public String boardModifyPro(FBoardVO vo, RedirectAttributes rttr) throws Exception {
		LOGGER.info(" F.C - boardModifyPro() 호출");
        fs.modifyBoard(vo);
        rttr.addFlashAttribute("result", "modify success");
        return "redirect:/fboard/boardList";
        
    }
	
	/* 게시판 삭제 처리 */
	@PostMapping("boardDelete")
    public String boardDeletePro(int fb_no, RedirectAttributes rttr) throws Exception {
		LOGGER.info(" F.C - boardDelete() 호출");
        fs.deleteBoard(fb_no);
        rttr.addFlashAttribute("result", "delete success");
        return "redirect:/fboard/boardList";
	}
	
	
	
	
}
