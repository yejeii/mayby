package com.mayby.pimage.domain;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mayby.model.vo.AttachImageVO;

@Mapper
public interface AttachMapper {

	/* 이미지 데이터 반환 */
	public List<AttachImageVO> getAttachList(int pi_p_id);
}
