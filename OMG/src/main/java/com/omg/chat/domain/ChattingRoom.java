package com.omg.chat.domain;

import com.omg.cmn.DTO;

public class ChattingRoom extends DTO {
	/** 채팅방 번호 */
	private int roomNo;
	/** 채팅방 이름 */
	private String roomNm;
	/** 방 만든이 */
	private String regId;
	
	public ChattingRoom() {
		super();
	}
	

	
	public ChattingRoom(int roomNo, String roomNm, String regId) {
		super();
		this.roomNo = roomNo;
		this.roomNm = roomNm;
		this.regId = regId;
	}



	public String getRegId() {
		return regId;
	}



	public void setRegId(String regId) {
		this.regId = regId;
	}



	public int getRoomNo() {
		return roomNo;
	}
	
	public void setRoomNo(int roomNo) {
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
		return "ChattingRoom [roomNo=" + roomNo + ", roomNm=" + roomNm + ", regId=" + regId + ", toString()="
				+ super.toString() + "]";
	}
	

	
	
	
	

}
