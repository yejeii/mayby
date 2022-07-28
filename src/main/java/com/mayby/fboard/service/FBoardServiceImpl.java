package com.mayby.fboard.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mayby.fboard.domain.FBoardMapper;
import com.mayby.fboard.vo.FBoardVO;
import com.mayby.model.vo.Criteria;


@Service
public class FBoardServiceImpl implements FBoardService {

	private static final Logger LOGGER = LogManager.getLogger(FBoardServiceImpl.class);
	
	@Autowired
	FBoardMapper fm;
	
	/* 글 등록 */
	@Override
	public void enrollBoard(FBoardVO vo) throws Exception {
		LOGGER.info(" F.S - enrollBoard() 호출");
		fm.enrollBoard(vo);
	}
	
    /* 게시판 목록(페이징 적용) */
	@Override
	public List<FBoardVO> getListPaging(Criteria cri) {
		LOGGER.info(" F.S - getListPaging() 호출");
		return fm.getListPaging(cri);
	}
	
	/* 전체 게시글 조회 */
	@Override
	public List<FBoardVO> getBoardList() throws Exception {
		LOGGER.info(" F.S - getBoardList() 호출");
		return fm.getBoardList();
	}
	
	/* 하나의 게시글 조회 */
	@Override
	public FBoardVO getBoardDetail(int fb_no) throws Exception {
		LOGGER.info(" F.S - getBoardDetail() 호출");
		return fm.getBoardDetail(fb_no);
	}


	/* 글 수정 */
	@Override
	public int modifyBoard(FBoardVO vo) throws Exception {
		LOGGER.info(" F.S - modifyBoard() 호출");
		return fm.modifyBoard(vo);
		
	}

	/* 글 삭제 */
	@Override
	public int deleteBoard(int fb_no) throws Exception {
		LOGGER.info(" F.S - deleteBoard() 호출");
		return fm.deleteBoard(fb_no);
		
	}

	 /* 게시물 총 갯수 */
    @Override
    public int getTotal(Criteria cri) {
		LOGGER.info(" F.S - getTotal() 호출");
        return fm.getTotal(cri);
    } 

}
