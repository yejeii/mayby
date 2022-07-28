package com.mayby.reply.service;

import java.util.List;

import com.mayby.reply.vo.ReplyVO;

public interface ReplyService {
	
	List<ReplyVO> list(Integer r_b_id) throws Exception;

	void create(ReplyVO vo) throws Exception;
	
    void update(ReplyVO vo) throws Exception;

    void delete(Integer r_no) throws Exception;


}
