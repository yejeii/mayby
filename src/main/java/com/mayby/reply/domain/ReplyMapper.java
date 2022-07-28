package com.mayby.reply.domain;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mayby.reply.vo.ReplyVO;

@Mapper
public interface ReplyMapper {

	public List<ReplyVO> list(Integer r_b_id) throws Exception;

	public void create(ReplyVO vo) throws Exception;
	
	public void update(ReplyVO vo) throws Exception;
	
	public void delete(Integer r_no)throws Exception;
}
