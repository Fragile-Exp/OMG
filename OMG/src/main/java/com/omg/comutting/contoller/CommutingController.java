package com.omg.comutting.contoller;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.omg.cmn.Message;
import com.omg.cmn.Search;
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
		LOG.debug("***************************");
		return message;
	}
	

	@RequestMapping(value="commuting/doUpdateAttendTime.do", method = RequestMethod.POST)
	@ResponseBody
	public Message doUpdateAttendTime(Commuting inVO, Locale locale) {
		LOG.debug("***************************");
		LOG.debug("=controller.doUpdateAttendTime=");
		
		if(null == inVO.getSeq()) {
			throw new IllegalArgumentException("시퀀스를 확인하세요");
		}
		if(null == inVO.getEmployeeId()) {
			throw new IllegalArgumentException("사번을 확인하세요");
		}
		
		int flag = commutingService.doUpdateAttendTime(inVO);
		
		Message message = new Message();
		message.setMsgId(flag+"");
		
		if(flag >0 ) {
			Object[] args = new String[] {"출근"};
			String msgStrConfirm = this.messageSource.getMessage("message.common.message.confirm",args, locale);
			LOG.debug(">msgStrConfirm()>"+msgStrConfirm);
			message.setMsgContents(msgStrConfirm);
		}else {
			
			message.setMsgContents("출근 실패");
		}
		
		LOG.debug(">message>" + message);
		LOG.debug("***************************");
		return message;
		
	}
	
	@RequestMapping(value="commuting/doUpdateLeaveTime.do", method = RequestMethod.POST)
	@ResponseBody
	public Message doUpdateLeaveTime(Commuting inVO, Locale locale) {
		LOG.debug("***************************");
		LOG.debug("=controller.doUpdateLeaveTime=");
		
		if(null == inVO.getSeq()) {
			throw new IllegalArgumentException("시퀀스를 확인하세요");
		}
		if(null == inVO.getEmployeeId()) {
			throw new IllegalArgumentException("사번을 확인하세요");
		}
		
		int flag = commutingService.doUpdateLeaveTime(inVO);
		
		Message message = new Message();
		message.setMsgId(flag+"");
		
		if(flag >0 ) {
			Object[] args = new String[] {"퇴근"};
			String msgStrConfirm = this.messageSource.getMessage("message.common.message.confirm",args, locale);
			LOG.debug(">msgStrConfirm()>"+msgStrConfirm);
			message.setMsgContents(msgStrConfirm);
		}else {
			
			message.setMsgContents("퇴근 실패");
		}
		
		LOG.debug(">message>" + message);
		LOG.debug("***************************");
		return message;
		
	}
	
	@RequestMapping(value="commuting/doSelectOne.do", method = RequestMethod.GET)
	@ResponseBody
	public String doSelectOne(Commuting inVO,Model model) {
		LOG.debug("***************************");
		LOG.debug("=controller.doSelectOne=");
		String returnUrl = "";
		if(null == inVO.getSeq()) {
			throw new IllegalArgumentException("시퀀스를 확인하세요");
		}else if(null == inVO.getEmployeeId()){
			throw new IllegalArgumentException("아이디를 확인하세요");
		}
		
		Commuting outVO = (Commuting) this.commutingService.doSelectOne(inVO);
		
		model.addAttribute("vo",outVO);
		
		LOG.debug("***************************");
		return returnUrl;
	}
	
	@RequestMapping(value="commuting/doSelectList.do", method = RequestMethod.GET)
	@ResponseBody
	public String doSelectList(Search search, Locale locale, Model model) {
		LOG.debug("***************************");
		LOG.debug("=controller.doSelectList=");
		LOG.debug("***************************");
		
		LOG.debug(">param>" + search);
		model.addAttribute("vo", search);
		
		List<Commuting> cList = this.commutingService.doSelectList(search);
		model.addAttribute("list",cList);
		
		for(Commuting vo : cList) {
			LOG.debug(vo.toString());
		}
		
		String view ="";
		return view;
	}
	
	
}
