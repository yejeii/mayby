package com.mayby.pimage.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mayby.admin.controller.adminControllerImpl;
import com.mayby.model.vo.AttachImageVO;
import com.mayby.pimage.domain.AttachMapper;

@Service
public class AttachServiceImpl implements AttachService {

	private static final Logger LOGGER = LogManager.getLogger(adminControllerImpl.class);

	@Autowired
	AttachMapper attachMapper;
	
	@Override
	public List<AttachImageVO> getAttachList(int pi_p_id) throws Exception {
		LOGGER.info(" AI.S - getAttachList()  호출");
		return attachMapper.getAttachList(pi_p_id);
	}

}
