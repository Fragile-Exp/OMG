package com.omg.chat;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.omg.chat.domain.ChattingRoom;
import com.omg.chat.service.ChattingRoomService;
import com.omg.cmn.Message;

import net.minidev.json.JSONObject;

@Controller
public class ChatController {
	final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	ChattingRoomService service;

	@RequestMapping( value="/chat/chat.do", method = RequestMethod.GET )
	public ModelAndView chat() {
		
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("chat/chat");
		return mv;
	}
	
	/** room page */
	@RequestMapping( value="/chat/room.do", method = RequestMethod.GET )
	public ModelAndView room() {
		ModelAndView mv = new ModelAndView();
		LOG.debug("== roomList ==");
		List<ChattingRoom> list = service.doSelectList();
		mv.addObject("list", list);
		mv.setViewName("chat/room");
		return mv;
	}
	
	@RequestMapping(value="/chat/doInsert.do", method = RequestMethod.GET
			,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doInsert(ChattingRoom room) {
		LOG.debug("insert room = " + room);
		
		Message message = service.doInsert(room);
		LOG.debug("message = "+ message);
		
		JSONObject json = new JSONObject();
		json.put("roomNo", room.getRoomNo());
		json.put("message", message);
		LOG.debug("json = " + json.toJSONString());
		
		return json.toJSONString();
	}
	
	/**
	 * 채팅방 이동
	 * @return
	 */
	@RequestMapping("/chat/moveChat.do")
	public ModelAndView chatting(ChattingRoom room, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		
		ChattingRoom outVO = service.doSelectOne(room);
		
		if(null != outVO) {
			mv.addObject("roomVO",outVO);
			mv.addObject("roomNo", outVO.getRoomNo());
			mv.addObject("roomNm", outVO.getRoomNm());
			mv.setViewName("chat/chat");
		} else {
			mv.setViewName("chat/room");
		}
		return mv;
	}
}
