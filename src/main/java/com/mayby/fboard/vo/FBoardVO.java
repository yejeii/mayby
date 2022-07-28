package com.mayby.fboard.vo;

import java.sql.Timestamp;

import org.springframework.stereotype.Repository;

import lombok.Data;

@Data
@Repository
public class FBoardVO {

	/* 글 번호 */
	private int fb_no;	
	
	/* 글 작성자 */
	private String fb_writer;
	
	/* 글 비번 */
//	private String fb_pass;

	/* 글 제목 */
	private String fb_subject;
	
	/* 글 내용 */
	private String fb_content;
	
	/* 첨부 파일 */
//	private String fb_file;
	
	/* 관련글 번호 */
//	private int fb_re_ref;
	
	/* 답글 레벨(깊이) */
//	private int fb_re_lev;
	
	/* 관련글 중 출력 순서 */
//	private int fb_re_step;
	
	/* 조회수 */
	private int fb_readcount;
	
	/*작성일 */
	private Timestamp fb_regdate;
}
