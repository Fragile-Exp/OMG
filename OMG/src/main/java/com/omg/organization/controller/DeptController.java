package com.omg.organization.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.omg.organization.domain.DeptVO;
import com.omg.organization.service.DeptService;

@Controller
public class DeptController {
	final Logger LOG = LoggerFactory.getLogger(DeptController.class);
	
	@Autowired
	DeptService service;
	
	public DeptController() {}
	
	@RequestMapping(value="org/org.do",method=RequestMethod.GET)
	public String org_view() {
		LOG.debug("== user_view ==");
		
		return "organization/org";
	}
	
	@RequestMapping(value="org/org_reg.do",method=RequestMethod.GET)
	public String org_reg() {
		LOG.debug("== user_view ==");
		
		return "organization/org_reg";
	}
	
	@RequestMapping(value="org/org_mod.do",method=RequestMethod.GET)
	public String org_mod() {
		LOG.debug("== user_view ==");
		
		return "organization/org_mod";
	}
	
	@RequestMapping(value="org/doSelectListDept.do",method = RequestMethod.GET
			,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doSelectListDept(HttpServletRequest req) {
		LOG.debug("== doSelectListDept ==");
		
		List<DeptVO> list = service.doSelectList();
		
		Gson gson=new Gson();
        
		String json = gson.toJson(list);
		LOG.debug("json = "+json);
		
		return json;
	}
	

}
