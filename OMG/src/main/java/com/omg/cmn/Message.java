package com.omg.cmn;

public class Message extends DTO {
	
	/**메시지 ID : 10*/
	private String msgId;
	
	/**메시지 ID : 등록되었습니다! */
	private String msgContents;
	
	public Message() {
		// TODO Auto-generated constructor stub
	}
	
	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public String getMsgContents() {
		return msgContents;
	}

	public void setMsgContents(String msgContents) {
		this.msgContents = msgContents;
	}

	@Override
	public String toString() {
		return "Message [msgId=" + msgId + ", msgContents=" + msgContents + ", toString()=" + super.toString() + "]";
	}
	
	
	
}
