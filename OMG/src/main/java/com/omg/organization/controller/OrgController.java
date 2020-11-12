package com.omg.organization.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.omg.cmn.DTO;
import com.omg.cmn.Message;
import com.omg.cmn.StringUtil;
import com.omg.organization.domain.DeptVO;
import com.omg.organization.domain.PositionVO;
import com.omg.organization.service.DeptService;
import com.omg.organization.service.PositionService;

@Controller
public class OrgController {
	final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	DeptService deptService;
	
	@Autowired
	PositionService positionService;
	
	public OrgController() {}
	
	@RequestMapping(value="org/org.do",method=RequestMethod.GET)
	public String org_view(String moveDiv,Model model) {
		LOG.debug("== 조직관리 ==");
		moveDiv = StringUtil.nvl(moveDiv, "dept");
		model.addAttribute("moveDiv",moveDiv);
		LOG.debug("moveDiv = "+moveDiv);
		return "organization/org";
	}
	
	@RequestMapping(value="org/org_reg.do",method=RequestMethod.GET)
	public String org_reg(String orgDiv, Model model) {
		LOG.debug("== 조직관리 등록페이지 ==");
		LOG.debug("param = " + orgDiv);
		
		List orgList = new ArrayList();
		
		if(orgDiv.equals("dept")) {
			orgList = deptService.doSelectList();
		}else if(orgDiv.equals("position")){
			orgList = positionService.doSelectList();
		}
		LOG.debug("orgList = " + orgList);
		
		model.addAttribute("orgList",orgList);
		model.addAttribute("orgDiv",orgDiv);
		
		return "organization/org_reg";
	}
	
	
	@RequestMapping(value="org/doSelectListDept.do",method = RequestMethod.GET
			,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doSelectListDept() {
		LOG.debug("== doSelectListDept ==");
		
		List<DeptVO> list = deptService.doSelectList();
		
		Gson gson=new Gson();
        
		String json = gson.toJson(list);
		LOG.debug("json = "+json);
		
		return json;
	}
	
	@RequestMapping(value="org/doSelectListPosition.do",method = RequestMethod.GET
			,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doSelectListPosition() {
		LOG.debug("== doSelectListDept ==");
		
		List<PositionVO> list = positionService.doSelectList();
		
		Gson gson=new Gson();
        
		String json = gson.toJson(list);
		LOG.debug("json = "+json);
		
		return json;
	}
	
	@RequestMapping(value="org/doInsert.do", method=RequestMethod.POST
			,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doInsert(HttpServletRequest req) {
		String orgDiv = req.getParameter("orgDiv");
		
		// 조직 param 세팅
		int upOrg = Integer.parseInt(req.getParameter("upOrg"));
		int orgNo = Integer.parseInt(req.getParameter("orgNo"));
		String orgNm = req.getParameter("orgNm");
		
		// 조직 등록
		int flag = 0;
		if(orgDiv.equals("dept")) { // 부서
			DeptVO outVO = new DeptVO();
			outVO.setUpDept(upOrg);
			outVO.setDeptNo(orgNo);
			outVO.setDeptNm(orgNm);
			LOG.debug("outVO = "+outVO);
			flag = deptService.doInsert(outVO);
			
		} else if(orgDiv.equals("position")) { // 직급
			PositionVO outVO = new PositionVO();
			outVO.setUpPosition(upOrg);
			outVO.setPositionNo(orgNo);
			outVO.setPositionNm(orgNm);
			LOG.debug("outVO = "+outVO);
			flag = positionService.doInsert(outVO);
		}
		
		//메시지 처리
        Message  message=new Message();
        message.setMsgId(flag+"");

        if(flag ==1 ) {
        	message.setMsgContents("등록 되었습니다.");
        }else {
        	message.setMsgContents("등록 실패.");
        }
        
        Gson gson=new Gson();
        String json = gson.toJson(message);
        LOG.debug("json = "+json);       

		return json;

	}
	
	@RequestMapping(value="org/doUpdate.do", method=RequestMethod.POST
			,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doUpdate(HttpServletRequest req) {
		String orgDiv = req.getParameter("orgDiv");
		
		// 조직 param 세팅
		int upOrg = Integer.parseInt(req.getParameter("upOrg"));
		int orgNo = Integer.parseInt(req.getParameter("orgNo"));
		String orgNm = req.getParameter("orgNm");
		
		// 조직 수정
		int flag = 0;
		if(orgDiv.equals("dept")) { // 부서
			DeptVO outVO = new DeptVO();
			outVO.setUpDept(upOrg);
			outVO.setDeptNo(orgNo);
			outVO.setDeptNm(orgNm);
			LOG.debug("outVO = "+outVO);
			flag = deptService.doUpdate(outVO);
			
		} else if(orgDiv.equals("position")) { // 직급
			PositionVO outVO = new PositionVO();
			outVO.setUpPosition(upOrg);
			outVO.setPositionNo(orgNo);
			outVO.setPositionNm(orgNm);
			LOG.debug("outVO = "+outVO);
			flag = positionService.doUpdate(outVO);
		}
		
		//메시지 처리
        Message  message=new Message();
        message.setMsgId(flag+"");

        if(flag ==1 ) {
        	message.setMsgContents("수정 되었습니다.");
        }else {
        	message.setMsgContents("수정 실패.");
        }
        
        Gson gson=new Gson();
        String json = gson.toJson(message);
        LOG.debug("json = "+json);       

		return json;

	}
	
	@RequestMapping(value = "org/doDelete.do", method = RequestMethod.GET
			,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doDelete(HttpServletRequest req) {
		String orgDiv = req.getParameter("orgDiv");
		LOG.debug("orgDiv = " +orgDiv);
		String[] args = req.getParameterValues("orgNo");
		
		List<String> list = new ArrayList(Arrays.asList(args));
		
		LOG.debug(args.toString());
		int flag = 0;
		for(String vo:list) {
			LOG.debug("orgNo = "+vo);
			if(orgDiv.equals("dept")) {
				DeptVO outVO = new DeptVO();
				outVO.setDeptNo(Integer.parseInt(vo));
				flag += deptService.doDelete(outVO);
			}else if(orgDiv.equals("position")) {
				PositionVO outVO = new PositionVO();
				outVO.setPositionNo(Integer.parseInt(vo));
				flag += positionService.doDelete(outVO);
			}
		}
		
		//메시지 처리
        Message  message=new Message();
        message.setMsgId(flag+"");

        if(flag ==1 ) {
        	message.setMsgContents("삭제 되었습니다.");
        }else {
        	message.setMsgContents("삭제 실패.");
        }
        
        Gson gson=new Gson();
        String json = gson.toJson(message);
        LOG.debug("json = "+json);       

		return json;
		
	}
	
	@RequestMapping(value = "org/org_mod.do", method = RequestMethod.GET )
	public String doSelectOne(HttpServletRequest req,Model model) {
		String orgDiv = req.getParameter("org_div");
		LOG.debug("orgDiv = " +orgDiv);
		int orgNo = Integer.parseInt(req.getParameter("org"));
		
		
		// 조직 구분
		model.addAttribute("orgDiv",orgDiv);
		
		// 조직 목록
		List orgList = new ArrayList();
		
		// 조직 선택
		if(orgDiv.equals("dept")) { // 부서 선택
			DeptVO inVO = new DeptVO();
			inVO.setDeptNo(orgNo);
			DeptVO outVO = deptService.doSelectOne(inVO);
			model.addAttribute("vo",outVO);
			
			orgList = deptService.doSelectList();
			
		}else if(orgDiv.equals("position")) { // 직급 선택
			PositionVO inVO = new PositionVO();
			inVO.setPositionNo(orgNo);
			PositionVO outVO = positionService.doSelectOne(inVO);
			model.addAttribute("vo",outVO);
			
			orgList = positionService.doSelectList();
		}
		
		model.addAttribute("orgList",orgList);
		
		return "organization/org_mod"; 
	}
	

}
