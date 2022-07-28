package com.mayby.reply.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mayby.reply.domain.ReplyMapper;
import com.mayby.reply.vo.ReplyVO;

@Service
public class ReplyServiceImpl implements ReplyService{

	private static final Logger LOGGER = LogManager.getLogger(ReplyServiceImpl.class);
	
	@Autowired
	ReplyMapper replyMapper;


	@Override
	public List<ReplyVO> list(Integer r_b_id) throws Exception {
		LOGGER.info(" R.S - list() 호출");
		return replyMapper.list(r_b_id);
	}

	@Override
	public void create(ReplyVO vo) throws Exception{
		LOGGER.info(" R.S - create() 호출");
		replyMapper.create(vo);
		
	}

	@Override
	public void update(ReplyVO vo) throws Exception{
		LOGGER.info(" R.S - update() 호출");
		replyMapper.update(vo);
	}

	@Override
	public void delete(Integer r_no) throws Exception {
		LOGGER.info(" R.S - delete() 호출");
		 replyMapper.delete(r_no);
	}
}
