package com.omg.comutting.contoller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.omg.cmn.Message;
import com.omg.commuting.domain.Commuting;
import com.omg.commuting.service.CommutingService;



@Controller
public class CommutingController {
	
	final Logger LOG = LoggerFactory.getLogger(CommutingController.class);
	
	@Autowired
	CommutingService commutingService;
	
	@Autowired
	MessageSource messageSource;
	
	public CommutingController() {
		// TODO Auto-generated constructor stub
	}
	
	@RequestMapping(value="commuting/doInit.do", method = RequestMethod.GET)
	@ResponseBody
	public Message doInit(Locale locale) {
		LOG.debug("***************************");
		LOG.debug("=controller.doInit=");
		
		int flag = commutingService.doInit();
		LOG.debug("::::::: flag :::::: \n" + flag);
		
		Message message = new Message();
		message.setMsgId(flag + "");
		
		if(flag > 0) {
			Object[] args = new String[] {"초기화"};
			String msgStrConfirm = this.messageSource.getMessage("message.common.message.confirm", args, locale);
			LOG.debug(">msgStrConfirm()>"+msgStrConfirm);
			message.setMsgContents(msgStrConfirm);
		}else {
			message.setMsgContents("초기화 실패");
		}
		
		LOG.debug(">message>" + message);
		LOG.debug("***************************");
		return message;
	}
	
	@RequestMapping(value="commuting/doDelete.do", method = RequestMethod.POST)
	@ResponseBody
	public Message doDelete(Commuting inVO, Locale locale) {
		LOG.debug("***************************");
		LOG.debug("=controller.doDelete=");
		if(null == inVO.getSeq()) {
			throw new IllegalArgumentException("시퀀스를 확인하세요");
		}
		if(null == inVO.getEmployeeId()) {
			throw new IllegalArgumentException("사번을 확인하세요");
		}
		
		int flag = commutingService.doDelete(inVO);
		
		Message message = new Message();
		message.setMsgId(flag+"");
		
		if(flag >0 ) {
			Object[] args = new String[] {"삭제"};
			String msgStrConfirm = this.messageSource.getMessage("message.common.message.confirm",args, locale);
			LOG.debug(">msgStrConfirm()>"+msgStrConfirm);
			message.setMsgContents(msgStrConfirm);
		}else {
			
			message.setMsgContents("삭제 실패");
		}
		
		LOG.debug(">message>" + message);
		LOG.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		return message;
	}
	
}
