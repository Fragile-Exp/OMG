package com.omg.chat;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ChatController {

	@RequestMapping( value="/chat/chat.do", method = RequestMethod.GET )
	public ModelAndView chat() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("chat/chat");
		return mv;
	}
}
