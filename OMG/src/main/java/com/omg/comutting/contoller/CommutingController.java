package com.omg.comutting.contoller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.omg.cmn.Criteria;
import com.omg.cmn.Message;
import com.omg.cmn.PageDTO;
import com.omg.cmn.Search;
import com.omg.cmn.StringUtil;
import com.omg.commuting.domain.Commuting;
import com.omg.commuting.service.CommutingService;
import com.omg.employee.domain.EmployeeVO;
import com.omg.organization.service.DeptService;



@Controller
@RequestMapping(value="/commuting/*")
public class CommutingController {
	
	final String  CURRENT_MONTH = StringUtil.formatDate("yyyy-MM");
	final String  CURRENT_DAY = StringUtil.formatDate("yyyy-MM-dd");
	
	final Logger LOG = LoggerFactory.getLogger(CommutingController.class);
	
	@Autowired
	CommutingService commutingService;
	
	@Autowired
	MessageSource messageSource;
	
	@Autowired
	DeptService deptService;
	
	/*Constructor*/
	public CommutingController() {
		// TODO Auto-generated constructor stub
	}
	
	@RequestMapping(value="doInit.do", method = RequestMethod.POST)
	public String doInit(Locale locale,RedirectAttributes rttr) {
		LOG.debug("******************************************************");
		LOG.debug("=controller.doInit=");
		
		int flag = commutingService.doInit();
		Message message = new Message();
		message.setMsgId(flag + "");
		
		if(flag > 0) {
			Object[] args = new String[] {"초기화"};
			String msgStrConfirm = this.messageSource.getMessage("message.common.message.confirm", args, locale);
			LOG.debug(">msgStrConfirm()>"+msgStrConfirm);
			message.setMsgContents(msgStrConfirm);
		}else {
			message.setMsgContents("전부 근무 준비중");
		}
		
		rttr.addFlashAttribute("result", message.getMsgContents());
		
		LOG.debug("******************************************************");
		return "redirect:doSelectDeptList.do";
	}
	
	@RequestMapping(value="doDelete.do", method = RequestMethod.POST)
	public String doDelete(String seq,String employeeId, Locale locale ,RedirectAttributes rttr) {
		LOG.debug("******************************************************");
		LOG.debug("=controller.doDelete=");
		
		Commuting deleteVO =  new Commuting(seq, employeeId);
		int flag = commutingService.doDelete(deleteVO);
		
		Message message = new Message();
		message.setMsgId(flag+"");
		
		if(flag >0 ) {
			Object[] args = new String[] {employeeId+"님 삭제"};
			String msgStrConfirm = this.messageSource.getMessage("message.common.message.confirm",args, locale);
			LOG.debug(">msgStrConfirm()>"+msgStrConfirm);
			message.setMsgContents(msgStrConfirm);
		}else {
			
			message.setMsgContents("삭제 실패");
		}
		
		rttr.addFlashAttribute("result", message.getMsgContents());
		
		LOG.debug(">message>" + message);
		LOG.debug("******************************************************");
		
		return "redirect:doSelectDeptList.do";
	}
	

	@RequestMapping(value="doUpdateAttendTime.do", method = RequestMethod.POST)
	public String doUpdateAttendTime(HttpServletRequest req,RedirectAttributes rttr,Locale locale ) {
		LOG.debug("******************************************************");
		LOG.debug("=controller.doUpdateAttendTime=");
		
		HttpSession session = req.getSession();
		EmployeeVO sessionVO = (EmployeeVO) session.getAttribute("employee");
		
		Commuting attendVO = new Commuting(CURRENT_DAY, sessionVO.getEmployee_id());
				
		int flag = commutingService.doUpdateAttendTime(attendVO);
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
		
		rttr.addFlashAttribute("result", message.getMsgContents());
		
		LOG.debug(">message>" + message);
		LOG.debug("******************************************************");
		return "redirect:doSelectMyList.do";
		
	}
	
	@RequestMapping(value="doUpdateLeaveTime.do", method = RequestMethod.POST)
	public String doUpdateLeaveTime(HttpServletRequest req,RedirectAttributes rttr,Locale locale) {
		LOG.debug("******************************************************");
		LOG.debug("=controller.doUpdateLeaveTime=");
		HttpSession session = req.getSession();
		EmployeeVO sessionVO = (EmployeeVO) session.getAttribute("employee");
		
		Commuting attendVO = new Commuting(CURRENT_DAY, sessionVO.getEmployee_id());
				
		int flag = commutingService.doUpdateLeaveTime(attendVO);
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
		
		rttr.addFlashAttribute("result", message.getMsgContents());
		
		LOG.debug(">message>" + message);
		LOG.debug("******************************************************");
		return "redirect:doSelectMyList.do";
	}
	
	@RequestMapping(value="doSelectOne.do", method = RequestMethod.GET)
	@ResponseBody
	public String doSelectOne(Commuting inVO,Model model) {
		LOG.debug("******************************************************");
		LOG.debug("=controller.doSelectOne=");
		String returnUrl = "";
		if(null == inVO.getSeq()) {
			throw new IllegalArgumentException("시퀀스를 확인하세요");
		}else if(null == inVO.getEmployeeId()){
			throw new IllegalArgumentException("아이디를 확인하세요");
		}
		
		Commuting outVO = (Commuting) this.commutingService.get(inVO);
		
		model.addAttribute("vo",outVO);
		
		LOG.debug("******************************************************");
		return returnUrl;
	}
	
	@RequestMapping(value="doSelectMyList.do", method = RequestMethod.GET)
	public void doSelectMyList(
			@RequestParam(value = "month" ,defaultValue = "2020-11")  String month, Model model, HttpServletRequest req) {
		LOG.debug("******************************************************");
		LOG.debug("=controller.my_attendence.do=");
		//1. 세션 GET
		HttpSession session = req.getSession();
		EmployeeVO sessionVO = (EmployeeVO) session.getAttribute("employee");
		
		
		LOG.debug(">param>" + month);
		Commuting searchVO = new Commuting();
		searchVO.setSeq(month);
		searchVO.setEmployeeId(sessionVO.getEmployee_id());
		model.addAttribute("month",month);
		model.addAttribute("list",commutingService.doSelectMyList(searchVO));
		LOG.debug("******************************************************");
	}
	
	@RequestMapping(value="doSelectDeptList.do" , method = RequestMethod.GET)
	public void doSelectDeptList(
			Criteria criteria, Model model) {
		
		LOG.debug("******************************************************");
		LOG.debug("=controller.doSelectDeptList.do=");
		
		
		LOG.debug(">param>" + criteria);
		
		model.addAttribute("deptList",deptService.doSelectList());	
		model.addAttribute("list", commutingService.doSelectList(criteria));
		int count =  commutingService.getTotalCount(criteria);
		model.addAttribute("pageMaker", new PageDTO(criteria, count));
		
		LOG.debug("******************************************************");
	}
	
	
}
