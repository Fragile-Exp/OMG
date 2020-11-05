package com.omg.note.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class NoteController {
	final Logger LOG = LoggerFactory.getLogger(NoteController.class);
	
	@RequestMapping(value="note/note.do",method=RequestMethod.GET)
	public String note_view() {
		LOG.debug("== note_view ==");
		
		return "note/note";
	}
	
	@RequestMapping(value="note/note_reg.do",method=RequestMethod.GET)
	public String note_reg() {
		LOG.debug("== note_reg ==");
		
		return "note/note_reg";
	}
	
	@RequestMapping(value="note/note_reply.do",method=RequestMethod.GET)
	public String note_reply() {
		LOG.debug("== note_reply ==");
		
		return "redirect:note_reg.do";
	}
	
	@RequestMapping(value="note/note_info.do",method=RequestMethod.GET)
	public String note_info() {
		LOG.debug("== note_info ==");
		
		return "note/note_info";
	}

}
