package com.omg.organization.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.omg.organization.service.DeptService;

@Controller
public class DeptController {
	final Logger LOG = LoggerFactory.getLogger(DeptController.class);
	
	@Autowired
	DeptService service;
	
	@RequestMapping(value="view/org.do",method=RequestMethod.GET)
	public String org_view() {
		LOG.debug("== user_view ==");
		
		return "organization/org";
	}
	
	@RequestMapping(value="view/org_reg.do",method=RequestMethod.GET)
	public String org_reg() {
		LOG.debug("== user_view ==");
		
		return "organization/org_reg";
	}
	

}
