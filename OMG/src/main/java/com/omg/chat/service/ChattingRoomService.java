package com.omg.chat.service;

import java.util.List;

import com.omg.chat.domain.ChattingRoom;
import com.omg.cmn.Message;

public interface ChattingRoomService {

	public List<ChattingRoom> doSelectList();

	public ChattingRoom doSelectOne(ChattingRoom room);
	
	public Message doInsert(ChattingRoom room);
	
	public int doDelete(ChattingRoom room) ;

}