package com.omg.document.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
public class DocumentController {
	final Logger  LOG = LoggerFactory.getLogger(DocumentController.class);
	
	
	@RequestMapping(value="document/document.do", method = RequestMethod.GET )
	public String document_view(){
		LOG.debug("===========================");
		LOG.debug("=document.do=");
		LOG.debug("===========================");
		return "document/document";
	}
	

	@RequestMapping(value="document/document_reg.do", method = RequestMethod.GET )
	public String document_reg(){
		LOG.debug("===========================");
		LOG.debug("=document_reg.do=");
		LOG.debug("===========================");
		return "document/document_reg";
	}
}
	

