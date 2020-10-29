package com.omg.cmn;

public class Message extends DTO {

	/**
	 * ë©”ì‹œì§? ID : 10
	 */
	private String msgId;
	
	/**
	 * ë©”ì‹œì§? ?‚´?š©: ?“±ë¡ë˜?—ˆ?Šµ?‹ˆ?‹¤.
	 */
	private String msgContents;
	
	public Message() {
		
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
