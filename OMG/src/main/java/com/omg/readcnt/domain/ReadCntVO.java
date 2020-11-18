package com.omg.readcnt.domain;

import com.omg.cmn.DTO;

public class ReadCntVO extends DTO {
	/** 게시글 순번*/
	private int seq;
	/** 사용자 아이디*/
	private String userId;
	
	public ReadCntVO() {
		super();
	}
	
	public ReadCntVO(int seq, String userId) {
		super();
		this.seq = seq;
		this.userId = userId;
	}
	
	
	
	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "ReadCntVO [seq=" + seq + ", userId=" + userId + ", toString()=" + super.toString() + "]";
	}
	
	

}
