package com.mayby.fboard.service;

import java.util.List;

import com.mayby.fboard.vo.FBoardVO;
import com.mayby.model.vo.Criteria;

public interface FBoardService {

	/* 게시글 등록 */
	public void enrollBoard(FBoardVO vo) throws Exception;

    /* 게시판 목록(페이징 적용) */
    public List<FBoardVO> getListPaging(Criteria cri);
    
	/* 전체 게시글 조회 */
	public List<FBoardVO> getBoardList() throws Exception;
	
	/* 게시글 하나 조회 */
	public FBoardVO getBoardDetail(int fb_no) throws Exception;

	/* 게시글 수정 */
	public int modifyBoard(FBoardVO vo) throws Exception;
	
	/* 게시글 삭제 */
	public int deleteBoard(int fb_no) throws Exception;
	
	/* 게시판 총 갯수 */
    public int getTotal(Criteria cri);
}
