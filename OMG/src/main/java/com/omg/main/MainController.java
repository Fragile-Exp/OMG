package com.omg.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {
	final Logger LOG = LoggerFactory.getLogger(MainController.class);
	
	@RequestMapping(value="view/main.do",method=RequestMethod.GET)
	public String main_view() {
		LOG.debug("== main ==");
		
		return "index";
	}
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String startPage() {
		LOG.debug("== main ==");
		
		return "index";
	} 
	
	@RequestMapping(value="view/blank.do",method=RequestMethod.GET)
	public String blank_view() {
		LOG.debug("== user_view ==");
		
		return "blank";
	}
	

}
