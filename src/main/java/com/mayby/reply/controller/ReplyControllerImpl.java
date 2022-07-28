package com.mayby.reply.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mayby.reply.service.ReplyService;
import com.mayby.reply.vo.ReplyVO;

@RestController
@RequestMapping("/reply")
public class ReplyControllerImpl implements ReplyController {
	
	private static final Logger LOGGER = LogManager.getLogger(ReplyControllerImpl.class);
	
	@Autowired
	ReplyService replyService;
	
	
	@Override
	@RequestMapping(value="", method=RequestMethod.POST)
	public ResponseEntity<String> register(@RequestBody ReplyVO vo ){
		LOGGER.info(" R.C - register() 호출");
		
		ResponseEntity<String> entity = null;
		try {
			replyService.create(vo);
			entity = new ResponseEntity<String>("success", HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	@Override
	@RequestMapping(value ="/{r_no}", method= {RequestMethod.PUT, RequestMethod.PATCH})
	public ResponseEntity<String> update(@PathVariable("r_no") Integer r_no, @RequestBody ReplyVO vo, HttpSession session)throws Exception{
		LOGGER.info(" R.C - update() 호출");
		
		ResponseEntity<String> entity = null;
		try {
			vo.setR_no(r_no);
			session.removeAttribute("m_id");
			replyService.update(vo);
			entity = new ResponseEntity<String>("success", HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
		
	}
	
	@Override
	@RequestMapping(value ="/all/{r_b_id}", method= RequestMethod.GET)
	public ResponseEntity<List<ReplyVO>>list(@PathVariable("r_b_id") Integer r_b_id){
		LOGGER.info(" R.C - list() 호출");
		
		ResponseEntity<List<ReplyVO>> entity = null;
		
		try {
			entity = new ResponseEntity<List<ReplyVO>>(replyService.list(r_b_id),HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<List<ReplyVO>>(HttpStatus.BAD_REQUEST);
		}
		return entity;
		}
	
	@Override
	@RequestMapping(value ="/{r_no}", method= RequestMethod.DELETE)
	public ResponseEntity<String> delete(@PathVariable("r_no") Integer r_no){
		LOGGER.info(" R.C - delete() 호출");
		
		ResponseEntity<String>entity = null;
		try {
			replyService.delete(r_no);
			entity = new ResponseEntity<String>("success",HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
}
