package com.omg.document.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.View;

import com.google.gson.Gson;
import com.omg.cmn.Message;
import com.omg.document.domain.DocumentVO;
import com.omg.document.service.DocumentService;



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
	

	@RequestMapping(value="document/document_rge.do", method = RequestMethod.GET )
	public String document_reg(){
		LOG.debug("===========================");
		LOG.debug("=document_rge.do=");
		LOG.debug("===========================");
		return "document/document_rge";
	}
}
	

