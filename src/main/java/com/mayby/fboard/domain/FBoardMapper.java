package com.mayby.fboard.domain;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mayby.fboard.vo.FBoardVO;
import com.mayby.model.vo.Criteria;

@Mapper
public interface FBoardMapper {
	
	/* 게시글 등록 */
	public void enrollBoard(FBoardVO vo) throws Exception;
	
	/* 전체 게시글 조회(페이징 적용) */
	 public List<FBoardVO> getListPaging(Criteria cri);
	
	/* 전체 게시글 조회 */
	public List<FBoardVO> getBoardList() throws Exception;

	/* 하나의 게시글 조회 */
	public FBoardVO getBoardDetail(int fb_no) throws Exception;

	/* 게시글 수정 */
	public int modifyBoard(FBoardVO vo) throws Exception;
	  
	 /* 게시글 삭제 */
	 public int deleteBoard(int fb_no) throws Exception;
	 
	 /* 게시판 총 갯수 
	  *  keyword 데이터를 전달할 수 있도록 keyword가 담겨있는 Criteria 인자로 준다 
	  **/
	 public int getTotal(Criteria cri);	 
	 
}
