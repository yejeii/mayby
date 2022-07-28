package com.mayby.pimage.service;

import java.util.List;

import com.mayby.model.vo.AttachImageVO;

public interface AttachService {

	/* 이미지 데이터 반환 */
	public List<AttachImageVO> getAttachList(int pi_p_id) throws Exception;
	
}
