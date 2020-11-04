package com.omg.organization.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.omg.organization.service.DeptService;

@Controller
public class DeptController {
	final Logger LOG = LoggerFactory.getLogger(DeptController.class);
	
	@Autowired
	DeptService service;
	
	
	

}
