package com.omg.chat.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omg.chat.dao.ChattingRoomDaoImpl;
import com.omg.chat.domain.ChattingRoom;
import com.omg.cmn.Message;

@Service("chattingRoomService")
public class ChattingRoomServiceImpl implements ChattingRoomService {
	static final Logger LOG = LoggerFactory.getLogger(ChattingRoomServiceImpl.class);
	
	@Autowired
	ChattingRoomDaoImpl dao;
	
	@Override
	public List<ChattingRoom> doSelectList() {
		return dao.doSelectList();
	}
	
	@Override
	public ChattingRoom doSelectOne(ChattingRoom room) {
		return dao.doSelectOne(room);
	}

	@Override
	public Message doInsert(ChattingRoom room) {
		int flag = dao.checkRoom(room);
		
		Message message = new Message();
		
		if(flag==1) {
			message.setMsgContents("동일한 방이 이미 존재 합니다.");
			flag = 0;
		} else {
			flag = dao.doInsert(room);
			message.setMsgContents("채팅방이 생성되었습니다.");
		}
		message.setMsgId(flag+"");
		
		return message;
	}

	@Override
	public int doDelete(ChattingRoom room) {
		return dao.doDelete(room);
	}

}
