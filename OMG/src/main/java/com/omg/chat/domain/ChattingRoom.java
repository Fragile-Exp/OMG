package com.omg.chat.domain;

import com.omg.cmn.DTO;

public class ChattingRoom extends DTO {
	/** 채팅방 번호 */
	private String roomNo;
	/** 채팅방 이름 */
	private String roomNm;
	
	public ChattingRoom() {
		super();
	}
	
	public ChattingRoom(String roomNo, String roomNm) {
		super();
		this.roomNo = roomNo;
		this.roomNm = roomNm;
	}
	
	public String getRoomNo() {
		return roomNo;
	}
	
	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}
	
	public String getRoomNm() {
		return roomNm;
	}
	
	public void setRoomNm(String roomNm) {
		this.roomNm = roomNm;
	}
	
	@Override
	public String toString() {
		return "ChattingRoom [roomNo=" + roomNo + ", roomNm=" + roomNm + ", toString()=" + super.toString() + "]";
	}
	
	
	
	

}
