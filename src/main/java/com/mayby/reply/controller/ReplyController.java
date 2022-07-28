package com.mayby.reply.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;

import com.mayby.reply.vo.ReplyVO;

public interface ReplyController {

	public ResponseEntity<String> register(ReplyVO vo ) throws Exception;
	
	public ResponseEntity<String> update(Integer r_no, ReplyVO vo, HttpSession session) throws Exception;
	
	public ResponseEntity<List<ReplyVO>>list(Integer r_b_id) throws Exception;
	
	public ResponseEntity<String> delete(Integer r_no) throws Exception;
	
}
